package org.work.personelbilgi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.work.personelbilgi.core.result.DataResult;
import org.work.personelbilgi.core.result.Result;
import org.work.personelbilgi.dto.ProjeDTO;
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
        return ResponseUtil.buildDataResultResponse(projeService.getAllProje());
    }

    @GetMapping("/getProjeById/{id}")
    public ResponseEntity<DataResult<ProjeDTO>> getProjeById(@PathVariable Long id){
        return ResponseUtil.buildDataResultResponse(projeService.getProjeById(id));
    }

    @GetMapping("/getProjeByPersonelId/{personelId}")
    public ResponseEntity<DataResult<List<ProjeDTO>>> getProjeByPersonelId(@PathVariable Long personelId){
        return ResponseUtil.buildDataResultResponse(projeService.getProjeByPersonelId(personelId));
    }

    @PostMapping("/addProje")
    public ResponseEntity<Result> addProje(@Valid @RequestBody ProjeDTO ProjeDTO){
        return ResponseUtil.buildResultResponse(projeService.addProje(ProjeDTO));
    }

    @PutMapping("/updateProje/{id}")
    public ResponseEntity<Result> updateProje(@PathVariable Long id, @Valid @RequestBody ProjeDTO ProjeDTO){
        return ResponseUtil.buildResultResponse(projeService.updateProje(id, ProjeDTO));
    }

    @DeleteMapping("/deleteProje/{id}")
    public ResponseEntity<Result> deleteProje(@PathVariable Long id){
        return ResponseUtil.buildResultResponse(projeService.deleteProje(id));
    }
}
