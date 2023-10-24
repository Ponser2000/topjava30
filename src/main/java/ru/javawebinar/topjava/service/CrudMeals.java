package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface CrudMeals {
    List<Meal> getAll();

    Meal save(Meal meal);

    Meal getById(int id);

    void deleteById(int id);
}
