package com.creative.foodwood.dao;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.creative.foodwood.entity.ChefMenuItemEntity;

public interface MenuItemDao extends CrudRepository<ChefMenuItemEntity, String> {
    

    @Query("Select * from menu_item where chefId=?0")
    public List<ChefMenuItemEntity> fetchMenuItemByUsername(String username);
}
