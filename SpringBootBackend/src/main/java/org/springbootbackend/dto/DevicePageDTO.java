package org.springbootbackend.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
public class DevicePageDTO {

    private int currentPage;

    private int pageSize;

    private List<DeviceDTO> items;

    private int totalPages;

    private long totalItems;

}
