package org.springbootangular.repository.JPACriteria;

import org.springbootangular.model.Device;

import java.util.List;

public interface DeviceDAO {

     List<Device> search2(String keyword, int pageNumber, int pageSize);

}
