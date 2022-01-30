package pl.sda.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.todoapp.model.Todo;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private List<Todo> todos = new ArrayList<>();

    public TodoController() {
        todos.add(new Todo("Zadanie 1", "Opis 1", new Date()));
        todos.add(new Todo("Zadanie 2", "Opis 2", new Date()));
        todos.add(new Todo("Zadanie 3", "Opis 3", new Date()));
        todos.add(new Todo("Zadanie 4", "Opis 4", new Date()));
        todos.add(new Todo("Zadanie 5", "Opis 5", new Date()));
        todos.add(new Todo("Zadanie 6", "Opis 6", new Date()));
        todos.add(new Todo("Zadanie 7", "Opis 7", new Date()));
        todos.add(new Todo("Zadanie 8", "Opis 8", new Date()));
        todos.add(new Todo("Zadanie 9", "Opis 9", new Date()));
    }

    // wyświetl wszystkie zadania
    // GET localhost:8080/todo
    // dlaczego "/" psuł????
    @GetMapping
    public String showTodos(Model model) {
        model.addAttribute("modelTodoList", todos);
        model.addAttribute("emptyTodo", new Todo());

        return "todoTemplate";
    }

    // HTTP GET /todo != HTTP POST todo

    // POST /todo
    // stwórz nowe zadanie
    @PostMapping
    public String createTodo(@Valid Todo todo, Errors errors) {
//        if (errors.hasErrors()) {
//            return "todoTemplate";
//        }

        todo.setCreateDate(new Date());
        todos.add(todo);
        return "redirect:/todo";
    }
}
