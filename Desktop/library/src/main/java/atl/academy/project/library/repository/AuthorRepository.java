package atl.academy.project.library.repository;

import atl.academy.project.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(value = "select a from Author a where a.fin in :fin")
    List<Author> findByFin(List<String> fin);
}
