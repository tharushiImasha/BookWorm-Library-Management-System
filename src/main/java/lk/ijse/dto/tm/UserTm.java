package lk.ijse.dto.tm;


import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserTm implements Serializable {

    private String userName;

    private String email;

    private String fullName;

    private String password;

    private String userRole;

    private Button btn;

}
