package com.oreilly.security.domain.repositories;

import com.oreilly.security.domain.entities.Appointment;
import com.oreilly.security.domain.entities.AutoUser;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppointmentUtils {

    @PreFilter("principal.autoUserId == filterObject.user.autoUserId")
    public String saveAll(List<Appointment> appointments) {
        StringBuilder stringBuilder = new StringBuilder(0);


        for (Appointment appointment : appointments) {
            stringBuilder.append(appointment.getUser().getEmail());
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }


    public Appointment createAppointment(AutoUser autoUser) {
        Appointment appointment = new Appointment();
        appointment.setUser(autoUser);
        return appointment;
    }

}
