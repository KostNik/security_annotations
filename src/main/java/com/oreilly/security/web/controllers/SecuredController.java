package com.oreilly.security.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/app")
public class SecuredController {

    @RequestMapping(value = "/secured", method = RequestMethod.GET)
    public String getAppointmentSecuredPage(@RequestParam(required = false, name = "secured") String secured) {
        return "services";
    }


}
