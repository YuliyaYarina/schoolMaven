package ru.hogwarts.school.web.controller;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ru.hogwarts.school.web.model.Book;
//import ru.hogwarts.school.web.servise.BookServise;

//@RestController
//@RequestMapping("books")
//public class BooksController {
//    private BookServise bookServise;
//
//    public BooksController(BookServise bookServise) {
//        this.bookServise = bookServise;
//    }
//
//    @GetMapping("{id}") //
//    public ResponseEntity getBookInfo(@PathVariable long id){
//        Book book = bookServise.findBook(id);
//        if (book == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(book);
//    }
//
//    @GetMapping
//    public ResponseEntity findBook (@RequestParam(required = false) String name,
//                                    @RequestParam(required = false) String author,
//                                    @RequestParam(required = false) String namePart) {
//        if (name != null && !name.isBlank()) {
//            return ResponseEntity.ok(bookServise.findByName(name));
//        }
//        if (author != null && !author.isBlank()) {
//            return ResponseEntity.ok(bookServise.findByAuthor(author));
//        }
//        if (namePart != null && !namePart.isBlank()) {
//            return ResponseEntity.ok(bookServise.findByNamePart(namePart));
//        }
//        return ResponseEntity.ok(bookServise.getAllBooks());
//    }
//
//      @PostMapping("{id}") //
//    public Book createBook(@RequestBody Book book){
//        return bookServise.createBook(book);
//    }
//
//    @PutMapping
//    public ResponseEntity<Book> editBook(@RequestBody Book book){
//        Book foundBook = bookServise.editBook(book);
//        if (foundBook == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//        return ResponseEntity.ok(foundBook);
//    }
//
// @DeleteMapping("{id}")
//    public ResponseEntity deleteBook(@PathVariable long id){
//        bookServise.deleteBook(id);
//        return ResponseEntity.ok().build();
//    }
//
//}
