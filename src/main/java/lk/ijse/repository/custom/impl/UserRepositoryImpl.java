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
    public List<User> getUsersAdmin() {
        String role = "Admin";

        String sql = "SELECT u FROM User AS u WHERE userRole = :role";
        Query query = session.createQuery(sql).setParameter("role", role);

        List list = query.list();

        session.close();

        return list;
    }

    @Override
    public String getUserName(String name) {
        try {

            String sql = "SELECT userName FROM User WHERE fullName like: name";

            Query query = session.createQuery(sql).setParameter("name", name);

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
    public int getReaderCount() {
        try {
            String sql = "SELECT COUNT(user_name) FROM user WHERE user_role = 'Reader'";
            Query query = session.createNativeQuery(sql);
            int count = ((Number) query.getSingleResult()).intValue();

            return count;

        }catch (Exception e) {
            e.printStackTrace();
            return 0;
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
