package com.app.pokemon.infrastructure.config;

import jakarta.annotation.PreDestroy;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Value("${redis.base-url}")
    private String baseUrl;

    @Value("${redis.port}")
    private String port;

    @Value("${redis.password}")
    private String password;

    private RedissonClient redissonClient;

    @Bean
    public RedissonClient redissonClient() {
        if (redissonClient == null) {
            Config config = new Config();
            config.setCodec(new JsonJacksonCodec());
            config.useSingleServer()
                    .setAddress("redis://" + baseUrl + ":" + port)
                    .setPassword(password);

            redissonClient = Redisson.create(config);
        }

        return redissonClient;
    }

    @PreDestroy
    public void destroy() {
        if (redissonClient != null) {
            redissonClient.shutdown();
        }
    }
}
