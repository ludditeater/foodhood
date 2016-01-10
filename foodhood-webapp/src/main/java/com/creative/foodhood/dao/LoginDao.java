package com.creative.foodhood.dao;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.creative.foodhood.entity.UserEntity;

public interface LoginDao extends CrudRepository<UserEntity, String> {
    
    @Query("Select * from user where username=?0 and password=?1")
    public UserEntity fetchByUsernamePassword(String username, String password);
    

    @Query("Select * from user where username=?0")
    public UserEntity fetchByUsername(String username);
}
