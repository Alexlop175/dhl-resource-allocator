package com.dhl.resourceallocator.infrastructure;

import com.dhl.resourceallocator.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Simulated data provider containing machine types and costs per region.
 */

@Component
public class DataProvider {

    public List<Region> getRegions() {
        List<MachineType> machines = List.of(
                new MachineType("Large", 10, Map.of("New York", 120, "India", 140, "China", 110)),
                new MachineType("XLarge", 20, Map.of("New York", 230, "China", 200)),
                new MachineType("2XLarge", 40, Map.of("New York", 450, "India", 413)),
                new MachineType("4XLarge", 80, Map.of("New York", 774, "India", 890, "China", 670)),
                new MachineType("8XLarge", 160, Map.of("New York", 1400, "India", 1300, "China", 1180)),
                new MachineType("10XLarge", 320, Map.of("New York", 2820, "India", 2970))
        );

        return List.of(
                new Region("New York", machines),
                new Region("India", machines),
                new Region("China", machines)
        );
    }
}
