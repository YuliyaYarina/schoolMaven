package ru.hogwarts.school.web.servise;

import org.springframework.stereotype.Service;

import ru.hogwarts.school.web.model.Book;
import ru.hogwarts.school.web.repository.BookRepository;

import java.util.Collection;


@Service
public class BookServise {

    private final BookRepository bookRepository;

    public BookServise(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book findBook (long id) {
        return bookRepository.findById(id).get();
    }

    public Book editBook (Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook (long id) {
         bookRepository.deleteById(id);
    }
    public Collection<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book findByName(String name){
        return bookRepository.findByNameIgnoreCase(name);
    }

    public Collection<Book> findByAuthor(String author){
        return bookRepository.findBookByAuthorContainsIgnoreCase(author);
    }

    public Collection<Book> findByNamePart(String part){
        return bookRepository.findAllByNameContainsIgnoreCase(part);
    }


}
