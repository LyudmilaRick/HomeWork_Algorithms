package ru.skypro.service_impl;

import ru.skypro.exception.ElementNotPresentException;
import ru.skypro.exception.NullElementException;
import ru.skypro.exception.OutOfArrayException;
import ru.skypro.service.StringList;

import java.util.Arrays;


/**
 * написать свою реализацию ArrayList
 * он должен работать только со строкам
 * В качестве хранилища используйте массив.
 * В конструкторе должен задаваться размер массива внутри.
 */

public class StringListImpl implements StringList {
    private String[] stringArray;
    /**
     * Величина приращения размера массива по умолчанию.
     */
    public static final int CAPACITY = 5;

    /**
     * Количество действительных (заполненных) записей в массиве.
     */
    protected int count = 0;

    public StringListImpl() {
        stringArray = new String[CAPACITY];
    }

    protected int getCount() {
        return count;
    }

    protected int getPosition() {
        return count - 1;
    }

    protected int getLength() {
        return stringArray.length;
    }

    protected void resize() {
        String[] array = new String[getLength() + CAPACITY];
        System.arraycopy(stringArray, 0, array, 0, getLength());
        stringArray = array;
    }

    @Override
    public String add(String item) {
        if (item == null) {
            throw new NullElementException();
        }
        if (getCount() == getLength()) {
            resize();
        }
        stringArray[count++] = item;
        return stringArray[getPosition()];
    }


    @Override
    public String add(int index, String item) {
        if (item == null) {
            throw new NullElementException();
        }
        if (index >= 0 && index <= getPosition()) {
            // массив заполнен до последнего элемента
            if (getCount() == getLength()) {
                resize();
            }
            // запомнить тот, на место которого вставляем
            String temp = stringArray[index];
            stringArray[index] = item;
            // остальых - сдвинем
            for (int i = getPosition(); i >= index; i--) {
                stringArray[i + 1] = stringArray[i];
            }
            stringArray[index + 1] = temp;
            count++;
            return stringArray[index];
        }
        throw new OutOfArrayException();
    }

    @Override
    public String set(int index, String item) {
        if (item == null) {
            throw new NullElementException();
        }
        if (index >= 0 && index <= getPosition()) {
            stringArray[index] = item;
            return stringArray[index];
        }
        throw new OutOfArrayException();
    }

    @Override
    public String remove(String item) {
        if (item == null) {
            throw new NullElementException();
        }
        // перебираем всех и ищем нужный
        for (int i = 0; i <= getPosition(); i++) {
            if (stringArray[i].equals(item)) {
                remove(i); // удаление по индексу
                return item;
            }
        }
        // цикл пройден, ничего не найдено
        throw new ElementNotPresentException();
    }

    @Override
    public String remove(int index) {
        if (index >= 0 && index <= getPosition()) {
            // сдивигаем всех на один влево
            for (int i = index; i < getLength() - 1; i++) {
                stringArray[i] = stringArray[i + 1];
            }
            count--;
            return stringArray[index];
        }
        throw new OutOfArrayException();

    }

    @Override
    public boolean contains(String item) {
        if (item == null) {
            throw new NullElementException();
        }
        if (indexOf(item) == -1){
            return false;
        }
        return true;
    }

    @Override
    public int indexOf(String item) {
        // перебираем всех и ищем нужный
        for (int i = 0; i < getPosition(); i++) {
            if (stringArray[i].equals(item)) {
                return i;
            }
        }
        // цикл пройден, ничего не найдено
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        if (item == null) {
            throw new NullElementException();
        }
        // перебираем всех и ищем нужный
        for (int i = getPosition(); i > 0; i--) {
            if (stringArray[i].equals(item)) {
                return i;
            }
        }
        // цикл пройден, ничего не найдено
        return -1;
    }

    @Override
    public String get(int index) {
        if (index >= 0 && index <= getPosition()) {
            return stringArray[index];
        }
        throw new OutOfArrayException();
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new NullElementException();
        }
        if (otherList.size() != getLength()) {
            return false;
        }
        return Arrays.equals(stringArray, otherList.toArray());
    }

    @Override
    public int size() {
        return getCount();
    }

    @Override
    public boolean isEmpty() {
        return getCount() == 0;
    }

    @Override
    public void clear() {
        stringArray = new String[getPosition()];
        count = 0;
    }

    @Override
    public String[] toArray() {
        String[] array = new String[getLength()];
        System.arraycopy(stringArray, 0, array, 0, getLength());
        return array;
    }
}
