package com.example.contact.repo;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.contact.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

	
}