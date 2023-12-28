package atl.academy.project.library.repository;

import atl.academy.project.library.model.Author;
import atl.academy.project.library.model.Book;
import atl.academy.project.library.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "select b from Book b where b.isbn in :isbn")
    Set<Book> findByIsbn(Set<String> isbn);

    List<Book> findByCategory(BookCategory bookCategory);

    void deleteByIsbn(String isbn);


    List<Book> findByAuthor(Author author);

    Optional<Book>findByIsbn(String isbn);





}
