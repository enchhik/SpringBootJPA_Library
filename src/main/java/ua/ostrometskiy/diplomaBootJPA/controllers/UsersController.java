package ua.ostrometskiy.diplomaBootJPA.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.ostrometskiy.diplomaBootJPA.models.Users;
import ua.ostrometskiy.diplomaBootJPA.services.UsersService;

@Controller
@RequestMapping("/people")
public class UsersController  {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", usersService.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("users", usersService.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("users",new Users());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("users") @Valid Users users,
                         BindingResult bindingResult){

        if (bindingResult.hasErrors())
            return "people/new";

        usersService.save(users);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("users",usersService.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("users") @Valid Users users, BindingResult bindingResult,
                         @PathVariable("id") int id){

        if (bindingResult.hasErrors())
            return "people/edit";

        usersService.update(id, users);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        usersService.delete(id);
        return "redirect:/people";
    }
}

