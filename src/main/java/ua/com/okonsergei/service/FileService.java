package ua.com.okonsergei.service;

import ua.com.okonsergei.converter.FileConverter;
import ua.com.okonsergei.model.dto.FileDto;
import ua.com.okonsergei.repository.FileRepository;
import ua.com.okonsergei.repository.entity.File;

import java.util.List;
import java.util.stream.Collectors;

public class FileService {

    FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<FileDto> findAll() {
        return fileRepository.findAll().stream().map(FileConverter::convertToDto).collect(Collectors.toList());
    }

    public FileDto findById(Long id) {
        return FileConverter.convertToDto(fileRepository.findById(id));
    }

    public File save(FileDto fileDto) {
        return fileRepository.save(FileConverter.convertToEntity(fileDto));
    }

    public void deleteById(Long id) {
        fileRepository.deleteById(id);
    }

    public Long update(FileDto fileDto) {
        return fileRepository.update(FileConverter.convertToEntity(fileDto));
    }
}
