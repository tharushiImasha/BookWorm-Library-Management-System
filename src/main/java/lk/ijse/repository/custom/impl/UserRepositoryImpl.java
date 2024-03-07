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
    public String getrole(String userName) {
        try {

            String sql = "SELECT userRole FROM User WHERE userName like: user_name";

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
    public boolean update(User entity) {
        try {
            session.update(entity);
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(User entity) {
        try {
            session.delete(entity);
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User search(String id) {
        try {
            User user = session.get(User.class, id);
            return user;

        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT u FROM User AS u";
        Query query = session.createQuery(sql);

        List list = query.list();

        session.close();

        return list;
    }

}
