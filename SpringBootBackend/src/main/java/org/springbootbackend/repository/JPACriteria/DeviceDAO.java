package org.springbootbackend.repository.JPACriteria;

import org.springbootbackend.model.Device;

import java.util.List;

public interface DeviceDAO {

     List<Device> search2(String keyword, int pageNumber, int pageSize);

}
