package com.example.skillfactory.service;

import com.example.skillfactory.model.PropertyType;
import com.example.skillfactory.repository.PropertyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyTypeService {

    @Autowired
    private PropertyTypeRepository propertyTypeRepository;

    public List<PropertyType> findAll() {
        return propertyTypeRepository.findAll();
    }

    public PropertyType findById(Integer id) {
        Optional<PropertyType> propertyType = propertyTypeRepository.findById(id);
        return propertyType.orElse(null);
    }

    public void save(PropertyType propertyType) {
        propertyTypeRepository.save(propertyType);
    }

    public void delete(Integer id) {
        propertyTypeRepository.deleteById(id);
    }
}

