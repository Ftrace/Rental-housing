//package com.example.zwf.service.impl;
//
//import com.example.zwf.dao.ImgTestReposrtory;
//import com.example.zwf.entity.ImgTest;
//import com.example.zwf.service.ImgService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class ImgTestServiceImpl implements ImgService {
//    @Autowired
//    private ImgTestReposrtory imgTestReposrtory;
//
//    @Override
//    public ImgTest add(ImgTest imgTest, String path) {
//
//        imgTest.setUrl(path);
//        return (ImgTest) imgTestReposrtory.save(imgTest);
//    }
//}
