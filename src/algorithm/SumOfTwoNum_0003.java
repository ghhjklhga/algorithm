package algorithm;

public class SumOfTwoNum_0003 {
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int t = 0;
        ListNode header = new ListNode();
        ListNode listNode = header;
        while (l1 != null || l2 != null || t > 0) {
            int x = (l1!=null ? l1.val : 0) + (l2!=null ? l2.val : 0) + t;
            ListNode listNode1 = new ListNode(x % 10);
            listNode.next = listNode1;
            listNode = listNode1;

            t = x / 10;
            l1 = l1!=null ? l1.next : null;
            l2 = l2!=null ? l2.next : null;
        }
        return header;

        //单链表为空或只有一个节点，直接返回原单链表
//        if (listNode == null || listNode.next == null){
//            return listNode;
//        }
//        //前一个节点指针
//        ListNode preNode = null;
//        //当前节点指针
//        ListNode curNode = listNode;
//        //下一个节点指针
//        ListNode nextNode = null;
//
//        while (curNode != null){
//            nextNode = curNode.next;//nextNode 指向下一个节点
//            curNode.next = preNode;//将当前节点next域指向前一个节点
//            preNode = curNode;//preNode 指针向后移动
//            curNode = nextNode;//curNode指针向后移动
//        }

//        return header;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int carry = 0;
        while(l1 != null || l2 != null || carry != 0) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            carry = sumVal / 10;

            ListNode sumNode = new ListNode(sumVal % 10);
            cursor.next = sumNode;
            cursor = sumNode;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        return root.next;
    }
}
