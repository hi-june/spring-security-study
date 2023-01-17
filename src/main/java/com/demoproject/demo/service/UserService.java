package com.demoproject.demo.service;

import com.demoproject.demo.advice.exception.UserNotFoundCException;
import com.demoproject.demo.domain.user.User;
import com.demoproject.demo.domain.user.UserJpaRepo;
import com.demoproject.demo.dto.user.UserRequestDto;
import com.demoproject.demo.dto.user.UserResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Get 요청에 대한 응답으로 UserResponseDto를 반환
 * Post, Put 요청에 대해서는 UserRequestDto를 받음
 */
@Service
@AllArgsConstructor
public class UserService {
    private UserJpaRepo userJpaRepo;

    /**
     * 회원 등록
     * @param userRequestDto
     * @return
     */
    @Transactional
    public Long save(UserRequestDto userRequestDto) {
        userJpaRepo.save(userRequestDto.toEntity());
        return userJpaRepo.findByEmail(userRequestDto.getEmail()).getUserId();
    }

    /**
     * 회원 조회(id)
     * @param id
     * @return UserResponseDto
     */
    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User user = userJpaRepo.findById(id).orElseThrow(UserNotFoundCException::new);
        return new UserResponseDto(user);
    }

    /**
     * 회원 조회(email)
     * @param email
     * @return UserResponseDto
     */
    @Transactional(readOnly = true)
    public UserResponseDto findByEmail(String email) {
        User user = userJpaRepo.findByEmail(email);
        if (user == null) throw new UserNotFoundCException();
        return new UserResponseDto(user);
    }

    /**
     * 모든 회원 조회
     * @return UserResponseDto
     */
    @Transactional
    public List<UserResponseDto> findAllUser() {
        return userJpaRepo.findAll()
                .stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 회원 이름 수정
     * @param id
     * @param userRequestDto
     * @return
     */
    @Transactional
    public Long update(Long id, UserRequestDto userRequestDto) {
        User modifiedUser = userJpaRepo.findById(id).orElseThrow(UserNotFoundCException::new);
        modifiedUser.setName(userRequestDto.getName());
        return id;
    }

    /**
     * 회원 삭제
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        userJpaRepo.deleteById(id);
    }
}
