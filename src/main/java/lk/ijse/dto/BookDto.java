package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BookDto {
    private String id;

    private String title;

    private String author;

    private String desc;

    private String genre;

    private String branchId;

    private byte[] image;

    public BookDto(String id, String title, String author, String desc, String genre, String branchId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.desc = desc;
        this.genre = genre;
        this.branchId = branchId;
    }
}
