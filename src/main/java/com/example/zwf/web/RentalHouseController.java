package com.example.zwf.web;


import com.example.zwf.entity.RentalHouse;
import com.example.zwf.service.RentalHouseService;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class RentalHouseController {
    @Autowired
    private RentalHouseService rentalHouseService;

    /**
     * 删除出租屋信息
     * @param id
     * @return boolean
     */
    @RequestMapping(value= "/deleteRentalHouseById", method = RequestMethod.POST)
    private boolean deleteRentalHouse(int id, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println("根据id删除出租屋信息");
        System.out.println("要删除的出租屋的id"+id);
        boolean flag =  rentalHouseService.deleteRentalHouse(id);
//        Map<String, Object> modelMap = new HashMap<String, Object>();
//        modelMap.put("success", rentalHouseService.deleteRentalHouse(id));
        return flag;

    }

    /**
     * 列出所有出租屋的信息
     * @param response
     * @return list
     */
    @RequestMapping(value = "/getRentalHouseList", method = RequestMethod.GET)
    private List<RentalHouse> getRentalHouseList(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
//        Map<String, Object> modelMap = new HashMap<String, Object>();
        System.out.println("列出所有出租屋信息");
        List<RentalHouse> list = rentalHouseService.getRentalHouseList();
        System.out.println("所有出租屋信息：" + list);
//        modelMap.put("message", list);
        return list;
    }

    /**
     * 根据email列出出租屋的信息
     * @param email
     * @param response
     * @return list
     */
    @RequestMapping(value = "/getRentalHouseByEmail", method = RequestMethod.GET)
    private List<RentalHouse> getRentalHouseByEmail(String email, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println("根据email列出出租屋的信息");
        System.out.println("该email为"+email);
//        Map<String, Object> modelMap = new HashMap<>();
        String realemail = email.substring(email.indexOf('=')+1);
        List<RentalHouse> list = rentalHouseService.getRentalHouseByEmail(realemail);
        System.out.println("该"+realemail+"出租屋信息：" + list);
//        modelMap.put("message", list);
        return list;
    }

    /**
     *
     * 添加出租屋信息
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
     * @param response
     * @return boolean
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping(value = "/addRentalHouse", method = RequestMethod.POST)
    public boolean addRentalHouse ( String email, String name, String rname,
                                      String location, int area, int price,
                                      String number, String oriented, String houseType,
                                      String introduction, String wechat,
                                      HttpServletResponse response)
            throws JsonMappingException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
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

        boolean judge = rentalHouseService.addRentalHouse(email,name,rname,location,area,price,number,
                oriented,houseType,introduction,wechat);
//        Map<String, Object> modelMap = new HashMap<String, Object>();
//        modelMap.put("success",judge);
        return  judge;
    }

    /**
     * 更新出租屋信息
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
     * @param response
     * @return boolean
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping(value = "/updateRentalHouse", method = RequestMethod.POST)
    public boolean updateRentalHouse ( int id,String email, String name, String rname,
                                       String location, int area, int price,
                                       String number, String oriented, String houseType,
                                       String introduction, String wechat,
                                       HttpServletResponse response)
            throws JsonMappingException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println("更新出租屋信息");
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

        boolean judge = rentalHouseService.modifyRentalHouse(id,email,name,rname,location,area,price,number,
                oriented,houseType,introduction,wechat);
        return judge;
    }


    /**
     * 根据价格范围列出出租屋信息
     * @param price
     * @param response
     * @return list
     */
    @RequestMapping(value = "/getRentalHouseByPrice", method = RequestMethod.GET)
    public  List<RentalHouse> getRentalHouseByPrice(String price,HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println("根据出租屋的价格查询出租屋信息");
        System.out.println("查询的价格："+price);
        int minPrice, maxPrice;
        String []price1 = price.split("-");
        minPrice=Integer.parseInt(price1[0]);
        maxPrice=Integer.parseInt(price1[1]);
//        Map<String, Object> modelMap = new HashMap<>();
        List<RentalHouse> list = rentalHouseService.getRentalHouseByPrice(minPrice, maxPrice);
        System.out.println("价格在"+minPrice+"--"+maxPrice+"之间的出租屋: ");
        System.out.println(list);
//        modelMap.put("message", list);
        return list;
    }

    /**
     * 根据价格范围列出出租屋信息
     * @param onePrice
     * @param twoPrice
     * @param response
     * @return
     */
    @RequestMapping(value = "/getRentalHouseByPrice1", method = RequestMethod.GET)
    public  List<RentalHouse> getRentalHouseByPrice(String onePrice, String twoPrice,HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println("根据出租屋的价格查询出租屋信息");
        System.out.println("查询的价格："+onePrice+"到"+twoPrice);
        int minprice, maxprice;
        minprice=Integer.parseInt(onePrice);
        maxprice=Integer.parseInt(twoPrice);
//        Map<String, Object> modelMap = new HashMap<>();
        List<RentalHouse> list = rentalHouseService.getRentalHouseByPrice(minprice, maxprice);
        System.out.println("价格在"+onePrice+"-"+twoPrice+"之间的出租屋: ");
        System.out.println(list);
//        modelMap.put("message", list);
        return list;
    }

    /**
     * 根据位置列出出租屋信息
     * @param location
     * @param response
     * @return list
     */
    @RequestMapping(value = "/getRentalHouseByLocation", method = RequestMethod.GET)
    public List<RentalHouse> getRentalHouseByLocation(String location,HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println("根据出租屋的位置查询出租屋信息");
        System.out.println("查询的位置："+location);
//        Map<String, Object> modelMap = new HashMap<>();
        List<RentalHouse> list = rentalHouseService.getRentalHouseByLocation(location);
        System.out.println("位置在：" + location + "的出租屋: ");
        System.out.println(list);
//        modelMap.put("message", list);
        return list;
    }

    /**
     * 根据面积范围列出出租屋信息
     * @param area
     * @param response
     * @return list
     */
    @RequestMapping(value = "/getRentalHouseByArea", method = RequestMethod.GET)
    public List<RentalHouse>  getRentalHouseByArea(String area,HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println("根据出租屋面积查询出租屋信息");
        System.out.println("查询的面积："+area);
        int minArea,maxArea;
        String []area1 = area.split("-");
        minArea=Integer.parseInt(area1[0]);
        maxArea=Integer.parseInt(area1[1]);
        Map<String, Object> modelMap = new HashMap<>();
        List<RentalHouse> list = rentalHouseService.getRentalHouseByArea(minArea, maxArea);
        System.out.println("面积在"+minArea+"--"+maxArea+"之间的出租屋: ");
        System.out.println(list);
//        modelMap.put("message", list);
        return list;
    }




    /**
     * 根据Id显示出租屋信息
     * @param messageId
     * @param response
     * @return RentalHouse
     */
    @RequestMapping(value = "/getRentalHouseById", method = RequestMethod.GET)
    private RentalHouse getRentalHouseById(String messageId, HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println("根据出租屋id查询出租屋信息");
        System.out.println("出租屋id："+messageId);
//        Map<String, Object> modelMap = new HashMap<>();
        System.out.println("接收到的前端"+messageId);
        String realid = messageId.substring(messageId.indexOf('=')+1);
        int id1=  Integer.parseInt(realid);
        System.out.println("变化后的"+id1);
        RentalHouse rentalHouse= rentalHouseService.getRentalHouseById(id1);
        System.out.println("出租屋ID: " + id1);
//        modelMap.put("message", rentalHouse);
        return rentalHouse;
    }

    @RequestMapping(value = "/getRentalHouseByGeneric", method = RequestMethod.GET)
    public List<RentalHouse> getRentalHouseByGeneric(String generic,HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println("泛式搜索");
        System.out.println("搜索的字符串："+generic);
        boolean judge;
        List<RentalHouse> list;
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(generic);
        if (!isNum.matches()) {
            judge = false;
            System.out.println("不是数字");
        } else {
            judge = true;
            System.out.println("是数字");
        }

        if (judge) {
            int a = Integer.parseInt(generic);
            list = rentalHouseService.getRentalHouseByOneArea(a);
            list.addAll(rentalHouseService.getRentalHouseByOnePrice(a));
            return list;
        } else {
            list = rentalHouseService.getRentalHouseByLocation(generic);
            return list;
        }
    }


    /**
     * 房客更新出租屋状态：未出租——待审核
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateRentalHouseState1", method = RequestMethod.POST)
    private boolean updateRentalHouseState1(String tenantEmail,String id,HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println("房客" + tenantEmail + "申请Id=" + id + "的出租屋");
        boolean judge=rentalHouseService.updateRentalHouseState1(tenantEmail,Integer.parseInt(id));
        return judge;
    }

    /**
     * 房东更新出租屋状态：待审核——已出租
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateRentalHouseState2", method = RequestMethod.POST)
    private boolean updateRentalHouseState2(String id,HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println("房东同意出租Id="+id+"出租屋");
        boolean judge=rentalHouseService.updateRentalHouseState2(Integer.parseInt(id));
        return judge;
    }fghjk

    /**
     *      * 房客获取申请过的出租屋信息
     *      * @param email
     *      * @param response
     *      * @return
     */
    @RequestMapping(value = "/getRentalHouseToTenant", method = RequestMethod.GET)
    private List<RentalHouse> getRentalHouseToTenant(String tenantEmail,HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println("获取房客" + tenantEmail + "申请过的出租屋信息");
        List<RentalHouse> list = rentalHouseService.getRentalHouseToTenant(tenantEmail);
        System.out.println(list);
        return list;
    }

    /**
     * 房东获取被申请过的出租屋信息
     * @param email
     * @param response
     * @return
     */
    @RequestMapping(value = "/getRentalHouseToLandlord", method = RequestMethod.GET)
    private List<RentalHouse> getRentalHouseToLandlord(String email,HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        List<RentalHouse> list = rentalHouseService.getRentalHouseToLandlord(email);
        System.out.println("获取房客" + email + "申请过的出租屋信息");
        System.out.println(list);
        return list;
    }


}




