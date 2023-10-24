package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.CrudMeals;
import ru.javawebinar.topjava.service.InMemoryMealsRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final CrudMeals repository = InMemoryMealsRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action != null) {
            action = action.toLowerCase();
            switch (action) {
                case "add": {
                    log.debug("redirect to edit.jsp : action=add");
                    request.getRequestDispatcher("edit.jsp").forward(request, response);
                    break;
                }
                case "edit": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("meal", repository.getById(id));
                    log.debug("redirect to edit.jsp : action=edit");
                    request.getRequestDispatcher("edit.jsp").forward(request, response);
                    break;
                }
                case "delete": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    repository.deleteById(id);
                    log.debug("redirect to meals : action=delete");
                    response.sendRedirect("meals");
                    break;
                }
            }
        } else {
            log.debug("redirect to meals");

            List<MealTo> mealList = MealsUtil.filteredByStreams(repository.getAll(), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY);
            request.setAttribute("mealList", mealList);
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
//        response.sendRedirect("meals.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        LocalDateTime localTime = LocalDateTime.parse(request.getParameter("datetime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        repository.save(new Meal(id.isEmpty() ? null : Integer.valueOf(id), localTime, description, calories));
        log.info("{}:{}:{}:{}", id, localTime, description, calories);

        response.sendRedirect("meals");
    }
}
