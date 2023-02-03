package com.joo.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// Controller : 사용자가 요청 -> 응답 (HTML 파일)
// RestController : 사용자가 요청 -> 응답 (Data)
@RestController
public class HttpControllerTest {

	// 인터넷 브라우저 요청은 무조건 get 요청밖에 할 수 없다.
	// http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) {
		// public String getTest(@RequestParam int id, @RequestParam String username)
		return "get 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();

		// postman에서
		// http://http://localhost:8080/http/get?id=1&username=ssar&password=1234&email=ssar@nate.com

	}

	// 나머지는 Postman에서.
	// http://localhost:8080/http/post (insert)
	// application/json
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { // MessageConverter
		return "post 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}

	/*
	 * test/plain : 평문을 보냈다
	 * 
	 * @PostMapping("/http/post") public String postTest(@RequestBody String text) {
	 * return "post 요청 : " + text; }
	 */

	/*
	 * Postman에서 x-www-form-urlencoded
	 * 
	 * public String postTest(Member m) { return "post 요청 : " + m.getId() + ", " +
	 * m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail(); }
	 */

	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();

	}

	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
