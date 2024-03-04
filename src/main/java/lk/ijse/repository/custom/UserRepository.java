package lk.ijse.repository.custom;

import lk.ijse.repository.CrudRepository;
import lk.ijse.entity.User;

public interface UserRepository extends CrudRepository<User> {
    String getPw (String userName);
}
