package lk.ijse.repository.custom.impl;

import lk.ijse.entity.Admin;
import lk.ijse.repository.custom.AdminRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class AdminRepositoryImpl implements AdminRepository {

    private Session session;
    private static AdminRepositoryImpl adminRepositoryImpl;

    public AdminRepositoryImpl(){}

    public static AdminRepositoryImpl getInstance(){
        return null == adminRepositoryImpl ? adminRepositoryImpl = new AdminRepositoryImpl() : adminRepositoryImpl;
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Admin entity) {
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
    public boolean update(Admin entity) {
        return false;
    }

    @Override
    public boolean delete(Admin entity) {
        return false;
    }

    @Override
    public Admin search(String id) {
        return null;
    }

    @Override
    public List<Admin> getAll() {
        return List.of();
    }

}
