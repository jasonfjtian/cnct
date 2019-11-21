package com.cnct.controller;

import com.cnct.pojo.User;
import com.cnct.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 1、用户注册
     * 2、用户手机号唯一验证
     * 3、短信验证码的发送、及验证
     * 4、用户信息的完善
     */
    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(User user){
        User resUser = userService.register(user);
        return ResponseEntity.ok().body(resUser);
    }

    /**
     * 用户手机号唯一验证
     * @param phone
     * @return
     */
    @GetMapping("/checkphone/{phone}")
    public ResponseEntity<Boolean> checkPhone(@PathVariable("phone") String phone){
        Boolean res = userService.checkPhone(phone);
        return ResponseEntity.ok(res);
    }

    /**
     * 短信验证码的发送
     * @param phone
     * @return
     */
    @PostMapping("/code")
    public ResponseEntity<Void> sendVerifyCode(String phone){
        Boolean aBoolean = userService.sendVerifyCode(phone);
        return ResponseEntity.ok(null);
    }

    /**
     * 短信验证码的验证
     * @param phone
     * @param code
     * @return
     */
    @PostMapping("/checkcode/{phone}")
    public ResponseEntity<Boolean> checkVerifyCode(@PathVariable("phone") String phone,String code){
        Boolean res = userService.checkVerifyCode(phone,code);
        return ResponseEntity.ok(res);
    }

    /**
     * 用户信息完善 用户信息更新
     */
    @PutMapping("/complete")
    public ResponseEntity<User> completeInfo(@Valid User user){
        User resUser = userService.completeInfo(user);
        return ResponseEntity.ok(resUser);
    }

    /**
     * 用户信息删除
     */
    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok(null);
    }
    /**
     * 用户信息封号
     */
    @PutMapping("/banneduser/{id}")
    public ResponseEntity<Void> bannedUser(@PathVariable("id") Long id){
        userService.bannedUser(id);
        return ResponseEntity.ok(null);
    }
    /**
     * TODO 用户登录验证功能
     */



}
