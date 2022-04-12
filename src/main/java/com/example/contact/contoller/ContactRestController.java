package com.example.contact.contoller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.contact.constant.AppConstants;
import com.example.contact.entity.Contact;
import com.example.contact.service.ContactService;
import com.example.contact.prop.AppProperties;

@RestController
public class ContactRestController {
	
	
	@Autowired
	ContactService contactService;
	
	@Autowired
	AppProperties appProperties;
	
	
	@GetMapping("/contacts")
	public List<Contact> getContacts() {
		return contactService.getContacts();
	}
	
	@GetMapping("/contact/{id}")
	public Contact getContactById(@PathVariable("id") Long id) {
		Optional<Contact> contactOptional = contactService.getContactById(id);
		if(contactOptional.isPresent()) {
			return contactOptional.get() ;
		}
		else {
			return null;
		}

	}
	
	@PostMapping("/contact")
	public String saveContacts(@RequestBody Contact contact) {
		
		Map<String, String> messages = appProperties.getMessages();
		
		boolean saveResult = contactService.saveContact(contact );
		
		if (saveResult) {
			return messages.get(AppConstants.CONTACT_SAVE_SUCCESS);
		} else {
			return messages.get(AppConstants.CONTACT_SAVE_FAIL);
		}
	}
	
	@DeleteMapping("/contact/{id}")
	public String deleteContact(@PathVariable("id") Long id) {
		Optional<Contact> contactOptional = contactService.getContactById(id);
		if(contactOptional.isPresent()) {

			Contact conatactTodelete = contactOptional.get();
			conatactTodelete.setDeleted(true);
			contactService.saveContact(contactOptional.get());
			
			return "contact deleted " ;
		}
		else {
			return "No contact present with id " + id;
		}

	}
	
	
	

}
