package com.rai.service;

import com.rai.domain.po.Reinformation;
import com.rai.domain.vo.Page;

import java.util.List;

public interface ReinformationService {
	boolean create(Reinformation reinformation);
	boolean remove(Reinformation reinformation);
	boolean modify(Reinformation reinformation);
	Reinformation findById(Integer id);
	List<Reinformation> findByEnterId(Integer id);
	List<Reinformation> findAll();
	Page selectReinByPage(int currentPage);
	List<Reinformation> fuzzyFind(String keyword);
	Page fuzzySelectByPage(int currentPage,String keyword);
}