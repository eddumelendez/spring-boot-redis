package com.example.springbootredis;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestSpringBootRedisApplication {

	@Bean
	@ServiceConnection(name = "redis")
	GenericContainer<?> redisContainer() {
		return new GenericContainer<>(DockerImageName.parse("redis/redis-stack:6.2.6-v9"))
				.withExposedPorts(6379, 8001);
	}

	public static void main(String[] args) {
		SpringApplication.from(SpringBootRedisApplication::main)
				.with(TestSpringBootRedisApplication.class)
				.run(args);
	}

	@Bean
	ApplicationRunner runner(UserRepository repository) {
		return args -> {
			repository.save(new User("eddumelendez", "eddu@test.com"));
		};
	}

}
