package com.demoproject.demo.controller.v1;

import com.demoproject.demo.entity.User;
import com.demoproject.demo.repository.UserJpaRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. User"})    // 제목 역할
@RequiredArgsConstructor    // final이 붙거나 @NotNull이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
@RestController
@RequestMapping("/v1")  // 들어온 요청을 특정 메서드와 매핑하기 위해 사용하는 것
public class UserController {
    private final UserJpaRepo userJpaRepo;  // final이 붙은 필드. 어노테이션을 통해 생성자를 주입해주었다.

    @ApiOperation(value = "모든 회원 조회", notes = "모든 회원 목록을 조회합니다.")   // value는 소제목, notes는 세부 내용 설명
    @GetMapping("/users")
    public List<User> findAllUser() {
        return userJpaRepo.findAll();
    }

    @ApiOperation(value = "회원 등록", notes = "회원을 등록합니다.")
    @PostMapping("/user")
    public User save(@ApiParam(value = "회원 이메일", required = true) @RequestParam String email,
                     @ApiParam(value = "회원 이름", required = true) @RequestParam String name) {
        // @RequestParam: http 메소드에서 매칭되는 request parameter 값이 자동으로 들어감
        // url 뒤에 쿼리로 붙는 값을 가져올 때 사용함
        User user = User.builder()
                .email(email)
                .name(name)
                .build();

        return userJpaRepo.save(user);
    }

    @ApiOperation(value = "회원 검색 (이름)", notes = "이름으로  회원을 검색합니다.")
    @GetMapping("/findUserByName/{name}")
    public User findUserByName(@ApiParam(value = "회원 이름", required = true) @PathVariable String name) {
        // @PathVariable: http 메소드에서 매칭되는 request parameter 값이 자동으로 들어감
        // 쿼리가 아니라 url에 추가되어서 전달된다.
        return userJpaRepo.findByName(name);
    }

    @ApiOperation(value = "회원 검색 (이메일)", notes = "이메일로 회원을 검색합니다.")
    @GetMapping("/findUserByEmail/{email}")
    public User findUserByEmail(@ApiParam(value = "회원 이메일", required = true) @PathVariable String email) {
        return userJpaRepo.findByEmail(email);
    }
}
