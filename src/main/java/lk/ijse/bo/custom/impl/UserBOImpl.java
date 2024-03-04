package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.User;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.custom.UserRepository;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserBOImpl implements UserBO {

    private static UserBO userBO;
    private Session session;

    UserRepository userRepository = (UserRepository) RepositoryFactory.getRepositoryFactory().getDAO(RepositoryFactory.DaoTypes.USER);

    public static UserBO getInstance() {
        return null == userBO
                ? userBO = new UserBOImpl()
                : userBO;
    }

    @Override
    public String getPw(String userName) {
        session = SessionFactoryConfig.getInstance().getSession();
        try {
            userRepository.setSession(session);
            String pw = userRepository.getPw(userName);

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
            userRepository.setSession(session);
            String name = userRepository.getName(userName);

            session.close();
            return name;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveUser(UserDto userDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            userRepository.setSession(session);
            boolean save = userRepository.save(new User(userDto.getUserName(), userDto.getEmail(), userDto.getFullName(), userDto.getPassword()));

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
