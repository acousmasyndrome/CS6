package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        double time;
        int time_cycle;
        AList<Integer> Ns=new AList<>();
        AList<Double> times=new AList<>();
        AList<Integer> opCounts=new AList<>();

        int[] timing=new int[]{1000,2000,4000,8000,16000,32000,64000,128000};
        for(int index=0;index< timing.length;index++){
            time_cycle=timing[index];
            time=timeGetLast_helper(time_cycle);
            Ns.addLast(time_cycle);
            times.addLast(time);
            opCounts.addLast(10000);
        }
        printTimingTable(Ns,times,opCounts);

    }
    public static double timeGetLast_helper(int times)
    {   SLList<String> sllist=new SLList<>();
        for(int i=0;i<times;i++){
            sllist.addLast("duck");
        }
        Stopwatch sw = new Stopwatch();
        sllist.getLast();
        double timeInSeconds = sw.elapsedTime();
        return timeInSeconds;
    }

    }


