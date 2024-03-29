package com.demoproject.demo.controller.v1;

import com.demoproject.demo.dto.user.UserRequestDto;
import com.demoproject.demo.dto.user.UserResponseDto;
import com.demoproject.demo.model.response.CommonResult;
import com.demoproject.demo.model.response.ListResult;
import com.demoproject.demo.model.response.SingleResult;
import com.demoproject.demo.service.ResponseService;
import com.demoproject.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * User Entity를 전달하는 것이 아닌 DTO를 통해 작업을 수행하고 있다.
 */
@Tag(name = "1. User")    // 제목 역할
@RequiredArgsConstructor    // final이 붙거나 @NotNull이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션
@RestController
@RequestMapping("/v1")  // 들어온 요청을 특정 메서드와 매핑하기 위해 사용하는 것
public class UserController {
    private final UserService userService;  // final이 붙은 필드. 어노테이션을 통해 생성자를 주입해주었다.
    private final ResponseService responseService;

    @Operation(summary = "회원 단건 검색", description = "userId로 회원을 조회합니다.")
    @GetMapping("/user/id/{userId}")
    public SingleResult<UserResponseDto> findUserById(
            @Parameter(description = "회원 ID", required = true) @PathVariable Long userId,
            @Parameter(description = "언어", example = "ko") @RequestParam String lang) {
        return responseService.getSingleResult(userService.findById(userId));
    }

    @Operation(summary = "회원 단건 검색 (이메일)", description = "이메일로 회원을 조회합니다.")
    @GetMapping("/user/email/{email}")
    public SingleResult<UserResponseDto> findUserByEmail(
            @Parameter(description = "회원 이메일", required = true) @PathVariable String email,
            @Parameter(description = "언어", example = "ko") @RequestParam String lang) {
        return responseService.getSingleResult(userService.findByEmail(email));
    }

    @Operation(summary = "회원 목록 조회", description = "모든 회원을 조회합니다.")   // value는 소제목, notes는 세부 내용 설명
    @GetMapping("/users")
    public ListResult<UserResponseDto> findAllUser() {
        return responseService.getListResult(userService.findAllUser());
    }

    @Operation(summary = "회원 등록", description = "회원을 등록합니다.")
    @PostMapping("/user")
    public SingleResult<Long> save(@Parameter(description = "회원 이메일", required = true) @RequestParam String email,
                                   @Parameter(description = "회원 이름", required = true) @RequestParam String name) {
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .email(email)
                .name(name)
                .build();
        return responseService.getSingleResult(userService.save(userRequestDto));
    }

    @Operation(summary = "회원 수정(이름)", description = "회원 정보(이름)를 수정합니다.")
    @PutMapping("/user")
    public SingleResult<Long> modify(@Parameter(description = "회원 아이디", required = true) @RequestParam Long userId,
                                     @Parameter(description = "회원 이름", required = true) @RequestParam String name) {
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .name(name)
                .build();
        return responseService.getSingleResult(userService.update(userId, userRequestDto));
    }

    @Operation(summary = "회원 삭제", description = "회원을 삭제합니다.")
    @DeleteMapping("/user/{userId}")
    public CommonResult delete(@Parameter(description = "회원 아이디", required = true) @PathVariable Long userId) {
        userService.delete(userId);
        return responseService.getSuccessResult();
    }
}
