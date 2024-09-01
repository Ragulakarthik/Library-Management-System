package com.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lms.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    List<Book> findByBookId(Long bookId);

    List<Book> findByAuthorId(Long authorId);

    List<Book> findByPublisherId(Long publisherId);

    @Query("SELECT b FROM Book b WHERE b.bookTitle LIKE :bookTitle%")
    List<Book> findByBookTitle(String bookTitle);

    @Query("SELECT b FROM Book b WHERE b.bookId = :bookId AND b.authorId = :authorId")
    List<Book> findByBookIdAndAuthorId(Long bookId, Long authorId);

    @Query("SELECT b FROM Book b WHERE b.bookId = :bookId AND b.publisherId = :publisherId")
    List<Book> findByBookIdAndPublisherId(Long bookId, Long publisherId);

    @Query("SELECT b FROM Book b WHERE b.bookId = :bookId AND b.bookTitle LIKE :bookTitle%")
    List<Book> findByBookIdAndBookTitle(Long bookId, String bookTitle);

    @Query("SELECT b FROM Book b WHERE b.authorId = :authorId AND b.bookTitle LIKE :bookTitle%")
    List<Book> findByAuthorIdAndBookTitle(Long authorId, String bookTitle);

    @Query("SELECT b FROM Book b WHERE b.authorId = :authorId AND b.publisherId = :publisherId")
    List<Book> findByAuthorIdAndPublisherId(Long authorId, Long publisherId);

    @Query("SELECT b FROM Book b WHERE b.publisherId = :publisherId AND b.bookTitle LIKE :bookTitle%")
    List<Book> findByPublisherIdAndBookTitle(Long publisherId, String bookTitle);

    @Query("SELECT b FROM Book b WHERE b.bookId = :bookId AND b.authorId = :authorId AND b.publisherId = :publisherId")
    List<Book> findByBookIdAndAuthorIdAndPublisherId(Long bookId, Long authorId, Long publisherId);

    @Query("SELECT b FROM Book b WHERE b.bookId = :bookId AND b.authorId = :authorId AND b.bookTitle LIKE :bookTitle%")
    List<Book> findByBookIdAndAuthorIdAndBookTitle(Long bookId, Long authorId, String bookTitle);

    @Query("SELECT b FROM Book b WHERE b.bookId = :bookId AND b.publisherId = :publisherId AND b.bookTitle LIKE :bookTitle%")
    List<Book> findByBookIdAndPublisherIdAndBookTitle(Long bookId, Long publisherId, String bookTitle);

    @Query("SELECT b FROM Book b WHERE b.authorId = :authorId AND b.publisherId = :publisherId AND b.bookTitle LIKE :bookTitle%")
    List<Book> findByAuthorIdAndPublisherIdAndBookTitle(Long authorId, Long publisherId, String bookTitle);

    @Query("SELECT b FROM Book b WHERE b.bookId = :bookId AND b.authorId = :authorId AND b.publisherId = :publisherId AND b.bookTitle LIKE :bookTitle%")
    List<Book> findByBookIdAndAuthorIdAndPublisherIdAndBookTitle(Long bookId, Long authorId, Long publisherId, String bookTitle);
}
