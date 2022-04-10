package ua.com.okonsergei.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.okonsergei.converter.FileConverter;
import ua.com.okonsergei.model.dto.FileDto;
import ua.com.okonsergei.repository.FileRepository;
import ua.com.okonsergei.repository.db.hibernate.FileEntityRepositoryImpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class FileServiceTest {

    FileRepository fileRepositoryMock;
    FileService fileService;

    @BeforeEach
    void setUp() {
        fileRepositoryMock = mock(FileEntityRepositoryImpl.class);
        fileService = new FileService(fileRepositoryMock);

    }

    @Test
    void findAllSuccess() {
        fileService.findAll();
        verify(fileRepositoryMock).findAll();
    }

    @Test
    void findById_Success() {
        Long id = 5L;
        fileService.findById(id);
        verify(fileRepositoryMock).findById(id);
    }

    @Test
    void save_Success() {
        FileDto fileDto = new FileDto();
        fileService.save(fileDto);
        verify(fileRepositoryMock).save(FileConverter.convertToEntity(fileDto));
    }

    @Test
    void deleteById_Success() {
        Long id = 5L;
        fileService.deleteById(id);
        verify(fileRepositoryMock).deleteById(id);
    }

    @Test
    void update_Success() {
        FileDto fileDto = new FileDto();
        fileService.update(fileDto);
        verify(fileRepositoryMock).update(FileConverter.convertToEntity(fileDto));
    }
}