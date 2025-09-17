package com.w3bd3vm4n.tm01_agenda_api.controller;

import com.w3bd3vm4n.tm01_agenda_api.dto.TaskCreateDTO;
import com.w3bd3vm4n.tm01_agenda_api.dto.TaskResponseDTO;
import com.w3bd3vm4n.tm01_agenda_api.mapper.TaskMapper;
import com.w3bd3vm4n.tm01_agenda_api.model.Task;
import com.w3bd3vm4n.tm01_agenda_api.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskMapper taskMapper;

//    @GetMapping
//    public List<TaskResponseDTO> getAllTasks() {
//        return taskMapper.mapToTaskResponseDTOList(taskService.getTasksListFromRepository());
//    }

    @GetMapping("/between")
    public List<TaskResponseDTO> getTasksInDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<Task> tasks = taskService.getTasksListByStartEndDate(startDate, endDate);
        return taskMapper.mapToTaskResponseDTOList(tasks);
    }

    @GetMapping("/relevance-options")
    public ResponseEntity<List<Integer>> getRelevanceOptions() {
        return ResponseEntity.ok(List.of(1, 2, 3, 4));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
//        return taskService.getTaskByIdFromRepository(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long id) {
        return taskService.getTaskByIdFromRepository(id)
                .map(taskMapper::mapToTaskResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskCreateDTO taskCreateDTO) {
        if (taskCreateDTO.getTitle() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Task task = taskMapper.mapToTask(taskCreateDTO);
        Task savedTask = taskService.saveTaskFromRepository(task);
        TaskResponseDTO responseDTO = taskMapper.mapToTaskResponseDTO(savedTask);

        return ResponseEntity.ok(responseDTO);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskCreateDTO taskCreateDTO) {
//        return taskService.getTaskByIdFromRepository(id)
//                .map(existingTask -> {
//                    existingTask.setTitle(taskCreateDTO.getTitle());
//                    existingTask.setStartDate(taskCreateDTO.getStartDate());
//                    existingTask.setEndDate(taskCreateDTO.getEndDate());
//                    existingTask.setRelevance(taskCreateDTO.getRelevance());
//                    existingTask.setEnergy(taskCreateDTO.getEnergy());
//                    existingTask.setColor(taskCreateDTO.getColor());
//                    existingTask.setDetail(taskCreateDTO.getDetail());
//
//                    Task updatedTask = taskService.saveTaskFromRepository(existingTask);
//                    TaskResponseDTO responseDTO = taskMapper.mapToTaskResponseDTO(updatedTask);
//
//                    return ResponseEntity.ok(responseDTO);
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskCreateDTO taskCreateDTO) {
        return taskService.getTaskByIdFromRepository(id)
                .map(existingTask -> {
                    taskMapper.updateTaskFromDto(taskCreateDTO, existingTask);

                    Task updatedTask = taskService.saveTaskFromRepository(existingTask);
                    return ResponseEntity.ok(taskMapper.mapToTaskResponseDTO(updatedTask));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskFromRepository(id);
        return ResponseEntity.noContent().build();
    }

}
