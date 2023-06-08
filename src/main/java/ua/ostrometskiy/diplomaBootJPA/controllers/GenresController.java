package ua.ostrometskiy.diplomaBootJPA.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.ostrometskiy.diplomaBootJPA.models.Genres;
import ua.ostrometskiy.diplomaBootJPA.services.GenresService;

@Controller
@RequestMapping("/genres")
public class GenresController {

    private final GenresService genresService;

    @Autowired
    public GenresController(GenresService genresService) {
        this.genresService = genresService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("genres", genresService.index());
        return "genres/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("genres", genresService.show(id));
        return "genres/show";
    }

    @GetMapping("/new")
    public String newGenre(Model model){
        model.addAttribute("genres",new Genres());
        return "genres/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("genres") @Valid Genres genres,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "genres/new";

        genresService.save(genres);
        return "redirect:/genres";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){

        model.addAttribute("genres",genresService.show(id));
        return "genres/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("genres") @Valid Genres genres, BindingResult bindingResult,
                         @PathVariable("id") int id){

        if (bindingResult.hasErrors())
            return "genres/edit";

        genresService.update(id, genres);
        return "redirect:/genres";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        genresService.delete(id);
        return "redirect:/genres";
    }
}

