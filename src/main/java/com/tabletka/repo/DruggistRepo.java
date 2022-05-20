package com.tabletka.repo;

import java.util.List;

import com.tabletka.Models.Drug;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DruggistRepo extends CrudRepository<Drug, Long>{
    @Query(value = "SELECT c FROM Drug c " +
            "WHERE c.name = :keyword" +
            " OR c.manufacturer = :keyword" +
            " OR c.description = :keyword")
    public Iterable<Drug> search(String keyword);

    @Query(value = "SELECT c FROM Drug c " +
            "WHERE c.name = :keyword")
    public Drug getByName(String keyword);
}

