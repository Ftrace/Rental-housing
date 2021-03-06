package com.example.zwf.service;

import com.example.zwf.entity.User;

public interface UserService {
//    /**
//     * 获取用户列表
//     * @return
//     */
//    List<User> getUserList();

    /**
     * 通过email获取用户信息
     *
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    /**
     * 增加用户信息
     *
     * @param user
     * @return
     */
    boolean addUser(User user);


    /**
     * 增加用户信息
     *
     * @param
     * @return
     */
    boolean addUser1(String email,String password,String ensure,String name,String number,String identity);

    /**
     *用户登录
     */
    String login(String email,String password);


    /**
     * 修改用户信息
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

   boolean modifyUser(String email,String password,String name,String number,String nickname,String hobby,String wechat,String type,String ID);
}
