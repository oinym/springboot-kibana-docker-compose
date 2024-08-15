package com.example.accessingdatamysql.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdatamysql.elastic.EcsModel;
import com.example.accessingdatamysql.jparepositories.UserRepository;
import com.example.accessingdatamysql.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin("*")
@RestController
// @RequiredArgsConstructor
@RequestMapping(path = "/users")
@Slf4j
public class MainController {
    @Autowired
    private UserRepository repository;

    @GetMapping(path = "/vitality/{productId}", produces = "application/json")
    public ResponseEntity<Object> getUserprofile(HttpServletRequest request, @PathVariable String productId) {
        log.info("Example  info  message -> Received product ID {}" + productId);
        // log.debug("Example debug message -> Received product ID {}" +
        // request.getPathInfo());
        // log.error("{\"ecs\":{\"firstName\":\"ofir\"}}");

        return new ResponseEntity<>(request.getHeader("x-transaction-id"), HttpStatus.OK);
    }

    @PostMapping(path = "/add", produces = "application/json") // Map ONLY POST Requests
    public @ResponseBody String addNewUser(HttpServletRequest request, @RequestParam String name,
            @RequestParam String email) {
        log.info(request.getMethod() + request.getLocalName());
        User n = new User();
        n.setEmail(email);
        n.setName(name);
        repository.save(n);
        return "Saved";
    }

    @GetMapping
    public @ResponseBody Iterable<User> getAllUsers(HttpServletRequest request) {
        List<User> users = repository.findAll();

        EcsModel ecs = new EcsModel();
        ecs.setMsg("Got a hit");
        try {

            log.info((new ObjectMapper().writer().withDefaultPrettyPrinter()).writeValueAsString(ecs));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return users;
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody User getuserById(@RequestParam Integer id) {
        return repository.findById(id).orElse(null);

    }

    @PutMapping
    public String editUser(@RequestBody User user) {
        repository.save(user);
        return "Saved";
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        repository.deleteById(id);
    }

    @PostMapping(path = "/call", produces = "application/json")
    public ResponseEntity doSomeBodyProcessing(@RequestHeader("X-Id") String xId) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
