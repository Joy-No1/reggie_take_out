package com.xml.reggie.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xml.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
