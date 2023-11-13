package com.sparta.springlv2.repository;

import com.sparta.springlv2.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByOrderByPubDateAsc();

    @Query("SELECT b FROM Book b LEFT JOIN Loan l ON l.bookId = b.id " +
            "ORDER BY b.id, l.loanDate DESC")
    List<Book> findAllBooksWithLatestLoanStatus();
    /*
    SELECT id, title, author, language, publisher, pub_date, loan_status
    FROM (
             SELECT b.id, b.title, b.author, b.language, b.publisher, b.pub_date, l.loan_status,
                    ROW_NUMBER() OVER (PARTITION BY b.id ORDER BY l.loan_date DESC) as row_num
             FROM book b
                      LEFT JOIN loan l ON l.book_id = b.id
         ) AS ranked
    WHERE row_num = 1;
     */


}
