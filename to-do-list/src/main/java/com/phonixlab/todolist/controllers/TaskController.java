package com.phonixlab.todolist.controllers;

import com.phonixlab.todolist.model.Task;
import com.phonixlab.todolist.services.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/tasks")
    @PreAuthorize("hasAnyAuthority('GET_ALL_TASKS')")
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/task/{id}")
    @PreAuthorize("hasAnyAuthority('GET_TASK')")
    public ResponseEntity<Task> getTask(@PathVariable int id) {
        return new ResponseEntity<>(taskService.getTask(id), HttpStatus.OK);
    }

    @PostMapping("task/create")
    @PreAuthorize("hasAnyAuthority('CREATE_TASK')")
    public ResponseEntity<Integer> createTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.createTask(task), HttpStatus.OK);
    }

    @PutMapping("task/update")
    @PreAuthorize("hasAnyAuthority('UPDATE_TASK')")
    public ResponseEntity<?> updateTask(@RequestBody Task task) {
        return taskService.updateTask(task);
    }

    @DeleteMapping("task/delete/{id}")
    @PreAuthorize("hasAnyAuthority('DELETE_TASK')")
    public void deleteTask(@PathVariable int id) {
        taskService.deleteTask(id);
    }
}
