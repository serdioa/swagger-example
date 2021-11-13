package de.serdioa.swagger.rest.controller;

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


    public PingApiControllerImpl(ObjectMapper objectMapper, HttpServletRequest request) {
        super(objectMapper, request);
    }


    @Override
    public ResponseEntity<Pong> restV1PingGet() {
        Pong pong = new Pong();
        pong.setToken(DEFAULT_TOKEN);
        pong.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.ok(pong);
    }


    @Override
    public ResponseEntity<Pong> restV1PingPost(@Parameter(in = ParameterIn.QUERY,
            description = "Token provided by the ping caller", schema =
            @Schema()) @Valid
            @RequestParam(value = "token", required = false) String token) {

        if (token != null && token.length() > 16) {
            return ResponseEntity.badRequest().build();
        }

        Pong pong = new Pong();
        pong.setToken(token != null ? token : DEFAULT_TOKEN);
        pong.setTimestamp(OffsetDateTime.now());

        return ResponseEntity.ok(pong);
    }
}
