package com.mycompany.property_management.service.impl;

import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.entity.AdressEntity;
import com.mycompany.property_management.entity.CategoryEntity;
import com.mycompany.property_management.entity.PropertyEntity;
import com.mycompany.property_management.entity.UserEntity;
import com.mycompany.property_management.exception.BusinessException;
import com.mycompany.property_management.exception.ErrorModel;
import com.mycompany.property_management.repository.AddressRepository;
import com.mycompany.property_management.repository.CategoryRepository;
import com.mycompany.property_management.repository.PropertyRepository;
import com.mycompany.property_management.repository.UserRepository;
import com.mycompany.property_management.service.PropertyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service  // to make singleton
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

         Optional<UserEntity> optUe = userRepository.findById(propertyDTO.getUserId());
         if(optUe.isPresent()) {
            PropertyEntity pe = new PropertyEntity();
            BeanUtils.copyProperties(propertyDTO, pe);
            pe.setUserEntity(optUe.get());

            //get the category instance from db
             CategoryEntity ce = categoryRepository.findById(propertyDTO.getCategoryId()).get();
             pe.setCategoryEntity(ce);
             pe.setCreationDate(new Date());

             AdressEntity ae = new AdressEntity();
             BeanUtils.copyProperties(propertyDTO.getAddressDTO(), ae);
             ae = addressRepository.save(ae);
             pe.setAdressEntity(ae);

            pe = propertyRepository.save(pe);

            BeanUtils.copyProperties(pe, propertyDTO);
        }
       else{
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("User ID does not exist");
            errorModel.setMessage("User does not exist");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> listOfProps = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propList= new ArrayList<>();

        for(PropertyEntity pe: listOfProps)
        {
            PropertyDTO dto = new PropertyDTO();
            BeanUtils.copyProperties(pe,dto);
            dto.setUserId(pe.getUserEntity().getId());
            dto.setCategoryId(pe.getCategoryEntity().getId());
            propList.add(dto);
        }
        return  propList;
    }

    @Override
    public List<PropertyDTO> getAllPropertiesForUser(Long userId) {
        List<PropertyEntity> listOfProps = (List<PropertyEntity>) propertyRepository.findAllByUserEntityId(userId);
        List<PropertyDTO> propList= new ArrayList<>();

        for(PropertyEntity pe: listOfProps)
        {
            PropertyDTO dto=new PropertyDTO();
            BeanUtils.copyProperties(pe,dto);
            dto.setUserId(pe.getUserEntity().getId());
            dto.setCategoryId(pe.getCategoryEntity().getId());
            propList.add(dto);
        }
        return  propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
       Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
       PropertyDTO dto= new PropertyDTO();
       if(optEn.isPresent())
       {
           PropertyEntity pe= optEn.get();
           pe.setTitle(propertyDTO.getTitle());
           pe.setPrice(propertyDTO.getPrice());
           pe.setDescription(propertyDTO.getDescription());
           BeanUtils.copyProperties(pe,dto);
           dto.setUserId(pe.getUserEntity().getId());
           dto.setCategoryId(pe.getCategoryEntity().getId());
           propertyRepository.save(pe);
       }
       return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(String description, Long id) {
       Optional<PropertyEntity> optEN = propertyRepository.findById(id);
       PropertyDTO dto= new PropertyDTO();

       if(optEN.isPresent())
       {
           PropertyEntity pe = optEN.get();
           pe.setDescription(description);
           BeanUtils.copyProperties(pe,dto);
           dto.setUserId(pe.getUserEntity().getId());
           dto.setCategoryId(pe.getCategoryEntity().getId());
           propertyRepository.save(pe);
       }
       return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(Double price, Long id) {
        Optional<PropertyEntity> optEN = propertyRepository.findById(id);
        PropertyDTO dto= new PropertyDTO();

        if(optEN.isPresent())
        {
            PropertyEntity pe = optEN.get();
            pe.setPrice(price);
            BeanUtils.copyProperties(pe,dto);
            dto.setUserId(pe.getUserEntity().getId());
            dto.setCategoryId(pe.getCategoryEntity().getId());
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }

}
