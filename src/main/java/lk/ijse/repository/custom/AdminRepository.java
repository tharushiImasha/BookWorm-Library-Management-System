package lk.ijse.repository.custom;

import lk.ijse.entity.Admin;
import lk.ijse.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin> {
    String getPw (String userName);

    String getName (String userName);
}
