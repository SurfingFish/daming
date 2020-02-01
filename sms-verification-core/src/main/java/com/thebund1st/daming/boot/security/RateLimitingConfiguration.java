package com.thebund1st.daming.boot.security;

import com.thebund1st.daming.security.ratelimiting.ErrorsFactory;
import com.thebund1st.daming.security.ratelimiting.RateLimitedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Deprecated
@Configuration
@Import(SlidingWindowConfiguration.class)
public class RateLimitingConfiguration {

    @Bean
    public RateLimitedAspect rateLimitedAspect() {
        return new RateLimitedAspect();
    }

    @Bean
    public ErrorsFactory errorsFactory() {
        return new ErrorsFactory();
    }

}
