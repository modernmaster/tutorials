package uk.co.jamesmcguigan.algorithms;

public class WholeCubes {
    public int whole_cubes_count(int a, int b) {
        final int inclusive = 1;
        if(a<=b) {
            int cubeRootA = (int) Math.cbrt(a);
            int cubeRootB = (int) Math.cbrt(b);
            int result = cubeRootB - cubeRootA;
            return result > 0 ? result + inclusive: 0;
        }
        return 0;
    }
}
