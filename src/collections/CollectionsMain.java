package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CollectionsMain {

    public static void main(String[] args) {
        Collection<Integer> sourceList = Arrays.asList(0, 1, 4);
        System.out.println("  Linked list");
        testList(new HWLinkedList<>(sourceList));
        System.out.println("  Array list");
        testList(new HWArrayList<>(sourceList));
    }

    private static void testList(HWList<Integer> list) {
        list.add(7);
        System.out.println(Arrays.toString(list.toArray()));

        list.remove(0);
        System.out.println(Arrays.toString(list.toArray()));

        list.remove(2);
        System.out.println(Arrays.toString(list.toArray()));

        list.add(1, 5);
        System.out.println(Arrays.toString(list.toArray()));

        try {
            list.add(4, -1);
            System.out.println(Arrays.toString(list.toArray()));
        } catch (Exception e) {
            System.out.println("nope");
        }

        list.add(3, 8);
        System.out.println(Arrays.toString(list.toArray()));

        list.add(0, 9);
        System.out.println(Arrays.toString(list.toArray()));

        list.add(4, 16);
        System.out.println(Arrays.toString(list.toArray()));

        list.set(0, 12);
        System.out.println(Arrays.toString(list.toArray()));

        list.set(4, 11);
        System.out.println(Arrays.toString(list.toArray()));

        HWList.sort(list);
        System.out.println("Sorted: " + Arrays.toString(list.toArray()));

        System.out.println(list.get(0));

        System.out.println(list.indexOf(8));

        System.out.println(list.contains(999));

        Collection<Integer> range = new ArrayList<>();
        for (int i = 25; i < 55; i++) {
            range.add(i);
        }
        list.addAll(range);
        System.out.println(Arrays.toString(list.toArray()));

        System.out.println();
    }

}
