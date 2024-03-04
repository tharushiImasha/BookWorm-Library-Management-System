package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDto;
import lk.ijse.entity.User;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.custom.UserRepository;

public class UserBOImpl implements UserBO {

    UserRepository userRepository = (UserRepository) RepositoryFactory.getRepositoryFactory().getDAO(RepositoryFactory.DaoTypes.USER);

    @Override
    public String getPw(String userName) {
        return userRepository.getPw(userName);
    }

    @Override
    public boolean saveUser(UserDto userDto) {
        return userRepository.save(new User(userDto.getUserName(), userDto.getEmail(), userDto.getFullName(), userDto.getPassword()));
    }
}
