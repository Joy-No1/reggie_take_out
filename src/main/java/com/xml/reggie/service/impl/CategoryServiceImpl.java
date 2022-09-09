package com.xml.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xml.reggie.common.CustomException;
import com.xml.reggie.entity.Category;
import com.xml.reggie.entity.Dish;
import com.xml.reggie.entity.Setmeal;
import com.xml.reggie.mapper.CategoryMapper;
import com.xml.reggie.service.CategoryService;
import com.xml.reggie.service.DishService;
import com.xml.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;
    /**
     * 根据id删除分类
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper=new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count = dishService.count(dishLambdaQueryWrapper);
        if (count>0){
          throw   new CustomException("已关联菜品分类,不能删除");
        }
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper=new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count1 = setmealService.count(setmealLambdaQueryWrapper);
        if (count1>0){
          throw   new CustomException("已关联套餐分类,不能删除");
        }

        super.removeById(id);
    }
}
