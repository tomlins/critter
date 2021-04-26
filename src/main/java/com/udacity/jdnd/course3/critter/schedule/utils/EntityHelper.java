package com.udacity.jdnd.course3.critter.schedule.utils;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.BeanUtils;

public class EntityHelper {

    public static Schedule ScheduleDTOToEntity(ScheduleDTO ScheduleDTO) {
        Schedule Schedule = new Schedule();
        BeanUtils.copyProperties(ScheduleDTO, Schedule);
        return Schedule;
    }

    public static ScheduleDTO entityToScheduleDTO(Schedule Schedule) {
        ScheduleDTO ScheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(Schedule, ScheduleDTO);
        return ScheduleDTO;
    }



}
