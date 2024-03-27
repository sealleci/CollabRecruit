package com.rai.dao;

import com.rai.domain.po.Delivery;
import com.rai.domain.vo.Page;

public interface DeliveryDao extends GenericDao<Delivery, Integer>{
    Delivery findByReIdAndUserId(int reId, int userId);

    int getTotalRows(String sql);
	Page findByPage(int currentPage);
	Page findByPageByUserId(int currentPage,Integer id);
	Page findByPageByEnterId(int currentPage,Integer id);
	Page findByPageByReinfoId(int currentPage,Integer id);
}
