package com.htsc.dao;

import com.htsc.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/5/12.
 */

@Repository
public interface UserDao extends CrudRepository<UserEntity,Long>{

    public UserEntity findByUsernameAndPassword(String username,String password);
    public UserEntity findByUsername(String username);
}
