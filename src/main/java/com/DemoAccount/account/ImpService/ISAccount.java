package com.DemoAccount.account.ImpService;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.DemoAccount.account.Model.Account;
import com.DemoAccount.account.Repository.AccountRepository;
import com.DemoAccount.account.Service.AccountService;

@Component
public class ISAccount implements AccountService {

	@Autowired
	private AccountRepository accountRepo;

	@Override
	public List<Account> findAllAccount() {
		return accountRepo.findAll();
	}

	@Override
	public void saveAccount(Account account) {
		accountRepo.save(account);

	}

	@Override
	public Account findAccountById(String id) {
		// TODO Auto-generated method stub
		return accountRepo.findById(id).get();
	}

	@Override
	public Optional<Account> findAccountByLicenceNuber(String licenceNumber) {

		return accountRepo.findByLicenceNumber(licenceNumber);
	}

	@Transactional
	public Boolean findAccountByAccountType(String companyCode, String accountType) {
		List<Account> list = accountRepo.findByAccountType(companyCode);
		for (Account a : list) {
			if (a.getAccountType().equals(accountType)) {
				return true;
			}
		}
		return false;
	}

}
