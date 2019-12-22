package com.tedu.dao;


import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tedu.entity.Menu;
import com.tedu.entity.Paging;

@Repository
@Transactional(rollbackFor=Exception.class)
public class MenuDAOImpl extends BaseDAOImpl<Menu> implements MenuDAO<Menu> {

}
