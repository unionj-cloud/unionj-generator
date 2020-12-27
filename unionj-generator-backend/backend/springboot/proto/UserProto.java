package com.tsingxiao.unionj.demo.proto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import com.tsingxiao.unionj.demo.vo.User;

public interface UserProto {

    @PostMapping("/user")
    ResponseEntity<Void> createUser(
        @RequestBody(required=false) User body
    );

    @PostMapping("/user/createWithList")
    User createUsersWithListInput(
        @RequestBody(required=false) List<User> body
    );

    @GetMapping("/user/login")
    String loginUser(
        @RequestParam(value="password", required=false) String password,
        @RequestParam(value="username", required=false) String username
    );

    @GetMapping("/user/logout")
    ResponseEntity<Void> logoutUser(
        
    );

    @GetMapping("/user/{username}")
    User getUserByName(
        @PathVariable("username") String username
    );

    @PutMapping("/user/{username}")
    ResponseEntity<Void> updateUser(
        @PathVariable("username") String username,
        @RequestBody(required=false) User body
    );

    @DeleteMapping("/user/{username}")
    ResponseEntity<Void> deleteUser(
        @PathVariable("username") String username
    );

}
