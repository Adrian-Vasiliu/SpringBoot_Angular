package org.springbootbackend.service;

import org.springbootbackend.model.Device;
import org.springbootbackend.repository.DeviceRepository;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DeviceService extends GenericService<Device, DeviceRepository> {

    Device add(Device device);

    Device update(Device device);

    Page<Device> search(String keyword, int pageNumber, int pageSize);

    List<Device> search2(String keyword, int pageNumber, int pageSize);

}
