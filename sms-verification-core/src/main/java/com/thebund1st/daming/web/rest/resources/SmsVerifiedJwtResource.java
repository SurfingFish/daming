package com.thebund1st.daming.web.rest.resources;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
public class SmsVerifiedJwtResource {
    private String token;
}
