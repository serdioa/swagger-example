package de.serdioa.swagger.rest.controller;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.serdioa.swagger.sample.api.PingApiController;
import de.serdioa.swagger.sample.model.Pong;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PingApiControllerImpl extends PingApiController {

    private static final String DEFAULT_TOKEN = "pong";
    private static final String TRIGGER_EXCEPTION_TOKEN = "exception";
    private static final int MAX_TOKEN_LENGTH = 16;


    public PingApiControllerImpl(ObjectMapper objectMapper, HttpServletRequest request) {
        super(objectMapper, request);
    }


    @Override
    public ResponseEntity<Pong> restV1PingGet() {
        System.out.println("!!! PingApiDelegateImpl::get()");

        final Pong pong = new Pong();
        pong.setToken(DEFAULT_TOKEN);
        pong.setTimestamp(OffsetDateTime.now());
        pong.setDt(LocalDate.now());
        pong.setTokenType(Pong.TokenTypeEnum.AUTOMATIC);

        return ResponseEntity.ok(pong);
    }


    @Override
    public ResponseEntity<Pong> restV1PingPost(@Parameter(in = ParameterIn.QUERY,
            description = "Token provided by the ping caller", schema =
            @Schema()) @Valid
            @RequestParam(value = "token", required = false) String token) {

        System.out.println("!!! PingApiDelegateImpl::post()");

        if(TRIGGER_EXCEPTION_TOKEN.equals(token)) {
          throw new RuntimeException("Test runtime exception");
        }

        if(token != null && token.length() > MAX_TOKEN_LENGTH) {
          return ResponseEntity.badRequest().build();
        }

        final Pong pong = new Pong();
        pong.setToken(token != null ? token : DEFAULT_TOKEN);
        pong.setTimestamp(OffsetDateTime.now());
        pong.setDt(LocalDate.now());
        pong.setTokenType(token != null ? Pong.TokenTypeEnum.PROVIDED : Pong.TokenTypeEnum.AUTOMATIC);

        return ResponseEntity.ok(pong);
    }
}
