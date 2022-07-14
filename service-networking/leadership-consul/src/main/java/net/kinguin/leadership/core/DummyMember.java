package net.kinguin.leadership.core;

import rx.Observable;

public class DummyMember implements Member {

  @Override
  public boolean isLeader() {
    return true;
  }

  @Override
  public Observable<Object> asObservable() {
    return Observable.just(
        ElectionMessage.builder().status(ElectionState.ELECTED_FIRST_TIME.name()).build());
  }
}
