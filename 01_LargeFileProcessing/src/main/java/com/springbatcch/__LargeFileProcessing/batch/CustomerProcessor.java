package com.springbatcch.__LargeFileProcessing.batch;

import com.springbatcch.__LargeFileProcessing.model.Customer;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerProcessor
    implements ItemProcessor<Map<String, List<Customer>>, List<Customer>> {

  @Override
  public List<Customer> process(Map<String, List<Customer>> items) throws Exception {
    // Multiply salary by 10 for all employees in each group
    Map<String, List<Customer>> tempRecord =
        items.entrySet().stream()
            .collect(
                Collectors.toMap(
                    key -> key.getKey(),
                    values ->
                        values.getValue().stream()
                            .map(
                                customer ->
                                    new Customer(
                                        customer.getCustId(), "SUMIT", customer.getCountry()))
                            .collect(Collectors.toList())));

      List<Customer> flattenedList = new ArrayList<>();
      for (List<Customer> customers : tempRecord.values()) {
          flattenedList.addAll(customers);
      }
      return flattenedList;

  }
}
