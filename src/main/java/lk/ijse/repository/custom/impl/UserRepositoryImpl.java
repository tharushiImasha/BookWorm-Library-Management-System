package lk.ijse.repository.custom.impl;

import lk.ijse.repository.custom.UserRepository;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private Session session;
    private static UserRepositoryImpl userRepositoryImpl;

    public UserRepositoryImpl(){}

    public static UserRepositoryImpl getInstance(){
        return null == userRepositoryImpl ? userRepositoryImpl = new UserRepositoryImpl() : userRepositoryImpl;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(User entity) {
        try {
            session.save(entity);
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String getPw(String userName) {
        try {

            String sql = "SELECT password FROM User WHERE userName like: user_name";

            Query query = session.createQuery(sql).setParameter("user_name", userName);

            List list = query.list();

            if (list.isEmpty()){
                return null;
            }else {
                return (String) list.get(0);
            }

        }catch (Exception e) {
            e.printStackTrace();

            return null;

        }
    }

    @Override
    public String getName(String userName) {
        try {

            String sql = "SELECT fullName FROM User WHERE userName like: user_name";

            Query query = session.createQuery(sql).setParameter("user_name", userName);

            List list = query.list();

            if (list.isEmpty()){
                return null;
            }else {

                return (String) list.get(0);
            }

        }catch (Exception e) {
            e.printStackTrace();

            return null;

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

}
