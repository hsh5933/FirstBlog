package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    //스프링이 로그인요청을 가로챌때 username, password변수 2개를 가로채는데
    //password부분처리는 알아서함
    //username이 DB에있는지 확인하면된다
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username).orElseThrow(()->{
            return new UsernameNotFoundException("해당 사용자를 찾을수 없습니다."+username);
        });
        return new PrincipalDetail(principal); //시큐리티세션에 유저정보저장
    }
}
