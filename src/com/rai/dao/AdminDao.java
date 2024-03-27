package com.rai.dao;

import com.rai.domain.po.Admin;

public interface AdminDao extends GenericDao<Admin, Integer>{
	Admin findByEmail(String email);
}
