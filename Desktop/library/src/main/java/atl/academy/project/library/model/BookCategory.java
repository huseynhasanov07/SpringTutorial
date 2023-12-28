package atl.academy.project.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
@Builder
public class BookCategory {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String category;
    @OneToMany(mappedBy = "category", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Book> books = new ArrayList<>();

    public void setAllBooks(List<Book> books) {
        books.forEach(book -> book.setCategory(this));
    }

}
