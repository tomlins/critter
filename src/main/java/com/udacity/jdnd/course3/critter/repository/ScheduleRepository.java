package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.domain.entity.Employee;
import com.udacity.jdnd.course3.critter.domain.entity.Pet;
import com.udacity.jdnd.course3.critter.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> getSchedulesByEmployeesContains(Employee employee);
    List<Schedule> getSchedulesByPetsContains(Pet pet);
    List<Schedule> findAllByPetsIn(List<Pet> customerPets);
}
