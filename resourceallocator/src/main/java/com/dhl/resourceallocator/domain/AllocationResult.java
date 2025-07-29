package com.dhl.resourceallocator.domain;

import java.util.Map;

public record AllocationResult(String region, int totalCost, Map<String, Integer> machines) {}