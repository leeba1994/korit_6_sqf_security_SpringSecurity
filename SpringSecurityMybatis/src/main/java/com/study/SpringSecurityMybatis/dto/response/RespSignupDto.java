package com.study.SpringSecurityMybatis.dto.response;

import com.study.SpringSecurityMybatis.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.annotation.Bean;

@Builder
@Data
public class RespSignupDto {
    private String message;
    private User user;
}
