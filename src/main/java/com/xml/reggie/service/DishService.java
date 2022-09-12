package com.xml.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xml.reggie.dto.DishDto;
import com.xml.reggie.entity.Dish;
import org.springframework.web.bind.annotation.RequestBody;

public interface DishService extends IService<Dish> {

    public void saveWithFlavor(DishDto dishDto);

    /**
     * 根据id查询菜品信息和口味信息
     * @param id
     * @return
     */
    public DishDto getByIdWithFlavor(Long id);


    public void updateWithFlavor(DishDto dishDto);
}
