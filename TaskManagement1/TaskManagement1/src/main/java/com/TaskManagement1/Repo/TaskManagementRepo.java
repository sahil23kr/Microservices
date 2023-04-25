package com.TaskManagement1.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.TaskManagement1.DTO.TaskManagementDTO;
import com.TaskManagement1.Model.TaskManagementModel;

public interface TaskManagementRepo extends JpaRepository<TaskManagementModel, Long>{

	@Query(value = "select * from TaskManagement where taskId = :taskId",nativeQuery = true)
    List<TaskManagementModel> findByTaskId(@Param("taskId") Long taskId);

}
