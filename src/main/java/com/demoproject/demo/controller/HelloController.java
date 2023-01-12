package com.demoproject.demo.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    // 1. response body로 출력
    @GetMapping("/helloWorld/string")
    @ResponseBody
    public String helloWorldString() {
        return "hello";
    }

    // 2. Hello class를 사용하여 출력
    @GetMapping("/helloWorld/json")
    @ResponseBody
    public Hello helloWorldJson() {
        Hello hello = new Hello();
        hello.setMessage("Hello");
        return hello;
    }

    // 3. helloWorld.ftl (view)파일을 이용해서 출력
    @GetMapping("/helloWorld/page")
    public String HelloWorldPage() {
        return "helloWorld";
    }
}

@Getter @Setter
class Hello {
    private String message;
}