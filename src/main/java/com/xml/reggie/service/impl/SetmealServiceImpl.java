package com.xml.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xml.reggie.common.CustomException;
import com.xml.reggie.dto.SetmealDto;
import com.xml.reggie.entity.Setmeal;
import com.xml.reggie.entity.SetmealDish;
import com.xml.reggie.common.mapper.SetmealMapper;
import com.xml.reggie.service.SetmealDishService;
import com.xml.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

  /*  @Autowired
    private SetmealService setmealService;*/

    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * 新增套餐同时保存套餐和菜品关联关系
     *
     * @param setmealDto
     */
    @Transactional
    @Override
    public void saveWithDish(SetmealDto setmealDto) {

        //保存套餐基本信息
        this.save(setmealDto);

        //保存套餐和菜品的管理信息
        List<SetmealDish> setmealDishList = setmealDto.getSetmealDishes();
        setmealDishList.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishList);
    }

    /**
     * 删除套餐 同时删除和菜品的关联数据
     *
     * @param ids
     */
    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        queryWrapper.eq(Setmeal::getStatus, 1);
        int count = this.count(queryWrapper);
        if (count > 0) {
            throw new CustomException("套餐正在售卖中,不能删除");
        }

        this.removeByIds(ids);

        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId, ids);
        setmealDishService.remove(lambdaQueryWrapper);
    }
}
