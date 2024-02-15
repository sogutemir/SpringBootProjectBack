package org.work.personelbilgi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.work.personelbilgi.core.result.DataResult;
import org.work.personelbilgi.core.result.Result;
import org.work.personelbilgi.dto.DeneyimDTO;
import org.work.personelbilgi.service.concretes.DeneyimServiceImp;
import org.work.personelbilgi.utils.ResponseUtil;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/deneyim")
public class DeneyimController {
    
    private final DeneyimServiceImp deneyimService;

    @GetMapping("/getAllDeneyim")
    public ResponseEntity<DataResult<List<DeneyimDTO>>> getAllDeneyim(){
        return ResponseUtil.buildDataResultResponse(deneyimService.getAllDeneyim());
    }

    @GetMapping("/getDeneyimById/{id}")
    public ResponseEntity<DataResult<DeneyimDTO>> getDeneyimById(@PathVariable Long id){
        return ResponseUtil.buildDataResultResponse(deneyimService.getDeneyimById(id));
    }

    @GetMapping("/getDeneyimByPersonelId/{personelId}")
    public ResponseEntity<DataResult<List<DeneyimDTO>>> getDeneyimByPersonelId(@PathVariable Long personelId){
        return ResponseUtil.buildDataResultResponse(deneyimService.getDeneyimByPersonelId(personelId));
    }

    @PostMapping("/addDeneyim")
    public ResponseEntity<Result> addDeneyim(@Valid @RequestBody DeneyimDTO DeneyimDTO){
        return ResponseUtil.buildResultResponse(deneyimService.addDeneyim(DeneyimDTO));
    }

    @PutMapping("/updateDeneyim/{id}")
    public ResponseEntity<Result> updateDeneyim(@PathVariable Long id, @Valid @RequestBody DeneyimDTO DeneyimDTO){
        return ResponseUtil.buildResultResponse(deneyimService.updateDeneyim(id, DeneyimDTO));
    }

    @DeleteMapping("/deleteDeneyim/{id}")
    public ResponseEntity<Result> deleteDeneyim(@PathVariable Long id){
        return ResponseUtil.buildResultResponse(deneyimService.deleteDeneyim(id));
    }
}
