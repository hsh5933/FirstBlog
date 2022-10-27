package com.cos.blog.controller;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

//html화면이아닌 data를 리턴해주는 restController
@RestController
public class DummyControllerTest {

    @Autowired //의존성주입(DI)
    private UserRepository userRepository;

    // email,password 받아야됨
    @Transactional //save메소드 사용안해도 업데이트가 가능하게한다.
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
        System.out.println("id : "+id);
        System.out.println("password : "+requestUser.getPassword());
        System.out.println("email : "+requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });

        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        //userRepository.save(user);

        //더티체킹


        return null;
    }


    //한페이지당 2건의 데이터를 리턴
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;

    }


    //http://localhost:8080/blog/dummy/user
    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    //{id} 주소로 파라미터를 전달받을수 있음
    //http://localhost:8080/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        // user/4를 찾으면 user가 null이되니
        // return일때 null이되니 문제가생길수있으니
        //Optional로 user객체를 감싸서 가져오니 판단
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
            }
        });
        //요청 : 웹브라우저
        //user 객체 = 자바오브젝트
        //변환 (웹브라우저가 이해할수있는 데이터) -> json
        //스프링부트 = MessageConverter가 응답시 자동작동
        return user;
    }

    //http://localhost:8080/blog/dummy/join(요청)
    //http의 body에 username, password, email데이터를 가지고 요청
    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("username:"+user.getUsername());
        System.out.println("password:"+user.getPassword());
        System.out.println("email:"+user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }
}
