package com.tabletka.controllers;

import com.tabletka.Models.Drug;
import com.tabletka.Models.DrugInCart;
import com.tabletka.repo.DrugInCartRepo;
import com.tabletka.repo.DruggistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class SpawnDrugController {

    @Autowired
    private DruggistRepo  DruggistRepo;


    @GetMapping("/drugs")
    public String SpawnDrug(Model model) {
        model.addAttribute("title", "Список лекарств");
        Iterable<Drug> kit = DruggistRepo.findAll();
        model.addAttribute("drg", kit);
        return "AllDrugs";
    }

    @GetMapping("/drugs/{id}")
    public String DrugDetails(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("title", "Подробнее");
        Optional<Drug> drg = DruggistRepo.findById(id);
        ArrayList<Drug> res = new ArrayList<>();
        drg.ifPresent(res::add);
        model.addAttribute("drg", res);
        return "drug-details";
    }

}
