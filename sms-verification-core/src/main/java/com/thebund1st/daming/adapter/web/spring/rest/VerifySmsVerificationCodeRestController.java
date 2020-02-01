package com.thebund1st.daming.adapter.web.spring.rest;

import com.thebund1st.daming.adapter.web.spring.rest.resources.SmsVerifiedJwtResource;
import com.thebund1st.daming.application.commandhandling.VerifySmsVerificationCodeCommandHandler;
import com.thebund1st.daming.application.command.VerifySmsVerificationCodeCommand;
import com.thebund1st.daming.application.jwt.SmsVerifiedJwtIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@ConditionalOnProperty(prefix = "daming.jwt", name = "enabled", havingValue = "true", matchIfMissing = true)
@RequiredArgsConstructor
@RestController
public class VerifySmsVerificationCodeRestController {

    private final VerifySmsVerificationCodeCommandHandler verifySmsVerificationCodeCommandHandler;

    private final SmsVerifiedJwtIssuer smsVerifiedJwtIssuer;


    @DeleteMapping("#{endpointProperties.verifySmsVerificationCodePath}")
    public SmsVerifiedJwtResource handle(@Valid @RequestBody VerifySmsVerificationCodeCommand command) {
        verifySmsVerificationCodeCommandHandler.handle(command);
        return new SmsVerifiedJwtResource(smsVerifiedJwtIssuer.issue(command.getMobile(), command.getScope()));
    }
}
