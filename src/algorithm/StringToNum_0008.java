package algorithm;

public class StringToNum_0008 {
    public static int myAtoi(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        for (int i=0,j=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (i == j &&c <= 57 && c >= 48) {
                j++;
                sb.append(Character.toString(c));
            } else {
                if (i == 0 && (c == 43 || c == 45)) {
                    sb.append(Character.toString(c));
                    j++;
                }
            }
        }
        if (sb.length() == 0 || (sb.length()==1 && (sb.toString().charAt(0) < 48 || sb.toString().charAt(0) > 57))) {
            return 0;
        }
        try {
            return Integer.valueOf(sb.toString());
        } catch (NumberFormatException e) {
            return (sb.toString().charAt(0)==45) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) {
        String a = "21474836460";
        System.out.println(myAtoi(a));
    }
}
