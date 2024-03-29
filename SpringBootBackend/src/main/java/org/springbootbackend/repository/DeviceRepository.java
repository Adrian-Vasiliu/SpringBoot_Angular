package org.springbootbackend.repository;

import org.springbootbackend.model.Device;
import org.springbootbackend.repository.JPACriteria.DeviceDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long>, JpaSpecificationExecutor<Device>, DeviceDAO {

    //Page<Device> findAll(Pageable pageable);

    //List<Device> findAllByAddress(String address, Pageable pageable);

}
