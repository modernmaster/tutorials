package net.kinguin.leadership.http.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class HttpMemberProperties {

  private String nodeName;
  private String checkUrl;
}
