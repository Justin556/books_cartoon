package com.app.books.mapper;

import com.app.books.dto.ComicQuery;
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

    @Select("<script>  select (SELECT tu.user_name from t_user as tu where id=trl.user_id)  as aliName from t_user_retail_level as trl where parent_id=#{id} </script>")
    List<User> findAllLowerLevel(String id);

    @Select("<script> insert into t_user_retail_level(down_one,user_id) values (#{downOne},#{userId}) </script>")
    int addPromote(UserRetailLevel userRetailLevel);
}
