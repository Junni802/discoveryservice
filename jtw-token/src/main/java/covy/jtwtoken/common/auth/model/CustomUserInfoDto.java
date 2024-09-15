package covy.jtwtoken.common.auth.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomUserInfoDto {

    private String userId;

    private String password;

    private String email;

    private String role;

}
