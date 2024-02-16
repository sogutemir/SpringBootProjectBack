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
import org.work.personelbilgi.dto.ProjeDTO;
import org.work.personelbilgi.exception.ResourceNotFoundException;
import org.work.personelbilgi.exception.ValidationException;
import org.work.personelbilgi.service.concretes.ProjeServiceImp;
import org.work.personelbilgi.utils.ResponseUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/proje")
public class ProjeController {

    private final ProjeServiceImp projeService;

    @GetMapping("/getAllProje")
    public ResponseEntity<DataResult<List<ProjeDTO>>> getAllProje(){
        try{
            return ResponseUtil.buildDataResultResponse(projeService.getAllProje());
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDataResult<>(ex.getMessage()));
        }
    }

    @GetMapping("/getProjeById/{id}")
    public ResponseEntity<DataResult<ProjeDTO>> getProjeById(@PathVariable Long id){
        try {
            return ResponseUtil.buildDataResultResponse(projeService.getProjeById(id));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDataResult<>(ex.getMessage()));
        }
    }

    @GetMapping("/getProjeByPersonelId/{personelId}")
    public ResponseEntity<DataResult<List<ProjeDTO>>> getProjeByPersonelId(@PathVariable Long personelId){
        try{
            return ResponseUtil.buildDataResultResponse(projeService.getProjeByPersonelId(personelId));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDataResult<>(ex.getMessage()));
        }
    }

    @PostMapping("/addProje")
    public ResponseEntity<Result> addProje(@Valid @RequestBody ProjeDTO projeDTO){
        try {
            return ResponseUtil.buildResultResponse(projeService.addProje(projeDTO));
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(ex.getMessage()));
        }
    }

    @PutMapping("/updateProje/{id}")
    public ResponseEntity<Result> updateProje(@PathVariable Long id, @Valid @RequestBody ProjeDTO projeDTO){
        try {
            return ResponseUtil.buildResultResponse(projeService.updateProje(id, projeDTO));
        } catch (ValidationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResult(ex.getMessage()));
        }
    }

    @DeleteMapping("/deleteProje/{id}")
    public ResponseEntity<Result> deleteProje(@PathVariable Long id){
        try {
            return ResponseUtil.buildResultResponse(projeService.deleteProje(id));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResult(ex.getMessage()));
        }
    }
}
