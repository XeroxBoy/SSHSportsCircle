package com.cdut.sx.service;

import com.cdut.sx.dao.Circledao;
import com.cdut.sx.pojo.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CircleService {
    @Autowired
    private Circledao circledao;
    public List<Circle> search(String key) {
        List<Circle> circles = circledao.search(key);
        return circles;
    }
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

    public Circle findCircle(String name) {
        return circledao.findByCircleName(name);
    }
}

