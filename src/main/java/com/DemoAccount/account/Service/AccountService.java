package com.DemoAccount.account.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.DemoAccount.account.Model.Account;


@Service
public interface AccountService{
	
	List<Account> findAllAccount();
	void saveAccount(Account account);
	Account findAccountById(String id );
	Optional<Account> findAccountByLicenceNuber(String licenceNumber);
	Boolean findAccountByAccountType(String companyCode,String accountType );


}
