package com.app.books.mapper;

import com.app.books.entity.RetailStore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RetailStoreMapper {
    @Select("SELECT divided, \n" +
            "create_time as createTime, \n" +
            "level_one as levelOne, \n" +
            "level_one_scale as levelOneScale, \n" +
            "level_three as levelThree, \n" +
            "level_three_scale as levelThreeScale, \n" +
            "level_two as levelTwo, \n" +
            "level_two_scale as levelTwoScale\n" +
            "FROM t_retail_store")
    RetailStore getRetailStore();
}
