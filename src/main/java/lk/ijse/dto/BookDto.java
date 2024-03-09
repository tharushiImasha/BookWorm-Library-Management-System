package lk.ijse.dto;

import lk.ijse.entity.BooksDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BookDto {
    private String id;

    private String title;

    private String author;

    private String desc;

    private List<BooksDetail> booksDetails;
}
