package com.example.contact.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.contact.entity.Contact;
import com.example.contact.repo.ContactRepository;

@Service
public class ContactService {

	@Autowired
	ContactRepository contactRepository;

	public List<Contact> getContacts() {
		Contact contact = new Contact();
		contact.setDeleted(false);

		return contactRepository.findAll(Example.of(contact));
	}

	public Optional<Contact> getContactById(Long contactId) {
		return contactRepository.findById(contactId);
	}

	public boolean saveContact(Contact contact) {

		Contact savedContact = contactRepository.save(contact);
		return savedContact.getContactId() != null;

	}
	
	public Optional<Contact> deleteContactById(Long contactId) {
		
		return contactRepository.findById(contactId);
	}
}
