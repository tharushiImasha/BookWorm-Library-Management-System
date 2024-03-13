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
}
