package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 49. 字母异位词分组
 *
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 */
public class GroupAnagrams_0049 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Character, Integer> map = new HashMap<Character, Integer>(){{
            put('a', 2);
            put('b', 3);
            put('c', 5);
            put('d', 7);
            put('e', 11);
            put('f', 13);
            put('g', 17);
            put('h', 19);
            put('i', 23);
            put('j', 29);
            put('k', 31);
            put('l', 37);
            put('m', 41);
            put('n', 43);
            put('o', 47);
            put('p', 53);
            put('q', 59);
            put('r', 61);
            put('s', 67);
            put('t', 71);
            put('u', 83);
            put('v', 89);
            put('w', 97);
            put('x', 103);
            put('y', 107);
            put('z', 109);
        }};
        Map<Long, List<String>> collect = new HashMap<>();
        for (String str : strs) {
            long count = 1;
            char[] chars = str.toCharArray();
            for (char aChar : chars) count *= map.get(aChar);
            List<String> list = collect.getOrDefault(count, new ArrayList<>());
            list.add(str);
            collect.put(count, list);
        }
        return new ArrayList<>(collect.values());
    }
}
