package org.example.calendarjapproject.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {


    // 회원가입, 로그인 요청은 인증 처리에서 제외
    private static final String[] WHITE_LIST = {"/users/signup", "/users/login"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 다운캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        // 다운캐스팅
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("로그인 필터 로직 실행");

        // WHITE_LIST에 포함되어 있지 않다면
        if (!isWhiteList(requestURI)) {

            // 로그인 확인(로그인하면 세션 존재)
            // 세션이 존재하면 가져오고, 존재하지 않으면 새로 만들어준다.
            HttpSession session = httpRequest.getSession(false);

            // 로그인하지 않은 사용자일 경우
            if (session == null || session.getAttribute("SESSION_KEY") == null) {
                throw new RuntimeException("로그인 해주세요.");
            }

            // 로그인 한 사용자일 경우
            log.info("로그인에 성공했습니다.");
        }

        // 1번 경우 : WHITE_LIST에 등록된 URL 요청이라면, 바로 chain.doFilter() 호출
        // 2번 경우 : WHITE_LIST가 아닌 경우, 위 필터 로직 통과 후에 chain.doFilter() 다음 필터나 Servlet 호출한다.
        // 즉, 다음 필터가 없으면 Servlet -> Controller, 다음 필터가 있으면 다음 Filter 호출한다.
        chain.doFilter(request, response);
    }

    // WHITE_LIST에 포함되는 URL인지 검사하는 메서드
    private boolean isWhiteList(String requestURI) {

        // 포함되면 true, 아니면 false 반환
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
