package org.springbootangular.mapper;

import org.springbootangular.dto.UserDTO;
import org.springbootangular.model.User;
import org.springbootangular.security.services.UserDetailsImpl;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserMapper {
    public static UserDTO toDto(UserDetailsImpl userDetails) {
//        Set<UserRoleDTO> roles = new HashSet<>();
//        for (Role role : user.getRole()) {
//            roles.add(UserRoleMapper.convertToDto(role));
//        }
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) // <=> .map(item -> item.getAuthority())
                .collect(toList());
        return UserDTO.builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .password("*confidential*")
                //.roles(user.getRoles().stream().map(UserRoleMapper::toDto).collect(Collectors.toSet()))
                .roles(roles)
                .build();
    }

    public static User toEntity(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
    }
}
