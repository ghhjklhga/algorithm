package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReconstructQueue_0406 {
    public static int[][] reconstructQueue(int[][] people) {
        int[][] newQueue = new int[people.length][2];
        for (int i=0; i<newQueue.length; i++) newQueue[i][0] = -9999;
        List<Integer> indexList = new ArrayList<>((int)(people.length * 1.5));
        for (int[] aPeople : people) {
            int shortestIndex = -1;
            for (int i = 0; i < people.length; i++) {
                if (!indexList.contains(i)) {
                    if (shortestIndex == -1) {
                        shortestIndex = i;
                        continue;
                    }
                    if (people[shortestIndex][0] > people[i][0]) {
                        shortestIndex =  i;
                    } else if (people[shortestIndex][0]==people[i][0] && people[shortestIndex][1] < people[i][1]) {
                        shortestIndex = i;
                    }
                }
            }
            // 找到这个矮子的K（前面排多少人）
            int K =people[shortestIndex][1];
            indexList.add(shortestIndex);

            if (K==0 && newQueue[0][0] != -9999) K++;
            for (int i = 0; i<K+1 && i<people.length; i++) {
                if (newQueue[i][0] != -9999)
                    K++; // 第K个，有人的不算
            }

            newQueue[K] = people[shortestIndex];
        }
        return newQueue;
    }

    public static int[][] reconstructQueue2(int[][] people) {
        if (people.length ==0 || people[0].length == 0) return new int[0][0];
        //按照身高降序 K升序排序
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        List<int[]> list = new ArrayList<>();
        //K值定义为 排在h前面且身高大于或等于h的人数
        //因为从身高降序开始插入，此时所有人身高都大于等于h
        //因此K值即为需要插入的位置
        for (int[] i : people) list.add(i[1], i);
        return list.toArray(new int[list.size()][]);
    }


    public static void main(String[] args) {
        int[][] a = reconstructQueue2(new int[][]{{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}});
        for (int[] ints : a) {
            System.out.print("[");
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println("]");
        }
    }
}
