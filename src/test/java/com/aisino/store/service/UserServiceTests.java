package com.aisino.store.service;

import com.aisino.store.entity.User;
import com.aisino.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wuxiang
 * @date 2021/12/4 10:07 下午
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @Autowired
    private IUserService iUserService;

    @Test
    public void reg(){
        try {
            User user=new User();
            user.setUsername("lele");
            user.setPassword("123456");
            iUserService.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());

        }
    }

    @Test
    public void login(){
        User lele = iUserService.login("lele", "123456");
        System.out.println(lele);
    }

    @Test
    public void changePassword(){
        iUserService.changePassword(4,"管理员","123456","654321");
    }

    @Test
    public void getByUid(){
        System.out.println(iUserService.getByUid(4));
    }

    @Test
    public void changeInfo(){
        User user=new User();
        user.setPhone("15955429191");
        user.setEmail("1325668250@qq.com");
        user.setGender(0);
        iUserService.changeInfo(4,"管理员",user);
    }


    @Test
    public void changeAvatar(){
        iUserService.changeAvatar(4,"load/avatar.png","专管员");
    }
}
