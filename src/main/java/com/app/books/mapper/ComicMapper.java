package com.app.books.mapper;

import com.app.books.entity.Comic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface ComicMapper {

    @Select("<script> select * from t_comic </script>")
    List<Comic> findAll();

}
