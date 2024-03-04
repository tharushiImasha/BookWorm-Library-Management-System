package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDto;

public interface UserBO extends SuperBO {
    String getPw (String userName);

    boolean saveUser(UserDto userDto);

}