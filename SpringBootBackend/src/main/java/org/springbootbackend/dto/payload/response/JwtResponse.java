package org.springbootbackend.dto.payload.response;

import org.springbootbackend.dto.UserDTO;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class JwtResponse {

    private final String type = "Bearer";

    private String token;

    private UserDTO user;

}
