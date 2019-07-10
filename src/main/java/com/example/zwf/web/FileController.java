package com.example.zwf.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

@RestController

public class FileController {

    /**
     * 通过照片名字查看图片
     * @param name
     * @param response
     */
    @RequestMapping("/avatar/{name:.+}")
    public void getAvatar(@PathVariable("name")String name,
                          HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println(name);
        String imType = name.substring(name.lastIndexOf('.') + 1);
        System.out.println("imType:" + imType);
        response.setContentType("image/" + imType);
        try {
            FileInputStream fromServer = new FileInputStream(
                    new File("D:/Rental-house/Rental-housing/src/main/resources/static/rentalhousepicture/" + name)
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
     */
    @RequestMapping(value = "/uploadRentalHouse")
    public void editAvatar(
            @RequestParam("file") MultipartFile file, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> modelMap = new HashMap<>();
        if (file.isEmpty()) {
            modelMap.put("message", "文件为空");
//            return false;
        }
        String path = "D:/Rental-house/Rental-housing/src/main/resources/static/rentalhousepicture/";
        File serverFile = new File(path + file.getOriginalFilename());
        File dir = new File(path);
        System.out.println("出租屋图片开始上传");
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            file.transferTo(serverFile);
        } catch (IOException e) {
            System.out.println("出租屋图片上传失败");
            e.printStackTrace();
            modelMap.put("message", "出租屋图片上传失败");
//            return false;
        }
        String name = file.getOriginalFilename();
        System.out.println(name);
        String imType = name.substring(name.lastIndexOf('.') + 1);
        System.out.println("imType:" + imType);
        response.setContentType("image/" + imType);
        try {
            FileInputStream fromServer = new FileInputStream(
                    new File("D:/Rental-house/Rental-housing/src/main/resources/static/rentalhousepicture/" + name)
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
//        modelMap.put("message", "localhost:8082/D:/Rental-house/Rental-housing/src/main/resources/static/rentalhousepicture/" + file.getOriginalFilename());
//        return true;
//        return ResultMap.success("http://192.168.3.96:8081/api/avatar/" + file.getOriginalFilename());
    }
}
