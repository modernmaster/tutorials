package isb.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RoundEventType {
    BET(0), WIN(1);

    private final int eventTypeCode;

    public static RoundEventType keyOf(int eventTypeCode){
        if(eventTypeCode==0) {
            return BET;
        } else if (eventTypeCode ==1) {
            return WIN;
        } else {
            throw new IllegalArgumentException(String.format("Illegal EventTypeCode %s", eventTypeCode));
        }
    }
}
