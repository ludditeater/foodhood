package com.creative.foodhood.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.creative.foodhood.dao.MenuItemDao;
import com.creative.foodhood.entity.ChefMenuItemEntity;
import com.creative.foodhood.model.ChefMenuItem;

@RestController
@RequestMapping("/chefboard")
public class ChefBoardController {
	@Autowired
	private MenuItemDao menuItemDao;

	@RequestMapping(method = RequestMethod.POST, value = "/createMenu")
	public String createMenuItem(@RequestBody ChefMenuItem menuItem) {
		ChefMenuItemEntity menuEntity = new ChefMenuItemEntity();
		BeanUtils.copyProperties(menuItem, menuEntity);
		menuEntity.setMenuItemId(UUID.randomUUID().toString());
		menuItemDao.save(menuEntity);
        return null;
    }

	@RequestMapping(method = RequestMethod.POST, value = "/getMenuByUser")
	public List<ChefMenuItem> retrieveMenuItemsByUser(String username) {
		List<ChefMenuItem> menuItemList = new ArrayList<>();
		List<ChefMenuItemEntity> menuItemEntityList = menuItemDao.fetchMenuItemByUsername(username);
		for(ChefMenuItemEntity menuEntity : menuItemEntityList){
			ChefMenuItem menuItem = new ChefMenuItem();
			BeanUtils.copyProperties(menuEntity, menuItem);
			menuItemList.add(menuItem);
		}
		return menuItemList;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/getAllMenuItems")
	public List<ChefMenuItem> retrieveAllMenuItems(String username) {
		List<ChefMenuItem> menuItemList = new ArrayList<>();
		Iterable<ChefMenuItemEntity> menuItemEntityList = menuItemDao.findAll();
		for(ChefMenuItemEntity menuEntity : menuItemEntityList){
			ChefMenuItem menuItem = new ChefMenuItem();
			BeanUtils.copyProperties(menuEntity, menuItem);
			menuItemList.add(menuItem);
		}
		return menuItemList;
	}

}
