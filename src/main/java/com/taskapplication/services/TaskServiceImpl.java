package com.taskapplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskapplication.exception.ResourceNotFoundException;
import com.taskapplication.model.Task;
import com.taskapplication.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task saveTask(Task task) {

		return taskRepository.save(task);
	}

	@Override
	public Task findTaskById(Long id) {
		return taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + id));
	}

	@Override
	public List<Task> findAllTask() {
		return taskRepository.findAll();
	}

	@Override
	public Task updateTask(Long id, Task updatedTask) {
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + id));

		task.setTitle(updatedTask.getTitle());
		task.setDescription(updatedTask.getDescription());
		task.setPriority(updatedTask.getPriority());

		Task updatedTaskValue = taskRepository.save(task);

		return updatedTaskValue;

	}

	@Override
	public void deleteTaskById(Long id) {
		taskRepository.deleteById(id);

	}

}
