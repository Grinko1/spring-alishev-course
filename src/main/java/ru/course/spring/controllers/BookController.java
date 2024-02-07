package ru.course.spring.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.course.spring.dao.BookDao;
import ru.course.spring.models.Book;

@Controller
@RequestMapping("/book")
public class BookController {
    private BookDao bookDao;

    @Autowired
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDao.index());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDao.show(id));
        return "book/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("book", new Book());
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/new";
        }

        bookDao.save(book);
        return "redirect:/book";
    }

    //take
    @GetMapping("/{id}/takeBook")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDao.show(id));
        return "book/take";
    }

    @PatchMapping("/{id}")
    public String takeBook(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "book/edit";
        }
        bookDao.takeBook(id, book);
        return "redirect:/people";

    }

    //return
    @GetMapping("/{id}/return")
    public String returnPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDao.show(id));
        return "book/return";
    }

    @PatchMapping("/{id}")
    public String returnBookBook(
                           BindingResult bindingResult,
                           @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) {
            return "book/return";
        }
        bookDao.returnBook(id);
        return "redirect:/book";

    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDao.delete(id);

        return "redirect:/book";

    }
}
