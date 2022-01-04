package com.cythr.greenhouseapi.repositories;

import com.cythr.greenhouseapi.models.Greenhouse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreenhouseRepository extends CrudRepository<Greenhouse, Long> {
}
