package com.sumit.h2db.operation.H2WithTableJoinInDetails.repository;

import com.sumit.h2db.operation.H2WithTableJoinInDetails.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
