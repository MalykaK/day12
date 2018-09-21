package com.capgemini.bankapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.capgemini.bankapp.config.AppConfig;
import com.capgemini.bankapp.controller.BankAccountController;
import com.capgemini.bankapp.exception.LowBalanceException;

public class Application {
	public static void main(String[] args) {
	//	ApplicationContext context= new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext context= new AnnotationConfigApplicationContext(AppConfig.class);
		BankAccountController bankAccountController=
		context.getBean("bankAccountController",BankAccountController.class);
		System.out.println(bankAccountController.getBalance(1234));
		System.out.println(bankAccountController.deposit(1234, 20000));
		try {
			System.out.println(bankAccountController.withdraw(1234, 1000));
		} catch (LowBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
