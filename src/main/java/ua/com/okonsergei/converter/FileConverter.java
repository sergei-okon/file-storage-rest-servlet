package ua.com.okonsergei.converter;

import ua.com.okonsergei.model.dto.FileDto;
import ua.com.okonsergei.repository.entity.File;

public class FileConverter {

    public static FileDto convertToDto(File file) {
        FileDto fileDto = new FileDto();
        if (file == null) {
            fileDto = null;
        } else {
            fileDto.setId(file.getId());
            fileDto.setFileName(file.getFileName());
            fileDto.setPath(file.getPath());
        }
        return fileDto;
    }

    public static File convertToEntity(FileDto fileDto) {
        File file = new File();
        if (fileDto == null) {
            file = null;
        } else {
            file.setId(fileDto.getId());
            file.setFileName(fileDto.getFileName());
            file.setPath(fileDto.getPath());
        }
        return file;
    }
}
