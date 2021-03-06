package com.example.zwf.service.impl;

import com.example.zwf.dao.UserDao;
import com.example.zwf.entity.RentalHouse;
import com.example.zwf.entity.User;
import com.example.zwf.service.UserService;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userdao;

    /**
     * 通过email获取用户的信息
     * @param email
     * @return
     */
    @Override
    public User getUserByEmail(String email) {
        return userdao.queryUserByEmail(email);
    }

    /**
     * 登录判断
     * @param email
     * @param password
     * @return string
     */
    @Override
    public String login(String email, String password) {
        String identity = userdao.selectUser(email,password);
        User user = new User();
        user = userdao.queryUserByEmail(email);
        String password1=user.getPassword();
        if(user==null)
            return "null";
        if(password.equals(password1)) {
            if (identity != null){
                return identity;
            }
            return "null";
        }else {
            return "密码错误";
        }

    }

    /**
     * 注册判断
     * @param email
     * @param password
     * @param ensure
     * @param name
     * @param number
     * @param identity
     * @return
     */
    @Transactional
    @Override
    public boolean addUser1(String email,String password,String ensure,String name,String number,String identity){
        User user1 = new User();
        user1.setEmail(email);
        user1.setPassword(password);
        user1.setEnsure(ensure);
        user1.setName(name);
        user1.setNumber(number);
        user1.setIdentity(identity);
        if(email==null||"".equals(email)) {
            System.out.println("邮箱不能为空！");
            throw new RuntimeException("邮箱不能为空！");


        } else if(!password.equals(ensure)){
            System.out.println("密码不一致！");
            throw new RuntimeException("密码不一致！");
        } else if(userdao.queryUserByEmail(email)!=null){
            System.out.println("该用户已存在！");
            throw new RuntimeException("该用户已存在！");
        }
        else  {

            try {
                int effectedNum = userdao.insertUser(user1);
                if (effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("添加用户信息失败!");
                }
            } catch (Exception e) {
                throw new RuntimeException("添加用户信息失败:" + e.toString());
            }
                }
    }

    @Transactional
    @Override
    public boolean addUser(User user) {
        if(user.getEmail()!=null&&!"".equals(user.getEmail())&&user.getPassword()==user.getEnsure()&&userdao.queryUserByEmail(user.getEmail())==null){
            throw new RuntimeException("注册失败！");

        }else {

            try {
                int effectedNum = userdao.insertUser(user);
                if (effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("添加用户信息失败!");
                }
            }catch (Exception e) {
                throw new RuntimeException("添加用户信息失败:" + e.toString());
            }
        }

    }

    /**
     * 更新用户信息
     * @param email
     * @param password
     * @param name
     * @param number
     * @param nickname
     * @param hobby
     * @param wechat
     * @param type
     * @param ID
     * @return
     */
    @Transactional
    @Override
    public boolean modifyUser(String email,String password,String name,String number,String nickname,String hobby,String wechat,String type,String
        ID){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setEnsure(password);
        user.setName(name);
        user.setNumber(number);
        user.setNickname(nickname);
        user.setHobby(hobby);
        user.setWechat(wechat);
        user.setType(type);
        user.setID(ID);

        if (email!= null && !"".equals(email))

        {

            try {
                int effectedNum = userdao.updateUser(user);
                if (effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("更新用户信息失败!");
                }
            } catch (Exception e) {
                throw new RuntimeException("更新用户信息失败:" + e.toString());
            }
        } else {
            throw new RuntimeException("email为空！请填写email");
        }
    }
}
