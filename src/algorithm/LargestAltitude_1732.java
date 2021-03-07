package algorithm;

public class LargestAltitude_1732 {

    public int largestAltitude(int[] gain) {
        int maxHeigth = 0;
        for (int i = 0, t = 0; i < gain.length; i++) {
            t += gain[i];
            maxHeigth = Math.max(t, maxHeigth);
        }
        return maxHeigth;
    }
}
