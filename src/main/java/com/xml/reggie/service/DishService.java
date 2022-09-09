package com.xml.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xml.reggie.dto.DishDto;
import com.xml.reggie.entity.Dish;

public interface DishService extends IService<Dish> {


    public void saveWithFlavor(DishDto dishDto);
}
