package com.example.todolist.controller;

import com.example.todolist.services.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String getTasks(Model model) {
        model.addAttribute("tasks", todoService.getAllTasks());
        return "tasks";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        todoService.toggleTask(id);
        return "redirect:/tasks";
    }
}
