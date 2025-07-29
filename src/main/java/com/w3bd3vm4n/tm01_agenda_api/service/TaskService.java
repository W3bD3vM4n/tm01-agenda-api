package com.w3bd3vm4n.tm01_agenda_api.service;

import com.w3bd3vm4n.tm01_agenda_api.model.Task;
import com.w3bd3vm4n.tm01_agenda_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasksListFromRepository() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskByIdFromRepository(Long id) {
        return taskRepository.findById(id);
    }

    public Task saveTaskFromRepository(Task task) {
        if (task.getId() != null && !taskRepository.existsById(task.getId())) {
            throw new IllegalArgumentException("Task with ID " + task.getId() + " does not exist.");
        }
        return taskRepository.save(task);
    }

    public void deleteTaskFromRepository(Long id) {
        taskRepository.deleteById(id);
    }

}
