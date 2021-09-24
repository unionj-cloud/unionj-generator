package com.github.myproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import com.github.myproject.vo.*;
import com.github.myproject.myproto.PetProto;
import com.github.myproject.service.PetService;
import java.util.List;
import cloud.unionj.demo.vo.PetVo;
import cloud.unionj.demo.vo.ApiResponse;

@RestController
@RequiredArgsConstructor
public class PetController implements PetProto {

    private final PetService petService;

    @Override
    public ResponseEntity<Void> addPet(
        @RequestBody PetVo body
    ) {
        return petService.addPet(
        body
        );
    }

    @Override
    public ResponseEntity<Void> updatePetWithForm(
        @PathVariable("petId") Long petId,
        @RequestParam(value="name", required=false) String name,
        @RequestParam(value="status", required=false) String status
    ) {
        return petService.updatePetWithForm(
        petId,
                    name,
                    status
        );
    }

    @Override
    public ApiResponse uploadFile(
        @PathVariable("petId") Long petId,
        @RequestParam(value="additionalMetadata", required=false) String additionalMetadata,
        @RequestPart(value="file", required=false) MultipartFile file
    ) {
        return petService.uploadFile(
        petId,
                    additionalMetadata,
                file
        );
    }

}
