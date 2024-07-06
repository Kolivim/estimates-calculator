package ru.kolivim.myproject.task.management.system.impl.service.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kolivim.myproject.task.management.system.api.dto.search.BaseSearchDto;
import ru.kolivim.myproject.task.management.system.domain.user.User;
import ru.kolivim.myproject.task.management.system.impl.exception.NotFoundException;

import ru.kolivim.myproject.task.management.system.impl.repository.user.UserRepository;
import ru.kolivim.myproject.task.management.system.impl.utils.auth.AuthUtil;
import ru.kolivim.myproject.task.management.system.impl.utils.specification.SpecificationUtils;

import java.util.NoSuchElementException;
import java.util.UUID;

//@Slf4j
//@Service
//@Transactional
//@RequiredArgsConstructor
public class TaskService {
/*
    private final TaskMapper taskMapper;
    private final CommentMapper commentMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private static String NOT_FOUND_MESSAGE = "Задача не найдена";

    public TaskDTO create(TaskDTO taskDTO) {
        log.info("TaskService: create(TaskDTO taskDTO) startMethod, taskDTO:{}", taskDTO);
        isUserExist(taskDTO.getExecutorId());
        taskDTO.setAuthorId(AuthUtil.getUserId());
        return taskMapper.toTaskDTO(taskRepository.save(taskMapper.toTask(taskDTO)));
    }

    public Page<TaskDTO> getAllMeAuthorId(Pageable page) {
        return getAllByAuthorId(AuthUtil.getUserId(), page);
    }

    public Page<TaskDTO> getAllMeExecutorId(Pageable page) {
        return getAllByExecutorId(AuthUtil.getUserId(), page);
    }

    public Page<TaskDTO> getAllMeByFilter(TaskDTO taskDTO, Pageable page) {
        taskDTO.setAuthorId(AuthUtil.getUserId());
        return getAllByFilter(taskDTO, page);
    }

    public Page<TaskDTO> getAllByFilter(TaskDTO taskDTO, Pageable page) {
        log.info("TaskService: getAllByFilter startMethod, TaskDTO: {}", taskDTO);

        BaseSearchDto baseSearchDto = new BaseSearchDto();
        baseSearchDto.setIsDeleted(false);

        Specification taskSpecification = SpecificationUtils.getBaseSpecification(baseSearchDto)
                .and(SpecificationUtils.in(Task_.AUTHOR_ID, taskDTO.getAuthorId()))
                .and(SpecificationUtils.in(Task_.EXECUTOR_ID, taskDTO.getExecutorId()))
                .and(SpecificationUtils.in(Task_.STATUS, taskDTO.getStatus()))
                .and(SpecificationUtils.in(Task_.PRIORITY, taskDTO.getPriority()));

        Page<Task> tasks = taskRepository.findAll(taskSpecification, page);
        Page<TaskDTO> tasksDto = tasks.map(taskMapper::toTaskDTO);
        return tasksDto;
    }

    public Page<TaskDTO> getAllByAuthorId(UUID id, Pageable page) {

        BaseSearchDto baseSearchDto = new BaseSearchDto();
        baseSearchDto.setIsDeleted(false);

        Specification taskSpecification = SpecificationUtils.getBaseSpecification(baseSearchDto)
                .and(SpecificationUtils.in(Task_.AUTHOR_ID, id));

        Page<Task> tasks = taskRepository.findAll(taskSpecification, page);
        Page<TaskDTO> tasksDto = tasks.map(taskMapper::toTaskDTO);
        return tasksDto;
    }

    public Page<TaskDTO> getAllByExecutorId(UUID id, Pageable page) {

        Specification taskSpecification = SpecificationUtils.getBaseSpecification(getBaseSearchDto())
                .and(SpecificationUtils.in(Task_.EXECUTOR_ID, id));

        Page<Task> tasks = taskRepository.findAll(taskSpecification, page);
        Page<TaskDTO> tasksDto = tasks.map(taskMapper::toTaskDTO);
        return tasksDto;
    }

    public void deleteById(UUID id) throws Throwable {
        log.info("TaskService: deleteById(UUID id) startMethod, id: {}", id);

        Specification taskSpecification = SpecificationUtils.getBaseSpecification(getBaseSearchDto())
                .and(SpecificationUtils.in(Task_.AUTHOR_ID, AuthUtil.getUserId()))
                .and(SpecificationUtils.in(Task_.ID, id));

        Task task = (Task) taskRepository.findOne(taskSpecification).orElseThrow(()
                -> new NotFoundException("Задачи с таким UUID не существует либо вы не являетесь автором задачи"));
        task.setIsDeleted(true);

        log.info("TaskService: deleteById(UUID id) endMethod, save Task: {}", taskRepository.save(task));
    }

    public TaskDTO update(TaskDTO taskDTO) throws Throwable {
        log.info("TaskService: update(TaskDTO taskDTO) startMethod, TaskDTO: {}", taskDTO);

        Specification taskSpecification = SpecificationUtils.getBaseSpecification(getBaseSearchDto())
                .and(SpecificationUtils.in(Task_.AUTHOR_ID, AuthUtil.getUserId()))
                .and(SpecificationUtils.in(Task_.ID, taskDTO.getId()));

        Task task = (Task) taskRepository.findOne(taskSpecification).orElseThrow(()
                -> new NotFoundException("Задачи с таким UUID не существует либо вы не являетесь автором задачи"));

        return taskMapper.toTaskDTO(taskRepository.save(taskMapper.toTask(taskDTO, task)));
    }

    public TaskDTO updateStatus(TaskDTO taskDTO) throws Throwable {
      log.info("TaskService: updateStatus(TaskDTO taskDTO) startMethod, TaskDTO: {}", taskDTO);

        Specification taskSpecification = SpecificationUtils.getBaseSpecification(getBaseSearchDto())
                .and(SpecificationUtils.in(Task_.AUTHOR_ID, AuthUtil.getUserId())
                        .or(SpecificationUtils.in(Task_.EXECUTOR_ID, AuthUtil.getUserId())))
                .and(SpecificationUtils.in(Task_.ID, taskDTO.getId()));

        try{
            Task task = (Task) taskRepository.findOne(taskSpecification).orElseThrow(()
                    -> new NotFoundException("Задачи с таким UUID не существует либо вы не являетесь " +
                    "автором/исполнителем задачи"));
            return taskMapper.toTaskDTO(taskRepository.save(taskMapper.toTaskStatus(taskDTO, task)));
        } catch (NoSuchElementException e) {
            throw new NotFoundException("Задачи с таким UUID не существует либо вы не являетесь " +
                    "автором/исполнителем задачи");
        }

    }

    public TaskDTO updateExecutor(TaskDTO taskDTO) throws Throwable {
        log.info("TaskService: updateExecutor(TaskDTO taskDTO) startMethod, TaskDTO: {}", taskDTO);

        Specification taskSpecification = SpecificationUtils.getBaseSpecification(getBaseSearchDto())
                .and(SpecificationUtils.in(Task_.AUTHOR_ID, AuthUtil.getUserId()))
                .and(SpecificationUtils.in(Task_.ID, taskDTO.getId()));

        Task task = (Task) taskRepository.findOne(taskSpecification).orElseThrow(()
                -> new NotFoundException("Задачи с таким UUID не существует либо вы не являетесь автором задачи"));

        isUserExist(taskDTO.getExecutorId());

        return taskMapper.toTaskDTO(taskRepository.save(taskMapper.toTaskExecutor(taskDTO, task)));
    }

    public TaskDTO getById(UUID id) throws Throwable {
        log.info("TaskService: getById(UUID id) startMethod, id: {}", id);

        Specification taskSpecification = SpecificationUtils.getBaseSpecification(getBaseSearchDto())
                .and(SpecificationUtils.in(Task_.ID, id));
        Task task = (Task) taskRepository.findOne(taskSpecification).orElseThrow(()
                -> new NotFoundException("Задачи с таким UUID не существует"));

        return taskMapper.toTaskDTO(task);
    }

    private BaseSearchDto getBaseSearchDto(){
        BaseSearchDto baseSearchDto = new BaseSearchDto();
        baseSearchDto.setIsDeleted(false);
        return  baseSearchDto;
    }

    private Boolean isUserExist(UUID id){
        log.info("TaskService: isUserExist(UUID id) startMethod, получен id: {}", id);
        User user = userRepository.findByIdAndIsDeletedFalse(id).orElseThrow(()
                -> new NotFoundException("Пользователя с таким UUID не существует"));
        return true;
    }

    */
}
