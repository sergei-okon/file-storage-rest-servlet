package ua.com.okonsergei.model.dto;

import lombok.Data;

@Data
public class EventDto {

    private Long id;
    private Long userId;
    private Long fileId;
    private Long created;
    private Long updated;
}
