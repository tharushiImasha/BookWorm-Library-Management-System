package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lk.ijse.embedded.BorrowedDetailPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BorrowedDetailsTm {
    private BorrowedDetailPK borrowedDetailPK;

    private Timestamp borrowedDate;

    private Timestamp dueDate;

    private String title;

    private String returned;

    private String name;

    private Button btn;

    public BorrowedDetailsTm(BorrowedDetailPK borrowedDetailPK, Timestamp borrowedDate, Timestamp dueDate, String title, String returned) {
        this.borrowedDetailPK = borrowedDetailPK;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.title = title;
        this.returned = returned;
    }

    public BorrowedDetailsTm(BorrowedDetailPK borrowedDetailPK, Timestamp borrowedDate, Timestamp dueDate, String title, String name, Button btn) {
        this.borrowedDetailPK = borrowedDetailPK;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.title = title;
        this.name = name;
        this.btn = btn;
    }
}
