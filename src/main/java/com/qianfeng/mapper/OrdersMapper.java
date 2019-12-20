package com.qianfeng.mapper;

import com.qianfeng.entry.DistributeOrders;
import com.qianfeng.entry.LastOrders;
import com.qianfeng.entry.OrderMapEntry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 订单业务接口
 */
@Mapper
public interface OrdersMapper {

    @Select("SELECT\n" +
            "SUM(dob.in_order_30_cnt) in_order_30_cnt,\n" +
            "SUM(dob.in_order_60_cnt) in_order_60_cnt,\n" +
            "SUM(dob.in_order_90_cnt) in_order_90_cnt,\n" +
            "SUM(dob.no_order_30_cnt) no_order_30_cnt,\n" +
            "SUM(dob.no_order_60_cnt) no_order_60_cnt,\n" +
            "SUM(dob.no_order_90_cnt) no_order_90_cnt\n" +
            "FROM qfbap_report.dm_order_basic dob\n" +
            "WHERE dob.date_dt = #{dt} \n" +
            "GROUP BY dob.date_dt;")
    public List<LastOrders> listLastOrders(String dt);

    @Select("SELECT\n" +
            "SUM(od.home_order) home_orders,\n" +
            "SUM(od.company_order) company_orders,\n" +
            "SUM(od.school_orders) school_orders,\n" +
            "SUM(od.hour6_12_order)+SUM(od.hour13_15_order) day_orders,\n" +
            "SUM(od.hour0_5_order) night_orders\n" +
            "FROM qfbap_report.order_distribute od\n" +
            "WHERE od.dt = #{dt} \n" +
            "GROUP BY od.dt;")
    public DistributeOrders distributeOrders(String dt);

    @Select("SELECT\n" +
            "od.province province,\n" +
            "SUM(od.home_order)+SUM(od.company_order)+SUM(od.school_orders) total_orders\n" +
            "FROM qfbap_report.order_distribute od\n" +
            "WHERE od.dt = #{dt} \n" +
            "GROUP BY od.dt,od.province;")
    public List<OrderMapEntry> mapOrders(String dt);
}
