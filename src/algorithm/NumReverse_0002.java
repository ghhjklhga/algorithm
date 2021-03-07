package algorithm;

public class NumReverse_0002 {

    public static int reverse(int x){
        long t = 0;
        int y = x;
        while(x != 0) {
            t = t * 10 + x % 10;
            x = x / 10;
        }
        return (int)t==t ? (int)t : 0;
    }

    public static int reverse2(int x){
        String s = String.valueOf(x);
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int y = 0;
        if (x < 0) {
            sb.append('-');
            y = 1;
        }
        for(int i=s.length()-1; i>=y; i--) {
            if (index == 0 && s.charAt(i) == '0') {
                continue;
            }
            index = 1;
            sb.append(s.charAt(i));
        }
        if(sb.toString().equals("")) {
            return 0;
        }
        Integer integer;
        try {
            integer = Integer.valueOf(sb.toString());
        } catch (Exception e) {
            return 0;
        }
        return integer;
    }

    public static void main(String[] args) {
        long startTime1 = System.currentTimeMillis();    //获取开始时间
        int result = reverse2(1534236469);
        System.out.println(result);
        long endTime1 = System.currentTimeMillis();    //获取结束时间

        System.out.println("代码运行时间：" + (endTime1 - startTime1) + "ms");    //输出程序运行时间
    }
}
