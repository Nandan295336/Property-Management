package com.mycompany.property_management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertyDTO {

    private Long id;
    private Long userId;
    private Long categoryId;
    private String title;
    private String description;
    private Double price;
    private String construction;
    private String image1;
    private String image2;
    private String image3;
    private String video;
    private Double area;
    private Integer noOfRooms;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date creationDate;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date updationDate;
    private AdressDTO addressDTO;

}
