package com.qianfeng.mapper;

import com.qianfeng.entry.KVEntry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface KVMapper {

    @Select("SELECT\n" +
            "od.city k,\n" +
            "od.home_order v\n" +
            "FROM order_distribute od;")
    public List<KVEntry> listkv();
}
