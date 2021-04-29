package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.orElse(null);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, Long id) {
        Employee employee = employeeRepository.getOne(id);
        employee.setDaysAvailable(daysAvailable);
        save(employee);
    }

    public List<Employee> getEmployeesForService(LocalDate date, Set<EmployeeSkill> skills) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        List<Employee> matchedEmployees = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAllByDaysAvailable(dayOfWeek);
        for (Employee employee : employees) {
            Set<EmployeeSkill> employeeSkills = employee.getSkills();
            if (employeeSkills.containsAll(skills))
                matchedEmployees.add(employee);
        }
        return matchedEmployees;
    }
}
