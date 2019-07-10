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
     * @param minPrice
     * @param maxPrice
     * @return
     */
    List<RentalHouse> getRentalHouseByPrice(int minPrice, int maxPrice);


    /**
     * 根据位置列出出租屋信息
     * @param location
     * @return
     */
    List<RentalHouse> getRentalHouseByLocation(String location);


    /**
     * 根据面积范围列出出租屋信息
     * @param minArea
     * @param maxArea
     * @return
     */
    List<RentalHouse> getRentalHouseByArea(int minArea, int maxArea);


    /**
     * 根据Id获取出租屋信息
     * @param id
     * @return
     */
    RentalHouse getRentalHouseById(int id);


    /**
     * 根据价格获取出租屋信息
     * @param price
     * @return
     */
    List<RentalHouse> getRentalHouseByOnePrice(int price);


    /**
     * 根据面积获取出租屋信息
     * @param area
     * @return
     */
    List<RentalHouse> getRentalHouseByOneArea(int area);


    /**
     * 房客改变房屋状态
     * @param tenantEmail
     * @param id
     * @return
     */
    boolean updateRentalHouseState1(String tenantEmail,int id);


    /**
     * 房东改变房屋状态
     * @param id
     * @return
     */
    boolean updateRentalHouseState2(int id);


    /**
     * 房东获取被申请过的出租屋信息
     * @param email
     * @return
     */
    List<RentalHouse> getRentalHouseToLandlord(String email);


    /**
     * 房客获取申请过的出租屋信息
     * @param email
     * @return
     */
    List<RentalHouse> getRentalHouseToTenant(String email);
}
