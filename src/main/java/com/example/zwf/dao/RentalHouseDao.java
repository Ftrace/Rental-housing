package com.example.zwf.dao;

import com.example.zwf.entity.RentalHouse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalHouseDao {
    /**
     * 列出出租屋列表
     *
     * @return RentalHouseList
     */
    List<RentalHouse> queryRentalHouse();

    /**
     * 根据email列出该用户发布的出租屋
     *
     * @return RentalHouse
     */
    List<RentalHouse> queryRentalHouseByEmail(String email);

    /**
     * 根据出租屋id查找出租屋信息
     * @param id
     * @return
     */
    RentalHouse queryRentalHouseById(int id);

    /**
     * 增加出租屋信息
     *
     * @param rentalHouse
     * @return
     */
    int insertRentalHouse(RentalHouse rentalHouse);

    /**
     * 更新出租屋信息
     *
     * @param rentalHouse
     * @return
     */
    int updateRentalHouse(RentalHouse rentalHouse);

    /**
     * 删除出租屋信息
     *
     * @param id
     * @return
     */
    int deleteRentalHouse(int id);

    /**
     * 根据价格范围列出出租屋信息
     */
    List<RentalHouse> queryRentalHouseByPrice(int minPrice, int maxPrice);

    /**
     * 根据价格列出出租屋信息
     */
    List<RentalHouse> queryRentalHouseByOnePrice(int price);

    /**
     * 根据位置列出出租屋信息
     */
    List<RentalHouse> queryRentalHouseByLocation(String location);

    /**
     * 根据面积范围列出出租屋信息
     */
    List<RentalHouse> queryRentalHouseByArea(int minArea, int maxArea);


    /**
     * 根据面积列出出租屋信息
     */
    List<RentalHouse> queryRentalHouseByOneArea(int area);

}
