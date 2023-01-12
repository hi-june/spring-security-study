package com.demoproject.demo.controller.v1;

import com.demoproject.demo.entity.User;
import com.demoproject.demo.repository.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor    // final이 붙거나 @NotNull이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
@RestController
@RequestMapping("/v1")  // 들어온 요청을 특정 메서드와 매핑하기 위해 사용하는 것
public class UserController {
    private final UserJpaRepo userJpaRepo;  // final이 붙은 필드. 어노테이션을 통해 생성자를 주입해주었다.

    @PostMapping("/user")
    public User save() {
        User user = User.builder()
                .email("wnsgur735@gmail.com")
                .name("최준혁")
                .build();

        return userJpaRepo.save(user);
    }

    @GetMapping("/findUser/{name}")
    public User findUserByName(@PathVariable String name) {
        return userJpaRepo.findByName(name);
    }
}
