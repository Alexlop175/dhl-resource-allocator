package com.dhl.resourceallocator;

import com.dhl.resourceallocator.application.CostPlannerService;
import com.dhl.resourceallocator.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CostPlannerServiceTest {

    private final CostPlannerService planner = new CostPlannerService();

    @Test
    void shouldAllocateMachinesForChinaCorrectly() {
        MachineType large = new MachineType("Large", 10, Map.of("China", 110));
        MachineType xlarge = new MachineType("XLarge", 20, Map.of("China", 200));
        MachineType fourXLarge = new MachineType("4XLarge", 80, Map.of("China", 670));
        MachineType eightXLarge = new MachineType("8XLarge", 160, Map.of("China", 1180));

        Region china = new Region("China", List.of(large, xlarge, fourXLarge, eightXLarge));

        AllocationResult result = planner.computeOptimalAllocation(china, 50, 1);

        assertEquals(510, result.totalCost());
        assertEquals(Map.of("XLarge", 2, "Large", 1), result.machines());
    }
}