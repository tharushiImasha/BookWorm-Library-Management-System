package lk.ijse.repository.custom.impl;

import lk.ijse.dto.UserDto;
import lk.ijse.repository.custom.UserRepository;
import lk.ijse.entity.User;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final Session session;

    public UserRepositoryImpl(){
        session = SessionFactoryConfig.getInstance().getSession();
    }

    @Override
    public boolean save(User entity) {
        Transaction transaction = session.beginTransaction();

        try {
            session.save(entity);

            transaction.commit();
            session.close();

            return true;

        }catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();

            return false;
        }

    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public User search(String id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public String getPw(String userName) {
        try {

            String sql = "SELECT password FROM User WHERE userName like: user_name";

            Query query = session.createQuery(sql).setParameter("user_name", userName);

            List list = query.list();

            session.close();

            if (list.size()==0){
                return null;
            }else {
                System.out.println("pw - "+list.get(0));
                return (String) list.get(0);
            }

        }catch (Exception e) {
            session.close();
            e.printStackTrace();

            return null;
        }
    }
}
