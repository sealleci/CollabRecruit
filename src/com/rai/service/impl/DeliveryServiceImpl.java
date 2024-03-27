package com.rai.service.impl;

import com.rai.dao.DeliveryDao;
import com.rai.dao.impl.DeliveryDaoImpl;
import com.rai.domain.po.Delivery;
import com.rai.domain.vo.Page;
import com.rai.service.DeliveryService;

import java.util.List;

public class DeliveryServiceImpl implements DeliveryService {
    private DeliveryDao deliveryDao = new DeliveryDaoImpl();

    @Override
    public boolean create(Delivery delivery) {
        boolean result = false;
        int res = deliveryDao.insert(delivery);
        if (res == 1) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean remove(Delivery delivery) {
        boolean result = false;
        int res = deliveryDao.delete(delivery);
        if (res == 1) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean modify(Delivery delivery) {
        boolean result = false;
        int res = deliveryDao.update(delivery);
        if (res == 1) {
            result = true;
        }
        return result;
    }

    @Override
    public Delivery findById(Integer id) {
        return deliveryDao.findById(id);
    }

    @Override
    public List<Delivery> findAll() {
        return deliveryDao.findAll();
    }

    @Override
    public Delivery findByReIdAndUserId(int reid, int userid) {
        return deliveryDao.findByReIdAndUserId(reid, userid);
    }

    @Override
    public Page selectReinByPage(int currentPage) {
        return deliveryDao.findByPage(currentPage);
    }

    @Override
    public Page selectReinByPageByUserId(int currentPage, Integer id) {
        return deliveryDao.findByPageByUserId(currentPage, id);
    }

    @Override
    public Page selectReinByPageByEnterId(int currentPage, Integer id) {
        return deliveryDao.findByPageByEnterId(currentPage, id);
    }

    @Override
    public Page selectReinByPageByReinfoId(int currentPage, Integer id) {
        return deliveryDao.findByPageByReinfoId(currentPage, id);
    }
}