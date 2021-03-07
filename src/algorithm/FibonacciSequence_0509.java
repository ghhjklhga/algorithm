package algorithm;

public class FibonacciSequence_0509 {
    /**
     * 递归
     * @param N
     * @return
     */
    public static int fib(int N) {
        if (N <= 1) return N;
        return fib(N - 1) + fib(N - 2);
    }

    /**
     * 循环
     * @param N
     * @return
     */
    public static int fib2(int N) {
        int t=0,f1=0,f2=N>1?1:N;
        for (int i=1; i<N; i++,t = f1 + f2,f1 = f2,f2 = t);
        return f2;
    }

    public static void main(String[] args) {
        System.out.println(fib2(4));
    }
}
