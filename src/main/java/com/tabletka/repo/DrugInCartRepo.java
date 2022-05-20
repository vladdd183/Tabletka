package com.tabletka.repo;

import com.tabletka.Models.Drug;
import com.tabletka.Models.DrugInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DrugInCartRepo extends JpaRepository<DrugInCart, Long> {

}
