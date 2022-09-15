package com.xml.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xml.reggie.dto.SetmealDto;
import com.xml.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {

    /**
     * 新增套餐同时保存套餐和菜品关联关系
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);

    /**
     * 删除套餐 同时删除和菜品的关联数据
     * @param ids
     */
    public void removeWithDish(List<Long> ids);
}
