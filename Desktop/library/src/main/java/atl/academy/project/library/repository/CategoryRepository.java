package atl.academy.project.library.repository;

import atl.academy.project.library.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<BookCategory, Long> {
    @Query(value = "select c from BookCategory c where c.category ilike :categories")
    Set<BookCategory> findByCategory(@Param("categories") Set<String> categories);

    @Query(value = "select c from BookCategory c where c.category ilike :category")
    Optional<BookCategory> findByCategory(@Param("category") String category);
}
