package com.w3bd3vm4n.tm01_agenda_api.mapper;

import com.w3bd3vm4n.tm01_agenda_api.dto.TaskCreateDTO;
import com.w3bd3vm4n.tm01_agenda_api.dto.TaskResponseDTO;
import com.w3bd3vm4n.tm01_agenda_api.model.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponseDTO mapToTaskResponseDTO(Task task);

    List<TaskResponseDTO> mapToTaskResponseDTOList(List<Task> tasks);

    Task mapToTask(TaskCreateDTO dto);

}
