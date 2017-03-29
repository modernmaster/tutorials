package uk.co.jamesmcguigan.patterns.structural.bridge;

import org.junit.Test;

public class BridgeTest {

    @Test
    public void testBridgeDisc() {
        StackArray[] stacks = {new StackArray(), new StackFifo(), new StackHanoi()};
        StackList stack2 = new StackList();
        for (int i = 1, num; i < 15; i++) {
            stacks[0].push(i);
            stack2.push(i);
            stacks[1].push(i);
        }
        java.util.Random rn = new java.util.Random();
        for (int i = 1, num; i < 15; i++)
            stacks[2].push(rn.nextInt(20));
        while (!stacks[0].isEmpty())
            System.out.print(stacks[0].pop() + "  ");
        System.out.println();
        while (!stack2.isEmpty())
            System.out.print(stack2.pop() + "  ");
        System.out.println();
        while (!stacks[1].isEmpty())
            System.out.print(stacks[1].pop() + "  ");
        System.out.println();
        while (!stacks[2].isEmpty())
            System.out.print(stacks[2].pop() + "  ");
        System.out.println();
        System.out.println("total rejected is "
                + ((StackHanoi) stacks[2]).reportRejected());
    }
}
