package ua.ostrometskiy.diplomaBootJPA.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.ostrometskiy.diplomaBootJPA.models.Authors;
import ua.ostrometskiy.diplomaBootJPA.services.AuthorsService;

@Controller
@RequestMapping("/authors")
public class AuthorsController {
    private final AuthorsService authorsService;


    @Autowired
    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("authors", authorsService.index());
        return "authors/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        model.addAttribute("authors", authorsService.show(id));
        return "authors/show";
    }

    @GetMapping("/new")
    public String newAuthor(Model model) {

        model.addAttribute("authors", new Authors());
        return "authors/new";
    }

    @PostMapping("")
    public String create(@ModelAttribute("authors") @Valid Authors authors,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "authors/new";

        authorsService.save(authors);
        return "redirect:/authors";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {

        model.addAttribute("authors", authorsService.show(id));
        return "authors/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("authors") @Valid Authors authors, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "authors/edit";

        authorsService.update(id, authors);
        return "redirect:/authors";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        authorsService.delete(id);
        return "redirect:/authors";
    }
}
