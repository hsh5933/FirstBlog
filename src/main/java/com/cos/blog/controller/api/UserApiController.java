package com.cos.blog.controller.api;

import com.cos.blog.controller.service.UserService;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encode;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController : save호출됨");
        //실제로 DB에 insert하고 return이되면됨.

        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }



    //구버전 로그인
//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
//        System.out.println("UserApiController : login호출됨");
//        User principal = userService.로그인(user); //principal(접근주체)
//
//        if(principal!=null){
//            session.setAttribute("principal",principal);
//        }
//        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
//    }


}
