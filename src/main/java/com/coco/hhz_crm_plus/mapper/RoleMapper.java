package com.coco.hhz_crm_plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.coco.hhz_crm_plus.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper extends BaseMapper<Role> {
//    int deleteByPrimaryKey(Integer roleId);
//
IPage<Role> selectList(Page<Role> page);
//
//    int insertSelective(Role record);
//
//    Role selectByPrimaryKey(Integer roleId);
//
//    int updateByPrimaryKeySelective(Role record);
//
//    int updateByPrimaryKey(Role record);

}
