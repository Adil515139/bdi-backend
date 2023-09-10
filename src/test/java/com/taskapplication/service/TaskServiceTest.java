package com.taskapplication.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.taskapplication.model.Task;
import com.taskapplication.repository.TaskRepository;
import com.taskapplication.services.TaskService;
import com.taskapplication.services.TaskServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
	@Mock
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskServiceImpl taskService;

	@Test
	void save() {
		Task taskExample1 = new Task();

		taskExample1.setTitle("SampleTask");
		taskExample1.setDescription("Description");
		taskExample1.setPriority("low");

		when(taskRepository.save(any(Task.class))).thenReturn(taskExample1);

		Task newTask = taskService.saveTask(taskExample1);

		assertNotNull(newTask);
		assertThat(newTask.getTitle()).isEqualTo("SampleTask");
	}

	@Test
	void getTaskList() {

		List<Task> list = new ArrayList<>();
		Task taskExample1 = new Task();

		taskExample1.setTitle("SampleTask");
		taskExample1.setDescription("Description");
		taskExample1.setPriority("low");

		Task taskExample2 = new Task();

		taskExample2.setTitle("SampleTask");
		taskExample2.setDescription("Description");
		taskExample2.setPriority("low");
		
		list.add(taskExample1);
		list.add(taskExample2);

		when(taskRepository.findAll()).thenReturn(list);

		List<Task> tasks = taskService.findAllTask();

		assertEquals(2, tasks.size());
		assertNotNull(tasks);
	}

	@Test
	void getTaskById() {
		Task taskExample1 = new Task();
		taskExample1.setId(19L);
		taskExample1.setTitle("SampleTask");
		taskExample1.setDescription("Description");
		taskExample1.setPriority("low");

		when(taskRepository.findById(anyLong())).thenReturn(Optional.of(taskExample1));
		Task existingTask = taskService.findTaskById(taskExample1.getId());
		assertNotNull(existingTask);
		assertThat(existingTask.getId()).isNotEqualTo(null);
	}

	@Test
	void updateTask() {
		Task taskExample1 = new Task();
		taskExample1.setId(19L);
		taskExample1.setTitle("SampleTask");
		taskExample1.setDescription("Description");
		taskExample1.setPriority("low");
		when(taskRepository.findById(anyLong())).thenReturn(Optional.of(taskExample1));

		when(taskRepository.save((Task) any(Task.class))).thenReturn(taskExample1);
		taskExample1.setTitle("Fantacy");
		Task exisitingTask = taskService.updateTask(taskExample1.getId(), taskExample1);

		assertNotNull(exisitingTask);
		assertEquals("Fantacy", taskExample1.getTitle());
	}


}
