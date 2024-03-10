package lk.ijse.entity;

import jakarta.persistence.*;
import lk.ijse.dto.BranchDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "branch")
public class Branch {

    @Id
    @Column(name = "branch_id")
    private String id;

    @Column
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "branch")
//    private List<BooksDetail> booksDetails = new ArrayList<>();


    public Branch() {
    }

    public Branch(String id, String location, User user) {
        this.id = id;
        this.location = location;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BranchDto toDto(){
        BranchDto branchDto = new BranchDto();
        branchDto.setId(this.id);
        branchDto.setLocation(this.location);
        branchDto.setUserName(this.user.getUserName());

        return branchDto;
    }

}
