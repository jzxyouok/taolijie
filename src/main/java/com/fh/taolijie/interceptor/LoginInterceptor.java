package com.fh.taolijie.interceptor;

import cn.fh.security.credential.Credential;
import cn.fh.security.utils.CredentialUtils;
import com.fh.taolijie.domain.acc.MemberModel;
import com.fh.taolijie.service.*;
import com.fh.taolijie.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    AccountService accountService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Credential credential = SessionUtils.getCredential(request);

        if (null != credential) {
            //已经登陆，将用户名放到model中
            request.setAttribute("isLoggedIn", true);
            request.setAttribute("username", credential.getUsername());
            MemberModel currUser = accountService.findMember(credential.getId());
            String roleName = currUser.getRoleList().get(0).getRolename();
            currUser.setPassword("secret field");
            request.setAttribute("currUser", currUser);
            request.setAttribute("roleName", roleName);;
        } else {
            // 没登陆
            request.setAttribute("isLoggedIn", false);
        }


        return super.preHandle(request, response, handler);
    }

}
