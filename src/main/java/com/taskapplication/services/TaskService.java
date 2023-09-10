package com.taskapplication.services;

import java.util.List;

import com.taskapplication.model.Task;

public interface TaskService {

	Task saveTask(Task task);

	Task findTaskById(Long id);

	List<Task> findAllTask();

	Task updateTask(Long id, Task updatedTask);

	void deleteTaskById(Long id);

}
