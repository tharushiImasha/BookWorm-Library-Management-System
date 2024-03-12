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

    public BorrowedDetailPK() {
    }

    public BorrowedDetailPK(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
