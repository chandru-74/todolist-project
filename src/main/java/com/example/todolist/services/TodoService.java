package com.example.todolist.services;

import com.example.todolist.models.Todo;
import com.example.todolist.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTasks() {
        return todoRepository.findAll(Sort.by(Sort.Direction.ASC, "orderIndex"));
    }


    public Todo createTask(String title) {
        Integer maxOrderIndex = todoRepository.findMaxOrderIndex();
        if (maxOrderIndex == null) {
            maxOrderIndex = 0;
        }

        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setCompleted(false);
        todo.setCreatedAt(LocalDateTime.now());
        todo.setOrderIndex(maxOrderIndex + 1);

        return todoRepository.save(todo);
    }

    public void deleteTask(Long id) {
        todoRepository.deleteById(id);
        // Optionally reorder tasks here after deletion
    }

    public void toggleTask(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task id: " + id));
        todo.setCompleted(!todo.getCompleted());
        todoRepository.save(todo);
    }

    public Todo saveTask(Todo task) {
        return todoRepository.save(task);
    }


}
