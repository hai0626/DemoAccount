package com.DemoAccount.account.Controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.DemoAccount.account.Extension.HandleMessage;
import com.DemoAccount.account.Model.Account;

import com.DemoAccount.account.Service.AccountService;


@Controller
public class AccountController {	
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private HandleMessage message;

	private Locale locale;
	
	final static Logger logger = Logger.getLogger(AccountController.class);

	@GetMapping("/")
	public ModelAndView index(String local) {
		ModelAndView mav = new ModelAndView("index");
		if (local != null) {
			locale = new Locale(local);
		} else {
			locale = new Locale("us");
		}
		ResourceBundle bundle = ResourceBundle.getBundle("res", locale);
		Enumeration<String> keys = bundle.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = bundle.getString(key);
			mav.addObject(key, value);
		}
		List<Account> list = accountService.findAllAccount();
		mav.addObject("account", list);
		return mav;
	}

	@GetMapping("/CreateAccount")
	public ModelAndView addClient(@ModelAttribute Account account) {
		ModelAndView mav = new ModelAndView("create-account");
		Account newAccount = new Account();
		mav.addObject("account", newAccount);
		return mav;
	}

	@PostMapping("/SaveAccount")
	public String createClient(@Valid Account account, BindingResult bindingResult, RedirectAttributes model) {
		Optional<Account> numberByLicenceNumber = accountService.findAccountByLicenceNuber(account.getLicenceNumber());
		boolean check = accountService.findAccountByAccountType(account.getCompanyCode(), account.getAccountType());
		if (bindingResult.hasErrors()) {
			return "create-account";
		}
		if (numberByLicenceNumber.isPresent()) {
			bindingResult.rejectValue("LicenceNumber", "account.LicenceNumber", message.existLicenceNumber());
			logger.error("This is error : " + message.existLicenceNumber());
			return "create-account";
		}

		if (check == true) {
			bindingResult.rejectValue("AccountType", "account.AccountType", message.duplicateAccountType());
			logger.error("This is error : " + message.duplicateAccountType());
			return "create-account";
		}
		accountService.saveAccount(account);
		model.addFlashAttribute("success", " Create account successfully");
		return "redirect:/";
	}

	@GetMapping("/ModifyAccount")
	public ModelAndView MmdifyClient(@RequestParam String Id) {
		ModelAndView mav = new ModelAndView("modify-account");
		Account account = accountService.findAccountById(Id);
		mav.addObject("account", account);
		return mav;
	}

	@PostMapping("/UpdateAccount")
	public String UpdateClient(@Valid Account account, BindingResult bindingResult, RedirectAttributes model) {
		// Optional<Account> numberByLicenceNumber =
		// accountService.findAccountByLicenceNuber(account.getLicenceNumber());

		if (bindingResult.hasErrors()) {
			return "modify-account";
		}
//		if (numberByLicenceNumber.isPresent()) {
//			bindingResult.rejectValue("LicenceNumber", "account.LicenceNumber", "Existing Licence number");
//			return "modify-account";
//		}
		accountService.saveAccount(account);
		model.addFlashAttribute("success", " Update account successfully");
		return "redirect:/";
	}

	@GetMapping("/DetailAccount")
	public ModelAndView etailAccount(@RequestParam String Id) {
		ModelAndView mav = new ModelAndView("detail-account");
		Account account = accountService.findAccountById(Id);
		mav.addObject("account", account);
		return mav;
	}
}
