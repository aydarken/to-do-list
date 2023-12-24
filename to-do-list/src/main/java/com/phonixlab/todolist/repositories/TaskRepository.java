package com.phonixlab.todolist.repositories;

import com.phonixlab.todolist.entities.TaskEntity;
import com.phonixlab.todolist.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    TaskEntity findFirstByIdAndStatusNot(int id, Status status);

    List<TaskEntity> findAllByStatusNot(Status status);
}
