package com.example.skillfactory.service;

import com.example.skillfactory.model.Property;
import com.example.skillfactory.repository.PropertyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> findAll() {
        return propertyRepository.findAll();
    }

    public Property findById(Integer id) {
        Optional<Property> property = propertyRepository.findById(id);
        return property.orElse(null);
    }

    public void save(Property property) {
        if (property.getPropertyId() == null) {
            propertyRepository.save(property);
        } else {
            propertyRepository.save(property);
        }
    }

    @Transactional
    public void delete(int propertyId) {
        propertyRepository.deleteById(propertyId);
    }
}

