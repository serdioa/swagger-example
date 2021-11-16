package de.serdioa.swagger.rest.controller;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.serdioa.swagger.sample.api.PingApiDelegate;
import de.serdioa.swagger.sample.model.Pong;

@Service
public class PingApiDelegateImpl implements PingApiDelegate {
  private static final String DEFAULT_TOKEN = "pong";
  private static final String TRIGGER_EXCEPTION_TOKEN = "exception";
  private static final int MAX_TOKEN_LENGTH = 16;

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @org.springframework.beans.factory.annotation.Autowired
  public PingApiDelegateImpl(final ObjectMapper objectMapper, final HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  @Override
  public Optional<ObjectMapper> getObjectMapper() {
    return Optional.ofNullable(this.objectMapper);
  }

  @Override
  public Optional<HttpServletRequest> getRequest() {
    return Optional.ofNullable(this.request);
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
  public ResponseEntity<Pong> restV1PingPost(final String token) {
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
