package com.github.myproject.service;

import org.springframework.stereotype.Service;
import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import com.github.myproject.vo.*;
import java.util.List;
import cloud.unionj.demo.vo.PetVo;
import cloud.unionj.demo.vo.ApiResponse;

@Service
public class PetServiceImpl implements PetService {

    @SneakyThrows
    @Override
    public ResponseEntity<Void> addPet(
    PetVo body
    ){
        throw new Exception("Implement me");
    }

    @SneakyThrows
    @Override
    public ResponseEntity<Void> updatePetWithForm(
    Long petId,
                String name,
                String status
    ){
        throw new Exception("Implement me");
    }

    @SneakyThrows
    @Override
    public ApiResponse uploadFile(
    Long petId,
                String additionalMetadata,
            MultipartFile file
    ){
        throw new Exception("Implement me");
    }

}
