package com.hi.app.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j2
public class AuthCheckInterceptor implements HandlerInterceptor {

    // servlet-context.xml 등록
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        log.info(">>>>> 회원 로그인 여부 체크 <<<<<");

        // session 획득 : 현재 세션이 존재하지 않으면 => null 반환
        HttpSession session = request.getSession(false);

        // 회원이 로그인 했을 때 체크 : return true -> 다음 인터셉터 또는 실행 컨트롤러 진입
        // session 이 null 이 아니고 , getAttribute("loginInfo") null 이 아니면 => 로그인 상태
        if(session != null && session.getAttribute("loginInfo") != null){
            log.info("회원 로그인 체크 : 로그인 상태 !!!");
            return true;
        }

        log.info("회원 로그인 체크 : 비 로그인 상태 !!!");
        // Login 페이지로 이동
        response.sendRedirect("/login");

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler, ModelAndView modelAndView
    ) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler, Exception ex
    ) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
