package com.tabletka.controllers;

import com.tabletka.Models.Drug;
import com.tabletka.Models.DrugInCart;
import com.tabletka.repo.DrugInCartRepo;
import com.tabletka.repo.DruggistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;


@Controller
public class MainController {

    @Autowired
    private DruggistRepo druggistRepo;

    @Autowired
    private DrugInCartRepo druggistInCartRepo;

    @GetMapping("/home")
    public String greeting(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }


    @GetMapping("/manufacturers")
    public String who(Model model) {
        model.addAttribute("title", "Производители");

        Iterable<Drug> drg = druggistRepo.findAll();

        String[] av = new String[] {"СЕРВЬЕ РУС ООО", "Обновление Пфк", "Фармстандарт", "Синтез"};

        model.addAttribute("drg", av);
        return "manufacturers";
    }


    @GetMapping("/Thanks")
    public String thanks(Model model) {
        model.addAttribute("title", "Спасибо за покупку!");
        return "Thanks";
    }

    @RequestMapping("/search")
    public String SearchDrug(@RequestParam String search, Model model) {
        model.addAttribute("title", "Результат поиска");
        Iterable<Drug> drg1 = druggistRepo.search(search);

        model.addAttribute("drg", drg1);
        return "search";
    }

    @PostMapping("/reg")
    public String AddToCart(@RequestParam Long id, @RequestParam String name,
                            @RequestParam String description, @RequestParam String manufacturer,
                            @RequestParam String country, @RequestParam int number, Model model) {


        model.addAttribute("title", "Авторизация");

        Iterable<DrugInCart> drg = druggistInCartRepo.findAll();

        if (!drg.iterator().hasNext()){
            model.addAttribute("txt2", "Корзина пуста, сначала добавьте товар в корзину");
            return "Cart";
        }

        model.addAttribute("drg", drg);
        return "reg";
    }



    @GetMapping("/manufacturers/{manufacturer}")
    public String AllDrugsOfManufacturer(@PathVariable(value = "manufacturer") String manufacturer, Model model) {
        model.addAttribute("title", manufacturer);
        Iterable<Drug> drg1 = druggistRepo.search(manufacturer);
        model.addAttribute("drg", drg1);
        return "drug-manufacturer";
    }


}
