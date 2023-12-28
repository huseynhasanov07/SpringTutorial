package atl.academy.project.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String isbn;
    private String name;
    private int stock;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;
    @ManyToOne
    @JoinColumn
    private BookCategory category;

    public void setBookCategory(BookCategory bookCategory) {
        bookCategory.getBooks().add(this);
        this.category = bookCategory;
    }

    public Book(String isbn, String name, int stock, Author author, BookCategory category) {
        this.isbn = isbn;
        this.name = name;
        this.stock = stock;
        this.author = author;
        this.category = category;
    }

}
