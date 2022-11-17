package org.springbootangular.mapper;

import org.springbootangular.dto.DeviceDTO;
import org.springbootangular.dto.DevicePageDTO;
import org.springbootangular.model.Device;
import org.springbootangular.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class DeviceMapper {

    public static DeviceDTO toDto(Device device) {
        return DeviceDTO.builder()
                .id(device.getId())
                .description(device.getDescription())
                .address(device.getAddress())
                .max_hourly_energy_consumption(device.getMax_hourly_energy_consumption())
                .userId(device.getUser().getId())
                .build();
    }

    public static Device toEntity(DeviceDTO deviceDTO) {
        return Device.builder()
                .id(deviceDTO.getId())
                .description(deviceDTO.getDescription())
                .address(deviceDTO.getAddress())
                .max_hourly_energy_consumption(deviceDTO.getMax_hourly_energy_consumption())
                .user(User.builder()
                        .id(deviceDTO.getUserId())
                        .build())
                .build();
    }

    public static List<DeviceDTO> toDTOList(List<Device> devices) {
        return devices.stream().map(DeviceMapper::toDto).collect(toList());
    }

    public static DevicePageDTO toPageDTO(Page<Device> devices) {
        return DevicePageDTO.builder()
                .currentPage(devices.getNumber())
                .pageSize(devices.getSize())
                .totalItems(devices.getTotalElements())
                .totalPages(devices.getTotalPages())
                .items(toDTOList(devices.getContent()))
                .build();
    }

}
