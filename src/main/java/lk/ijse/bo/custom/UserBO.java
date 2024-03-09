package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.User;

import java.util.List;

public interface UserBO extends SuperBO {
    String getPw (String userName);

    String getName (String userName);

    String getRole (String userName);

    boolean saveUser(UserDto userDto);

    boolean updateUser(UserDto userDto);

    boolean deleteUser(UserDto userDto);

    User searchUser(String id);

    List<UserDto> getAllUser();

    List<UserDto> getUsersAdmin();

}
