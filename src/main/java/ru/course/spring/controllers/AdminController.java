package ru.course.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.course.spring.dao.PersonDAO;
import ru.course.spring.models.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonDAO personDAO;

    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String index(Model model, @ModelAttribute("person")Person person){
        model.addAttribute("people", personDAO.index());

        return "admin/index";
    }
    @PatchMapping("/add")
    public String addAdmin(@ModelAttribute("person") Person person){
        System.out.println(person.getId());
        return "redirect:/people";

    }
}
