package lk.ijse.dto.tm;

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
}
