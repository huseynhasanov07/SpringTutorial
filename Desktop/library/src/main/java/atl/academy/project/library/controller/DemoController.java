package atl.academy.project.library.controller;

import atl.academy.project.library.dto.BookRequest;
import atl.academy.project.library.dto.BookResponse;
import atl.academy.project.library.model.Book;
import atl.academy.project.library.model.BookCategory;
import atl.academy.project.library.service.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class DemoController {

    private final BookServiceImpl bookService;


    @PostMapping
    public void createBook(@RequestBody List<Book> books) {
        bookService.create(books);
    }

    @GetMapping("/category")
    public ResponseEntity<List<BookResponse>> findByCategory(@RequestParam String category) {
        return ResponseEntity.ok(bookService.findByCategory(category));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookResponse> findByIsbn(@PathVariable String isbn) {
        return new ResponseEntity<>(bookService.findByIsbn(isbn), HttpStatus.OK);
    }

    @DeleteMapping("/{isbn}")
    public void deleteByIsbn(@PathVariable String isbn) {
        bookService.delete(isbn);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<BookResponse> updateByIsbn(@PathVariable String isbn, @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.update(isbn,bookRequest));
    }


//    @GetMapping
//    public ResponseEntity<BookResponse> findBook(@RequestParam("isbn") String isbn) {
//        return ResponseEntity.ok(bookService.findByIsbn(isbn));
//    }
//
//    @GetMapping("/{category}")
//    public ResponseEntity<List<BookResponse>> findByCategory(@PathVariable(value = "category") String category) {
//        return ResponseEntity.ok(bookService.findByCategory(category));
//    }
}
