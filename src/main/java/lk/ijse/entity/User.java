package lk.ijse.entity;

import jakarta.persistence.*;
import lk.ijse.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

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
}
