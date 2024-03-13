package lk.ijse.entity;

import jakarta.persistence.*;
import lk.ijse.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_name")
    private String userName;

    @Column
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private String password;

    @Column(name = "user_role")
    private String userRole;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Branch> branches = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<BorrowedDetails> borrowedDetails = new ArrayList<>();

    public UserDto toDto(){
        UserDto userDto = new UserDto();
        userDto.setUserName(this.userName);
        userDto.setEmail(this.email);
        userDto.setFullName(this.fullName);
        userDto.setPassword(this.password);
        userDto.setUserRole(this.userRole);

        return userDto;
    }

    public User(String userName, String email, String fullName, String password, String userRole) {
        this.userName = userName;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.userRole = userRole;
    }

    public User() {
    }

    public User(String userName, String email, String fullName, String password, String userRole, List<Branch> branches, List<BorrowedDetails> borrowedDetails) {
        this.userName = userName;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.userRole = userRole;
        this.branches = branches;
        this.borrowedDetails = borrowedDetails;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public List<BorrowedDetails> getBorrowedDetails() {
        return borrowedDetails;
    }

    public void setBorrowedDetails(List<BorrowedDetails> borrowedDetails) {
        this.borrowedDetails = borrowedDetails;
    }
}
