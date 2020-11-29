package com.harunugur.entity.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "BOOKS")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer formatPaperback;
    private Date publicationDate;
    private String publisher;
    private String imprint;
    private String publicationCountry;
    private String language;
    private String isbn10;
    private String isbn13;
    private boolean published;

}
