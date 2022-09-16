package com.xml.reggie.controller;

import com.xml.reggie.common.R;
import com.xml.reggie.entity.User;
import com.xml.reggie.service.UserService;
import com.xml.reggie.utils.SMSUtils;
import com.xml.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 发送验证码短信
     * @param user
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        String phone = user.getPhone();
        if (StringUtils.isNotEmpty(phone)){
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code:{}",code);
//            SMSUtils.sendMessage("瑞吉外卖","",phone,code);
            session.setAttribute("phone",code);
            return R.success("手机验证码短信发送成功!");
        }
        return R.error("短信发送失败");
    }

}
