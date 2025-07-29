package com.dhl.resourceallocator.interfaces.controller;

import com.dhl.resourceallocator.application.CostPlannerService;
import com.dhl.resourceallocator.domain.AllocationResult;
import com.dhl.resourceallocator.infrastructure.DataProvider;
import com.dhl.resourceallocator.interfaces.dto.AllocationRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/allocate")
public class AllocationController {

    private final CostPlannerService planner;
    private final DataProvider provider;

    public AllocationController(CostPlannerService planner, DataProvider provider) {
        this.planner = planner;
        this.provider = provider;
    }

    /**
     * Returns the optimal allocation of machines per region.
     */
    @PostMapping
    public Map<String, List<AllocationResult>> allocate(@RequestBody AllocationRequest request) {
        List<AllocationResult> results = provider.getRegions().stream()
                .map(region -> planner.computeOptimalAllocation(region, request.capacity(), request.hours()))
                .toList();

        return Map.of("output", results);
    }
}
