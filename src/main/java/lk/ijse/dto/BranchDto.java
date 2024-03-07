package lk.ijse.dto;

import lk.ijse.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BranchDto {

    private String id;

    private String location;

    private String userName;

    private User user;

    public BranchDto(String id, String location, String userName) {
        this.id = id;
        this.location = location;
        this.userName = userName;
    }
}
