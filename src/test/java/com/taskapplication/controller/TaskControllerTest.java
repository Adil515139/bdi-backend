package com.taskapplication.controller;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskapplication.model.Task;
import com.taskapplication.services.TaskService;

@WebMvcTest
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TaskControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TaskService taskService;

	@Test
	public void testCreateTask() throws JsonProcessingException, Exception {
		Task taskExample1 = new Task();

		taskExample1.setTitle("SampleTask");
		taskExample1.setDescription("Description");
		taskExample1.setPriority("low");


		when(taskService.saveTask(any(Task.class))).thenReturn(taskExample1);

		mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(taskExample1)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value(taskExample1.getTitle()));
				
	}

	@Test
	public void getTaskByIdTest() throws Exception {
		Task task = new Task();
		task.setId(1L);
		task.setTitle("Sample Task");
		task.setDescription("Description");
		task.setPriority("low");

		when(taskService.findTaskById(1L)).thenReturn(task);
		

		mockMvc.perform(get("/tasks/1")).
		andExpect(status().isOk())
		.andExpect(jsonPath("$.title").value(task.getTitle()));
	}

	@Test
	public void getAllTasksTest() throws Exception {
		Task task1 = new Task();
		task1.setId(1L);
		task1.setTitle("Task 1");
		task1.setDescription("Description 1");
		task1.setPriority("low");

		Task task2 = new Task();
		task2.setId(2L);
		task2.setTitle("Task 2");
		task2.setDescription("Description 2");
		task2.setPriority("high");

		when(taskService.findAllTask()).thenReturn(Arrays.asList(task1, task2));

		mockMvc.perform(get("/tasks")).andExpect(status().isOk())
//				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].title").value(task1.getTitle()))
				.andExpect(jsonPath("$[1].title").value(task2.getTitle()));
	}

	@Test
	public void updateTaskTest() throws Exception {
		Task existingTask = new Task();
		existingTask.setId(1L);
		existingTask.setTitle("Existing Task");
		existingTask.setDescription("Description");
		existingTask.setPriority("normal");

		Task updatedTask = new Task();
		updatedTask.setId(1L);
		updatedTask.setTitle("Updated Task");
		updatedTask.setDescription("Updated Description");
		updatedTask.setPriority("Semi Normal");

		when(taskService.updateTask(1L, updatedTask)).thenReturn(updatedTask);

		mockMvc.perform(put("/tasks/1").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(updatedTask))).andExpect(status().isOk())
				.andExpect(jsonPath("$.title", is(updatedTask.getTitle())));
//                .andExpect(jsonPath("$.completed", is(true)));
	}

	@Test
	public void deleteTaskTest() throws Exception {
		mockMvc.perform(delete("/tasks/1")).andExpect(status().isOk());
	}

}
