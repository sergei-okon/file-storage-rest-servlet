package ua.com.okonsergei.service;

import ua.com.okonsergei.converter.UserConverter;
import ua.com.okonsergei.model.dto.UserDto;
import ua.com.okonsergei.repository.UserRepository;
import ua.com.okonsergei.repository.db.hibernate.UserEntityRepositoryImpl;
import ua.com.okonsergei.repository.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    UserRepository userRepository;

    public UserService(UserEntityRepositoryImpl userEntityRepository) {
        this.userRepository = userEntityRepository;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserConverter::convertToDto).collect(Collectors.toList());
    }

    public UserDto findById(Long id) {
        return UserConverter.convertToDto(userRepository.findById(id));
    }

    public User save(UserDto userDto) {
        return userRepository.save(UserConverter.convertToEntity(userDto));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Long update(UserDto userDto) {
        return userRepository.update(UserConverter.convertToEntity(userDto));
    }
}
