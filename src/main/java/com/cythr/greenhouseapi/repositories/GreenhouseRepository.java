package com.cythr.greenhouseapi.repositories;

import com.cythr.greenhouseapi.models.Greenhouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * CrudRepository to Greenhouse Table
 */
@Repository
public interface GreenhouseRepository extends CrudRepository<Greenhouse, Long> {

    /**
     * @param addr greenhouse address
     * @return Greenhouse found by address
     */
    Greenhouse findByAddr(String addr);

    /**
     * Delete by greenhouse address
     * @param addr greenhouse address
     */
    @Transactional
    void deleteByAddr(String addr);
}
