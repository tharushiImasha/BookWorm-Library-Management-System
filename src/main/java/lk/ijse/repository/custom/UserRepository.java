package lk.ijse.repository.custom;

import lk.ijse.repository.CrudRepository;
import lk.ijse.entity.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User> {
    String getPw (String userName);

    String getName (String userName);

    String getrole (String userName);

    List<User> getUsersAdmin ();

    String getUserName(String name);
}
