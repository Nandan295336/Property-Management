package com.mycompany.property_management.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private long id;
    private String firstName;
    private String lastName;

    @NotNull(message="owner Email is Mandatory")
    @NotEmpty(message = "Owner email cannot be empty")
    @Size(min =1, max=50, message="Owner Email should be between 1 to 50 characters in long")
    private String Email;

    private String phone;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date creationDate;

    @NotNull(message="Password cannot be null")
    @NotEmpty(message="Password cannot be empty")
    private String password;

}
