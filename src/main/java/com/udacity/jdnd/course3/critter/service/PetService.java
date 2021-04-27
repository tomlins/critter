package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;
    @Autowired
    CustomerRepository customerRepository;

    public Pet findById(Long id) {
        Optional<Pet> petOptional = petRepository.findById(id);
        return petOptional.orElse(null);
    }

    public Pet save(Pet pet, Long ownerId) {
        Customer customer = customerRepository.getOne(ownerId);
        pet.setCustomer(customer);
        Pet savedPet = petRepository.save(pet);

        customer = savedPet.getCustomer();
        customer.addPet(savedPet);
        customerRepository.save(customer);
        return savedPet;
    }

    public List<Pet> getAll() {
        return petRepository.findAll();
    }

    public List<Pet> getPetsByOwner(Long id) {
        return petRepository.findAllByCustomerId(id);
    }
}
