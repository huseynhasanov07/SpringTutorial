package atl.academy.project.library.service;

import atl.academy.project.library.dto.BookRequest;
import atl.academy.project.library.dto.BookResponse;
import atl.academy.project.library.exception.BookNotFoundException;
import atl.academy.project.library.model.Author;
import atl.academy.project.library.model.Book;
import atl.academy.project.library.model.BookCategory;
import atl.academy.project.library.repository.BookRepository;
import atl.academy.project.library.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;


    private static List<BookResponse> entityListToResponseList(List<Book> books) {
        return books.stream().map(book -> new BookResponse(
                book.getId(), book.getName(), book.getIsbn(), book.getStock(), book.getAuthor().getName(), book.getAuthor().getSurname()
                , book.getCategory().getCategory())).toList();

    }

    private static BookResponse entityToResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .stock(book.getStock())
                .authorName(book.getAuthor().getName())
                .authorSurname(book.getAuthor().getSurname())
                .category(book.getCategory().getCategory())
                .isbn(book.getIsbn())
                .name(book.getName())
                .build();
    }

    @Transactional
    public void create(List<Book> book) {

        List<Book> newBooks = new ArrayList<>();
        for (Book books : book) {
            Optional<BookCategory> category = categoryRepository.findByCategory(books.getCategory().getCategory());
            if (category.isPresent()) {
                books.setCategory(category.get());
            } else {
                BookCategory newCategory = new BookCategory();
                newCategory.setCategory(books.getCategory().getCategory());
                categoryRepository.save(newCategory);
                books.setCategory(newCategory);
            }
            newBooks.add(books);
        }
        bookRepository.saveAll(newBooks);

    }

    public BookResponse update(String isbn, BookRequest bookRequest) {
        BookCategory bookCategory = BookCategory.builder()
                .category(bookRequest.getCategory().getCategory())
                .build();
        Author author = Author.builder()
                .name(bookRequest.getName())
                .build();
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException("Book not found with isbn : " + isbn));
        Optional<BookCategory> category = categoryRepository.findByCategory(bookCategory.getCategory());
        if (category.isPresent()) {
            book.setCategory(category.get());
        } else {

            categoryRepository.save(bookCategory);
            book.setCategory(bookCategory);
        }

        book.setAuthor(author);
        book.setStock(bookRequest.getStock());
        book.setIsbn(bookRequest.getIsbn());
        book.setName(bookRequest.getName());
        return entityToResponse(bookRepository.save(book));
    }

    @Transactional
    public void delete(String isbn) {
        bookRepository.findByIsbn(isbn).ifPresentOrElse(
                book -> bookRepository.deleteByIsbn(isbn),
                () -> {
                    throw new BookNotFoundException("Book not found with isbn : " + isbn);
                }
        );
    }

    public List<BookResponse> findByCategory(String category) {

        BookCategory bookCategory = new BookCategory();
        bookCategory.setCategory(category);
        if (bookRepository.findByCategory(bookCategory).isEmpty())
            throw new BookNotFoundException("Book not found with category : " + bookCategory.getCategory());
        return entityListToResponseList(bookRepository.findByCategory(bookCategory));
    }

    public BookResponse findByIsbn(String isbn) {
        return entityToResponse(bookRepository.findByIsbn(isbn).orElseThrow(
                () -> new BookNotFoundException("Book not found with isbn :" + isbn)));
    }

    public List<BookResponse> findByAuthor(String authorName, String authorSurname) {
        Author author = new Author();
        author.setName(authorName);
        author.setSurname(authorSurname);
        List<Book> bookList = bookRepository.findByAuthor(author);
        if (bookList.isEmpty())
            throw new BookNotFoundException("Book not found with author : " + authorName + authorSurname);

        return entityListToResponseList(bookList);
    }

    public List<BookResponse> findAll() {
        return entityListToResponseList(bookRepository.findAll());
    }


}
