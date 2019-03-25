package com.cdut.sx.dao;

import com.cdut.sx.pojo.remind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface reminddao extends JpaRepository<remind, Integer> {

}

 