package com.tabletka;

import com.tabletka.Models.Drug;
import com.tabletka.repo.DruggistRepo;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.util.List;



@SpringBootTest
@RunWith(SpringRunner.class)
public class DrugTest {
    @Autowired
    private DruggistRepo drgRep;

    @Test
    public void searchBook() {
        Iterable<Drug> drg1 = drgRep.search("Сальбутамол-Фармстандарт");
        Drug l = new Drug(5L,"Сальбутамол-Фармстандарт","ОАО \"Фармстандарт-Лексредства\"", "Бронхиальная астма и другие заболевания со спастическими состояниями бронхов.", "Российская Федерация", 322,"/img/drugs_imgs/sulbul.png");

        Assertions.assertEquals(1, drg1.spliterator().getExactSizeIfKnown());
    }

}
