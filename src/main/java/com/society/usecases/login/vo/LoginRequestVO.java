package com.society.usecases.login.vo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestVO {

	private String email;
	private String senha;
}
