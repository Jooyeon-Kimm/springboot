package com.joo.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob // 대용량 데이터
	private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인이 된다

	@ColumnDefault("0")
	private int count; // 조회수

	@ManyToOne // Many = Board, User = One, // 한 명의 User는 여러 개의 Board 게시글 을 쓸 수 있다.
	@JoinColumn(name = "userId")
	private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

	@CreationTimestamp
	private Timestamp createDate;
}

/*
 * 결과 Hibernate:
 * 
 * drop table if exists User Hibernate:
 * 
 * create table Board ( id integer not null auto_increment, content tinytext,
 * count integer default 0 not null, createDate datetime(6), title varchar(100)
 * not null, userId integer, primary key (id) ) engine=InnoDB Hibernate:
 * 
 * create table User ( id integer not null auto_increment, createDate
 * datetime(6), email varchar(50) not null, password varchar(100) not null, role
 * varchar(255) default 'user', username varchar(30) not null, primary key (id)
 * ) engine=InnoDB
 */