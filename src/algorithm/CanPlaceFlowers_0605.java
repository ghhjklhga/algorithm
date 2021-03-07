package algorithm;

/**
 * 605. 种花问题
 */
public class CanPlaceFlowers_0605 {

    /**
     * 在原序列左右两侧都先添个0
     * @param flowerbed
     * @param n
     * @return
     */
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0, t = 1; i <= flowerbed.length; i++) {
            if (i == flowerbed.length) t++;
            else if (flowerbed[i] == 0) t++;
            else t = 0;
            if (t >= 3) {
                count++;
                t = 1;
            }
            if (count >= n) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(canPlaceFlowers(new int[]{0,0,1,0,1,0, 0}, 2));
    }
}
