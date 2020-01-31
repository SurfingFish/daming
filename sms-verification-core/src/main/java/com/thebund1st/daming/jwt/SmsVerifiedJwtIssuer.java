package com.thebund1st.daming.jwt;

import com.thebund1st.daming.application.domain.MobilePhoneNumber;
import com.thebund1st.daming.application.domain.SmsVerificationScope;
import com.thebund1st.daming.time.Clock;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.security.Key;
import java.time.Duration;
import java.util.Date;

@RequiredArgsConstructor
public class SmsVerifiedJwtIssuer {
    @Setter
    private Duration expires = Duration.ofSeconds(900);

    private final Clock clock;

    private final Key key;

    public String issue(MobilePhoneNumber mobilePhoneNumber, SmsVerificationScope scope) {
        return Jwts.builder().setSubject("verifiedMobilePhoneNumber")
                .claim("mobile", mobilePhoneNumber.getValue())
                .claim("scope", scope.getValue())
                .signWith(key)
                .setExpiration(Date.from(clock.now().plusSeconds(expires.getSeconds()).toInstant()))
                .compact();
    }


}
