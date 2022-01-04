package com.cythr.greenhouseapi.repositories;

import com.cythr.greenhouseapi.models.GreenhouseData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreenhouseDataRepository extends CrudRepository<GreenhouseData, Long> {
}
