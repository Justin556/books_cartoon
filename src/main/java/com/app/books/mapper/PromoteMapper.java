package com.app.books.mapper;

import com.app.books.entity.Comic;
import com.app.books.entity.User;
import com.app.books.entity.UserRetailLevel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface PromoteMapper {

    @Select("<script>  SELECT\n" +
            "tu.user_name as aliName,trl.user_id as id,tu.is_vip as isVip,tu.create_time as createTime,\n" +
            "(SELECT sum(tucl.commission) FROM t_user_cent_log tucl WHERE tucl.user_id=#{userId} and tucl.out_user_id=trl.user_id) \n" +
            "as tiCommission\n" +
            "FROM\n" +
            "\tt_user_retail_level AS trl\n" +
            "LEFT JOIN t_user tu ON \ttu.id = trl.user_id\n" +
            "WHERE\n" +
            "\tparent_id = #{id}</script>")
    List<User> findAllLowerLevel(String id,String userId);

    @Select("<script> insert into t_user_retail_level(down_one,user_id) values (#{downOne},#{userId}) </script>")
    int addPromote(UserRetailLevel userRetailLevel);
}
