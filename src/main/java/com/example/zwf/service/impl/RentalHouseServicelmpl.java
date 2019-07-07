package com.example.zwf.service.impl;

import com.example.zwf.dao.RentalHouseDao;
import com.example.zwf.entity.RentalHouse;
import com.example.zwf.service.RentalHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RentalHouseServicelmpl implements RentalHouseService {

    @Autowired
    private RentalHouseDao rentalHouseDao;
    @Override
    public List<RentalHouse> getRentalHouseList() {
        return rentalHouseDao.queryRentalHouse();
    }

    @Override
    public List<RentalHouse> getRentalHouseByEmail(String email) {
        return rentalHouseDao.queryRentalHouseByEmail(email);
    }

    @Transactional
    @Override
    public boolean addRentalHouse(String email, String name,String rname,  String location, int area, int price, String number, String oriented, String houseType, String introduction, String wechat) {
        RentalHouse rentalHouse = new RentalHouse();
        rentalHouse.setId(12);
        rentalHouse.setEmail(email);
        rentalHouse.setName(name);
        rentalHouse.setRname(rname);
        rentalHouse.setLocation(location);
        rentalHouse.setArea(area);
        rentalHouse.setPrice(price);
        rentalHouse.setNumber(number);
        rentalHouse.setOriented(oriented);
        rentalHouse.setHouseType(houseType);
        rentalHouse.setIntroduction(introduction);
        rentalHouse.setWechat(wechat);

        if (    (email!= null && !"".equals(email))||
                (name!= null && !"".equals(name))||
                (rname!= null && !"".equals(rname))||
                (location!= null && !"".equals(location))||
                (area!= 0 && !"".equals(area))||
                (price!= 0 && !"".equals(price))||
                (number!= null && !"".equals(number))||
                (oriented!= null && !"".equals(oriented))||
                (houseType!= null && !"".equals(houseType))||
                (introduction!= null && !"".equals(introduction))||
                (wechat!= null && !"".equals(wechat)))
        {

            try {
                int effectedNum = rentalHouseDao.insertRentalHouse(rentalHouse);
                if (effectedNum > 0) {
                    return true;
                } else {
                    System.out.println("添加出租屋信息失败!");
                    throw new RuntimeException("添加出租屋信息失败!");
                }
            } catch (Exception e) {
                System.out.println("添加出租屋信息失败:" + e.toString());
                throw new RuntimeException("添加出租屋信息失败:" + e.toString());
            }
        } else {
            System.out.println("有信息为空！请填全信息");
            throw new RuntimeException("有信息为空！请填全信息");
        }
    }

    @Transactional
    @Override
    public boolean modifyRentalHouse(int id,String email,String name,String rname,String location,int area,int price,String number,String oriented,String houseType,String introduction,String wechat){

        RentalHouse rentalHouse = new RentalHouse();
        rentalHouse.setId(id);
        rentalHouse.setEmail(email);
        rentalHouse.setName(name);
        rentalHouse.setRname(rname);
        rentalHouse.setLocation(location);
        rentalHouse.setArea(area);
        rentalHouse.setPrice(price);
        rentalHouse.setNumber(number);
        rentalHouse.setOriented(oriented);
        rentalHouse.setHouseType(houseType);
        rentalHouse.setIntroduction(introduction);
        rentalHouse.setWechat(wechat);

        if (    (email!= null && !"".equals(email))||
                (name!= null && !"".equals(name))||
                (rname!= null && !"".equals(rname))||
                (location!= null && !"".equals(location))||
                (area!= 0 && !"".equals(area))||
                (price!= 0 && !"".equals(price))||
                (number!= null && !"".equals(number))||
                (oriented!= null && !"".equals(oriented))||
                (houseType!= null && !"".equals(houseType))||
                (introduction!= null && !"".equals(introduction))||
                (wechat!= null && !"".equals(wechat)))
        {

            try {
                int effectedNum = rentalHouseDao.updateRentalHouse(rentalHouse);
                if (effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("更新出租屋信息失败!");
                }
            } catch (Exception e) {
                throw new RuntimeException("更新出租屋信息失败:" + e.toString());
            }
        } else {
            throw new RuntimeException("有信息为空！请填全信息");
        }

    }

    @Transactional
    @Override
    public boolean deleteRentalHouse(int id) {
        if (id > 0) {
            try {
                // 删除出租屋信息
                int effectedNum = rentalHouseDao.deleteRentalHouse(id);
                if (effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("删除区域信息失败!");
                }
            } catch (Exception e) {
                throw new RuntimeException("删除区域信息失败:" + e.toString());
            }
        } else {
            throw new RuntimeException("区域Id不能为空！");
        }
    }


    /**
     * 根据价格范围获取出租屋信息
     */
    @Override
    public List<RentalHouse> getRentalHouseByPrice(int minPrice, int maxPrice) {
        return rentalHouseDao.queryRentalHouseByPrice(minPrice, maxPrice);
    }

    /**
     * 根据位置列出出租屋信息
     */
    @Override
    public List<RentalHouse> getRentalHouseByLocation(String location) {
        return rentalHouseDao.queryRentalHouseByLocation(location);
    }

    /**
     * 根据面积范围列出出租屋信息
     */
    @Override
    public List<RentalHouse> getRentalHouseByArea(int minArea, int maxArea) {
        return rentalHouseDao.queryRentalHouseByArea(minArea, maxArea);
    }

}
