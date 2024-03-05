package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.AdminBO;
import lk.ijse.dto.AdminDto;
import lk.ijse.entity.Admin;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.custom.AdminRepository;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AdminBOImpl implements AdminBO {

    private static AdminBO adminBO;
    private Session session;

    AdminRepository adminRepository = (AdminRepository) RepositoryFactory.getRepositoryFactory().getDAO(RepositoryFactory.DaoTypes.ADMIN);

    public static AdminBO getInstance() {
        return null == adminBO
                ? adminBO = new AdminBOImpl()
                : adminBO;
    }

    @Override
    public String getPw(String userName) {
        session = SessionFactoryConfig.getInstance().getSession();
        try {
            adminRepository.setSession(session);
            String pw = adminRepository.getPw(userName);

            session.close();
            return pw;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getName(String userName) {
        session = SessionFactoryConfig.getInstance().getSession();
        try {
            adminRepository.setSession(session);
            String name = adminRepository.getName(userName);

            session.close();
            return name;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveUser(AdminDto adminDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            adminRepository.setSession(session);
            boolean save = adminRepository.save(new Admin(adminDto.getUserName(), adminDto.getEmail(), adminDto.getFullName(), adminDto.getPassword()));

            transaction.commit();
            session.close();
            return save;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }

    }
}
