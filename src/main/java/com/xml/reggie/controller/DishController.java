package com.xml.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xml.reggie.common.R;
import com.xml.reggie.dto.DishDto;
import com.xml.reggie.entity.Category;
import com.xml.reggie.entity.Dish;
import com.xml.reggie.service.CategoryService;
import com.xml.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {


    @Autowired
    private DishService dishService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R<String> save(@RequestBody DishDto dish) {
        log.info("dish:{}", dish);
        dishService.save(dish);
        return R.success("添加成功");
    }


    @GetMapping("/page")
    public R<Page> page(Dish dish, int page, int pageSize) {
        log.info("page:{}  pageSize:{} dish:{} ", page, pageSize, dish);
        Page<Dish> pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(dish.getName()), Dish::getName, dish.getName());
        queryWrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo, queryWrapper);

        Page<DishDto> resPage = new Page<>(page, pageSize);
        BeanUtils.copyProperties(pageInfo, resPage, "records");
        resPage.setRecords(pageInfo.getRecords().stream().map(item -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Category category = categoryService.getById(item.getCategoryId());
            if (category != null) {
                dishDto.setCategoryName(category.getName());
            }
            return dishDto;
        }).collect(Collectors.toList()));
        return R.success(resPage);
    }


}
