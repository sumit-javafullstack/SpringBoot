package com.springbatcch.__LargeFileProcessing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Customer {

  private int index;
  private String custId;
  private String fName;
  private String lName;
  private String companyName;
  private String city;
  private String country;
  private String phone1;
  private String phone2;
  private String subscriptondate;
  private String website;


    public Customer(String custId, String fName, String country) {
        this.custId = custId;
        this.fName= fName;
        this.country = country;


    }
}
