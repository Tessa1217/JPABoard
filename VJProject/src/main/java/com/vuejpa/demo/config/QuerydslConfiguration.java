package com.vuejpa.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


// QueryDSL 쿼리에 사용되는 JPAQueryFactory를 빈으로 등록
@Configuration
public class QuerydslConfiguration {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Bean
	public JPAQueryFactory queryFactory() {
		return new JPAQueryFactory(entityManager);
	}

}
