package org.springbootbackend.mapper;

import org.springbootbackend.dto.UserRoleDTO;
import org.springbootbackend.model.RoleType;
import org.springbootbackend.model.UserRole;

public class UserRoleMapper {

    public static UserRoleDTO toDto(UserRole role){
        return UserRoleDTO.builder()
                .name(role.getName().toString())
                .build();
    }

    public static UserRole toEntity(UserRoleDTO roleDTO){
        return UserRole.builder()
                .name(RoleType.valueOf(roleDTO.getName()))
                .build();
    }
}
