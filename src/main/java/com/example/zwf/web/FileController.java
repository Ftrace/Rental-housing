package com.example.zwf.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

@RestController

public class FileController {

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
                    new File("D:/data/" + name)
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


    @RequestMapping(value = "/uploadRentalHouse",method = RequestMethod.PUT )
    public Map<String, Object> editAvatar(
            @RequestParam("file") MultipartFile file, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> modelMap = new HashMap<>();
        if (file.isEmpty()) {
            modelMap.put("message", "文件为空");
            return modelMap;
        }
        String path = "D:/data/";
        File serverFile = new File(path + file.getOriginalFilename());
        File dir = new File(path);
        System.out.println("开始上传");
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            file.transferTo(serverFile);
        } catch (IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
            modelMap.put("message", "上传失败");
            return modelMap;
        }
        modelMap.put("message", "localhost:8082/D:/data/" + file.getOriginalFilename());
        return modelMap;
//        return ResultMap.success("http://192.168.3.96:8081/api/avatar/" + file.getOriginalFilename());
    }
}
