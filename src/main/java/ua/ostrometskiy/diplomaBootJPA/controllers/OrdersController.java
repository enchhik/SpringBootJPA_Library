package ua.ostrometskiy.diplomaBootJPA.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.ostrometskiy.diplomaBootJPA.models.Books;
import ua.ostrometskiy.diplomaBootJPA.models.Orders;
import ua.ostrometskiy.diplomaBootJPA.models.Users;
import ua.ostrometskiy.diplomaBootJPA.services.BooksService;
import ua.ostrometskiy.diplomaBootJPA.services.OrdersService;
import ua.ostrometskiy.diplomaBootJPA.services.UsersService;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;
    private final UsersService usersService;
    private final BooksService booksService;

    @Autowired
    public OrdersController(OrdersService ordersService, UsersService usersService, BooksService booksService) {
        this.ordersService = ordersService;
        this.usersService = usersService;
        this.booksService = booksService;
    }

    @GetMapping()
    public String index(Model model) {
       model.addAttribute("orders", ordersService.index());
        return "orders/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

            model.addAttribute("orders", ordersService.show(id));
            return "orders/show";
    }


    @GetMapping("/new")
    public String newOrder(Model model) {
        List<Users> usersList = usersService.index();
        List<Books> booksList = booksService.index();

        model.addAttribute("usersList", usersList);
        model.addAttribute("booksList", booksList);
        model.addAttribute("orders", new Orders());
        return "orders/new";
    }

    @PostMapping("")
    public String create(@ModelAttribute("orders") @Valid Orders orders,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "orders/new";

        orders.setBooksList(orders.getBooksList());
        orders.setUser(usersService.show(orders.getUser().getId()));

        ordersService.save(orders);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        List<Users> usersList = usersService.index();
        List<Books> booksList = booksService.index();

        model.addAttribute("usersList", usersList);
        model.addAttribute("booksList", booksList);
        model.addAttribute("orders", ordersService.show(id));
        return "orders/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("orders") @Valid Orders orders, BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "orders/edit";

        ordersService.update(id, orders);
        return "redirect:/orders";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        ordersService.delete(id);
        return "redirect:/orders";
    }

}
