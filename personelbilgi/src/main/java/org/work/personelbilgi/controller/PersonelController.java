package org.work.personelbilgi.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.work.personelbilgi.core.result.DataResult;
import org.work.personelbilgi.core.result.ErrorDataResult;
import org.work.personelbilgi.core.result.ErrorResult;
import org.work.personelbilgi.core.result.Result;
import org.work.personelbilgi.dto.PersonelDTO;
import org.work.personelbilgi.exception.ResourceNotFoundException;
import org.work.personelbilgi.exception.ValidationException;
import org.work.personelbilgi.service.concretes.PersonelServiceImp;
import org.work.personelbilgi.utils.ResponseUtil;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/personel")
public class PersonelController {

    private final PersonelServiceImp personelService;

    @GetMapping("/getAllPersonel")
    public ResponseEntity<DataResult<List<PersonelDTO>>> getAllPersonel(){
        try{
            return ResponseUtil.buildDataResultResponse(personelService.getAllPersonel());
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDataResult<>(ex.getMessage()));
        }
    }

    @GetMapping("/getPersonelById/{id}")
    public ResponseEntity<DataResult<PersonelDTO>> getPersonelById(@PathVariable Long id){
        try {
            return ResponseUtil.buildDataResultResponse(personelService.getPersonelById(id));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDataResult<>(ex.getMessage()));
        }
    }

    @PostMapping("/addPersonel")
    public ResponseEntity<Result> addPersonel(@Valid @RequestBody PersonelDTO personelDTO){
        try {
            return ResponseUtil.buildResultResponse(personelService.addPersonel(personelDTO));
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(ex.getMessage()));
        }
    }

    @PutMapping("/updatePersonel/{id}")
    public ResponseEntity<Result> updatePersonel(@PathVariable Long id, @Valid @RequestBody PersonelDTO personelDTO){
        try {
            return ResponseUtil.buildResultResponse(personelService.updatePersonel(id, personelDTO));
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(ex.getMessage()));
        }
    }

    @DeleteMapping("/deletePersonel/{id}")
    public ResponseEntity<Result> deletePersonel(@PathVariable Long id){
        try {
            return ResponseUtil.buildResultResponse(personelService.deletePersonel(id));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResult(ex.getMessage()));
        }
    }
}
