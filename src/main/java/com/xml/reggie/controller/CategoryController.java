package com.xml.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xml.reggie.common.R;
import com.xml.reggie.entity.Category;
import com.xml.reggie.entity.Employee;
import com.xml.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增套餐分类
     *
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category) {
        log.info("category:{}", category);
        categoryService.save(category);
        return R.success("新增分类成功");
    }


    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {
        Page pageInfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Category::getSort);
        categoryService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long id) {
        log.info("删除分类:{id}", id);
        categoryService.remove(id);
        return R.success("删除成功!");
    }


    /**
     * 修改
     *
     * @param category
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Category category) {
        log.info("修改分类信息:{}", category);
        categoryService.updateById(category);
        return R.success("修改分类信息成功");
    }

    /**
     * 查询分类数据
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> list(Category category) {
        log.info("category:{}", category);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null != (category.getType()), Category::getType, category.getType());
        queryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        return R.success(categoryService.list(queryWrapper));
    }


}
