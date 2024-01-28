package ru.mts.domain.pet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Cat extends Pet {
    public Cat(String breed, String name, BigDecimal cost, String character, LocalDate birthDate) {
        super(breed, name, cost, character, birthDate);
    }

}
