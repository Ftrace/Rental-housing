//package com.example.zwf.web;
//
//import com.example.zwf.entity.FileUtil;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.net.ssl.SSLEngine;
//
//public class filecontrolller1 {
//    private SSLEngine request;
//
//    @RequestMapping(value="/testuploadimg", method = RequestMethod.POST)
//    public @ResponseBody
//    String uploadImg(@RequestParam("file") MultipartFile file) {
//        String fileName = file.getOriginalFilename();
//        //设置文件上传路径
//        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
//        try {
//            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
//            return "上传成功";
//        } catch (Exception e) {
//            return "上传失败";
//        }
//
//}
