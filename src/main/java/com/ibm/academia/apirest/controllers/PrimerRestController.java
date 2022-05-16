package com.ibm.academia.apirest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restapi")
public class PrimerRestController {

	//ESTO DEBE IR EN LOS CONTROLLERS, DAO Y DAOIMPL
	Logger logger = LoggerFactory.getLogger(PrimerRestController.class);
	
	@GetMapping
	public String HolaMundo() {
		logger.trace("trace log");
		logger.debug("debug log");
		
		logger.info("info log");
		logger.warn("warn log");
		logger.error("error log");
		
		return "Hola mundo api";
	}
	
}
