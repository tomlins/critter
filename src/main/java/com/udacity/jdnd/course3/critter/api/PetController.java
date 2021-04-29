package com.udacity.jdnd.course3.critter.api;

import com.udacity.jdnd.course3.critter.domain.entity.Pet;
import com.udacity.jdnd.course3.critter.domain.dto.PetDTO;
import com.udacity.jdnd.course3.critter.service.PetService;
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
        Pet pet = petService.save(petDTOToEntity(petDTO), petDTO.getOwnerId());
        return entityToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return entityToPetDTO(petService.findById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return entityArrayToPetDTOArray(petService.getAll());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetDTO> petDTOList = new ArrayList<>();
        List<Pet> petList = petService.getPetsByOwner(ownerId);
        petList.forEach(pet -> petDTOList.add(entityToPetDTO(pet)));
        return petDTOList;
    }

    /**
     * Helper method to convert PetDTO object into Pet entity
     * @param petDTO
     * @return pet
     */
    private Pet petDTOToEntity(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }

    /**
     * Helper method to convert Pet entity to its corresponding DTO object
     * @param pet
     * @return petDTO
     */
    private PetDTO entityToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }

    /**
     * Helper method to convert an array of Pet entities into an array of PetDTO objects
     * @param petList
     * @return
     */
    private List<PetDTO> entityArrayToPetDTOArray(List<Pet> petList) {
        List<PetDTO> petDTOList = new ArrayList<>();
        for (Pet pet : petList) {
            petDTOList.add(entityToPetDTO(pet));
        }
        return petDTOList;

    }
}
