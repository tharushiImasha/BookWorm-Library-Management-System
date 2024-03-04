package lk.ijse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto implements Serializable {

    private String userName;

    private String email;

    private String fullName;

    private String password;

}
