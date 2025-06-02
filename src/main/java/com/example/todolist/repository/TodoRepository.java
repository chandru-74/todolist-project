package com.example.todolist.repository;

import com.example.todolist.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {

    @Query("SELECT COALESCE(MAX(t.orderIndex), 0) FROM Todo t")
    Integer findMaxOrderIndex();

    List<Todo> findAllByOrderByOrderIndexAsc();



}
