package net.kinguin.leadership.core;

import rx.Observable;

public interface Member {
    String ELECTED_FIRST_TIME = "elected.first";
    String ELECTED = "elected";
    String RELEGATION = "relegation";
    String NOT_ELECTED = "notelected";
    String ERROR = "error";

    boolean isLeader();
    Observable<Object> asObservable();
}