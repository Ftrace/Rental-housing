package com.example.zwf.service.impl;

import com.example.zwf.dao.RentalHouseDao;
import com.example.zwf.entity.RentalHouse;
import com.example.zwf.service.RentalHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class RentalHouseServicelmpl implements RentalHouseService {

    @Autowired
    private RentalHouseDao rentalHouseDao;

    /**
     * 房东获取出租屋状态为待审核和已出租的信息
     * @param email
     * @return
     */
    @Override
    public List<RentalHouse> getRentalHouseToLandlord(String email) {
        return rentalHouseDao.getRentalHouseToLandlord(email);
    }

    /**
     * 房客获取申请过的出租屋状态为待审核和已出租的信息
     * @param email
     * @return
     */
    @Override
    public List<RentalHouse> getRentalHouseToTenant(String email) {
        return rentalHouseDao.getRentalHouseToTenant(email);
    }

    /**
     * 列出所有的出租屋信息
     * @return
     */
    @Override
    public List<RentalHouse> getRentalHouseList() {
        return rentalHouseDao.queryRentalHouse();
    }

    /**
     * 房东列出与自己有关出租屋信息
     * @param email
     * @return
     */
    @Override
    public List<RentalHouse> getRentalHouseByEmail(String email) {
        return rentalHouseDao.queryRentalHouseByEmail(email);
    }

    /**
     * 根据Id获取出租屋信息
     */
    @Override
    public RentalHouse getRentalHouseById(int id) {
        return rentalHouseDao.queryRentalHouseById(id);
    }

    /**
     * 根据价格获取出租屋信息
     * @param price
     * @return
     */
    @Override
    public List<RentalHouse> getRentalHouseByOnePrice(int price) {
        return rentalHouseDao.queryRentalHouseByOnePrice(price);
    }


    /**
     * 根据面积列出出租屋信息
     * @param area
     * @return
     */
    @Override
    public List<RentalHouse> getRentalHouseByOneArea(int area) {
        return rentalHouseDao.queryRentalHouseByOneArea(area);
    }


    /**
     * 房客更新出租屋的状态 未出租——>待审核
     * @param tenantEmail
     * @param id
     * @return
     */
    @Transactional
    @Override
    public boolean updateRentalHouseState1(String tenantEmail,int id){
        RentalHouse rentalHouse = new RentalHouse();
        rentalHouse.setId(id);
        rentalHouse.setTenantEmail(tenantEmail);
        rentalHouse.setState("待审核");
        if(id>0&& tenantEmail!= null && !"".equals(tenantEmail)){
            try {
                int effectedNum = rentalHouseDao.updateRentalHouseState1(rentalHouse);
                if (effectedNum > 0) {
                    return true;
                } else {
                    System.out.println("用户更改出租屋状态失败!");
                    throw new RuntimeException("用户更改出租屋状态失败!");
                }
            } catch (Exception e) {
                System.out.println("用户更改出租屋状态失败:" + e.toString());
                throw new RuntimeException("用户更改出租屋状态失败:" + e.toString());
            }
        }else {
            System.out.println("有信息为空！请填全信息");
            throw new RuntimeException("有信息为空！请填全信息");
        }
    }

    /**
     * 房东更新出租屋的状态 待审核—>已出租
     * @param id
     * @return
     */

    @Transactional
    @Override
    public  boolean updateRentalHouseState2(int id){
        RentalHouse rentalHouse = new RentalHouse();
        rentalHouse.setId(id);
        rentalHouse.setState("已出租");
        if(id> 0){
            try {
                int effectedNum = rentalHouseDao.updateRentalHouseState1(rentalHouse);
                if (effectedNum > 0) {
                    return true;
                } else {
                    System.out.println("房东更改出租屋状态失败!");
                    throw new RuntimeException("房东更改出租屋状态失败!");
                }
            } catch (Exception e) {
                System.out.println("房东更改出租屋状态失败:" + e.toString());
                throw new RuntimeException("房东更改出租屋状态失败:" + e.toString());
            }
        }else {
            System.out.println("有信息为空！请填全信息");
            throw new RuntimeException("有信息为空！请填全信息");
        }
    }

    /**
     * 增加出租屋信息
     * @param email
     * @param name
     * @param rname
     * @param location
     * @param area
     * @param price
     * @param number
     * @param oriented
     * @param houseType
     * @param introduction
     * @param wechat
     * @return
     */
    @Transactional
    @Override
    public boolean addRentalHouse(String email, String name, String rname,
                                   String location, int area, int price,
                                   String number, String oriented, String houseType,
                                   String introduction, String wechat) {
        RentalHouse rentalHouse = new RentalHouse();
        rentalHouse.setId(0);
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
        rentalHouse.setState("未出租");
        if("".equals(rentalHouse.getEmail())||
                "".equals(rentalHouse.getName())||
                "".equals(rentalHouse.getRname())||
                "".equals(rentalHouse.getLocation())||
                rentalHouse.getArea()<=0||
                rentalHouse.getPrice()<=0||
                "".equals(rentalHouse.getNumber())||
                "".equals(rentalHouse.getOriented())||
                "".equals(rentalHouse.getHouseType())||
                "".equals(rentalHouse.getIntroduction())||
                "".equals(rentalHouse.getWechat())){
            System.out.println("某个信息为空，添加出租屋失败！");
            throw new RuntimeException("添加出租屋失败！");

        }else {
            try {
                int effectedNum = rentalHouseDao.insertRentalHouse(rentalHouse);
                if (effectedNum > 0) {
                    return true;
                } else {
                    System.out.println("1添加出租屋失败！");
                    throw new RuntimeException("添加出租屋信息失败!");
                }
            }catch (Exception e) {
                System.out.println("2添加出租屋失败！");
                throw new RuntimeException("添加出租屋信息失败:" + e.toString());
            }
        }
    }

    /**
     * 修改出租屋信息
     * @param id
     * @param email
     * @param name
     * @param rname
     * @param location
     * @param area
     * @param price
     * @param number
     * @param oriented
     * @param houseType
     * @param introduction
     * @param wechat
     * @return
     */
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

    /**
     * 根据id删除出租屋信息
     * @param id
     * @return
     */
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
     * @param minPrice
     * @param maxPrice
     * @return
     */
    @Override
    public List<RentalHouse> getRentalHouseByPrice(int minPrice, int maxPrice) {
        return rentalHouseDao.queryRentalHouseByPrice(minPrice, maxPrice);
    }

    /**
     * 根据位置列出出租屋信息
     * @param location
     * @return
     */
    @Override
    public List<RentalHouse> getRentalHouseByLocation(String location) {
        return rentalHouseDao.queryRentalHouseByLocation(location);
    }

    /**
     * 根据面积范围列出出租屋信息
     * @param minArea
     * @param maxArea
     * @return
     */
    @Override
    public List<RentalHouse> getRentalHouseByArea(int minArea, int maxArea) {
        return rentalHouseDao.queryRentalHouseByArea(minArea, maxArea);
    }

    /**
     * 房客更新出租屋状态：待审核——未出租
     * 取消申请
     * @param id
     * @return
     */
    @Transactional
    @Override
    public boolean cancelRentalHouseState1(int id){
        RentalHouse rentalHouse = new RentalHouse();
        rentalHouse.setId(id);
        rentalHouse.setState("未出租");
        if(id> 0){
            try {
                int effectedNum = rentalHouseDao.cancelRentalHouseState1(rentalHouse);
                if (effectedNum > 0) {
                    return true;
                } else {
                    System.out.println("用户更改出租屋状态失败!");
                    throw new RuntimeException("用户更改出租屋状态失败!");
                }
            } catch (Exception e) {
                System.out.println("用户更改出租屋状态失败:" + e.toString());
                throw new RuntimeException("用户更改出租屋状态失败:" + e.toString());
            }
        }else {
            System.out.println("有信息为空！请填全信息");
            throw new RuntimeException("有信息为空！请填全信息");
        }
    }

    /**
     * 房主更新出租屋状态：待审核——审核失败
     * 拒绝申请
     * @param id
     * @return
     */
    @Transactional
    @Override
    public boolean cancelRentalHouseState2(int id){
        RentalHouse rentalHouse = new RentalHouse();
        rentalHouse.setId(id);
        rentalHouse.setTenantEmail(null);
        rentalHouse.setState("审核失败");
        if(id> 0){
            try {
                int effectedNum = rentalHouseDao.cancelRentalHouseState2(rentalHouse);
                if (effectedNum > 0) {
                    return true;
                } else {
                    System.out.println("房主更改出租屋状态失败!");
                    throw new RuntimeException("房主更改出租屋状态失败!");
                }
            } catch (Exception e) {
                System.out.println("房主更改出租屋状态失败:" + e.toString());
                throw new RuntimeException("房主更改出租屋状态失败:" + e.toString());
            }
        }else {
            System.out.println("有信息为空！请填全信息");
            throw new RuntimeException("有信息为空！请填全信息");
        }
    }

}

