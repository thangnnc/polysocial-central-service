package com.polysocial.service.impl.storageCapacity;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polysocial.dto.StorageCapacityDTO;
import com.polysocial.entity.StorageCapacity;
import com.polysocial.entity.Users;
import com.polysocial.repo.StorageCapacityRepo;
import com.polysocial.repo.UserRepo;
import com.polysocial.service.StorageCapacity.StorageCapacityService;

@Service
public class StorageCapacityServiceImpl implements StorageCapacityService {

    @Autowired
    private StorageCapacityRepo storageCapacityRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Override
    public StorageCapacityDTO getOne(Long userId) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        StorageCapacity storageCapacity = storageCapacityRepo.findByUserId(userId);
        StorageCapacityDTO storageCapacityDTO = modelMapper.map(storageCapacity, StorageCapacityDTO.class);
        storageCapacityDTO.setUserId(userId);
        storageCapacityDTO.setFullName(userRepo.findById(userId).get().getFullName());
        return storageCapacityDTO;
    }

    @Override
    public StorageCapacityDTO update(StorageCapacityDTO storageCapacityDTO) {
        return modelMapper.map(storageCapacityRepo.save(modelMapper.map(storageCapacityDTO, StorageCapacity.class)), StorageCapacityDTO.class);
    }

    @Override
    public StorageCapacityDTO create(StorageCapacityDTO storageCapacityDTO) {
        Users user = userRepo.findById(storageCapacityDTO.getUserId()).get();
        StorageCapacity storageCapacity = modelMapper.map(storageCapacityDTO, StorageCapacity.class);
        storageCapacity.setUser(user);
        storageCapacity.setUsed(0L);
        System.out.println(storageCapacity.getCapacity());
        System.out.println(storageCapacity.getUsed());
        storageCapacity = storageCapacityRepo.save(storageCapacity);
        return modelMapper.map(storageCapacity, StorageCapacityDTO.class);
    }

    @Override
    public void delete(Long userId) {
        storageCapacityRepo.delete(storageCapacityRepo.findByUserId(userId));
    }

}

