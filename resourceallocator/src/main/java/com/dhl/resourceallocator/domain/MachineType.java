package com.dhl.resourceallocator.domain;

import java.util.Map;

public record MachineType(String name, int capacity, Map<String, Integer> costPerRegion) {}

