package com.oreilly.security.web.controllers;

import com.oreilly.security.domain.entities.Appointment;
import com.oreilly.security.domain.entities.AutoUser;
import com.oreilly.security.domain.repositories.AppointmentRepository;
import com.oreilly.security.domain.repositories.AppointmentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;


@Controller()
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentUtils appointmentUtils;

    @ModelAttribute
    public Appointment getAppointment() {
        return new Appointment();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAppointmentPage() {
        return "appointments";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public List<Appointment> saveAppointment(@ModelAttribute Appointment appointment) {
        AutoUser user = (AutoUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        appointment.setUser(user);
        appointment.setStatus("Initial");
        appointmentRepository.save(appointment);
        return this.appointmentRepository.findAll();
    }

    @ModelAttribute("isUser")
    public boolean isUser(Authentication authentication) {
        return authentication != null
                && authentication.getAuthorities().contains(AuthorityUtils.createAuthorityList("ROLE_USER").get(0));
    }

    @ResponseBody
    @RequestMapping("/all")
    public List<Appointment> getAppointments(Authentication auth) {
        return this.appointmentRepository.findByUser((AutoUser) auth.getPrincipal());
    }

    @RequestMapping("/{appointmentId}")
    @PostAuthorize("principal.autoUserId== #model[appointment].user.uatoUserId")
    public String getAppointment(@PathVariable("appointmentId") Long appointmentId, Model model) {
        Appointment appointment = appointmentRepository.findOne(appointmentId);
        model.addAttribute("appointment", appointment);
        return "appointment";
    }

    @ResponseBody
    @RequestMapping("/confirm")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String confirm() {
        return "confirm";
    }

    @ResponseBody
    @RequestMapping("/cancel")
    @RolesAllowed("ROLE_ADMIN")
    public String cancel() {
        return "cancel";
    }


    @RequestMapping("/testPreFilter")
    @ResponseBody
    public String testPreFilter(Authentication authentication) {
        AutoUser autoUser = (AutoUser) authentication.getPrincipal();
        AutoUser otherAutoUser = new AutoUser();
        otherAutoUser.setEmail("some@email.com");
        otherAutoUser.setAutoUserId(100L);

        return appointmentUtils.saveAll(new ArrayList<Appointment>() {
            {
                add(appointmentUtils.createAppointment(autoUser));
                add(appointmentUtils.createAppointment(otherAutoUser));
            }
        });
    }

}
