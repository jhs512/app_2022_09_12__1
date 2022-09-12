package com.ll.exam.app_2022_09_12__1.app.member.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class JoinForm {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String password_confirm;
    @NotEmpty
    private String email;
    private MultipartFile profileImg;
}
