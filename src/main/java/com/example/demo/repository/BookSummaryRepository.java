package com.example.demo.repository;

import com.example.demo.model.BookSummary;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BookSummaryRepository extends MongoRepository<BookSummary,String> {
}
