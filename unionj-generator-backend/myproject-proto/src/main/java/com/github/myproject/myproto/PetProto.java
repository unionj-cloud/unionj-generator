package com.github.myproject.myproto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import com.github.myproject.vo.*;
import java.util.List;
import cloud.unionj.demo.vo.PetVo;
import cloud.unionj.demo.vo.ApiResponse;

public interface PetProto {

    @PostMapping("/pet")
    ResponseEntity<Void> addPet(
        @RequestBody PetVo body
    );

    @PostMapping("/pet/{petId}")
    ResponseEntity<Void> updatePetWithForm(
        @PathVariable("petId") Long petId,
        @RequestParam(value="name", required=false) String name,
        @RequestParam(value="status", required=false) String status
    );

    @PostMapping("/pet/{petId}/uploadImage")
    ApiResponse uploadFile(
        @PathVariable("petId") Long petId,
        @RequestParam(value="additionalMetadata", required=false) String additionalMetadata,
        @RequestPart(value="file", required=false) MultipartFile file
    );

}
