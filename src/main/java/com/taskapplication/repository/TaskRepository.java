package com.taskapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskapplication.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
