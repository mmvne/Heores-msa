package com.hq.heroes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ScheduleApplication {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ScheduleApplication.class, args);
	}

	@Bean
	public ApplicationRunner checkRedisConnection(RedisConnectionFactory connectionFactory) {
		return args -> {
			try {
				connectionFactory.getConnection().ping();
				logger.info("✅ Redis 연결이 성공적으로 설정되었습니다.22");
			} catch (Exception e) {
				logger.error("❌ Redis에 연결할 수 없습니다: {}", e.getMessage());
			}
		};
	}
}
