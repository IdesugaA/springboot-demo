package com.demo.freemarkerdemo.mapper;

import com.demo.freemarkerdemo.domain.Personnel;
import com.demo.freemarkerdemo.domain.PersonnelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PersonnelMapper {
    long countByExample(PersonnelExample example);

    int deleteByExample(PersonnelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Personnel record);

    int insertSelective(Personnel record);

    List<Personnel> selectByExample(PersonnelExample example);

    Personnel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Personnel record, @Param("example") PersonnelExample example);

    int updateByExample(@Param("record") Personnel record, @Param("example") PersonnelExample example);

    int updateByPrimaryKeySelective(Personnel record);

    int updateByPrimaryKey(Personnel record);
}