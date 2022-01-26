package ru.skypro;

import ru.skypro.service.StringList;
import ru.skypro.service_impl.StringListImpl;

public class Main {

    public static void main(String[] args) {
        // write your code here
        StringList array = new StringListImpl();
        /**
         * add(String item)
         * add(int index, String item);
         */
        array.add("первый");
        array.add("второй");
        array.add("третий");
        array.add("четвертый");
        array.add("пятый");
        array.add("шестой");
        array.add(3, "третий");
        /**
         * set(int index, String item)
         * get(int index)
         */
        array.set(1, "first");
        array.get(3);
        /**
         *   remove(String item)
         *   remove(String item)
         */
        array.remove(1);
        array.remove("четвертый");
        //array.remove("forth");
        /**
         *  indexOf(String item)
         *  lastIndexOf(String item)
         */
        System.out.println("Индекс элемента равен " + array.indexOf("пятый"));
        System.out.println("Индекс элемента равен " + array.lastIndexOf("третий"));
        System.out.println("Индекс элемента равен " + array.lastIndexOf("нулевой"));
        /**
         *  int size();
         *  void clear();
         */
        System.out.println(array.size());
        array.clear();

    }
}
