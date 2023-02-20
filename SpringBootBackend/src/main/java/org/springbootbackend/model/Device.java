package org.springbootbackend.model;

import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)
@Table(name = "device")
public class Device {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_sequence")
    @SequenceGenerator(name = "device_sequence", allocationSize = 2)
    private Long id;

    private String description;

    private String address;

    private Integer max_hourly_energy_consumption;

    @ManyToOne
    private User user;

}
