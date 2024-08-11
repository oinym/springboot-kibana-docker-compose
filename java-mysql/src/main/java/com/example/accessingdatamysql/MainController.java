package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.accessingdatamysql.jparepositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
@Slf4j
public class MainController {
    @Autowired
    private UserRepository repository;

    @PostMapping(path = "/add") // Map ONLY POST Requests
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
        log.info(request.getMethod() + request.getLocalName());
        return repository.findAll();
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
}
