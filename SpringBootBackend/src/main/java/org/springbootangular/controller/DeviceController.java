package org.springbootangular.controller;

import org.springbootangular.dto.DeviceDTO;
import org.springbootangular.dto.DevicePageDTO;
import org.springbootangular.mapper.DeviceMapper;
import org.springbootangular.model.Device;
import org.springbootangular.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/devices")
@AllArgsConstructor
public class DeviceController {

    private static final String DEFAULT_PAGE_NUMBER = "0";
    private static final String DEFAULT_PAGE_SIZE = "3";

    private DeviceService deviceService;

    @GetMapping("")
    public ResponseEntity<DevicePageDTO> get(
            @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam(required = false) String keyword) {
//            Page<Device> devicePage = null;
//            Pageable pageable = PageRequest.of(pageNumber, pageSize);
//            if (title == null)
//                devicePage = deviceRepository.findAll(pageable);
//            else {
//                devicePage = deviceRepository.findByTitleContaining(title, pageable);
//            }
        if (keyword == null) {
            return new ResponseEntity<>(DeviceMapper.toPageDTO(deviceService.getPage(pageNumber, pageSize)),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(DeviceMapper.toPageDTO(deviceService.search(keyword, pageNumber, pageSize)),
                HttpStatus.OK);
    }

    @GetMapping("/search2")
    public ResponseEntity<List<DeviceDTO>> search2(@RequestParam String keyword,
                                                   @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) int pageNumber,
                                                   @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        return new ResponseEntity<>(deviceService.search2(keyword, pageNumber, pageSize).stream()
                .map(DeviceMapper::toDto).collect(toList()), HttpStatus.OK);
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<DeviceDTO> getById(@PathVariable long deviceId) {
        return new ResponseEntity<>(DeviceMapper.toDto(deviceService.getById(deviceId)), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<DeviceDTO> add(@RequestBody DeviceDTO deviceDTO) {
        Device device = DeviceMapper.toEntity(deviceDTO);
        return new ResponseEntity<>(DeviceMapper.toDto(deviceService.add(device)), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<DeviceDTO> update(@RequestBody DeviceDTO deviceDTO) {
        Device device = DeviceMapper.toEntity(deviceDTO);
        return new ResponseEntity<>(DeviceMapper.toDto(deviceService.update(device)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        deviceService.delete(id);
        return new ResponseEntity<>("Device deleted successfully", HttpStatus.OK);
    }

}
