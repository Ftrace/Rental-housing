package com.example.zwf.web;

import com.example.zwf.entity.User;
import com.example.zwf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserControllor {
    @Autowired
    UserService userService;

    /**
     * 通过用户email获取用户信息
     * @param message
     * @param response
     * @return
     */

    @RequestMapping(value = "/getUserByEmail", method = RequestMethod.GET)
    private User getUserByEmail(String message , HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println("通过email查询用户信息");
        System.out.println("用户email："+message);
//        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 获取用户信息
        User user = userService.getUserByEmail(message);
        System.out.println("用户Id"+message);
//        modelMap.put("message", user);
        return user;
    }

    /**
     * 查看图片
     * @param name
     * @param response
     */
    @RequestMapping("/avatarUser/{name:.+}")
    public void getAvatar(@PathVariable("name")String name,
                          HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println("查看图片");
        System.out.println(name);
        String imType = name.substring(name.lastIndexOf('.') + 1);
        System.out.println("imType:" + imType);
        response.setContentType("image/" + imType);
        try {
            FileInputStream fromServer = new FileInputStream(
                    new File("D:/Rental-house/Rental-housing/src/main/resources/static/userpicture/" + name)
            );
            OutputStream toClient = response.getOutputStream();
            byte[] avatar = new byte[fromServer.available()];
            fromServer.read(avatar);
            toClient.write(avatar);
            toClient.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传图片
     * @param file
     * @param response
     * @return
     */

    @RequestMapping(value = "/uploadProfilePicture",method = RequestMethod.POST )
    public boolean editAvatar(
            @RequestParam("file") MultipartFile file, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println("接收文件");
        Map<String, Object> modelMap = new HashMap<>();
        if (file.isEmpty()) {
            modelMap.put("message", "文件为空");
            return false;
        }
        String path = "D:/Rental-house/Rental-housing/src/main/resources/static/userpicture/";
        File serverFile = new File(path + file.getOriginalFilename());
        File dir = new File(path);
        System.out.println("用户图片开始上传");
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            //把文件写到文件夹里面
            file.transferTo(serverFile);
        } catch (IOException e) {
            System.out.println("用户图片上传失败");
            e.printStackTrace();
            modelMap.put("message", "用户图片上传失败");
            return false;
        }
        modelMap.put("message", "localhost:8082/D:/Rental-house/Rental-housing/src/main/resources/static/userpicture/" + file.getOriginalFilename());
        return true;
//        return ResultMap.success("http://192.168.3.96:8081/api/avatar/" + file.getOriginalFilename());
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
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateByEmail", method = RequestMethod.POST)
    public boolean updateByEmail(String email,String password,String name,String number,String nickname,String hobby,String wechat,String type,String ID, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");

        System.out.println("通过email更改用户信息");
        System.out.println("email: " + email);
        System.out.println("name: " + name);
        System.out.println("number: " + number);
        System.out.println("nickname: " + nickname);
        System.out.println("hobbye: " + hobby);
        System.out.println("wechat: " + wechat);
        System.out.println("type: " + type);
        System.out.println("ID: " + ID);
        boolean flag = userService.modifyUser(email,password,name,number,nickname,hobby,wechat,type,ID);
        return flag;
    }

}
