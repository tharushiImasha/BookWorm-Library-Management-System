package lk.ijse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user")
public class Admin {

    @Id
    @Column(name = "user_name")
    private String userName;

    @Column
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column
    private String password;

}
