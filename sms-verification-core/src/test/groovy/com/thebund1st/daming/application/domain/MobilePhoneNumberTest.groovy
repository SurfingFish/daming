package com.thebund1st.daming.application.domain

import spock.lang.Specification

import static com.thebund1st.daming.application.domain.MobilePhoneNumber.mobilePhoneNumberOf

class MobilePhoneNumberTest extends Specification {

    def "it should mask the mobile phone number for security"(String mobile, String masked) {
        expect:
        assert mobilePhoneNumberOf(mobile).toString() == masked

        where:
        mobile        | masked
        '13411111234' | '134****1234'
        '12341111'    | '****1111'
        '1234'        | '1234'
    }
}
