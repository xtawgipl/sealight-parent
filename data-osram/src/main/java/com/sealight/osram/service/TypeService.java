package com.osram.osram.service;

import com.osram.osram.entity.TypeBean;
import com.osram.osram.mapper.TypeBeanMapper;
import com.sealight.page.Page;
import com.sealight.page.annotion.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("typeService")
public class TypeService {

    @Resource(name = "typeBeanMapper")
    private TypeBeanMapper typeBeanMapper;

    @Pageable
    public List<TypeBean> findByPage(Page<TypeBean> page){
        return typeBeanMapper.find();
    }

    public TypeBean findByTypeId(Integer typeId){
        return typeBeanMapper.selectByPrimaryKey(typeId);
    }
}