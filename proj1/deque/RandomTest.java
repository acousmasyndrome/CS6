package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by hug.
 */
public class RandomTest {
    @Test
    public void testThreeAddThreeRemove() {
        LinkedListDeque<Integer> correctAlist = new LinkedListDeque<>();
        ArrayDeque<Integer> buggyAlist = new ArrayDeque<>();
        for (int i = 0; i < 100000; i++) {
            correctAlist.addLast(i);
            buggyAlist.addLast(i);
        }
        Assert.assertEquals(correctAlist.size(), buggyAlist.size());
        int times = 100000;
        while (times != 0) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                Assert.assertEquals(correctAlist.removeLast(), buggyAlist.removeLast());
                times--;
            } else if (operationNumber == 1) {
                Assert.assertEquals(correctAlist.removeFirst(), buggyAlist.removeFirst());
                times--;
            } else {
                Assert.assertEquals(correctAlist.get(correctAlist.size() - 1),
                        buggyAlist.get(buggyAlist.size() - 1));
            }
        }
    }

    @Test
    public void randomizedTest() {
        LinkedListDeque<Integer> L1 = new LinkedListDeque<>();
        ArrayDeque<Integer> L2 = new ArrayDeque<>();
        int N = 100000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 5);
            int randVal = StdRandom.uniform(0, 100);
            Assert.assertEquals(randomized(L1, operationNumber, randVal), randomized(L2, operationNumber, randVal));

        }
    }

    public static String randomized(ArrayDeque<Integer> L, int operationNumber, int randVal) {
        if (operationNumber == 0) {
            // addLast

            L.addLast(randVal);
            return "addLast(" + randVal + ")";
        } else if (operationNumber == 1) {
            // removeFirst
            if (L.size() > 0) {
                return "removeFirst." + L.removeFirst();
            } else {
                return "oh,no,crashed";
            }
        } else if (operationNumber == 2) {
            if (L.size() > 0) {
                return "getLast:" + L.get(L.size() - 1);
            } else {
                return "oh,no,crashed";
            }
        } else if (operationNumber == 3) {
            if (L.size() > 0) {
                return "removeLast:" + L.removeLast();
            } else {
                return "oh,no,crashed";
            }
        } else {// addFirst
            L.addFirst(randVal);
            return "addFirst(" + randVal + ")";
        }
    }

    public static String randomized(LinkedListDeque<Integer> L, int operationNumber, int randVal) {
        if (operationNumber == 0) {
            // addLast

            L.addLast(randVal);
            return "addLast(" + randVal + ")";
        } else if (operationNumber == 1) {
            // removeFirst
            if (L.size() > 0) {
                return "removeFirst." + L.removeFirst();
            } else {
                return "oh,no,crashed";
            }
        } else if (operationNumber == 2) {
            if (L.size() > 0) {
                return "getLast:" + L.get(L.size() - 1);
            } else {
                return "oh,no,crashed";
            }
        } else if (operationNumber == 3) {
            if (L.size() > 0) {
                return "removeLast:" + L.removeLast();
            } else {
                return "oh,no,crashed";
            }
        } else {// addFirst
            L.addFirst(randVal);
            return "addFirst(" + randVal + ")";
        }
    }
}
