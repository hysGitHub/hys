package com.didispace.web;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest")
public class HelloRest {

	@RequestMapping(value="/",method = RequestMethod.GET)
	public Object get(){
		return new Date();
	}
}
