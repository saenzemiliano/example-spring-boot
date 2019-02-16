package com.example.springboot.demospringboot.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springboot.demospringboot.common.TextPrinterCustomer;
import com.example.springboot.demospringboot.model.db.Customer;
import com.example.springboot.demospringboot.rest.CustumerRestController;

@Controller
public class TextWebController {

	@Autowired
	private CustumerRestController custumerRestController;

	@ResponseBody
	@RequestMapping(value = "/report/cutomer/text", produces = MediaType.TEXT_PLAIN_VALUE)
	public String plainTextAnnotation(HttpServletResponse response) {
		Iterable<Customer> customers = custumerRestController.findAll();
		TextPrinterCustomer textPrinter = new TextPrinterCustomer(customers);
		try {
			return textPrinter.generate();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

}