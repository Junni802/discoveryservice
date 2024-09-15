package covy.jtwtoken.common.auth.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Member {

    /**
     * 사용자 Id
     */
    @Min(5) @Max(20)
    private String userId;

    /**
     * Email
     */
    @Min(5) @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")
    private String email;

    /**
     * 비밀번호
     */
    @Min(5) @Max(20)
    private String password;

    /**
     * 역할
     */
    private String role;

    /**
     * 카테고리
     */
    private List<String> categories;

}
