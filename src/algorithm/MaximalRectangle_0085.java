package algorithm;

import java.util.Stack;

/**
 * 85. 最大矩形
 *
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 */
public class MaximalRectangle_0085 {

    /**
     * 暴力，每个点为右下角，遍历，O(m²n)
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        //保存以当前数字结尾的连续 1 的个数
        int[][] width = new int[matrix.length][matrix[0].length];
        int maxArea = 0;
        //遍历每一行
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                //更新 width
                if (matrix[row][col] == '1') {
                    if (col == 0) {
                        width[row][col] = 1;
                    } else {
                        width[row][col] = width[row][col - 1] + 1;
                    }
                } else {
                    width[row][col] = 0;
                }
                //记录所有行中最小的数
                int minWidth = width[row][col];
                //向上扩展行
                for (int up_row = row; up_row >= 0; up_row--) {
                    int height = row - up_row + 1;
                    //找最小的数作为矩阵的宽
                    minWidth = Math.min(minWidth, width[up_row][col]);
                    //更新面积
                    maxArea = Math.max(maxArea, height * minWidth);
                }
            }
        }
        return maxArea;
    }

    /**
     * 动态规划
     *
     * 以下解法都是
     * 时间复杂度：O（mn）。
     * 空间复杂度：O（n）。
     * @return
     */
    public int maximalRectangle3(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int maxArea = 0;
        //记忆化动态数组
        int[][] rect = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            //针对每一行计算[0,col]能够形成的最大矩形
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    //如果当前是第一个元素需要单独处理
                    if (j == 0) rect[i][j] = 1; //rect[i][j]代表[i][j]位置能够形成的宽度，是[i][j-1]加1
                    else rect[i][j] = rect[i][j - 1] + 1;
                } else {
                    //如果为‘0’，则为0
                    rect[i][j] = 0;
                }
                int minwidth = rect[i][j];
                //这个地方的理解是难点
                //当前要确定matrix[i][j]能形成的最大矩形
                //方法是通过通过当前行逐行递减，意味着高度（hegiht）逐渐增加(这个过程中为了提高时间效率,通过`if(rect[row][j]==0) break;`可以节省时间我的从192ms到64ms),然后再通过比较当前获得宽度与之前动态规划在相应位置获得宽度比较，得到最小的宽度，进而更新最大矩形长度。
                for (int row = i; row >= 0; row--) {
                    if (rect[row][j] == 0) break;
                    //获得高
                    int height = i - row + 1;
                    //获得最小宽度
                    minwidth = Math.min(minwidth, rect[row][j]);
                    maxArea = Math.max(maxArea, height * minwidth);
                }
            }
        }
        return maxArea;
    }

    public int maximalRectangle4(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[] heights = new int[matrix[0].length];
        int maxArea = 0;
        for (int row = 0; row < matrix.length; row++) {
            //遍历每一列，更新高度
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == '1') {
                    heights[col] += 1;
                } else {
                    heights[col] = 0;
                }
            }
            //调用上一题的解法，更新函数
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }


    /**
     * 单调栈法
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        int p = 0;
        while (p < heights.length) {
            //栈空入栈
            if (stack.isEmpty()) {
                stack.push(p);
                p++;
            } else {
                int top = stack.peek();
                //当前高度大于栈顶，入栈
                if (heights[p] >= heights[top]) {
                    stack.push(p);
                    p++;
                } else {
                    //保存栈顶高度
                    int height = heights[stack.pop()];
                    //左边第一个小于当前柱子的下标
                    int leftLessMin = stack.isEmpty() ? -1 : stack.peek();
                    //右边第一个小于当前柱子的下标
                    int RightLessMin = p;
                    //计算面积
                    int area = (RightLessMin - leftLessMin - 1) * height;
                    maxArea = Math.max(area, maxArea);
                }
            }
        }
        while (!stack.isEmpty()) {
            //保存栈顶高度
            int height = heights[stack.pop()];
            //左边第一个小于当前柱子的下标
            int leftLessMin = stack.isEmpty() ? -1 : stack.peek();
            //右边没有小于当前高度的柱子，所以赋值为数组的长度便于计算
            int RightLessMin = heights.length;
            int area = (RightLessMin - leftLessMin - 1) * height;
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }

    /**
     * 解法二中套用的栈的解法，我们其实可以不用调用函数，而是把栈糅合到原来求 heights 中。因为栈的话并不是一次性需要所有的高度，所以可以求出一个高度，然后就操作栈。
     *
     * 里边有一个小技巧，84 题的栈解法中，我们用了两个 while 循环，第二个 while 循环用来解决遍历完元素栈不空的情况。其实，我们注意到两个 while 循环的逻辑完全一样的。所以我们可以通过一些操作，使得遍历结束后，依旧进第一个 while 循环，从而剩下了第 2 个 while 循环，代码看起来会更简洁。
     *
     * 那就是 heights 多申请一个元素，赋值为 0。这样最后一次遍历的时候，栈顶肯定会大于当前元素，所以就进入了第一个 while 循环。
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle5(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[] heights = new int[matrix[0].length + 1]; //小技巧后边讲
        int maxArea = 0;
        for (int row = 0; row < matrix.length; row++) {
            Stack<Integer> stack = new Stack<Integer>();
            heights[matrix[0].length] = 0;
            //每求一个高度就进行栈的操作
            for (int col = 0; col <= matrix[0].length; col++) {
                if (col < matrix[0].length) { //多申请了 1 个元素，所以要判断
                    if (matrix[row][col] == '1') {
                        heights[col] += 1;
                    } else {
                        heights[col] = 0;
                    }
                }
                if (stack.isEmpty() || heights[col] >= heights[stack.peek()]) {
                    stack.push(col);
                } else {
                    //每次要判断新的栈顶是否高于当前元素
                    while (!stack.isEmpty() && heights[col] < heights[stack.peek()]) {
                        int height = heights[stack.pop()];
                        int leftLessMin = stack.isEmpty() ? -1 : stack.peek();
                        int RightLessMin = col;
                        int area = (RightLessMin - leftLessMin - 1) * height;
                        maxArea = Math.max(area, maxArea);
                    }
                    stack.push(col);
                }
            }

        }
        return maxArea;
    }

    // 有时候，如果可以把题抽象到已解决的问题当中去，可以大大的简化问题，很酷！
}
