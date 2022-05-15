package ru.boringowl.myroadmap.infrastructure.config

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

class AppCoroutineScope : CoroutineScope by GlobalScope

@SpringBootConfiguration
@Configuration
@EnableScheduling
class SpringConfig {
    @Bean
    fun scope(): AppCoroutineScope = AppCoroutineScope()
}

