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
        storageCapacityDTO.setFullName(userRepo.findById(userId).get().getFullName());
        return storageCapacityDTO;
    }

    @Override
    public StorageCapacityDTO update(StorageCapacityDTO storageCapacityDTO) {
        return modelMapper.map(storageCapacityRepo.save(modelMapper.map(storageCapacityDTO, StorageCapacity.class)), StorageCapacityDTO.class);
    }

    @Override
    public StorageCapacityDTO create(StorageCapacityDTO storageCapacityDTO) {
        return modelMapper.map(storageCapacityRepo.save(modelMapper.map(storageCapacityDTO, StorageCapacity.class)), StorageCapacityDTO.class);
    }

    @Override
    public void delete(Long userId) {
        storageCapacityRepo.delete(storageCapacityRepo.findByUserId(userId));
    }

}

