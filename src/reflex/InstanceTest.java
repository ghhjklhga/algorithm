package reflex;

import algorithm.AllCellsDistOrder_1030;

public class InstanceTest extends AllCellsDistOrder_1030{

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object instanceTest = Class.forName("reflex.InstanceTest").newInstance();
        System.out.println(instanceTest instanceof AllCellsDistOrder_1030);
    }
}
