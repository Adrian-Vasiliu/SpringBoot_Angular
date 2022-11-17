package org.springbootangular.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceDTO {

    private Long id;

    private String description;

    private String address ;

    private Integer max_hourly_energy_consumption;

    private Long userId;

}
