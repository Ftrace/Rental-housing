# Rental-house
2019暑期综合实训，出租屋管理系统

#用户登录，返回用户身份
接口：/login


#用户注册
接口：/register


#删除出租屋信息
接口：/deleteRentalHouseById


#列出所有出租屋的信息
接口：/getRentalHouseList


#根据email列出出租屋的信息
接口：/getRentalHouseByEmail


# 添加出租屋信息
接口：/addRentalHouse


#更新出租屋信息
接口：/updateRentalHouse

#根据价格范围列出出租屋信息
接口：/getRentalHouseByPrice


#根据价格范围列出出租屋信息
接口：/getRentalHouseByPrice1


#根据位置列出出租屋信息
接口：/getRentalHouseByLocation


#根据面积范围列出出租屋信息
接口：/getRentalHouseByArea


#根据Id显示出租屋信息
接口：/getRentalHouseById


#泛式搜索
接口：/getRentalHouseByGeneric


#房客更新出租屋状态：未出租——待审核
接口：/updateRentalHouseState1


#房东更新出租屋状态：待审核——已出租
接口：/updateRentalHouseState2


#房客获取申请过的出租屋信息
接口：/getRentalHouseToTenant

#房东获取被申请过的出租屋信息
接口：/getRentalHouseToLandlord


 #房客取消申请,更新出租屋状态：待审核——未出租
接口：/cancelRentalHouseState1


#房主拒绝申请,更新出租屋状态：待审核——审核失败
接口：/cancelRentalHouseState2