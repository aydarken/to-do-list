package com.phonixlab.todolist.services;

import com.phonixlab.todolist.model.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();

    Task getTask(int id);

    Integer createTask(Task task);

    ResponseEntity<?> updateTask(Task task);

    void deleteTask(int id);
}
