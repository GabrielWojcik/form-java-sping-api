package com.wojcik.back_end.controller;

import com.wojcik.back_end.domain.User;
import com.wojcik.back_end.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@ResponseBody
@RequestMapping("/hello-world")
public class HelloWorldController {

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

}
