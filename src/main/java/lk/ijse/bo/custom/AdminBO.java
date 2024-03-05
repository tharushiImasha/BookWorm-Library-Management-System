package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.AdminDto;
import lk.ijse.dto.UserDto;

public interface AdminBO extends SuperBO {
    String getPw (String userName);

    String getName (String userName);

    boolean saveUser(AdminDto adminDto);

}
