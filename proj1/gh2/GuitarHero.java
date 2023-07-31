package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;
import org.apache.commons.math3.analysis.function.Pow;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero{
    public static final double CONCERT = 440.0;
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static void main(String[] args) {
        GuitarString string=new GuitarString(CONCERT);
        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.contains(String.valueOf(key))){
                    int index=keyboard.indexOf(key);
                    double connect=connect_key(index);
                    string=new GuitarString(connect);
                    string.pluck();
                } else  {
                    continue;
                }
            }

            /* compute the superposition of samples */
            double sample = string.sample();

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            string.tic();

        }
    }
    private static double connect_key(int index){
        double connect=CONCERT*Math.pow(2,(index-24)/12.0);
        return connect;
    }
}

