package org.work.personelbilgi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.work.personelbilgi.core.result.DataResult;
import org.work.personelbilgi.core.result.Result;
import org.work.personelbilgi.dto.EtkinlikDTO;
import org.work.personelbilgi.service.concretes.EtkinlikServiceImp;
import org.work.personelbilgi.utils.ResponseUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/etkinlik")
public class EtkinlikController {
    
    private final EtkinlikServiceImp etkinlikService;

    @GetMapping("/getAllEtkinlik")
    public ResponseEntity<DataResult<List<EtkinlikDTO>>> getAllEtkinlik(){
        return ResponseUtil.buildDataResultResponse(etkinlikService.getAllEtkinlik());
    }

    @GetMapping("/getEtkinlikById/{id}")
    public ResponseEntity<DataResult<EtkinlikDTO>> getEtkinlikById(@PathVariable Long id){
        return ResponseUtil.buildDataResultResponse(etkinlikService.getEtkinlikById(id));
    }

    @GetMapping("/getEtkinlikByPersonelId/{personelId}")
    public ResponseEntity<DataResult<List<EtkinlikDTO>>> getEtkinlikByPersonelId(@PathVariable Long personelId){
        return ResponseUtil.buildDataResultResponse(etkinlikService.getEtkinlikByPersonelId(personelId));
    }

    @PostMapping("/addEtkinlik")
    public ResponseEntity<Result> addEtkinlik(@Valid @RequestBody EtkinlikDTO EtkinlikDTO){
        return ResponseUtil.buildResultResponse(etkinlikService.addEtkinlik(EtkinlikDTO));
    }

    @PutMapping("/updateEtkinlik/{id}")
    public ResponseEntity<Result> updateEtkinlik(@PathVariable Long id, @Valid @RequestBody EtkinlikDTO EtkinlikDTO){
        return ResponseUtil.buildResultResponse(etkinlikService.updateEtkinlik(id, EtkinlikDTO));
    }

    @DeleteMapping("/deleteEtkinlik/{id}")
    public ResponseEntity<Result> deleteEtkinlik(@PathVariable Long id){
        return ResponseUtil.buildResultResponse(etkinlikService.deleteEtkinlik(id));
    }
}
