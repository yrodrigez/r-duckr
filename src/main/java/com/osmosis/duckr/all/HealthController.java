package com.osmosis.duckr.all;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author Yago on 01/03/2019.
 */
@RestController
@RequestMapping("/health")
public class HealthController {

  @GetMapping
  public LocalDateTime get() {
    return LocalDateTime.now();
  }
}
