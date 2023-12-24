package com.phonixlab.todolist.services.impl;

import com.phonixlab.todolist.entities.TaskEntity;
import com.phonixlab.todolist.model.Status;
import com.phonixlab.todolist.model.Task;
import com.phonixlab.todolist.repositories.TaskRepository;
import com.phonixlab.todolist.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAllByStatusNot(Status.DELETED).stream().map(this::mapToModel).collect(Collectors.toList());
    }

    @Override
    public Task getTask(int id) {
        return mapToModel(taskRepository.findFirstByIdAndStatusNot(id, Status.DELETED));
    }

    @Override
    public Integer createTask(Task task) {
        return taskRepository.save(TaskEntity.builder()
                        .id(task.getId())
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .createdAt(LocalDateTime.now())
                        .status(Status.ACTIVE)
                .build()).getId();
    }

    @Override
    public ResponseEntity<?> updateTask(Task task) {
        Optional<TaskEntity> taskEntityOptional = taskRepository.findById(task.getId());
        if (taskEntityOptional.isEmpty() || taskEntityOptional.get().getStatus().equals(Status.DELETED)) {
            return new ResponseEntity<>("Task with id " + task.getId() + " not found", HttpStatus.NOT_FOUND);
        }
        TaskEntity taskEntity = taskEntityOptional.get();
        taskEntity.setTitle(task.getTitle());
        taskEntity.setDescription(task.getDescription());
        taskEntity.setStatus(Status.UPDATED);
        taskEntity.setUpdatedAt(LocalDateTime.now());
        taskRepository.save(taskEntity);
        return new ResponseEntity<>(taskEntity, HttpStatus.OK);
    }

    @Override
    public void deleteTask(int id) {
        Optional<TaskEntity> optionalTaskEntity = taskRepository.findById(id);
        if (optionalTaskEntity.isPresent()) {
            TaskEntity taskEntity = optionalTaskEntity.get();
            taskEntity.setStatus(Status.DELETED);
            taskEntity.setUpdatedAt(LocalDateTime.now());
        }
    }

    private Task mapToModel(TaskEntity taskEntity) {
        if (taskEntity == null) {
            return null;
        }
        return Task.builder()
                .id(taskEntity.getId())
                .title(taskEntity.getTitle())
                .description(taskEntity.getDescription())
                .build();
    }
}
