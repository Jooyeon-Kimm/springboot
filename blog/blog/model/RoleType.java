package com.joo.blog.model;

// Enum 을 만들면, 내가 넣는 값의 오타를 방지할 수 있다.
// 예를 들어, user 로 role을 지정해야할 때
// 오타로 useer를 넣는 일을 막아준다.

// ENUM은 데이터의 도메인을 만들 때 사용한다.
public enum RoleType {
	USER, ADMIN

}
