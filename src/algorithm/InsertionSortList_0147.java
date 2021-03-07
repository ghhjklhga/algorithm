package algorithm;

public class InsertionSortList_0147 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode insertionSortList(ListNode head) {
        ListNode temp = new ListNode(0), pre;
        temp.next = head;
        while(head != null && head.next != null) {
            if(head.val <= head.next.val) {    // 找到最后一个未排序节点
                head = head.next;
                continue;
            }
            pre = temp;
            while (pre.next.val < head.next.val) pre = pre.next;  // 比较
            ListNode curr = head.next;    // 插入
            head.next = curr.next;
            curr.next = pre.next;
            pre.next = curr;
        }
        return temp.next;
    }

    public static void main(String[] args) {

    }
}
