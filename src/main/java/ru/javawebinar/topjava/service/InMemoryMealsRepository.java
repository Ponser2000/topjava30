package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealsRepository implements CrudMeals {
    private static volatile InMemoryMealsRepository instance;
    private final Map<Integer, Meal> meals;
    private final AtomicInteger counter = new AtomicInteger(0);

    private InMemoryMealsRepository() {
        meals = new ConcurrentHashMap<>();
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        save(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    public static InMemoryMealsRepository getInstance() {
        InMemoryMealsRepository localInstance = instance;

        if (localInstance == null) {
            synchronized (InMemoryMealsRepository.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new InMemoryMealsRepository();
                }
            }
        }
        return localInstance;
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.getId() == null) {
            meal.setId(counter.incrementAndGet());
        }
        return meals.put(meal.getId(), meal);
    }

    @Override
    public Meal getById(int id) {
        return meals.get(id);
    }

    @Override
    public void deleteById(int id) {
        meals.remove(id);
    }
}
