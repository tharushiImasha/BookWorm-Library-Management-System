package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.User;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.custom.UserRepository;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

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
    public String getRole(String userName) {
        session = SessionFactoryConfig.getInstance().getSession();
        try {
            userRepository.setSession(session);
            String role = userRepository.getrole(userName);

            session.close();
            return role;

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
            boolean save = userRepository.save(new User(userDto.getUserName(), userDto.getEmail(), userDto.getFullName(), userDto.getPassword(), userDto.getUserRole()));

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

    @Override
    public boolean updateUser(UserDto userDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            userRepository.setSession(session);
            boolean update = userRepository.update(new User(userDto.getUserName(), userDto.getEmail(), userDto.getFullName(), userDto.getPassword(), userDto.getUserRole()));

            transaction.commit();
            session.close();
            return update;

        } catch (Exception e) {
            System.out.println(e);

            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(UserDto userDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            userRepository.setSession(session);
            boolean delete = userRepository.delete(new User(userDto.getUserName(), userDto.getEmail(), userDto.getFullName(), userDto.getPassword(), userDto.getUserRole()));

            transaction.commit();
            session.close();
            return delete;

        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User searchUser(String id) {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            userRepository.setSession(session);
            User search = userRepository.search(id);

            session.close();
            return search;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<UserDto> getAllUser() {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            userRepository.setSession(session);
            List<User> all = userRepository.getAll();
            List<UserDto> userDtoList = new ArrayList<>();

            for (User user : all){
                userDtoList.add(user.toDto());
            }

            session.close();
            return userDtoList;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<UserDto> getUsersAdmin() {
        session = SessionFactoryConfig.getInstance().getSession();

        try {
            userRepository.setSession(session);
            List<User> all = userRepository.getUsersAdmin();
            List<UserDto> userDtoList = new ArrayList<>();

            for (User user : all){
                userDtoList.add(user.toDto());
            }

            session.close();
            return userDtoList;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public String getUserName(String name) {
        session = SessionFactoryConfig.getInstance().getSession();
        try {
            userRepository.setSession(session);
            String userName = userRepository.getUserName(name);

            session.close();
            return userName;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getReaderCount() {
        session = SessionFactoryConfig.getInstance().getSession();
        try {
            userRepository.setSession(session);
            int readerCount = userRepository.getReaderCount();

            session.close();
            return readerCount;

        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            return 0;
        }
    }
}
