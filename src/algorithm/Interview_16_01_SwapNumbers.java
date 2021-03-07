package algorithm;

import java.util.Arrays;

/**
 * 交换数字
 * 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
 */
public class Interview_16_01_SwapNumbers {
    /**
     * 异或
     * @param numbers
     * @return
     */
    public int[] swapNumbers(int[] numbers) {
        numbers[0] = numbers[0]^numbers[1];
        numbers[1] = numbers[0]^numbers[1];
        numbers[0] = numbers[0]^numbers[1];
        return numbers;
    }

    /**
     * 加减法
     * @param numbers
     * @return
     */
    public int[] swapNumbers2(int[] numbers) {
        numbers[0] = numbers[0] + numbers[1];
        numbers[1] = numbers[0] - numbers[1];
        numbers[0] = numbers[0] - numbers[1];
        return numbers;
    }

    public static void main(String[] args) {

    }
}
