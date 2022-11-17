package org.springbootangular.service.implementation;

import org.springbootangular.model.Device;
import org.springbootangular.repository.DeviceRepository;
import org.springbootangular.repository.DeviceSpecification;
import org.springbootangular.repository.SearchCriteria;
import org.springbootangular.service.DeviceService;
import org.springbootangular.service.UserService;
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
        System.out.printf("instance type:" + deviceRepository.getClass().getSimpleName());
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
        System.out.printf("instance type:" + repository.getClass().getSimpleName());
        return repository.search2(keyword, pageNumber, pageSize);
    }

}
