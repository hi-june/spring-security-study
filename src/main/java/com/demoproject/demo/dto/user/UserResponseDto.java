package com.demoproject.demo.dto.user;

import com.demoproject.demo.domain.user.User;
import lombok.Getter;

@Getter // @Setter를 빼서 무분별하게 수정되지 않도로 한다. DTO로 받은 객체는 영속성 관리도 받지 않는 객체이므로 값을 변경할 필요가 없다.
public class UserResponseDto {
    private final Long userId;
    private final String email;
    private final String name;

    public UserResponseDto(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
