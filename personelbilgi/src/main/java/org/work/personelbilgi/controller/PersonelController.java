package org.work.personelbilgi.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.work.personelbilgi.core.result.DataResult;
import org.work.personelbilgi.core.result.Result;
import org.work.personelbilgi.dto.PersonelDTO;
import org.work.personelbilgi.service.concretes.PersonelServiceImpl;
import org.work.personelbilgi.utils.ResponseUtil;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/personel")
public class PersonelController {

    private final PersonelServiceImpl personelService;

    @GetMapping("/getAllPersonel")
    public ResponseEntity<DataResult<List<PersonelDTO>>> getAllPersonel(){
        return ResponseUtil.buildDataResultResponse(personelService.getAllPersonel());
    }

    @GetMapping("/getPersonelById/{id}")
    public ResponseEntity<DataResult<PersonelDTO>> getPersonelById(@PathVariable Long id){
        return ResponseUtil.buildDataResultResponse(personelService.getPersonelById(id));
    }

    @PostMapping("/addPersonel")
    public ResponseEntity<Result> addPersonel(@Valid @RequestBody PersonelDTO personelDTO){
        return ResponseUtil.buildResultResponse(personelService.addPersonel(personelDTO));
    }

    @PutMapping("/updatePersonel/{id}")
    public ResponseEntity<Result> updatePersonel(@PathVariable Long id, @Valid @RequestBody PersonelDTO personelDTO){
        return ResponseUtil.buildResultResponse(personelService.updatePersonel(id, personelDTO));
    }

    @DeleteMapping("/deletePersonel/{id}")
    public ResponseEntity<Result> deletePersonel(@PathVariable Long id){
        return ResponseUtil.buildResultResponse(personelService.deletePersonel(id));
    }
}
