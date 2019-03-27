package com.cdut.sx.service;

import com.cdut.sx.dao.Reminddao;
import com.cdut.sx.pojo.Remind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Service
public class RemindService {
    @Autowired
    private Reminddao reminddao;

    public ArrayList<Remind> queryAll(String user) {
        ArrayList<Remind> myReminds = (ArrayList<Remind>) reminddao.findAll();
        return myReminds;
    }

    public void save(Remind remind) {
        reminddao.save(remind);
    }

    public void update(Remind remind) {
        reminddao.save(remind);

    }

    public void delete(Remind remind) {
        reminddao.delete(remind);
    }
}

