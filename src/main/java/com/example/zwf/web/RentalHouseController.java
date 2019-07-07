package com.example.zwf.web;


import com.example.zwf.entity.RentalHouse;
import com.example.zwf.service.RentalHouseService;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RentalHouseController {
    @Autowired
    private RentalHouseService rentalHouseService;

    /**
     * 删除出租屋信息
     * @param id
     * @return
     */
    @RequestMapping(value= "/deleteRentalHouse", method = RequestMethod.POST)
    private Map<String, Object> deleteRentalHouse(int id) {
        System.out.println("要删除的出租屋的id"+id);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", rentalHouseService.deleteRentalHouse(id));
        return modelMap;

    }

    /**
     * 列出所有出租屋的信息
     */
    @RequestMapping(value = "/getRentalHouseList", method = RequestMethod.GET)
    private Map<String, Object> getRentalHouseList(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> modelMap = new HashMap<String, Object>();
        List<RentalHouse> list = rentalHouseService.getRentalHouseList();
        System.out.println("所有出租屋信息：" + list);
        modelMap.put("message", list);
        return modelMap;
    }

    /**
     * 根据email列出出租屋的信息
     */
    @RequestMapping(value = "/getRentalHouseByEmail", method = RequestMethod.GET)
    private Map<String, Object> getRentalHouseByEmail(String email,HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> modelMap = new HashMap<>();
        List<RentalHouse> list = rentalHouseService.getRentalHouseByEmail(email);
        System.out.println("该"+email+"出租屋信息：" + list);
        modelMap.put("message", list);
        return modelMap;
    }
    /**
     * 添加出租屋信息
     */
    @RequestMapping(value = "/addRentalHouse", method = RequestMethod.POST)
    public Map<String, Object> addRentalHouse ( String email, String name, String rname,
                                      String location, int area, int price,
                                      String number, String oriented, String houseType,
                                      String introduction, String wechat,
                                      HttpServletResponse response)
            throws JsonMappingException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        boolean judge = rentalHouseService.addRentalHouse(email,name,rname,location,area,price,number,
                oriented,houseType,introduction,wechat);
        System.out.println("添加出租屋信息");
        System.out.println("email: " + email);
        System.out.println("name: " + name);
        System.out.println("rname: " + rname);
        System.out.println("location: " + location);
        System.out.println("area: " + area);
        System.out.println("price: " + price);
        System.out.println("number: " + number);
        System.out.println("oriented: " + oriented);
        System.out.println("houseType: " + houseType);
        System.out.println("introduction: " + introduction);
        System.out.println("wechat: " + wechat);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success",judge);
        return  modelMap;
    }

    /**
     * 更新出租屋信息
     */
    @RequestMapping(value = "/updateRentalHouse", method = RequestMethod.POST)
    public Map<String, Object> updateRentalHouse ( int id,String email, String name, String rname,
                                       String location, int area, int price,
                                       String number, String oriented, String houseType,
                                       String introduction, String wechat,
                                       HttpServletResponse response)
            throws JsonMappingException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        boolean judge = rentalHouseService.modifyRentalHouse(id,email,name,rname,location,area,price,number,
                oriented,houseType,introduction,wechat);
        System.out.println("修改出租屋信息");
        System.out.println("id: " + id);
        System.out.println("email: " + email);
        System.out.println("name: " + name);
        System.out.println("rname: " + rname);
        System.out.println("location: " + location);
        System.out.println("area: " + area);
        System.out.println("price: " + price);
        System.out.println("number: " + number);
        System.out.println("oriented: " + oriented);
        System.out.println("houseType: " + houseType);
        System.out.println("introduction: " + introduction);
        System.out.println("wechat: " + wechat);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        RentalHouse rentalHouse = new RentalHouse();
        rentalHouse.setId(id);
        rentalHouse.setEmail(email);
        rentalHouse.setRname(rname);
        rentalHouse.setName(name);
        rentalHouse.setLocation(location);
        rentalHouse.setArea(area);
        rentalHouse.setPrice(price);
        rentalHouse.setNumber(number);
        rentalHouse.setOriented(oriented);
        rentalHouse.setHouseType(houseType);
        rentalHouse.setIntroduction(introduction);
        rentalHouse.setWechat(wechat);
        modelMap.put("success",judge);
        modelMap.put("出租屋信息",rentalHouse);
        return modelMap;
    }


    /**
     * 根据价格范围列出出租屋信息
     */
    @RequestMapping(value = "/getRentalHouseByPrice", method = RequestMethod.GET)
    public Map<String, Object> getRentalHouseByPrice(int minPrice, int maxPrice,HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> modelMap = new HashMap<>();
        List<RentalHouse> list = rentalHouseService.getRentalHouseByPrice(minPrice, maxPrice);
        System.out.println("价格在"+minPrice+"--"+maxPrice+"之间的出租屋: ");
        System.out.println(list);
        modelMap.put("message", list);
        return modelMap;
    }

    /**
     * 根据位置列出出租屋信息
     */
    @RequestMapping(value = "/getRentalHouseByLocation", method = RequestMethod.GET)
    public Map<String, Object> getRentalHouseByLocation(String location,HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> modelMap = new HashMap<>();
        List<RentalHouse> list = rentalHouseService.getRentalHouseByLocation(location);
        System.out.println("位置在：" + location + "的出租屋: ");
        System.out.println(list);
        modelMap.put("message", list);
        return modelMap;
    }

    /**
     * 根据面积范围列出出租屋信息
     */
    @RequestMapping(value = "/getRentalHouseByArea", method = RequestMethod.GET)
    public Map<String, Object> getRentalHouseByArea(int minArea, int maxArea,HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> modelMap = new HashMap<>();
        List<RentalHouse> list = rentalHouseService.getRentalHouseByArea(minArea, maxArea);
        System.out.println("面积在"+minArea+"--"+maxArea+"之间的出租屋: ");
        System.out.println(list);
        modelMap.put("message", list);
        return modelMap;
    }

}




