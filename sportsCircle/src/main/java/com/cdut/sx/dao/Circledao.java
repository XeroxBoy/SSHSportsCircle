package com.cdut.sx.dao;

import com.cdut.sx.pojo.Circle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Circledao extends JpaRepository<Circle, Integer> {
    @Query(value="select from circle where circle_name like CONCAT('%',:key,'%')",nativeQuery = true)
    public List<Circle> search(@Param("key") String key);

    public Circle findByCircleName(String name);
}

