package org.springbootangular.mapper;

import org.springbootangular.dto.UserRoleDTO;
import org.springbootangular.model.RoleType;
import org.springbootangular.model.UserRole;

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
