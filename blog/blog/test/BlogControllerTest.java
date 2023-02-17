package com.joo.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
스프링이 com.joo.blog 패키지 이하를 스캔해서
모든 파일에 new (heap 메모리에 올리기) 하는 것은 아니다
특정 어노테이션이 붙어있는 클래스 파일들을 new 해서 (IoC)
스프링 컨테이너에 관리해준다
*/
@RestController
public class BlogControllerTest {

	// http://localhost:8080/test/hello
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1> hello spring boot </h1>";
	}
	// Tomcat started on port(s): 8080 (http) with context path ''
	// Started BlogApplication in 3.599 seconds (process running for 4.997)

}
