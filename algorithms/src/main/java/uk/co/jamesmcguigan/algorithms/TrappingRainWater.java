package uk.co.jamesmcguigan.algorithms;

import java.util.LinkedList;

public class TrappingRainWater {
    public int calculate(int[] input) {
        int currentVolume = 0;
        int currentPeak = 0;
        LinkedList<Integer> peaks = new LinkedList<Integer>();
        for(int i=0;i<input.length;i++) {
            if(input[i]>currentPeak) {
                peaks.add(i);
            }
        }

        for


//        int currentPeak = 0;
//        for(int i=0;i<input.length;i++) {
//            if(input[i]>currentPeak) {
//                currentPeak = input[i];
//            } else {
//                currentVolume += currentPeak -input[i];
//            }
//        }
    return currentVolume;
    }
}
