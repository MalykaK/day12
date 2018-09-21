package com.capgemini.bankapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.exception.LowBalanceException;

import com.capgemini.bankapp.repository.BankAccountRepository;
import com.capgemini.bankapp.service.BankAccountService;
@Service
public class BankAccountServiceImpl implements BankAccountService {

	private BankAccountRepository bankRepository;
/*
	public void setBankRepository(BankAccountRepository bankRepository) {
		this.bankRepository = bankRepository;
	}*/

	@Override
	public double getBalance(long accountId) {
		return bankRepository.getBalance(accountId);

	}

	@Autowired
	private BankAccountServiceImpl(BankAccountRepository bankRepository) {
	super();
	this.bankRepository = bankRepository;
}



	@Override
	public double withdraw(long accountId, double amount) throws LowBalanceException {
		double balance = bankRepository.getBalance(accountId);
		if (balance != -1) {
			if (balance - amount >= 0) {
				bankRepository.updateBalance(accountId, balance - amount);
				return bankRepository.getBalance(accountId);
			}
		}
		return balance;
	}

	@Override
	public double deposit(long accountId, double amount) {
		double balance = bankRepository.getBalance(accountId);
		if (balance != -1) {
			bankRepository.updateBalance(accountId, balance + amount);
			return bankRepository.getBalance(accountId);

		}
		return balance;
	}

	@Override
	public boolean fundTransfer(long fromAccount, long toAccount, double amount) throws LowBalanceException {

		double balance = withdraw(fromAccount, amount);
		if (balance != -1) {
			deposit(toAccount, amount);
			return false;
		}
		return true;
	}

	@Override
	public boolean addBankAccount(BankAccount account) {
		// TODO Auto-generated method stub
		return bankRepository.addBankAccount(account);
	}

	@Override
	public BankAccount findBankAccountById(long accountId) {
		// TODO Auto-generated method stub
		return bankRepository.findBankAccountById(accountId);
	}

	@Override
	public List<BankAccount> findAllBankAccounts() {
		// TODO Auto-generated method stub
		return bankRepository.findAllBankAccounts();
	}

	@Override
	public BankAccount updateBankAccount(BankAccount account) {
		// TODO Auto-generated method stub
		return bankRepository.updateBankAccount(account);
	}

	@Override
	public boolean deleteBankAccount(long accountId) {
		// TODO Auto-generated method stub
		return  bankRepository.deleteBankAccount(accountId);
	}

}
