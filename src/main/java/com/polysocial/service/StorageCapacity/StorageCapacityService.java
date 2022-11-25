package com.polysocial.service.StorageCapacity;

import com.polysocial.dto.StorageCapacityDTO;

public interface StorageCapacityService {
    
    StorageCapacityDTO getOne(Long userId);
    StorageCapacityDTO update(StorageCapacityDTO storageCapacityDTO);
    StorageCapacityDTO create(StorageCapacityDTO storageCapacityDTO);
    void delete(Long userId);
}
