package com.oreilly.security.domain.repositories;

import com.oreilly.security.domain.entities.AutoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oreilly.security.domain.entities.Appointment;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByUser(AutoUser user);

}
