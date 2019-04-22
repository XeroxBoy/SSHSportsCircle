package com.cdut.sx.dao;

import com.cdut.sx.pojo.Circle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface Circledao extends JpaRepository<Circle, Integer> {
    @Query(value="select from circle where circle_name like CONCAT('%',:key,'%')",nativeQuery = true)
    List<Circle> search(@Param("key") String key);

    @Query(value = "select u from Circle u where u.circleName = :name")
    List<Circle> findByCircleName(@Param("name") String name);
}

