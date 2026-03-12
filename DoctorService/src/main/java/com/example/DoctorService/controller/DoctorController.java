package com.example.DoctorService.controller;

import com.example.DoctorService.client.UserClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DoctorController {
    private final UserClient userClient;

    public DoctorController(UserClient userClient) {
        this.userClient = userClient;
    }

//    private final RestTemplate restTemplate;
//
//    public DoctorController(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    @GetMapping("/doctors")
    public String getDoctors() {
//        String users = restTemplate.getForObject("http://UserService/users",String.class);

        String users = userClient.getUsers();
        return "Doctors Fetched, Also Calling -> "+users;
    }
}
