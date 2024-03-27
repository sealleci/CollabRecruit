package com.rai.service;

import java.util.List;

import com.rai.domain.po.Delivery;
import com.rai.domain.vo.Page;

public interface DeliveryService {
	boolean create(Delivery delivery);
	boolean remove(Delivery delivery);
	boolean modify(Delivery delivery);
	Delivery findById(Integer id);
	List<Delivery> findAll();

    Delivery findByReIdAndUserId(int reid, int userid);

    Page selectReinByPage(int currentPage);
	Page selectReinByPageByUserId(int currentPage,Integer id);
	Page selectReinByPageByEnterId(int currentPage,Integer id);
	Page selectReinByPageByReinfoId(int currentPage,Integer id);
}
