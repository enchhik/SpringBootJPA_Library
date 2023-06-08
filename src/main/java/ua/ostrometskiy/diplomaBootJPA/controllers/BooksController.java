package ua.ostrometskiy.diplomaBootJPA.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.ostrometskiy.diplomaBootJPA.models.Authors;
import ua.ostrometskiy.diplomaBootJPA.models.Books;
import ua.ostrometskiy.diplomaBootJPA.models.Genres;
import ua.ostrometskiy.diplomaBootJPA.services.AuthorsService;
import ua.ostrometskiy.diplomaBootJPA.services.BooksService;
import ua.ostrometskiy.diplomaBootJPA.services.GenresService;

import java.util.List;


@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final GenresService genresService;
    private final AuthorsService authorsService;

    @Autowired
    public BooksController(BooksService booksService, GenresService genresService, AuthorsService authorsService) {
        this.booksService = booksService;
        this.genresService = genresService;
        this.authorsService = authorsService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksService.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("books", booksService.show(id));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(Model model){
        List<Genres> genresList = genresService.index();
        List<Authors> authorList = authorsService.index();

        model.addAttribute("genresList", genresList);
        model.addAttribute("authorList", authorList);
        model.addAttribute("books", new Books());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("books") @Valid Books books,
                         BindingResult bindingResult){

        if (bindingResult.hasErrors())
            return "books/new";

        books.setAuthorList(books.getAuthorList());
        books.setGenre(genresService.show(books.getGenre().getId()));

        booksService.save(books);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){

        List<Genres> genresList = genresService.index();
        List<Authors> authorList = authorsService.index();

        model.addAttribute("genresList", genresList);
        model.addAttribute("authorList", authorList);
        model.addAttribute("books",booksService.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("books") @Valid Books books, BindingResult bindingResult,
                         @PathVariable("id") int id){

        if (bindingResult.hasErrors())
            return "books/edit";

        booksService.update(id, books);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

}

