package de.serdioa.swagger.rest.controller;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import de.serdioa.swagger.sample.api.PingApiDelegate;
import de.serdioa.swagger.sample.model.Pong;

@Service
public class PingApiDelegateImpl implements PingApiDelegate {
  private static final String DEFAULT_TOKEN = "pong";
  private static final String TRIGGER_EXCEPTION_TOKEN = "exception";
  private static final int MAX_TOKEN_LENGTH = 16;

  // Thread-local proxy, specific to the request being processed.
  private final NativeWebRequest request;

  @Autowired
  public PingApiDelegateImpl(final NativeWebRequest request) {
    this.request = request;
  }

  @Override
  public ResponseEntity<Pong> restV1PingGet() {
    System.out.println("!!! PingApiDelegateImpl::get()");

    final Pong pong = new Pong();
    pong.setToken(DEFAULT_TOKEN);
    pong.setTimestamp(OffsetDateTime.now());

    return ResponseEntity.ok(pong);
  }

  @Override
  public ResponseEntity<Pong> restV1PingPost(final String token) {
    System.out.println("!!! PingApiDelegateImpl::post()");

    if(token.equals(TRIGGER_EXCEPTION_TOKEN)) {
      throw new RuntimeException("Test runtime exception");
    }

    if(token != null && token.length() > MAX_TOKEN_LENGTH) {
      return ResponseEntity.badRequest().build();
    }

    final Pong pong = new Pong();
    pong.setToken(token != null ? token : DEFAULT_TOKEN);
    pong.setTimestamp(OffsetDateTime.now());

    return ResponseEntity.ok(pong);
  }
}
