package algorithm;

/**
 * 86. 分隔链表
 *
 * 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 */
public class Partition {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode partition(ListNode head, int x) {
        // 小链表 头
        ListNode smallHead = new ListNode(0);
        // 大连表 头
        ListNode bigHead = new ListNode(0);
        // 移动指针
        ListNode small = smallHead;
        ListNode big = bigHead;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else if (head.val >= x) {
                big.next = head;
                big = big.next;
            }
            head = head.next;
        }
        // 注意，这里一定要释放，否则可能会有环
        big.next = null;
        // 将两个链表连接起来
        small.next = bigHead.next;
        return smallHead.next;
    }

}
