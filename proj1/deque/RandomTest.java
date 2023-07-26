package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class RandomTest {
    @Test
    public void  testThreeAddThreeRemove(){
        LinkedListDeque<Integer> correct_alist = new LinkedListDeque<>();
        ArrayDeque<Integer>buggy_alist = new ArrayDeque<>();
        for(int i=4;i<7;i++) {
            correct_alist.addLast(i);
            buggy_alist.addLast(i);
        }
        Assert.assertEquals(correct_alist.size(),buggy_alist.size());
        int times=3;
        while(times !=0) {
            Assert.assertEquals(correct_alist.removeLast(),buggy_alist.removeLast());
            times--;
        }
    }

    @Test
    public void randomizedTest() {
        LinkedListDeque<Integer> L1 = new LinkedListDeque<>();
        ArrayDeque<Integer> L2 = new ArrayDeque<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            int randVal = StdRandom.uniform(0, 100);
            Assert.assertEquals(randomized(L1,operationNumber,randVal),randomized(L2,operationNumber,randVal));

        }
    }
    public static String randomized(ArrayDeque<Integer> L,int operationNumber,int randVal) {
        if (operationNumber == 0) {
            // addLast
            L.addLast(randVal);
            return "addLast(" + randVal + ")";
        } else if (operationNumber == 1) {
            // size
            int size = L.size();
            return "size: " + size;
        } else if (operationNumber == 2) {
            if (L.size() > 0) {
                return "getLast:" + L.get(L.size()-1);
            } else {
                return "oh,no,crashed";
            }
        } else {
            if (L.size() > 0) {
                return "removeLast:" + L.removeLast();
            } else {
                return "oh,no,crashed";
            }
        }
    }
    public static String randomized(LinkedListDeque<Integer> L,int operationNumber,int randVal) {
        if (operationNumber == 0) {
            // addLast

            L.addLast(randVal);
            return "addLast(" + randVal + ")";
        } else if (operationNumber == 1) {
            // size
            int size = L.size();
            return "size: " + size;
        } else if (operationNumber == 2) {
            if (L.size() > 0) {
                return "getLast:" + L.get(L.size()-1);
            } else {
                return "oh,no,crashed";
            }
        } else {
            if (L.size() > 0) {
                return "removeLast:" + L.removeLast();
            } else {
                return "oh,no,crashed";
            }
        }
    }
}
