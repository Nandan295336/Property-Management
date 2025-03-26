package com.mycompany.property_management.service.impl;

import com.mycompany.property_management.dto.UserDTO;
import com.mycompany.property_management.entity.UserEntity;
import com.mycompany.property_management.exception.BusinessException;
import com.mycompany.property_management.exception.ErrorModel;
import com.mycompany.property_management.repository.AddressRepository;
import com.mycompany.property_management.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public UserDTO register(UserDTO userDTO) {

        Optional<UserEntity> optUe = userRepository.findByEmail(userDTO.getEmail());
        if(optUe.isPresent())
        {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("EMAIL_ALREADY_EXIST");
            errorModel.setMessage("The email with which you are trying to register already exist");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO,userEntity);
        userEntity.setCreationDate(new Date()); //current date
/*
        AdressEntity adressEntity=new AdressEntity();
        adressEntity.setCity(userDTO.getCity());
        adressEntity.setCountry(userDTO.getCountry());
        adressEntity.setHouseNo(userDTO.getHouseNo());
        adressEntity.setLat(userDTO.getLat());
        adressEntity.setLandmark(userDTO.getLandmark());
        adressEntity.setLng(userDTO.getLng());
        adressEntity.setState(userDTO.getState());
        adressEntity.setPostalCode(userDTO.getPostalCode());

        //adressEntity.setUserEntity(userEntity);
        addressRepository.save(adressEntity);

        userEntity.setAdressEntity(adressEntity);
        */
        userEntity = userRepository.save(userEntity);


        BeanUtils.copyProperties(userEntity,userDTO);
        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO userDTO=null;
        Optional<UserEntity> optionalUserEntity = userRepository.findByEmailAndPassword(email, password);
        if(optionalUserEntity.isPresent())
        {
            //userDTO=userConverter.convertEntityToDTO(optionalUserEntity.get());
            userDTO = new UserDTO();
            BeanUtils.copyProperties(optionalUserEntity.get(), userDTO);
            userDTO.setPassword(null);
        }
        else {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("Invalid Login");
            errorModel.setMessage("Incorrect email or password");
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }

        return userDTO;
    }
}
