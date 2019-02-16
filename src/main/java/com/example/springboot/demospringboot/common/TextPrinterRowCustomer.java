package com.example.springboot.demospringboot.common;

import com.example.springboot.demospringboot.model.db.Customer;

public class TextPrinterRowCustomer extends TextPrinterRow<Customer> {

	@Override
	public String rowByIndex(Customer customer, int indexRow) throws Exception {

		switch (indexRow) {
		case 0:
			return customer.getTypeDocument();
		case 1:
			return customer.getDocument();
		case 2:
			return customer.getName();
		case 3:
			return customer.getPhone();
		case 4:
			return customer.getState();
		default:
			throw new Exception("Outbound Index" + indexRow);
		}
	}
}