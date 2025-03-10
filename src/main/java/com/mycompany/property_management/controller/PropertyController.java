package com.mycompany.property_management.controller;

import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

   // @Value("${pms.dummy}")
   // private String dummy;

    @Autowired
    private PropertyService propertyService;

    //RESTFUL api is just mapping of a url to a java class function
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @PostMapping("/properties")
    public ResponseEntity<PropertyDTO> saveProperty(@RequestBody PropertyDTO propertyDTO) {

        propertyDTO = propertyService.saveProperty(propertyDTO);

        return new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);
    }

    @GetMapping("/properties")
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        //System.out.println("Inside controller"+dummy);
        List<PropertyDTO> propertyList = propertyService.getAllProperties();
        return new ResponseEntity<>(propertyList, HttpStatus.OK);

    }

    @PutMapping("/properties/{propertyId}")
    public ResponseEntity<PropertyDTO> updateAllProperty(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId)
    {
        propertyDTO = propertyService.updateProperty(propertyDTO, propertyId);
        return new ResponseEntity<>(propertyDTO, HttpStatus.CREATED);

    }

    @PatchMapping("/properties/update-description/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyDescription(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId)
    {
        propertyDTO = propertyService.updatePropertyDescription(propertyDTO, propertyId);
        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);

    }

    @PatchMapping("/properties/update-price/{propertyId}")
    public ResponseEntity<PropertyDTO> updatePropertyPrice(@RequestBody PropertyDTO propertyDTO, @PathVariable Long propertyId)
    {
        propertyDTO = propertyService.updatePropertyPrice(propertyDTO, propertyId);
        return new ResponseEntity<>(propertyDTO, HttpStatus.OK);

    }

    @DeleteMapping("/properties/deleteProperty/{propertyId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long propertyId)
    {
        propertyService.deleteProperty(propertyId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
