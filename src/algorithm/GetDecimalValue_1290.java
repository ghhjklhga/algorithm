package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 1290. 二进制链表转整数
 */
public class GetDecimalValue_1290 {

    public static int getDecimalValue(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int res = 0;
        for (int i = list.size()-1, power=0; i >= 0; i--,power++) {
            if (list.get(i) > 0) {
                res += Math.pow(2, power);
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(getDecimalValue(new ListNode(1, new ListNode(0, new ListNode(1)))));
        System.out.println(Math.pow(2, 0));
    }
}

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
