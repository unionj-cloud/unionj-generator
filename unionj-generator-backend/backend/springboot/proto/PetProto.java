package com.tsingxiao.unionj.demo.proto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import com.tsingxiao.unionj.demo.vo.Pet;
import com.tsingxiao.unionj.demo.vo.ApiResponse;

public interface PetProto {

    @PostMapping("/pet")
    Pet addPet(
        @RequestBody Pet body
    );

    @PutMapping("/pet")
    Pet updatePet(
        @RequestBody Pet body
    );

    @GetMapping("/pet/findByStatus")
    List<Pet> findPetsByStatus(
        @RequestParam(value="status", required=false) String status
    );

    @GetMapping("/pet/findByTags")
    List<Pet> findPetsByTags(
        @RequestParam(value="tags", required=false) List<String> tags
    );

    @GetMapping("/pet/{petId}")
    Pet getPetById(
        @PathVariable("petId") Long petId
    );

    @PostMapping("/pet/{petId}")
    ResponseEntity<Void> updatePetWithForm(
        @PathVariable("petId") Long petId,
        @RequestParam(value="name", required=false) String name,
        @RequestParam(value="status", required=false) String status
    );

    @DeleteMapping("/pet/{petId}")
    ResponseEntity<Void> deletePet(
        @PathVariable("petId") Long petId
    );

    @PostMapping("/pet/{petId}/uploadImage")
    ApiResponse uploadFile(
        @PathVariable("petId") Long petId,
        @RequestParam(value="additionalMetadata", required=false) String additionalMetadata,
        @RequestPart(value="file", required=false) MultipartFile file
    );

}
