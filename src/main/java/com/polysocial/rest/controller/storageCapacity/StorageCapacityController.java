package com.polysocial.rest.controller.storageCapacity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.polysocial.consts.StorageCapacityAPI;
import com.polysocial.dto.StorageCapacityDTO;
import com.polysocial.service.StorageCapacity.StorageCapacityService;

@RestController
public class StorageCapacityController {
    
    @Autowired
    private StorageCapacityService storageCapacityService;

    @GetMapping(StorageCapacityAPI.GET_ONE_STORAGE_CAPACITY)
    public ResponseEntity getOneStorageCapacity(@RequestParam Long userId) {
        try{
            return ResponseEntity.ok(storageCapacityService.getOne(userId));
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(StorageCapacityAPI.CREATE_STORAGE_CAPACITY)
    public ResponseEntity createStorageCapacity(@RequestBody StorageCapacityDTO storageCapacityDTO) {
        try{
            return ResponseEntity.ok(storageCapacityService.create(storageCapacityDTO));
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(StorageCapacityAPI.UPDATE_STORAGE_CAPACITY)
    public ResponseEntity updateStorageCapacity(@RequestBody StorageCapacityDTO storageCapacityDTO) {
        try{
            return ResponseEntity.ok(storageCapacityService.update(storageCapacityDTO));
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(StorageCapacityAPI.DELETE_STORAGE_CAPACITY)
    public ResponseEntity deleteStorageCapacity(@RequestParam Long userId) {
        try{
            storageCapacityService.delete(userId);
            return new ResponseEntity(HttpStatus.OK.toString(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
