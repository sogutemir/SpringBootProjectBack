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
import org.work.personelbilgi.dto.EgitimDTO;
import org.work.personelbilgi.exception.ResourceNotFoundException;
import org.work.personelbilgi.exception.ValidationException;
import org.work.personelbilgi.service.concretes.EgitimServiceImp;
import org.work.personelbilgi.utils.ResponseUtil;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/egitim")
public class EgitimController {

    private final EgitimServiceImp egitimService;

    @GetMapping("/getAllEgitim")
    public ResponseEntity<DataResult<List<EgitimDTO>>> getAllEgitim(){
        try{
            return ResponseUtil.buildDataResultResponse(egitimService.getAllEgitim());
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDataResult<>(ex.getMessage()));
        }
    }

    @GetMapping("/getEgitimById/{id}")
    public ResponseEntity<DataResult<EgitimDTO>> getEgitimById(@PathVariable Long id){
        try{
            return ResponseUtil.buildDataResultResponse(egitimService.getEgitimById(id));
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDataResult<>(ex.getMessage()));
        }
    }

    @GetMapping("/getEgitimByPersonelId/{personelId}")
    public ResponseEntity<DataResult<List<EgitimDTO>>> getEgitimByPersonelId(@PathVariable Long personelId){
        try{
            return ResponseUtil.buildDataResultResponse(egitimService.getEgitimByPersonelId(personelId));
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDataResult<>(ex.getMessage()));
        }
    }

    @PostMapping("/addEgitim")
    public ResponseEntity<Result> addEgitim(@Valid @RequestBody EgitimDTO EgitimDTO){
        try {
            return ResponseUtil.buildResultResponse(egitimService.addEgitim(EgitimDTO));
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(ex.getMessage()));
        }
    }

    @PutMapping("/updateEgitim/{id}")
    public ResponseEntity<Result> updateEgitim(@PathVariable Long id, @Valid @RequestBody EgitimDTO EgitimDTO){
        try{
            return ResponseUtil.buildResultResponse(egitimService.updateEgitim(id, EgitimDTO));
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(ex.getMessage()));
        }
    }

    @DeleteMapping("/deleteEgitim/{id}")
    public ResponseEntity<Result> deleteEgitim(@PathVariable Long id){
        try{
            return ResponseUtil.buildResultResponse(egitimService.deleteEgitim(id));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResult(ex.getMessage()));
        }
    }
}
