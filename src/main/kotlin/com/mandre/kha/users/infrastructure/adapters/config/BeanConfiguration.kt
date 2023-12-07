package com.mandre.kha.users.infrastructure.adapters.config

import com.mandre.kha.users.domain.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfiguration {

    @Bean
    fun userService(): UserService {
        return UserService()
    }
}