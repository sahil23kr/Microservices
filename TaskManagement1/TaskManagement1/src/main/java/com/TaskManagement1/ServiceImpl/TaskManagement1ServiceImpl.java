package com.TaskManagement1.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jca.work.SimpleTaskWorkManager;
import org.springframework.stereotype.Service;

import com.TaskManagement1.DTO.TaskManagementDTO;
import com.TaskManagement1.Model.TaskManagementModel;
import com.TaskManagement1.Repo.TaskManagementRepo;
import com.TaskManagement1.Service.TaskMangementService;

@Service
public class TaskManagement1ServiceImpl implements TaskMangementService {

	@Autowired
	TaskManagementRepo taskManagementRepo;

	@Override
	public ResponseEntity<TaskManagementDTO> saveTask(TaskManagementModel taskdetail) {
		// TODO Auto-generated method stub
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

	@Override
	public ResponseEntity<TaskManagementDTO> getAlltasks() {
		// TODO Auto-generated method stub
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

}
