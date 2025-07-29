package com.w3bd3vm4n.tm01_agenda_api.repository;

import com.w3bd3vm4n.tm01_agenda_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
