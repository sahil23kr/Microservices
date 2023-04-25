package com.TaskManagement1.Service;

import org.springframework.http.ResponseEntity;

import com.TaskManagement1.DTO.TaskManagementDTO;
import com.TaskManagement1.Model.TaskManagementModel;

public interface TaskMangementService {

	ResponseEntity<TaskManagementDTO> saveTask(TaskManagementModel taskdetail);

	ResponseEntity<TaskManagementDTO> getAlltasks();

}
