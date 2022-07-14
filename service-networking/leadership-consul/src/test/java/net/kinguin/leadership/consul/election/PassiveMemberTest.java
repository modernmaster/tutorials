package net.kinguin.leadership.consul.election;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import net.kinguin.leadership.core.ElectionMessage;
import net.kinguin.leadership.core.ElectionState;
import net.kinguin.leadership.core.Member;
import net.kinguin.leadership.core.DummyMember;
import org.junit.Before;
import org.junit.Test;

public class PassiveMemberTest {

  private DummyMember dummyMember;

  @Before
  public void setUp() {
    dummyMember = new DummyMember();
  }

  @Test
  public void should_emit_first_time_leader_elected_event() {
    dummyMember.asObservable()
        .toBlocking()
        .subscribe(i -> {
          ElectionMessage n = (ElectionMessage) i;
          assertEquals(ElectionState.ELECTED_FIRST_TIME.name(), ((ElectionMessage) i).status);
        });
  }

  @Test
  public void should_always_return_leader_elected() {
    assertTrue(dummyMember.isLeader());
  }
}