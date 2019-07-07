//package com.example.zwf.web;
//
//import com.example.zwf.entity.ImgTest;
//import com.example.zwf.service.ImgService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.swing.*;
//import java.io.File;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.logging.Logger;
//
//public class filecontroller {
//    @Autowired
//    private ImgService imgService;
//    @Value("${server.port")
//    private String post;
//
//    private String host;
//
//    private String rootPath = "H";
//
//    private String sonPath = "/img/";
//
//    private String imgPath;
//
//
//
//    @RequestMapping(value = "upload")
//    public String upload(@RequestParam("test") MultipartFile file){
//        if(file.isEmpty())
//            return "文件为空";
//        //获取本机IP
//        try {
//            host = InetAddress.getLocalHost().getHostAddress();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//
//        // 获取文件名
//        String fileName = file.getOriginalFilename();
//
//        // 设置文件上传后的路径
//        String filePath = rootPath + sonPath;
//
//        //创建文件路径
//        File dest = new File(filePath + fileName);
//
//        String imgPath = (host + ":" + post + sonPath + fileName).toString();
//
//
//        // 检测是否存在目录
//        if (!dest.getParentFile().exists()) {
//            //假如文件不存在即重新创建新的文件已防止异常发生
//            dest.getParentFile().mkdirs();
//        }
//        try {
//            //transferTo（dest）方法将上传文件写到服务器上指定的文件
//            file.transferTo(dest);
//            //将链接保存到URL中
//            ImgTest imgTest = imgService.add(new ImgTest(), imgPath);
//            return "上传成功";
//        } catch (Exception e) {
//            return "上传失败";
//        }
//
//    }
//
//
//
//}
