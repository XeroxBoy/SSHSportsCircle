package com.cdut.sx.service;

import com.cdut.sx.dao.Reminddao;
import com.cdut.sx.pojo.remind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Service
public class RemindService {
    @Autowired
    private Reminddao reminddao;

    public ArrayList<remind> queryAll(String user) {
        ArrayList<remind> myReminds = (ArrayList<remind>) reminddao.findAll();
        return myReminds;
    }

    public void save(remind remind) {
        reminddao.save(remind);
    }

    public void update(remind remind) {
        reminddao.save(remind);

    }

    public void delete(remind remind) {
        reminddao.delete(remind);
    }
}

