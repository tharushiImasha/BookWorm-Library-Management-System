package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BookDto {
    private int id;

    private String title;

    private String author;

    private String desc;

    private String genre;


    //private List<BooksDetail> booksDetails;
}
