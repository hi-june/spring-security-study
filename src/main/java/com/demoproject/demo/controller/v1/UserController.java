package com.demoproject.demo.controller.v1;

import com.demoproject.demo.advice.exception.UserNotFoundCException;
import com.demoproject.demo.entity.User;
import com.demoproject.demo.model.response.CommonResult;
import com.demoproject.demo.model.response.ListResult;
import com.demoproject.demo.model.response.SingleResult;
import com.demoproject.demo.repository.UserJpaRepo;
import com.demoproject.demo.service.ResponseService;
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
    private final ResponseService responseService;

    @ApiOperation(value = "회원 단건 검색", notes = "userId로 회원을 조회합니다.")
    @GetMapping("/user/{userId}")
    public SingleResult<User> findUserById(@ApiParam(value = "회원 ID", required = true) @PathVariable Long userId) throws Exception{
        return responseService
                .getSingleResult(userJpaRepo.findById(userId).orElseThrow(UserNotFoundCException::new)); // null이 아니라 예외가 터지도록 수정
                //.getSingleResult(userJpaRepo.findById(userId).orElse(null));
    }

    @ApiOperation(value = "회원 단건 검색 (이메일)", notes = "이메일로 회원을 조회합니다.")
    @GetMapping("/user/email/{email}")
    public SingleResult<User> findUserByEmail(@ApiParam(value = "회원 이메일", required = true) @PathVariable String email) {
        User user = userJpaRepo.findByEmail(email);
        if (user == null) throw new UserNotFoundCException();
        return responseService.getSingleResult(user);
    }

    @ApiOperation(value = "회원 검색 (이름)", notes = "이름으로  회원을 검색합니다.")
    @GetMapping("/findUserByName/{name}")
    public SingleResult<User> findUserByName(@ApiParam(value = "회원 이름", required = true) @PathVariable String name) {
        User user = userJpaRepo.findByName(name);
        if (user == null) throw new UserNotFoundCException();
        return responseService.getSingleResult(user);
    }

    @ApiOperation(value = "회원 목록 조회", notes = "모든 회원을 조회합니다.")   // value는 소제목, notes는 세부 내용 설명
    @GetMapping("/users")
    public ListResult<User> findAllUser() {
        return responseService
                .getListResult(userJpaRepo.findAll());
    }

    @ApiOperation(value = "회원 등록", notes = "회원을 등록합니다.")
    @PostMapping("/user")
    public SingleResult<User> save(@ApiParam(value = "회원 이메일", required = true) @RequestParam String email,
                                   @ApiParam(value = "회원 이름", required = true) @RequestParam String name) {
        // @RequestParam: http 메소드에서 매칭되는 request parameter 값이 자동으로 들어감
        // url 뒤에 쿼리로 붙는 값을 가져올 때 사용함
        User user = User.builder()
                .email(email)
                .name(name)
                .build();

        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 수정", notes = "회원 정보를 수정합니다.")
    @PutMapping("/user")
    public SingleResult<User> modify(@ApiParam(value = "회원 아이디", required = true) @RequestParam Long userId,
                                     @ApiParam(value = "회원 이메일", required = true) @RequestParam String email,
                                     @ApiParam(value = "회원 이름", required = true) @RequestParam String name) {
        User user = User.builder()
                .userId(userId)
                .email(email)
                .name(name)
                .build();

        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 삭제", notes = "회원을 삭제합니다.")
    @DeleteMapping("/user/{userId}")
    public CommonResult delete(Long userId) {
        userJpaRepo.deleteById(userId);
        return responseService.getSuccessResult();
    }
}
