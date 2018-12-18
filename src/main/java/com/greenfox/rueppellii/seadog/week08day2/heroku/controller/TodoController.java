package com.greenfox.rueppellii.seadog.week08day2.heroku.controller;

import com.greenfox.rueppellii.seadog.week08day2.heroku.Todo;
import com.greenfox.rueppellii.seadog.week08day2.heroku.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private TodoRepository repo;

    @Autowired
    public TodoController(TodoRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String list(Model model, @RequestParam(required=false) String todoitem, @ModelAttribute(name="todoObject") Todo todo) {
    //    model.addAttribute("todoObject", todo);
        model.addAttribute("todo", repo.findAll());
        return "todo";
    }

    @PostMapping("/")
    public String addTodo(@ModelAttribute(name="todoObject") Todo todo) {
        repo.save(todo);
        return "redirect:/todo/";
    }

    @RequestMapping(value ="/{todoId}/delete", method = RequestMethod.POST)
    public String deleteTask(@PathVariable("todoId") Long id) {
        repo.deleteById(id);
        return "redirect:/todo/";
    }

    @RequestMapping(value ="/{todoId}/edit", method = RequestMethod.GET)
    public String editTask(@PathVariable("todoId") Long id, Model model) {
        Optional<Todo> todo = repo.findById(id);
        model.addAttribute("todoEdit", todo.get());
        return "edit";
    }

    @RequestMapping(value ="/{todoId}/edit", method = RequestMethod.POST)
    public String editTask(@PathVariable("todoId") Long id, @ModelAttribute(name="todoEdit") Todo todo) {
        repo.save(todo);
        return "redirect:/todo/";
    }
}