package atl.academy.project.library.service;

import atl.academy.project.library.dto.BookRequest;
import atl.academy.project.library.dto.BookResponse;

import java.util.List;

public interface BookService {



    void update(BookRequest bookRequest);
//
    void delete(BookRequest bookRequest);

    BookResponse findByIsbn(String isbn);

    List<BookResponse>findByCategory(String category);

    List<BookResponse>findByAuthor(String authorName,String authorSurname);


}
