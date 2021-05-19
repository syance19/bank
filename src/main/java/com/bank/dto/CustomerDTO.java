package com.bank.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

	
	@NotNull
	private Integer custId;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	
	private String address;
	@NotNull
	@NotEmpty
	@Email
	@Size(max = 255)
	
	private String email;
	@NotNull
	@NotEmpty
	@Size(max = 1)

	private String enable;
	@NotNull
	@NotEmpty
	@Size(max = 255)

	private String name;
	@NotNull
	@NotEmpty
	@Size(min=4,max = 255)
	private String phone;

	private String token;
	@NotNull
	@Min(0)
	private Integer dotyId;

}
