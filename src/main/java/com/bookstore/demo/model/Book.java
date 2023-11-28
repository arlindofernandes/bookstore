package com.bookstore.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Document("Books")
@Data
public class Book {
    @Id
    public String id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("price")
    public String price;

    @JsonProperty("category")
    public String category;

    @JsonProperty("author")
    public String author;


    @JsonProperty("cover")
    public String cover;

}
