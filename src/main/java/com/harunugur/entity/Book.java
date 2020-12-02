package com.harunugur.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

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
    private Date publicationDate;
    private String publicationCountry;
    private BigDecimal price;
    private Integer bookType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "BOOKS_AUTHORS",
    joinColumns = @JoinColumn(name = "BOOK_ID"),
    inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
    private List<Author> authors;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;

}
