package com.dhl.resourceallocator.domain;

import java.util.List;

public record Region(String name, List<MachineType> machines) {}