package com.springbatcch.__LargeFileProcessing.batch;

import com.springbatcch.__LargeFileProcessing.model.Customer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerReader implements ItemReader<Map<String, List<Customer>>> {

  private Map<String, List<Customer>> groupedData = new HashMap<>();

  @Override
  public Map<String, List<Customer>> read()
      throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
    try (BufferedReader br = new BufferedReader(new FileReader("C:\\tmp\\customers.csv"))) {
      List<Customer> customers =
          br.lines()
              .skip(1) // skip header if there is one
              .map(
                  line -> {
                    String[] fields = line.split(",");
                    return new Customer(
                        Integer.parseInt(fields[0]),
                        fields[1],
                        fields[2],
                        fields[3],
                        fields[4],
                        fields[5],
                        fields[6],
                        fields[7],
                        fields[8],
                        fields[9],
                        fields[10]);
                  })
              .collect(Collectors.toList());

      //// creating Map<Country, List<Customer>
      groupedData = customers.parallelStream().collect(Collectors.groupingBy(e -> e.getCountry()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return groupedData;
  }
}
