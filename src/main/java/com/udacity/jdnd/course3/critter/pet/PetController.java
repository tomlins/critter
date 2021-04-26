package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.pet.utils.EntityHelper;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = petService.save(EntityHelper.petDTOToEntity(petDTO), petDTO.getOwnerId());
        return EntityHelper.entityToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return EntityHelper.entityToPetDTO(petService.findById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetDTO> petDTOList = new ArrayList<>();
        List<Pet> petList = petService.getPetsByOwner(ownerId);
        petList.forEach(pet -> petDTOList.add(EntityHelper.entityToPetDTO(pet)));
        return petDTOList;
    }

}
