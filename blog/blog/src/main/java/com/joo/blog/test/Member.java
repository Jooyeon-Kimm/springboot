package com.joo.blog.test;

import lombok.Builder;
import lombok.Data;

/*
@Getter
@Setter

//Getter, Setter
@Data

//final 형 변수에 대한 생성자 만들어준다
@RequiredArgsConstructor

//생성자
@AllArgsConstructor

//빈 생성자 (final 형에서 사용 불가)
@NoArgsConstructor
 */

@Data
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;

	// final : 불변성 유지

	@Builder
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	/*
	 * @Builder 어노테이션의 장점: 1. 객체에 값을 넣을때 순서를 지키지 않아도 된다. 2. 객체 값 순서를 헷갈려서 객체의 값을 잘못
	 * 넣는 실수하는 것을 방지한다
	 */
}

/*
 * public class Member { private int id; private String username; private String
 * password; private String email;
 * 
 * // Constructor public Member(int id, String username, String password, String
 * email) { // super(); this.id = id; this.username = username; this.password =
 * password; this.email = email; }
 * 
 * // getters and setters public int getId() { return id; }
 * 
 * public void setId(int id) { this.id = id; }
 * 
 * public String getUsername() { return username; }
 * 
 * public void setUsername(String username) { this.username = username; }
 * 
 * public String getPassword() { return password; }
 * 
 * public void setPassword(String password) { this.password = password; }
 * 
 * public String getEmail() { return email; }
 * 
 * public void setEmail(String email) { this.email = email; } }
 */
