package lk.ijse.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class BorrowedDetailPK implements Serializable {

    @Column(name = "book_id")
    private String id;

    @Column(name = "user_name")
    private String userName;
}
