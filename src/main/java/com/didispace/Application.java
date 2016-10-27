package com.didispace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.didispace")
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

	}

}
