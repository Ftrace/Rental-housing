package com.example.zwf.service;

import com.example.zwf.entity.RentalHouse;

import java.util.List;

public interface RentalHouseService {
     /**
     * 获取所有出租屋信息列表
     * @return
     */
    List<RentalHouse> getRentalHouseList();


    /**
     * 通过email获取出租屋信息
     *
     * @param email
     * @return
     */
    List<RentalHouse> getRentalHouseByEmail(String email);



    /**
     * 增加出租屋信息
     *
     * @param
     * @return
     */
    boolean addRentalHouse(String email,String name,String rname,String location,int area,int price,String number,String oriented,String houseType,String introduction,String wechat);

    /**
     * 修改出租屋信息
     *
     * @param
     * @return
     */
    boolean modifyRentalHouse(int id,String email,String name,String rname,String location,int area,int price,String number,String oriented,String houseType,String introduction,String wechat);

    /**
     * 删除出租屋信息
     *
     * @param id
     * @return
     */
    boolean deleteRentalHouse(int id);

    /**
     * 根据价格范围获取出租屋信息
     */
    List<RentalHouse> getRentalHouseByPrice(int minPrice, int maxPrice);

    /**
     * 根据位置列出出租屋信息
     */
    List<RentalHouse> getRentalHouseByLocation(String location);

    /**
     * 根据面积范围列出出租屋信息
     */
    List<RentalHouse> getRentalHouseByArea(int minArea, int maxArea);


}
