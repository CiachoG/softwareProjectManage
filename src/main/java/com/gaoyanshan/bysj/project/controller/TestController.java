package com.gaoyanshan.bysj.project.controller;

import com.gaoyanshan.bysj.project.response.Response;
import com.gaoyanshan.bysj.project.util.Md5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>类名: TestController</pre>
 * <pre>描述: Test Controller</pre>
 * <pre>日期: 19-3-13 下午11:45</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@RestController
public class TestController {

    @GetMapping("/test")
    public Response test(){
        List<Integer> data = new ArrayList<>();
        return Response.success(data);
    }


    @RequiresAuthentication
    @GetMapping("/index")
    public Response index(){
        return Response.success("index");
    }
//
//    @PostMapping("/logout")
//    public Response logout(@RequestBody  LoginBody loginBody){
//        Subject subject = SecurityUtils.getSubject();
//        String md5Password = Md5Util.passwordToHash(loginBody.getUserName(),loginBody.getPassword());
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
//                loginBody.getUserName(),
//                md5Password);
//        System.out.println(usernamePasswordToken.getPrincipal());
//
//        System.out.println("------------->"+subject.isAuthenticated());
//        //进行验证，这里可以捕获异常，然后返回对应信息
//        if (! subject.isAuthenticated() ){
//            try {
//                usernamePasswordToken.setRememberMe(true);
//                subject.logout();
//
//            }catch (AuthenticationException e){
//                return Response.success(e);
//            }
//        }
//
//        return Response.success("ok");
//    }


}
