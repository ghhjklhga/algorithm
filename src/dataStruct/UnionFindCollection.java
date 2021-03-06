package dataStruct;

/**
 * 并查集
 */
public class UnionFindCollection {
    private int[] pre;

    int find(int x) {               //查找根节点（代表）
        int r=x;
        while (pre[r] != r )        // 返回根节点 r
            r=pre[r];
        int i=x, j;
        while(i != r) {             // 路径压缩,顺便
            j = pre[i];             // 在改变上级之前用临时变量  j 记录下他的值
            pre[ i ]= r ;           // 把代表改为根节点
            i=j;
        }
        return r;
    }

    public void join(int x,int y) { //判断x y是否连通，
        int fx=find(x),fy=find(y);
        if(fx!=fy) pre[fx]=fy;      //如果已经连通，就不用管了 //如果不连通，就把它们所在的连通分支合并起
    }
}
