package com.joo.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");

		// 파일 리턴 기본경로: src/main/resources/static

		// return "home.html";
		// 리턴명: /home.html
		// full 경로: src/main/resources/statichome.html

		return "/home.html";
		// full 경로: src/main/resources/static/home.html
	}
	// RestController는 String 문자 그 자체를 return 한다면
	// Controller는 해당 경로 이하에 있는 file을 return 해준다.

	// http://localhost:8000/blog/temp/image
	@GetMapping("/temp/image")
	public String StringtempImage() {
		return "/que.png";
	}

	@GetMapping("/temp/jsp")
	public String StringtempJsp() {
		// application.yml 참고 (prefix/suffix)
		// prefix : /WEB-INF/views/
		// suffix : .jsp

		return "test";
		// full 경로: /WEB-INF/views/test.jsp
	}
}
