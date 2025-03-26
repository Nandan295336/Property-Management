package com.mycompany.property_management.service;

import com.mycompany.property_management.dto.PropertyDTO;

import java.util.List;

public interface PropertyService {

    PropertyDTO saveProperty(PropertyDTO propertyDTO);
    List<PropertyDTO> getAllProperties();
    List<PropertyDTO> getAllPropertiesForUser(Long userId);
    PropertyDTO updateProperty(PropertyDTO propertyDTO, Long id);
    PropertyDTO updatePropertyDescription(String description, Long id);
    PropertyDTO updatePropertyPrice(Double price, Long id);
    void deleteProperty(Long id);

}
