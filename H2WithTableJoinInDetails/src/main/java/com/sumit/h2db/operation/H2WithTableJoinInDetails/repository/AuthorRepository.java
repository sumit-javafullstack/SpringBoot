package com.sumit.h2db.operation.H2WithTableJoinInDetails.repository;

import com.sumit.h2db.operation.H2WithTableJoinInDetails.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
