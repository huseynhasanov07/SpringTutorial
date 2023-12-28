package atl.academy.project.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {
    private String name;
    private String isbn;
    private int stock;
    private AuthorRequest author;
    private CategoryRequest category;
}
