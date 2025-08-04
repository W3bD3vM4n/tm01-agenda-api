package com.w3bd3vm4n.tm01_agenda_api.repository;

import com.w3bd3vm4n.tm01_agenda_api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
