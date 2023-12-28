package atl.academy.project.library.dto;

import atl.academy.project.library.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {
    private Long id;

    private String name;

    private String surname;

    private List<Book> books = new ArrayList<>();

}
