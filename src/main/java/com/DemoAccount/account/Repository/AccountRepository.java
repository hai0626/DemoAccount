package com.DemoAccount.account.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DemoAccount.account.Model.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	
	@Query("SELECT a FROM Account a WHERE a.LicenceNumber = ?1")
	Optional<Account> findByLicenceNumber(String licenceNumber);
	
	@Query("SELECT a FROM Account a WHERE a.CompanyCode = :companyCode")
	List<Account> findByAccountType(String companyCode);

}
