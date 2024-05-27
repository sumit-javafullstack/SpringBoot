package com.springbatcch.__LargeFileProcessing.batch;

import com.springbatcch.__LargeFileProcessing.model.Customer;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class CustomerWriter implements ItemWriter<List<Customer>> {
  private static final String OUTPUT_FILE = "output/processed_customer.dat";

  @Override
  public void write(Chunk<? extends List<Customer>> chunk) throws Exception {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE, true))) {
      for (List<Customer> customers : chunk) {
        for (Customer customer : customers) {
          writer.write(
              customer.getCustId()
                  + ","
                  + customer.getFName()
                  + ","
                  + customer.getCountry()
                  + ","
                  + customer.getLName()
                  + ","
                  + customer.getPhone2()
                  + ","
                  + customer.getIndex());

          writer.newLine();
        }
      }
    }
  }
}
