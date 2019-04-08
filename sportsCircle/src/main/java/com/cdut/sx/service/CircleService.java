package com.cdut.sx.service;

import com.cdut.sx.dao.Circledao;
import com.cdut.sx.pojo.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CircleService {
    @Autowired
    private Circledao circledao;

    public void save(Circle message) {
        // TODO Auto-generated method stub
        circledao.save(message);
    }

    public void update(Circle message) {
        // TODO Auto-generated method stub
        circledao.save(message);

    }


    public void delete(Circle message) {
        // TODO Auto-generated method stub
        circledao.delete(message);
    }
}

