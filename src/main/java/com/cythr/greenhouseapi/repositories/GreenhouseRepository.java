package com.cythr.greenhouseapi.repositories;

import com.cythr.greenhouseapi.models.Greenhouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GreenhouseRepository extends CrudRepository<Greenhouse, Long> {

    Greenhouse findByAddr(@Param("addr") String addr);

    @Transactional
    void deleteByAddr(@Param("addr") String addr);
}
