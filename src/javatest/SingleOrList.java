package javatest;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试单个元素和转为List的区别
 */
public class SingleOrList {

    public static Long testSingle(int count){
        List<SingleBean> list = new ArrayList<>();
        for (int i=0; i<count; i++) {
            list.add(new SingleBean(String.valueOf(i), String.valueOf(i)));
        }
        String t = String.valueOf(count);
        long startTime = System.currentTimeMillis();
        for (SingleBean singleBean : list) {
            if (singleBean.getId().equals(t) && singleBean.getName().equals(t)){
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static Long testList(int count){
        List<ListBean> list = new ArrayList<>();
        for (int i=0; i<count; i++) {
            List<String> children = new ArrayList<>(5);
            children.add(String.valueOf(i++));
            children.add(String.valueOf(i++));
            children.add(String.valueOf(i++));
            children.add(String.valueOf(i++));
            children.add(String.valueOf(i));
            ListBean listBean = new ListBean(String.valueOf(i), children);
            list.add(listBean);
        }
        String t = String.valueOf(count);
        long startTime = System.currentTimeMillis();
        for (ListBean listBean : list) {
            if (listBean.getId().equals(t) ){
                List<String> names = listBean.getName();
                for (String name : names) {
                    if (name.equals(t)) break;
                }
            }
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        for (int i=1000; i <= 10000000; i*=10) {
            System.out.println("数据量：" + i);
            System.out.println("单个用时：" + testSingle(i));
            System.out.println("列表用时：" + testList(i));
        }
    }
}

class SingleBean {
    private String id;
    private String name;

    public SingleBean() {
    }

    public SingleBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class ListBean {
    private String id;
    private List<String> name;

    public ListBean() {
    }

    public ListBean(String id, List<String> name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}


