package com.rai.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<Entity extends Serializable, ID extends Object> {
	Entity findById(ID id);
	List<Entity> findAll();
	List<Entity> findBySQL(String sql, Object... params);
	
	int insert(Entity entity);
	int delete(Entity entity);
	int delete(ID id);
	int update(Entity entity);
}