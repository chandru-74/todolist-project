package com.example.todolist.controller;

import com.example.todolist.models.Todo;
import com.example.todolist.services.TodoService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TodoApiController {

    private final TodoService todoService;

    public TodoApiController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/tasks")
    public String getTasks(@RequestParam(required = false) String filter,
                           @RequestParam(required = false) String sort,
                           Model model) {
        model.addAttribute("filter", filter);
        model.addAttribute("sort", sort);
        return "tasks";
    }




    @PostMapping
    public Todo addTask(@RequestBody Todo task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setCompleted(false);
        return todoService.saveTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        todoService.deleteTask(id);
    }

    @GetMapping
    public List<Todo> getAllTasks() {
        return todoService.getAllTasks();
    }
}
