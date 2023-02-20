package org.springbootbackend.service.implementation;

import org.springbootbackend.model.Device;
import org.springbootbackend.repository.DeviceRepository;
import org.springbootbackend.repository.DeviceSpecification;
import org.springbootbackend.repository.SearchCriteria;
import org.springbootbackend.service.DeviceService;
import org.springbootbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl extends GenericServiceImpl<Device, DeviceRepository> implements DeviceService {

    private final UserService userService;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository, UserService userService) {
        super(deviceRepository);
        System.out.println("instance type:" + deviceRepository.getClass().getSimpleName());
        this.userService = userService;
    }

    public Device add(Device device) {
        userService.getById(device.getUser().getId());
        return save(device);
    }

    public Device update(Device newDevice) {
        getById(newDevice.getId());
        return add(newDevice);
    }

    public Page<Device> search(String keyword, int pageNumber, int pageSize) {
        DeviceSpecification deviceSpecification1 = new DeviceSpecification(SearchCriteria.builder()
                .operation(":")
                .key("description")
                .value(keyword).build());
        DeviceSpecification deviceSpecification2 = new DeviceSpecification(SearchCriteria.builder()
                .operation(":")
                .key("address")
                .value(keyword).build());
        return repository.findAll(Specification.where(deviceSpecification1).or(deviceSpecification2),
                PageRequest.of(pageNumber, pageSize));
    }

    public List<Device> search2(String keyword, int pageNumber, int pageSize) {
        System.out.println("instance type:" + repository.getClass().getSimpleName());
        return repository.search2(keyword, pageNumber, pageSize);
    }

}
