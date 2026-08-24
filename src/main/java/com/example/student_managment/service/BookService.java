package com.example.student_managment.service;

import com.example.student_managment.domain.Book;
import com.example.student_managment.domain.Student;
import com.example.student_managment.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book>getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(long bookId){
        return bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(long bookId){
        Book book = getBookById(bookId);

        if (book.getStudent()!=null) {
            book.getStudent().getBooks().remove(book);
            book.setStudent(null);
        }

        bookRepository.delete(book);
    }

    public Student getBookOwner(long bookId){
        return bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found with id: "+ bookId))
                .getStudent();
    }
}
