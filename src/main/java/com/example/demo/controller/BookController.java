package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.BookSummary;
import com.example.demo.repository.BookRepository;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/get")
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Optional<Book> getById(@PathVariable String id){
        return bookRepository.findById(id);
    }

    @GetMapping("/group/type/{type}")
    public List<BookSummary> groupBookByType(@PathVariable String type){
        Aggregation agg = newAggregation(
                match(Criteria.where("type").is(type)),
                project("name","isbn","price","type"),         //provide each of fields for use to show
                group("type")                                  //grouping type fields
                        .count().as("total_book")               //counting numbers of type field as "total_book"
                        .sum("price").as("total_price") //sum fields of price as "total_price"
                        .addToSet("type").as("type")    //add type field to "type" for show
                        .addToSet(Aggregation.ROOT).as("books") //add object of books to "books" for show

        );
        AggregationResults<BookSummary> grouping = mongoTemplate.aggregate(agg,Book.class,BookSummary.class);
        List<BookSummary> books = grouping.getMappedResults();
        return books;
    }

    @PostMapping("/save")
    public String save(@RequestBody Book book){
        bookRepository.insert(book);
        return "Saved!!";
    }

    @PostMapping("/saveall")
    public String saveAll(@RequestBody List<Book> book){
        bookRepository.insert(book);
        return "All is Saved!!";
    }

    @DeleteMapping("/remove")
    public String removeAll(){
        bookRepository.deleteAll();
        return "All removed!!";
    }

    @DeleteMapping("/remove/{id}")
    public String removeById(@PathVariable String id){
        bookRepository.deleteById(id);
        return "Id: "+id+" is removed!";
    }





}
