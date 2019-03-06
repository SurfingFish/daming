package com.thebund1st.daming.boot.redis

import com.thebund1st.daming.boot.AbstractAutoConfigurationTest
import com.thebund1st.daming.core.SmsVerification
import com.thebund1st.daming.core.SmsVerificationStore
import com.thebund1st.daming.redis.RedisSmsVerificationStore
import foo.bar.WithCustomizedRedisTemplate
import foo.bar.WithCustomizedSmsVerificationStore
import org.springframework.data.redis.core.RedisTemplate

class RedisConfigurationTest extends AbstractAutoConfigurationTest {

    def "it should provide one bean of RedisTemplate<String, SmsVerification> given no customized configuration"() {

        when:
        this.contextRunner

        then:
        this.contextRunner.run { it ->
            //FIXME this does not work
            //assert it.getBeanNamesForType(ResolvableType.forClassWithGenerics(RedisTemplate, String, SmsVerification)).length == 1
            RedisTemplate<String, SmsVerification> actual = it.getBean("smsVerificationRedisTemplate")
            assert actual != null
        }
    }

    def "it should skip default instance of RedisTemplate<String, SmsVerification> given customized one is provided"() {

        when:
        def contextRunner = this.contextRunner
                .withUserConfiguration(WithCustomizedRedisTemplate)

        then:

        contextRunner.run { it ->
            RedisTemplate<String, SmsVerification> actual = it.getBean("smsVerificationRedisTemplate")
            assert actual != null
            assert actual instanceof CustomizedRedisTemplate
        }
    }

    def "it should provide one bean of RedisSmsVerificationStore given no customized configuration"() {

        when:
        this.contextRunner

        then:
        this.contextRunner.run { it ->
            SmsVerificationStore actual = it.getBean(SmsVerificationStore)
            assert actual instanceof RedisSmsVerificationStore
        }
    }

    def "it should skip default RedisSmsVerificationStore given customized configuration"() {

        when:
        def contextRunner = this.contextRunner.withUserConfiguration(WithCustomizedSmsVerificationStore)

        then:
        contextRunner.run { it ->
            def actual = it.getBean(SmsVerificationStore)
            assert actual instanceof WithCustomizedSmsVerificationStore.SmsVerificationStoreStub
        }
    }
}
