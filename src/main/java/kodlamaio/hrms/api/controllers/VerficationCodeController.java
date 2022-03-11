package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.concretes.VerificationCodeManager;
import kodlamaio.hrms.core.utilities.results.Result;



@RestController
@RequestMapping("/api/verificationcodes")
public class VerficationCodeController {

	
	private VerificationCodeManager verificationCodeManager;
	
	@Autowired
	public VerficationCodeController(VerificationCodeManager verificationCodeManager) {
		this.verificationCodeManager =verificationCodeManager;
	}
	
	@PostMapping("/verify/{userId}/{code}")
	public Result verifyUser(@PathVariable int userId, @PathVariable String code) {
		
		return this.verificationCodeManager.verifyUser(userId,code);		
	}
	
}
