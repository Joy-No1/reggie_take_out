package com.xml.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xml.reggie.entity.OrderDetail;
import com.xml.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
