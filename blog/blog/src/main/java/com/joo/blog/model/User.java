package com.joo.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM -> Java (다른 언어) Object -> 테이블로 매핑해주는 기술
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다.
// @DynamicInsert // insert 시에 null인 필드를 제외시켜준다.
public class User {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다
	private int id; // 시퀀스, auto_increment

	@Column(nullable = false, length = 30, unique = true)
	private String username; // ID

	@Column(nullable = false, length = 100) // 123456 -> 해쉬 (비밀번호 암호화)
	private String password;

	@Column(nullable = false, length = 50)
	private String email;

	// @ColumnDefault("'user'")
	// DB는 RoleType이라는 게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role; // Enum 을 사용하는 것이 좋다. // ADMIN, USER

	// admin, user, manager
	// (String 형을 사용하면 managerrrr 오타날 수 있다)
	// Enum을 사용하면 도메인 설정을 할 수 있다.

	@CreationTimestamp // 시간이 자동 입력 됨
	private Timestamp createDate;

}
// application.yml 의
// jpa 아래 ddl-auto 가 create로 변경되었는지 확인하기
// 최초에는 create, 기존의 테이블 사용해야하므로 update

/*
 * application.yml에서 show-sql: true 이기 때문에 콘솔 창에서 아래 내용이 보인다.
 * 
 * 원래는 콘솔 창에서 한 줄로 보이는데 properities: hibernate.format_sql: true 덕분에 정리된 형태로 보인다.
 * 
 * drop table if exists User Hibernate:
 * 
 * use-new-id-generator-mappings: false JPA의 기본 넘버링 전략을 따라가지 않겠다는 의미
 * 
 * physical-strategy:
 * org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl 테이블을 만들 때,
 * 변수명 그대로 데이터베이스에 넣어준다. SpringPhysicalNamingStrategy를 사용하면 private String
 * myEmail; 의 필드명이 my_email이 된다.
 * 
 * create table User ( id integer not null auto_increment, createDate
 * datetime(6), email varchar(50) not null, password varchar(100) not null, role
 * varchar(255) default 'user', username varchar(30) not null, primary key (id)
 * ) engine=InnoDB
 */