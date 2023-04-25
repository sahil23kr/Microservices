package com.TaskManagement1.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement1.DTO.TaskManagementDTO;
import com.TaskManagement1.Model.TaskManagementModel;
import com.TaskManagement1.Repo.TaskManagementRepo;
import com.TaskManagement1.Service.TaskMangementService;

@RestController
@RequestMapping("taskmng")
public class TaskManagementController {

	@Autowired
	TaskManagementRepo taskManagementRepo;

	@Autowired
	TaskMangementService taskMangementService;

	@PostMapping("/saveTask")
	public ResponseEntity<TaskManagementDTO> saveTask(@RequestBody TaskManagementModel taskdetail) {
		//return taskMangementService.saveTask(taskdetail);
		TaskManagementDTO res = new TaskManagementDTO();

		try {
			List<TaskManagementModel> task = taskManagementRepo.findByTaskId(taskdetail.getTaskId());
			if (!task.isEmpty()) {
				// if (!taskdetail.isEmpty()){
				res.setData("Task id " + taskdetail.getTaskId() + " is already exist");

				return new ResponseEntity<>(res, HttpStatus.ALREADY_REPORTED);
			} else {

				// empNew.setDate(LocalDateTime.now());
				// dat.setData(empNew);
				res.setData(taskdetail);
				taskManagementRepo.save(taskdetail);
				return new ResponseEntity<>(res, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			// res.setMsg("Something got wrong");
			return new ResponseEntity<>(res, HttpStatus.BAD_GATEWAY);
		}
	}

	@GetMapping("/alltasks")
	public ResponseEntity<TaskManagementDTO> getAlltasks() {
		//return taskMangementService.getAlltasks();
		TaskManagementDTO res = new TaskManagementDTO();
		try {
			List<TaskManagementModel> task = taskManagementRepo.findAll();
			res.setData(task);
			return new ResponseEntity<>(res, HttpStatus.FOUND);

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(res, HttpStatus.BAD_GATEWAY);
		}
	}

	

	@GetMapping("/changeStatus")
	public ResponseEntity<TaskManagementDTO> changeStatus(@RequestParam(value = "taskId") long taskId) {
		TaskManagementDTO dto = new TaskManagementDTO();
		TaskManagementModel mod = new TaskManagementModel();
		try {
			Optional<TaskManagementModel> task = taskManagementRepo.findById(taskId);
			if (!task.isEmpty()) {

				// m1=taskManagementRepo.findOne(null)
				mod.setTaskStatus("inprogress");
				mod.setTaskId(taskId);
				mod.setTaskDate(task.get().getTaskDate());
				mod.setTaskHolderName(task.get().getTaskHolderName());
				mod.setTaskName(task.get().getTaskName());
				dto.setData(mod);
				taskManagementRepo.save(mod);
				return new ResponseEntity<>(dto, HttpStatus.OK);
			}

			else {
				dto.setData("Task id does not exist");
				return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {

			return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/deleteStatus")
	public ResponseEntity<TaskManagementDTO> deleteStatus(@RequestParam(value = "taskId") long taskId) {
		TaskManagementDTO dto = new TaskManagementDTO();
		try {
			Optional<TaskManagementModel> task = taskManagementRepo.findById(taskId);
			if (!task.isEmpty()) {

				dto.setData("deleted taskId is " + taskId);
				taskManagementRepo.deleteById(taskId);
				return new ResponseEntity<>(dto, HttpStatus.OK);
			}

			else {
				dto.setData("Task id does not exist");
				return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {

			return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getTask")
	public ResponseEntity<TaskManagementDTO> getTask(@RequestParam(value = "taskId") long taskId) {
		TaskManagementDTO dto = new TaskManagementDTO();
		TaskManagementModel mod = new TaskManagementModel();
		try {
			Optional<TaskManagementModel> task = taskManagementRepo.findById(taskId);
			if (!task.isEmpty()) {

				mod.setTaskId(taskId);
				mod.setTaskHolderName(task.get().getTaskHolderName());
				mod.setTaskName(task.get().getTaskName());
				mod.setTaskDate(task.get().getTaskDate());
				mod.setTaskStatus(task.get().getTaskStatus());
				//taskManagementRepo.(taskId);
				dto.setData(mod);
				return new ResponseEntity<>(dto, HttpStatus.OK);
			}

			else {
				dto.setData(task);
				return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {

			return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
		}
	}

}
