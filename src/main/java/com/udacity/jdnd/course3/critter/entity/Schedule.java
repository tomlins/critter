package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    //@OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
    //@JoinColumn(name = "schedule_fk")
    @OneToMany
    private List<Employee> employee;

    //@OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
    //@JoinColumn(name = "schedule_fk")
    @OneToMany
    private List<Pet> pets;

    @ElementCollection
    private Set<EmployeeSkill> activities;

    public Schedule() {
        // Default public constructor
    }

    public Schedule(Long id, LocalDate date, List<Pet> pets, Set<EmployeeSkill> activities) {
        this.id = id;
        this.date = date;
        this.pets = pets;
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
