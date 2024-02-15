package org.work.personelbilgi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.work.personelbilgi.core.result.DataResult;
import org.work.personelbilgi.core.result.Result;
import org.work.personelbilgi.dto.EgitimDTO;
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
        return ResponseUtil.buildDataResultResponse(egitimService.getAllEgitim());
    }

    @GetMapping("/getEgitimById/{id}")
    public ResponseEntity<DataResult<EgitimDTO>> getEgitimById(@PathVariable Long id){
        return ResponseUtil.buildDataResultResponse(egitimService.getEgitimById(id));
    }

    @GetMapping("/getEgitimByPersonelId/{personelId}")
    public ResponseEntity<DataResult<List<EgitimDTO>>> getEgitimByPersonelId(@PathVariable Long personelId){
        return ResponseUtil.buildDataResultResponse(egitimService.getEgitimByPersonelId(personelId));
    }

    @PostMapping("/addEgitim")
    public ResponseEntity<Result> addEgitim(@Valid @RequestBody EgitimDTO EgitimDTO){
        return ResponseUtil.buildResultResponse(egitimService.addEgitim(EgitimDTO));
    }

    @PutMapping("/updateEgitim/{id}")
    public ResponseEntity<Result> updateEgitim(@PathVariable Long id, @Valid @RequestBody EgitimDTO EgitimDTO){
        return ResponseUtil.buildResultResponse(egitimService.updateEgitim(id, EgitimDTO));
    }

    @DeleteMapping("/deleteEgitim/{id}")
    public ResponseEntity<Result> deleteEgitim(@PathVariable Long id){
        return ResponseUtil.buildResultResponse(egitimService.deleteEgitim(id));
    }
}
