package algorithm;

import java.util.*;

/**
 * 串联所有单词的子串
 *
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 *
 */
public class FindSubstring_0030 {
    /**
     * 判断map.equal(map)会循环，也是O(M)
     * @param s
     * @param words
     * @return
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        if (s == null || s.length() == 0 || words.length == 0) return Collections.emptyList();
        // 单词长度
        int length = words[0].length();
        List<Integer> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        // 记录每个单词出现次数
        Arrays.stream(words).forEach(a -> map.put(a, map.getOrDefault(a, 0)+1));
        for (int i=0; (i+length*words.length)<=s.length(); i++) {
            Map<String, Integer> tMap = new HashMap<>();
            // 记录子串的每个单词出现次数
            for (int j=i; j+length<=i+length*words.length; j+=length) {
                tMap.put(s.substring(j, j+length), tMap.getOrDefault(s.substring(j, j+length), 0)+1);
            }
            // 判断两个map是否相等（key-value）
            if (tMap.equals(map)) list.add(i);
        }
        return list;
    }

    /**
     * 执行用时：121 ms, 在所有 Java 提交中击败了51.54%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了85.06%的用户
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int wordNum = words.length;
        if (wordNum == 0) return res;
        int wordLen = words[0].length();
        //HashMap1 存所有单词
        HashMap<String, Integer> allWords = new HashMap<String, Integer>();
        Arrays.stream(words).forEach(a -> allWords.put(a, allWords.getOrDefault(a, 0)+1));
        //遍历所有子串
        for (int i = 0; i < s.length() - wordNum * wordLen + 1; i++) {
            //HashMap2 存当前扫描的字符串含有的单词
            HashMap<String, Integer> hasWords = new HashMap<String, Integer>();
            int num = 0;
            //判断该子串是否符合
            while (num < wordNum) {
                String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                //判断该单词在 HashMap1 中
                if (allWords.containsKey(word)) {
                    int value = hasWords.getOrDefault(word, 0);
                    hasWords.put(word, value + 1);
                    //判断当前单词的 value 和 HashMap1 中该单词的 value
                    if (hasWords.get(word) > allWords.get(word)) {
                        break;
                    }
                } else {
                    break;
                }
                num++;
            }
            //判断是不是所有的单词都符合条件
            if (num == wordNum) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 7ms，是个大佬
     * 查过的就不再查，分三种情况
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring3(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int wordNum = words.length;
        if (wordNum == 0) {
            return res;
        }
        int wordLen = words[0].length();
        HashMap<String, Integer> allWords = new HashMap<String, Integer>();
        for (String w : words) {
            int value = allWords.getOrDefault(w, 0);
            allWords.put(w, value + 1);
        }
        //将所有移动分成 wordLen 类情况
        for (int j = 0; j < wordLen; j++) {
            HashMap<String, Integer> hasWords = new HashMap<String, Integer>();
            int num = 0; //记录当前 HashMap2（这里的 hasWords 变量）中有多少个单词
            //每次移动一个单词长度
            for (int i = j; i < s.length() - wordNum * wordLen + 1; i = i + wordLen) {
                boolean hasRemoved = false; //防止情况三移除后，情况一继续移除
                while (num < wordNum) {
                    String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                    if (allWords.containsKey(word)) {
                        int value = hasWords.getOrDefault(word, 0);
                        hasWords.put(word, value + 1);
                        //出现情况三，遇到了符合的单词，但是次数超了
                        if (hasWords.get(word) > allWords.get(word)) {
                            // hasWords.put(word, value);
                            hasRemoved = true;
                            int removeNum = 0;
                            //一直移除单词，直到次数符合了
                            while (hasWords.get(word) > allWords.get(word)) {
                                String firstWord = s.substring(i + removeNum * wordLen, i + (removeNum + 1) * wordLen);
                                int v = hasWords.get(firstWord);
                                hasWords.put(firstWord, v - 1);
                                removeNum++;
                            }
                            num = num - removeNum + 1; //加 1 是因为我们把当前单词加入到了 HashMap 2 中
                            i = i + (removeNum - 1) * wordLen; //这里依旧是考虑到了最外层的 for 循环，看情况二的解释
                            break;
                        }
                        //出现情况二，遇到了不匹配的单词，直接将 i 移动到该单词的后边（但其实这里
                        //只是移动到了出现问题单词的地方，因为最外层有 for 循环， i 还会移动一个单词
                        //然后刚好就移动到了单词后边）
                    } else {
                        hasWords.clear();
                        i = i + num * wordLen;
                        num = 0;
                        break;
                    }
                    num++;
                }
                if (num == wordNum) {
                    res.add(i);

                }
                //出现情况一，子串完全匹配，我们将上一个子串的第一个单词从 HashMap2 中移除
                if (num > 0 && !hasRemoved) {
                    String firstWord = s.substring(i, i + wordLen);
                    int v = hasWords.get(firstWord);
                    hasWords.put(firstWord, v - 1);
                    num = num - 1;
                }

            }

        }
        return res;
    }

    public static void main(String[] args) {
        findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", new String[]{"fooo","barr","wing","ding","wing"}).forEach(System.out::println);
    }
}
