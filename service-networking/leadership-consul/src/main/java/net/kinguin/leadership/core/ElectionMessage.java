package net.kinguin.leadership.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.kinguin.leadership.consul.election.Vote;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ElectionMessage {

  public String status;
  public Vote vote;
  public String error;
}
