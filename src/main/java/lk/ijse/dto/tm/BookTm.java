package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class BookTm {
    private String id;

    private String title;

    private String author;

    private String desc;

    private String genre;

    private String branchId;

    private String status;

    private byte[] image;

    private Button btn;

    public BookTm(String id, String title, String author, String desc, String genre, String branchId, String status, byte[] image) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.desc = desc;
        this.genre = genre;
        this.branchId = branchId;
        this.status = status;
        this.image = image;
    }
}
