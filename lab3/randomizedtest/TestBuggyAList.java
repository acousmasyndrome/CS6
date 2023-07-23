package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void  testThreeAddThreeRemove(){
        AListNoResizing<Integer> correct_alist = new AListNoResizing<>();
        BuggyAList<Integer>buggy_alist = new BuggyAList<>();
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
        AListNoResizing<Integer> L1 = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();
        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            int randVal = StdRandom.uniform(0, 100);
            Assert.assertEquals(randomized(L1,operationNumber,randVal),randomized(L2,operationNumber,randVal));

        }
    }
    public static String randomized(BuggyAList<Integer> L,int operationNumber,int randVal) {
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
                return "getLast:" + L.getLast();
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
        public static String randomized(AListNoResizing<Integer> L,int operationNumber,int randVal) {
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
                    return "getLast:" + L.getLast();
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
