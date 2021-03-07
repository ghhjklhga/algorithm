package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 */
public class SortList_0148 {
    class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 换成list，部分开好序的比较慢，全乱序的相对快
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        ListNode temp = head;
        List<Integer> list = new ArrayList<>();
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        list.sort(Comparator.comparingInt(o -> o));
        ListNode res = head;
        for (Integer a : list) {
            head.next = new ListNode(a);
            head = head.next;
        }
        return res!=null ? res.next : null;
    }

    /**
     * 插入法，部分排好序的比较快，全乱序的更慢
      * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
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

    /**
     * 下面是归并做法，稳定O(nlogn)，适合链表操作。
     * @param head
     * @return
     */
    public ListNode sortList2(ListNode head) {
        return mergeSort(head);
    }
    // 归并排序
    public ListNode mergeSort(ListNode head){
        // 如果没有结点/只有一个结点，无需排序，直接返回
        if (head==null||head.next==null) return head;
        // 快慢指针找出中位点
        ListNode slowp=head,fastp=head.next.next,l,r;
        while (fastp!=null&&fastp.next!=null){
            slowp=slowp.next;
            fastp=fastp.next.next;
        }
        // 对右半部分进行归并排序
        r=mergeSort(slowp.next);
        // 链表判断结束的标志：末尾节点.next==null
        slowp.next=null;
        // 对左半部分进行归并排序
        l=mergeSort(head);
        return mergeList(l,r);
    }

    // 合并链表
    private ListNode mergeList(ListNode l,ListNode r){
        // 临时头节点
        ListNode tmpHead=new ListNode(-1);
        ListNode p=tmpHead;
        while (l!=null&&r!=null){
            if (l.val<r.val){
                p.next=l;
                l=l.next;
            }else {
                p.next=r;
                r=r.next;
            }
            p=p.next;
        }
        p.next=l==null?r:l;
        return tmpHead.next;
    }
    /**
     * 快排
     * @param head
     * @return
     */
    public ListNode sortList4(ListNode head) {
        ListNode cur = head;
        ListNode rst = new ListNode(0);
        ArrayList<Integer> data = new ArrayList<>();
        while (cur != null) {
            data.add(cur.val);
            cur = cur.next;
        }
        data.sort(Comparator.comparingInt(o -> o));
        cur = rst;
        for (int i : data) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        return rst.next;
    }


    /**
     * 2ms这个逼！？竟然是归并的4/1，流批
     * @param head
     * @return
     */
    public static ListNode sortList5(ListNode head) {
        int maxNumber = Integer.MIN_VALUE, minNumber = Integer.MAX_VALUE;
        // 先找出最大最小
        for (ListNode current = head; current != null; current = current.next) {
            maxNumber = Math.max(current.val, maxNumber);
            minNumber = Math.min(current.val, minNumber);
        }
        // 保存每一个 以val-最小val作为下标、i++作为值的数组
        int[] numberCountMap = new int[maxNumber - minNumber + 1];
        for (ListNode current = head; current != null; current = current.next) {
            numberCountMap[current.val - minNumber]++;
        }
        ListNode current = head;
        for (int i = 0; i < numberCountMap.length; i++) {
            while (numberCountMap[i]-- > 0) {
                current.val = i + minNumber;
                current = current.next;
            }
        }
        return head;
    }

    public ListNode sortList6(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode fast = head.next; //快指针
        ListNode slow = head; //慢指针

        while(fast!=null && fast.next!=null){ //快慢指针找到链表中点
            fast = fast.next.next; //快指针走两步
            slow = slow.next; //慢指针走一步
        }
        ListNode rightHead = slow.next; //链表第二部分的头节点
        slow.next = null; //cut 链表

        ListNode left = sortList6(head); //递归排序前一段链表
        ListNode right = sortList6(rightHead); //递归排序后一段链表
        return merge6(left,right);
    }
    public ListNode merge6(ListNode h1,ListNode h2){ //合并两个有序链表
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while(h1!=null && h2!=null){
            if(h1.val < h2.val){
                p.next = h1;
                h1 = h1.next;
            }else{
                p.next = h2;
                h2 = h2.next;
            }
            p = p.next;
        }
        if(h1!=null)    p.next = h1;
        else if(h2!=null) p.next = h2;
        return dummy.next;
    }

    public static void main(String[] args) {
    }
}
