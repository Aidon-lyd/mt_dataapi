package com.qianfeng.controller;

import com.qianfeng.entry.DistributeOrders;
import com.qianfeng.entry.LastOrders;
import com.qianfeng.entry.OrderMapEntry;
import com.qianfeng.mapper.OrdersMapper;
import com.qianfeng.util.ResMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单业务的控制器
 */
@RestController
public class OrdersController {

    @Autowired
    OrdersMapper ordersMapper;

    /**
     *
     * @return
     * {
     *     "code":200,
     *     "msg":"请求成功",
     *     "data":
     * }
     */
    @RequestMapping(value = "/lastOrders/{dt}",method = RequestMethod.GET)
    public ResMsg listLastOrders(@PathVariable("dt") String dt){
        List<Integer> res = null;
        ResMsg msg = null;
        try {
            List<LastOrders> los = ordersMapper.listLastOrders(dt);
            res = new ArrayList<Integer>();
            for (LastOrders lo: los) {
                res.add(Integer.parseInt(lo.getIn_order_30_cnt()));
                res.add(Integer.parseInt(lo.getIn_order_60_cnt()));
                res.add(Integer.parseInt(lo.getIn_order_90_cnt()));
                res.add(Integer.parseInt(lo.getNo_order_30_cnt()));
                res.add(Integer.parseInt(lo.getNo_order_60_cnt()));
                res.add(Integer.parseInt(lo.getNo_order_90_cnt()));
            }
            //成功后
            msg = ResMsg.success();
            msg.setData(res);
        } catch (NumberFormatException e) {
            msg = ResMsg.fail();
            msg.setData("[]");
            e.printStackTrace();
        }
        return msg;
    }


    /**
     *
     * @return
     * {
     *     "code":200,
     *     "msg":"请求成功",
     *     "data":[{},{},{}]
     * }
     */
    @RequestMapping(value = "/distOrders/{dt}",method = RequestMethod.GET)
    public ResMsg distributeOrders(@PathVariable("dt") String dt){
        List<Map<String,String>> res = new ArrayList<>();
        ResMsg msg = null;
        try {
            DistributeOrders dio = ordersMapper.distributeOrders(dt);
            if(dio != null){
                //封装数据
                Map<String,String> m1 = new HashMap<>();
                m1.put("name","家里下单");
                m1.put("value",dio.getHome_orders());
                res.add(m1);

                Map<String,String> m2 = new HashMap<>();
                m2.put("name","公司下单");
                m2.put("value",dio.getCompany_orders());
                res.add(m2);

                Map<String,String> m3 = new HashMap<>();
                m3.put("name","学校下单");
                m3.put("value",dio.getSchool_orders());
                res.add(m3);

                Map<String,String> m4 = new HashMap<>();
                m4.put("name","白天下单");
                m4.put("value",dio.getDay_orders());
                res.add(m4);

                Map<String,String> m5 = new HashMap<>();
                m5.put("name","晚上下单");
                m5.put("value",dio.getNight_orders());
                res.add(m5);
            }

            //成功后
            msg = ResMsg.success();
            msg.setData(res);
        } catch (NumberFormatException e) {
            msg = ResMsg.fail();
            msg.setData("[]");
            e.printStackTrace();
        }
        return msg;
    }


    @RequestMapping(value = "/mapOrders/{dt}",method = RequestMethod.GET)
    public ResMsg mapOrders(@PathVariable("dt") String dt){
        List<Map<String,String>> res1 = null;
        ResMsg msg = null;
        try {
            List<OrderMapEntry> los = ordersMapper.mapOrders(dt);
            /*for (OrderMapEntry om:los) {
                Map<String,String> m1 = new HashMap<>();
                String pro = om.getProvince();
                if(pro.contains("省")){
                    pro = om.getProvince().replace("省","");
                } else if (pro.contains("市")) {
                    pro = om.getProvince().replace("市","");
                } else if (pro.contains("内蒙古")) {
                    pro = om.getProvince().substring(0,3);
                } else if (pro.contains("自治区")) {
                    pro = om.getProvince().substring(0,2);
                } else if (pro.contains("特别行政区")) {
                    pro = om.getProvince().substring(0,2);
                }
                m1.put("name",pro);
                m1.put("value",om.getTotal_orders());
                res.add(m1);
            }*/
            res1 = initMap();
            for (Map<String,String> m:res1) {

                for (OrderMapEntry om:los) {
                    Map<String, String> m1 = new HashMap<>();
                    String pro = om.getProvince();
                    if (pro.contains("省")) {
                        pro = om.getProvince().replace("省", "");
                    } else if (pro.contains("市")) {
                        pro = om.getProvince().replace("市", "");
                    } else if (pro.contains("内蒙古")) {
                        pro = om.getProvince().substring(0, 3);
                    } else if (pro.contains("自治区")) {
                        pro = om.getProvince().substring(0, 2);
                    } else if (pro.contains("特别行政区")) {
                        pro = om.getProvince().substring(0, 2);
                    }
                    //判断
                    if(pro.equals(m.get("name"))){
                        m.put("value",om.getTotal_orders());
                        break;
                    }
                }

            }
            //成功后
            msg = ResMsg.success();
            msg.setData(res1);
        } catch (NumberFormatException e) {
            msg = ResMsg.fail();
            msg.setData("[]");
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * 初始化map
     * @return
     */
    public static List<Map<String,String>> initMap(){
        List<Map<String,String>> maps = new ArrayList<>();
        String[] arr = {"北京","天津","上海","重庆","河北","山西","辽宁","吉林","黑龙江",
                "江苏","浙江","安徽","福建","江西","山东","河南","湖北","湖南","广东",
                "海南","四川","贵州","云南","陕西","甘肃","青海","台湾","内蒙古","广西",
                "西藏","宁夏","新疆","香港","澳门"};
        for (int i=0;i < 34;i++){
            Map<String,String> map = new HashMap<>();
            map.put("name",arr[i]);
            map.put("value","0");
            maps.add(map);
        }
        return maps;
    }

}
