package com.flaneur.blog.mapper;

import com.flaneur.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
