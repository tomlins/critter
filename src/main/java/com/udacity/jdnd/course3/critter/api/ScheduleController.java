package com.udacity.jdnd.course3.critter.api;

import com.udacity.jdnd.course3.critter.domain.entity.Employee;
import com.udacity.jdnd.course3.critter.domain.entity.Pet;
import com.udacity.jdnd.course3.critter.domain.entity.Schedule;
import com.udacity.jdnd.course3.critter.domain.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    PetService petService;
    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleService.createSchedule(scheduleDTOToEntity(scheduleDTO));
        return entityToScheduleDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> scheduleList = scheduleService.getAllSchedules();
        return entityArrayToScheduleDTOArray(scheduleList);
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> scheduleList = scheduleService.getSchedulesForPetId(petId);
        return entityArrayToScheduleDTOArray(scheduleList);
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> scheduleList = scheduleService.getSchedulesForEmployee(employeeId);
        return entityArrayToScheduleDTOArray(scheduleList);
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> scheduleList = scheduleService.getSchedulesForCustomer(customerId);
        return entityArrayToScheduleDTOArray(scheduleList);
    }

    /**
     * Helper method to convert ScheduleDTO object to its corresponding entity object
     * @param scheduleDTO
     * @return schedule
     */
    private Schedule scheduleDTOToEntity(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        Set<Employee> employees = new HashSet<>();
        for(Long employeeId : scheduleDTO.getEmployeeIds()) {
            employees.add(employeeService.findById(employeeId));
        }

        Set<Pet> pets = new HashSet<>();
        for(Long petId : scheduleDTO.getPetIds()) {
            pets.add(petService.findById(petId));
        }

        schedule.setEmployees(employees);
        schedule.setPets(pets);

        return schedule;
    }

    /**
     * Helper method to convert Schedule entity to its corresponding DTO object
     * @param schedule
     * @return scheduleDTO
     */
    private ScheduleDTO entityToScheduleDTO(Schedule schedule) {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        List<Long> employeeIds = new ArrayList<>();
        for (Employee employee : schedule.getEmployees()) {
            employeeIds.add(employee.getId());
        }

        List<Long> petIds = new ArrayList<>();
        for (Pet pet : schedule.getPets()) {
            petIds.add(pet.getId());
        }

        scheduleDTO.setEmployeeIds(employeeIds);
        scheduleDTO.setPetIds(petIds);

        return scheduleDTO;
    }

    /**
     * Help method to convert an array of Schedule entities into an array of scheduleDTO
     * @param scheduleList
     * @return scheduleDTOList
     */
    private List<ScheduleDTO> entityArrayToScheduleDTOArray(List<Schedule> scheduleList) {
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            scheduleDTOList.add(entityToScheduleDTO(schedule));
        }
        return scheduleDTOList;
    }

    /**
     * Helper method to convert an array of scheduleDTO objects to an array of Schedule entities
     * @param scheduleDTOList
     * @return
     */
    private List<Schedule> scheduleDTOArrayToEntityArray(List<ScheduleDTO> scheduleDTOList) {
        List<Schedule> scheduleList = new ArrayList<>();
        for (ScheduleDTO scheduleDTO : scheduleDTOList) {
            scheduleList.add(scheduleDTOToEntity(scheduleDTO));
        }
        return scheduleList;
    }

}
