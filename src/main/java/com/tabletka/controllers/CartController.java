package com.tabletka.controllers;

import com.tabletka.Models.Drug;
import com.tabletka.Models.DrugInCart;
import com.tabletka.Models.User;
import com.tabletka.repo.DrugInCartRepo;
import com.tabletka.repo.DruggistRepo;
import com.tabletka.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.stream.Collectors;


import java.util.ArrayList;
@Controller
public class CartController {
    @Autowired
    private UserRepo UserRepo;
    @Autowired
    private DrugInCartRepo druggistInCartRepo;
    @Autowired
    private DruggistRepo druggistRepo;

    @GetMapping("/check")
    public String CheckingOrder(Model model) {
        model.addAttribute("title", "Проверка заказа");

        return "check";
    }


    @PostMapping("/check")
    public String CheckGet(@RequestParam String name, @RequestParam String phone, Model model) {
        model.addAttribute("title", "Проверка заказа");

        User usrs = UserRepo.search(name, phone);

        if (usrs == null){
            model.addAttribute("txt1", "Нет такого заказа");
            return "check";
        }

        model.addAttribute("txt1", "Товары в заказе");

        String ans = usrs.getMyKit();

        ArrayList<Drug> myList = new ArrayList<Drug>();

        for (String drg_str : ans.split(",")) {
              Drug drg1 = druggistRepo.getByName(drg_str.trim());
              myList.add(drg1);
         }
        model.addAttribute("drg", myList);

        return "check";

        }

    @GetMapping("/Cart")
    public String LookingInCart(Model model) {
        model.addAttribute("title", "Корзина");
        Iterable<DrugInCart> drg = druggistInCartRepo.findAll();
        model.addAttribute("txt2", "Корзина");
        model.addAttribute("drg", drg);
        return "Cart";
    }

    @PostMapping("/Cart")
    public String AddToCart(@RequestParam Long id, @RequestParam String name,
                            @RequestParam String description, @RequestParam String manufacturer,
                            @RequestParam String country, @RequestParam int number, @RequestParam String image, Model model) {

        model.addAttribute("title", "Корзина");
        if(id == -1){
            druggistInCartRepo.deleteAll();
            Iterable<DrugInCart> drg = druggistInCartRepo.findAll();
            model.addAttribute("txt2", "Корзина");
            model.addAttribute("drg", drg);
            return "Cart";
            }
        else{
            DrugInCart drug = new DrugInCart(id, name, manufacturer, description, country, number, image);
            druggistInCartRepo.save(drug);

            return "redirect:drugs";

        }

    }


    @PostMapping("/Thanks")
    public String addUser(@RequestParam String name,
                          @RequestParam String phone, Model model){
        model.addAttribute("title", "Спасибо за покупку!");
        String drugs = druggistInCartRepo.findAll().stream().map(DrugInCart::getName)
                .collect(Collectors.joining(", "));

        User user1 = new User(name, phone, drugs);

        UserRepo.save(user1);
        druggistInCartRepo.deleteAll();
        return "Thanks";
    }




}
