package com.cos.blog.controller.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

//스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해준다.
@Service("sssss")
public class UserService {
    @Autowired
    private UserRepository userRepository;



    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void 회원가입(User user){
        User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원찾기 실패");
        });
        String rawPassword = user.getPassword();//1234원문
        String encPassword = encoder.encode(rawPassword); //해쉬로 바뀜
        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());

        userRepository.save(persistance);
    }

    @Transactional
    public void 회원수정(User user) {
        //수정시에는 영속성 컨텍스트 User 오브젝트를 영속화 시키고,
        //영속화된 USER오브젝트를 수정한다.
        User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원찾기 실패");
        });
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());


    }


    //구방식 로그인
//    @Transactional(readOnly = true) //select 할때 트랜잭션 시작, 서비스 종료시 트랜잭션 종료
//    public User 로그인(User user) {
//       return userRepository.login(user.getUsername(),user.getPassword());
//    }
}
