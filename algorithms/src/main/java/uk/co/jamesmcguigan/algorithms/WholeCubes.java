package uk.co.jamesmcguigan.algorithms;

public class WholeCubes {
    public int wholeCubesCount(final int a, final int b) {
        final int inclusive = 1;
        if (a <= b) {
            int cubeRootA = (int) Math.cbrt(a);
            int cubeRootB = (int) Math.cbrt(b);
            int result = cubeRootB - cubeRootA;
            if (result > 0) {
                return result + inclusive;
            }
        }
        return 0;
    }
}
