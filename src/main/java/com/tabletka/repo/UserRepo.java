package com.tabletka.repo;

import com.tabletka.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends CrudRepository<User, Long> {
    @Query(value = "SELECT c FROM User c " +
            "WHERE c.name = :name" +
            " AND c.phone = :phone")
    public User search(String name, String phone);
}
