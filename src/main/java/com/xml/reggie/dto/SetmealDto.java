package com.xml.reggie.dto;

import com.xml.reggie.entity.Setmeal;
import com.xml.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
