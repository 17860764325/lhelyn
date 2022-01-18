package com.lhrlyn.cn.lhrlynadmin.user.service.impl;

import com.lhrlyn.cn.lhrlynadmin.user.dto.UserDto;
import com.lhrlyn.cn.lhrlynadmin.user.enity.User;
import com.lhrlyn.cn.lhrlynadmin.user.enity.UserRole;
import com.lhrlyn.cn.lhrlynadmin.user.enity.Userinfo;
import com.lhrlyn.cn.lhrlynadmin.user.mapper.RoleMapper;
import com.lhrlyn.cn.lhrlynadmin.user.mapper.UserMapper;
import com.lhrlyn.cn.lhrlynadmin.user.mapper.UserRoleMapper;
import com.lhrlyn.cn.lhrlynadmin.user.service.LoginService;
import com.lhrlyn.cn.lhrlynadmin.user.util.ResultData;
import com.lhrlyn.cn.lhrlynadmin.user.util.ReturnCode;
import com.lhrlyn.cn.lhrlynadmin.user.util.beanCopy.BeanCopyUtils;
import com.lhrlyn.cn.lhrlynadmin.user.util.tokenJWT.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.lhrlyn.cn.lhrlynadmin.user.enity.Role;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 校验用户账号密码正确性
     *
     * @param
     * @author lhr
     * @date 2021/12/30 11:09 PM
     * @return:
     */
    @Override
    public ResultData checkUser(UserDto user) {
        User user2 = BeanCopyUtils.beanCopy(user, User.class);
        // 进行查询
        User user3 = userMapper.selectOne(user2);
        UserDto userDto = BeanCopyUtils.beanCopy(user3, UserDto.class);
        // 进行比较
        if (user2.getUsername().equals(user3.getUsername    ()) && user2.getPassword().equals(user3.getPassword())) {
            // 比较成功
            log.info(userDto.toString());
            // 生成token
            String token = JwtUtil.sign(userDto.getUserid().intValue());
            redisTemplate.opsForValue().set(token, userDto, Duration.ofMinutes(30L));
            userDto.setToken(token);
            return ResultData.success(userDto);

        }
        // 比较不成功
        return ResultData.fail(ReturnCode.USERNAME_OR_PASSWORD_ERROR.getCode(), ReturnCode.USERNAME_OR_PASSWORD_ERROR.getMessage());
    }

    /**
     * 获取用户的信息根绝token
     *
     * @param token
     * @author lhr
     * @date 2022/1/18 2:19 下午
     * @return: com.lhrlyn.cn.lhrlynadmin.user.util.ResultData
     */
    @Override
    public ResultData getUserInfo(String token) {
        Object userDto = redisTemplate.opsForValue().get(token);//封装到redis里
        UserDto userDto1 = BeanCopyUtils.beanCopy(userDto, UserDto.class);
        if (userDto != null) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userDto1.getUserid());
            List<UserRole> select = userRoleMapper.select(userRole);
            List<String> list = new ArrayList<>();
            for (UserRole role : select) {
                Role role1 = new Role();
                role1.setId(role.getRole_id());
                Role role2 = roleMapper.selectOne(role1);
                list.add(role2.getRoleName());
            }
            Userinfo userinfo = new Userinfo();
            userinfo.setRoles(list);
            userinfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            userinfo.setName("Super Admin");
            userinfo.setIntroduction("I am a super administrator");
            return ResultData.success(userinfo);
        } else {
            return ResultData.fail(ReturnCode.RC401.getCode(), ReturnCode.RC401.getMessage());
        }
    }
}
