package com.wojcik.back_end.controller;

import com.wojcik.back_end.domain.User;
import com.wojcik.back_end.repository.UserRepository;
import com.wojcik.back_end.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@ResponseBody
@RequestMapping("/hello-world")
public class HelloWorldController {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private HelloWorldService helloWorldService;

    // GET /hello-world
    @GetMapping
    public String helloWorld() {
        return this.helloWorldService.helloWorld( "Gabriel");
    }

    @PostMapping("/{id}")
    public String helloWorldPost(@PathVariable("id") String id,@RequestParam(value = "filter", defaultValue = "nenhum") String filter,  @RequestBody User body){
        return  "Hello World Post" + body.getClass().getName() + id + filter;
    }

    @PostMapping("/create-user")
    public String createUserPost(@RequestBody User body) {
        try {
            // Log do corpo recebido
            System.out.println("Received user: " + body);

            // Validação dos campos
            if (body.getEmail() == null || body.getEmail().isEmpty()) {
                return "Error: Email is required.";
            }
            if (body.getName() == null || body.getName().isEmpty()) {
                return "Error: Name is required.";
            }
            if (body.getPassword() == null || body.getPassword().isEmpty()) {
                return "Error: Password is required.";
            }

            // Verifica se o email já existe no banco de dados
            if (userRepository.existsByEmail(body.getEmail())) {
                return "Error: Email already exists.";
            }

            // Salva o usuário
            userRepository.save(body);
            return "User created successfully: " + body.getName();
        } catch (Exception e) {
            return "Error creating user: " + e.getMessage();
        }
    }



}
