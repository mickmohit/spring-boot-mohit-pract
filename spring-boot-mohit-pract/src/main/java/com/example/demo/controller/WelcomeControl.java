package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping("/asa")
public class WelcomeControl {

	@Value("${welcome.message}")
	private String message="Hello";
	
	@Autowired
	LoginService service;
	
	//@GetMapping("/get")
	@RequestMapping(value="/login", method = RequestMethod.GET) 
	public String welcome(Map<String, Object> model) 
	{
		model.put("message",this.message);
		return "welcome";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST) 
	public String resend(Map<String, Object> model,@RequestParam String name, @RequestParam String password) 
	{
		boolean isValidUser = service.validateUser(name, password);

        if (!isValidUser) {
            model.put("errorMessage", "Invalid Credentials");
            return "welcome";
        }

        model.put("name", name);
        model.put("password", password);

        return "success";
	}
	
}

