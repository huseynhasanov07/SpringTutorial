package atl.academy.project.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    private Long id;
    private String name;
    private String isbn;
    private int stock;
    private String authorName;
    private String authorSurname;
    private String category;

}
