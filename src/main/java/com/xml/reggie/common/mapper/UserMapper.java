package com.xml.reggie.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xml.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
