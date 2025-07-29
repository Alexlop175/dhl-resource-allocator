package com.dhl.resourceallocator.application;

import com.dhl.resourceallocator.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CostPlannerService {

    public AllocationResult computeOptimalAllocation(Region region, int units, int hours) {
        // Sort machine types by cost efficiency (lower cost per unit comes first)
        List<MachineType> sorted = region.machines().stream()
                .filter(mt -> mt.costPerRegion().containsKey(region.name()))
                .sorted((a, b) -> {
                    double costA = (double) a.costPerRegion().get(region.name()) / a.capacity();
                    double costB = (double) b.costPerRegion().get(region.name()) / b.capacity();
                    return Double.compare(costA, costB);
                })
                .toList();

        Map<String, Integer> allocation = new LinkedHashMap<>();
        int totalCost = 0;
        int remainingUnits = units;

        // Greedy allocation: use the most cost-effective machines first to cover required capacity
        for (MachineType machine : sorted) {
            int count = remainingUnits / machine.capacity();
            if (count > 0) {
                allocation.put(machine.name(), count);
                totalCost += count * machine.costPerRegion().get(region.name()) * hours;
                remainingUnits -= count * machine.capacity();
            }
        }
        return new AllocationResult(region.name(), totalCost, allocation);
    }
}
