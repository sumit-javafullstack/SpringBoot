package com.springbatcch.__LargeFileProcessing.config;

import com.springbatcch.__LargeFileProcessing.model.Customer;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomPartitioner implements Partitioner {

    private Map<Integer, List<Customer>> groupedData;

    public void setGroupedData(Map<Integer, List<Customer>> groupedData) {
        this.groupedData = groupedData;
    }

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> partitions = new HashMap<>();
        int partitionSize = groupedData.size() / gridSize;
        int remainder = groupedData.size() % gridSize;

        int start = 0;
        for (int i = 0; i < gridSize; i++) {
            ExecutionContext context = new ExecutionContext();
            int end = start + partitionSize - 1;
            if (i == gridSize - 1) {
                end += remainder;
            }

            context.putInt("start", start);
            context.putInt("end", end);
            partitions.put("partition" + i, context);

            start = end + 1;
        }

        return partitions;
    }
}

