package ua.com.okonsergei.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.okonsergei.converter.UserConverter;
import ua.com.okonsergei.model.dto.UserDto;
import ua.com.okonsergei.repository.UserRepository;
import ua.com.okonsergei.repository.db.hibernate.UserEntityRepositoryImpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    UserRepository userRepositoryMock;
    UserService userService;

    @BeforeEach
    void setUp() {
        userRepositoryMock = mock(UserEntityRepositoryImpl.class);
        userService = new UserService((UserEntityRepositoryImpl) userRepositoryMock);
    }

    @Test
    void findAll_Success() {
        userService.findAll();
        verify(userRepositoryMock).findAll();
    }

    @Test
    void findBy_Id_Success() {
        Long id = 5L;
        userService.findById(id);
        verify(userRepositoryMock).findById(id);
    }

    @Test
    void save_Success() {
        UserDto userDto = new UserDto();
        userService.save(userDto);
        verify(userRepositoryMock).save(UserConverter.convertToEntity(userDto));
    }

    @Test
    void deleteById_Success() {
        Long id = 5L;
        userService.deleteById(id);
        verify(userRepositoryMock).deleteById(id);
    }

    @Test
    void update_Success() {
        UserDto userDto = new UserDto();
        userService.update(userDto);
        verify(userRepositoryMock).update(UserConverter.convertToEntity(userDto));
    }
}