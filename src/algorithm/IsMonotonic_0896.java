package algorithm;

public class IsMonotonic_0896 {

    public boolean isMonotonic(int[] A) {
        if (A.length == 1) return true;
        boolean rise = true, desc = true;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i-1]) desc = false;
            if (A[i] < A[i-1]) rise = false;
            if (!rise && !desc) return false;
        }
        return true;
    }
}
