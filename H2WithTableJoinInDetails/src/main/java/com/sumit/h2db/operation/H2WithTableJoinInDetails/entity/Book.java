package com.sumit.h2db.operation.H2WithTableJoinInDetails.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String bookName;
  private double bookPrice;
  private LocalDate publishDate;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "authorId")
  private Author author;
}
