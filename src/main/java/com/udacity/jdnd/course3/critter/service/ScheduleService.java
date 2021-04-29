package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PetRepository petRepository;

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesForPetId(Pet pet) {
        return scheduleRepository.getSchedulesByPetsContains(pet);
    }

    public List<Schedule> getSchedulesForEmployee(Employee employee) {
        return scheduleRepository.getSchedulesByEmployeesContains(employee);
    }

    public List<Schedule> getSchedulesForCustomer(long customerId) {
        List<Pet> customerPets = petRepository.findAllByCustomerId(customerId);
        List<Schedule> schedules = scheduleRepository.findAllByPetsIn(customerPets);
        return schedules;

    }
}
