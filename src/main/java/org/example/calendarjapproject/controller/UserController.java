package org.example.calendarjapproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.calendarjapproject.dto.LoginRequestDto;
import org.example.calendarjapproject.dto.UserResponseDto;
import org.example.calendarjapproject.dto.UserSignUpRequestDto;
import org.example.calendarjapproject.dto.UserSignUpResponseDto;
import org.example.calendarjapproject.entity.User;
import org.example.calendarjapproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDto> signUp(@Valid @RequestBody UserSignUpRequestDto requestDto) {

        UserSignUpResponseDto userSignUpResponseDto = userService.signUp(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword());

        return new ResponseEntity<>(userSignUpResponseDto, HttpStatus.CREATED);
    }

    // 특정 유저 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {

        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 유저 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @RequestBody LoginRequestDto requestDto){

        UserResponseDto updateUser = userService.update(id, requestDto.getEmail(), requestDto.getPassword());

        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }


    // 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        User loginedUser = userService.loginUser(loginRequestDto);

        // 로그인 했으니까 Session 등록
        HttpSession session = request.getSession();
        session.setAttribute("SESSION_KEY", loginedUser.getId());

        return new ResponseEntity<>("정상적으로 로그인되었습니다.", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        // 등록된 Session이 있으면 무효화
        if (session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>("정상적으로 로그아웃되었습니다.", HttpStatus.OK);
    }

}
