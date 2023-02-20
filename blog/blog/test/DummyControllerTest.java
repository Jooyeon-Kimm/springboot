package com.joo.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joo.blog.model.RoleType;
import com.joo.blog.model.User;
import com.joo.blog.repository.UserRepository;

import jakarta.transaction.Transactional;

//html 파일이 아니라 data를 return 해주는 controller = RestController
@RestController
public class DummyControllerTest {

	@Autowired // 의존성 주입 (DI)
	private UserRepository userRepository;

	// save 함수는 id를 전달하지 않으면 insert를 해주고
	// save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	// save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 isnert를 해준다.
	// email, password

	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // json 데이터를 요청 => Java Object
																					// (MessageConverter의 Jackson라이브러리가
																					// 변환해서로 변환해서 받아준다.)
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());

		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

		requestUser.setId(id);
		requestUser.setUsername("ssar");

		// userRepository.save(requestUser);
		// @Transactional 이 있으면
		// .save 하지 않아도 update가 된다.

		// 더티 체킹
		return null;

// postman에서 put에서 raw, json 으로 send
//		{
//		    "password" : "5678",
//		    "email" : "ssar@gmail.com"
//		}
	}

	// http://localhost:8000/blog/dummy/users
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}

	// 한 페이지당 2건의 데이터를 리턴받아 보기
	// http://localhost:8000/blog/dummy/user?page=0
	@GetMapping("/dummy/user")
	public List<User> pageList(
			@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) org.springframework.data.domain.Pageable pageable) {
		Page<User> pagingUser = userRepository.findAll(pageable);

//		if(pagingUser.isFirst()) {
//			
//		}

		List<User> users = pagingUser.getContent();
		return users;

//		방법 1
//		public List<User> pageList(
//				@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) org.springframework.data.domain.Pageable pageable) {
//			List<User> users = userRepository.findAll(pageable).getContent();
//			return users;

// 방법 2
//		@GetMapping("/dummy/user")
//		public Page<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) org.springframework.data.domain.Pageable pageable) {
//		Page<User> users = userRepository.findAll(pageable);
//		return users;

	}

	// {id} 주소로 파라미터를 전달받을 수 있음
	// http:/localhost:8000/blog/dummy/user/100
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {

		// user/100 (없는 user)를 찾으면 내가 데이터베이스에서 못찾아오게되면 user가 null이 된다.
		// 그럼 return할 때 null이 리턴이 된다. 그럼 프로그램에 문제가 있다.
		// Optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해라.

		User user = userRepository.findById(id)
				.orElseThrow(new java.util.function.Supplier<IllegalArgumentException>() {

					@Override
					public IllegalArgumentException get() {
						// TODO Auto-generated method stub
						return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
					}

				});

		// 요청 : 웹브라우저
		// user 객체는 자바 오브젝트
		// 변환 ( 웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
		// 스프링부트 = MessageConverter가 응답시에 자동 작동을 한다.
		// 만약 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
		return user;

//		방법 1
//		람다식 (Supplier 타입을 return 해야 하는 지 알 필요가 없다)
//		User user = userRepository.findById(id).orElseThrow(() -> {
//			return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
//		});
//		return user;

// 방법 2		
//			User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//
//			@Override
//			public User get() {
//				// TODO Auto-generated method stub
//				return new User();
//			}
//
//		});
//		return user;

	}

	// http://localhost:8000/blog/dummy/join (요청)
	// http의 body에 username, password, email 데이터를 가지고 (요청)
	@PostMapping("/dummy/join")
	public String join(User user) { // key = value (약속된 규칙)
		System.out.println("id :" + user.getId());
		System.out.println("username :" + user.getUsername());
		System.out.println("password :" + user.getPassword());
		System.out.println("email :" + user.getEmail());
		System.out.println("role :" + user.getRole());
		System.out.println("createDate :" + user.getCreateDate());

		user.setRole(RoleType.USER);

		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
//	public String join(String username, String password, String email) { // key = value (약속된 규칙)
//		System.out.println("username :" + username);
//		System.out.println("password :" + password);
//		System.out.println("email :" + email);
//
//		return "회원가입이 완료되었습니다.";
//	}

}
