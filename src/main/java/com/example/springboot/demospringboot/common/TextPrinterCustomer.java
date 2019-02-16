package com.example.springboot.demospringboot.common;

import com.example.springboot.demospringboot.model.db.Customer;

public class TextPrinterCustomer extends TextPrinter<Customer> {

	public TextPrinterCustomer(Iterable<Customer> it) {

		super(it,
				new TextPrinterColumn[] { new TextPrinterColumn("Type Document", 10),
						new TextPrinterColumn("Document", 25), new TextPrinterColumn("Name", 50),
						new TextPrinterColumn("Phone", 20), new TextPrinterColumn("State", 50) },
				new TextPrinterRowCustomer());

	}

}
