package tester;


import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void testArray() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        ArrayDequeSolution<String> methodsString = new ArrayDequeSolution<>();
        int testNumber = 1000;
        for (int i = 1; i <= testNumber; i++) {
            int randomNumber = StdRandom.uniform(0, 4);
            random_test(sad1, ads1, randomNumber, i, methodsString);
        }

    }

    public void random_test(StudentArrayDeque<Integer> sad1,
                            ArrayDequeSolution<Integer> ads1, int randomNumber,
                            int ranval, ArrayDequeSolution<String> methodsString) {
        if (randomNumber == 0) {
            ads1.addFirst(ranval);
            sad1.addFirst(ranval);
            methodsString.addLast("addFirst(" + ranval + ")");
            Assert.assertEquals(ranval, ranval);

        } else if (randomNumber == 1) {
            ads1.addLast(ranval);
            sad1.addLast(ranval);
            methodsString.addLast("addLast(" + ranval + ")");
            Assert.assertEquals(ranval, ranval);

        } else if (randomNumber == 2) {
            if (!(ads1.isEmpty() && sad1.isEmpty())) {
                Integer expected = ads1.removeFirst();
                Integer actual = sad1.removeFirst();
                methodsString.addLast("removeFirst()");
                Assert.assertEquals("\n" + String.join("\n",
                        methodsString), expected, actual);
            }

        } else {
            if (!(ads1.isEmpty() && sad1.isEmpty())) {
                Integer expected = ads1.removeLast();
                Integer actual = sad1.removeLast();
                methodsString.addLast("removeLast()");
                Assert.assertEquals("\n" + String.join("\n",
                        methodsString), expected, actual);
            }
        }
    }
}
