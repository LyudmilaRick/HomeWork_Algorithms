package ru.skypro.service_impl;

import ru.skypro.exception.ElementNotPresentException;
import ru.skypro.exception.NullElementException;
import ru.skypro.exception.OutOfArrayException;
import ru.skypro.service.IntegerList;
import ru.skypro.sort.SortUtils;


/**
 * написать свою реализацию ArrayList
 * он должен работать только со строкам
 * В качестве хранилища используйте массив.
 * В конструкторе должен задаваться размер массива внутри.
 */
public class IntegerListImpl implements IntegerList {
    private Integer[] intArray;
    /**
     * Величина приращения размера массива по умолчанию.
     */
    public static final int CAPACITY = 5;

    /**
     * Количество действительных (заполненных) записей в массиве.
     */
    protected int count = 0;

    public IntegerListImpl() {
        intArray = new Integer[CAPACITY];
    }

    protected int getCount() {
        return count;
    }

    protected int getPosition() {
        return count - 1;
    }

    protected int getLength() {
        return intArray.length;
    }

    protected boolean checkLimits(int index) {
        return index >= 0 && index <= getPosition();
    }

    protected boolean checkContent(Integer value) {
        return value == null;
    }
    //private void resize() {
    //    Integer[] array = new Integer[getLength() + CAPACITY];
    //    System.arraycopy(intArray, 0, array, 0, getLength());
    //    intArray = array;
    //}

    /**
     * Реализовать приватный метод grow
     * который будет отвечать за расширение массива-хранилища в 1,5 раза в ситуации,
     * когда место закончилось.
     */
    protected void grow() {
        Integer[] array = new Integer[getLength() * 3 / 2];
        System.arraycopy(intArray, 0, array, 0, getLength());
        intArray = array;
    }

    /**
     * Реализовать приватный метод resize
     * который будет уменьшать массив-хранилище на треть,
     * когда он заполнен меньше, чем наполовину
     */
    protected void resize() {
        Integer[] array = new Integer[getLength() * 2 / 3];
        System.arraycopy(intArray, 0, array, 0, getLength());
        intArray = array;
    }

    private boolean contains(int[] arr, int element) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (element == arr[mid]) {
                return true;
            }

            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public Integer add(Integer item) {
        if (checkContent(item)) {
            throw new NullElementException();
        }
        if (getCount() == getLength()) {
            grow();
        }
        intArray[count++] = item;
        return intArray[getPosition()];
    }

    @Override
    public Integer add(Integer index, Integer item) {
        if (checkContent(item)) {
            throw new NullElementException();
        }
        if (checkLimits(index)) {
            // массив заполнен до последнего элемента
            if (getCount() == getLength()) {
                resize();
            }
            // запомнить тот, на место которого вставляем
            int temp = intArray[index];
            intArray[index] = item;
            // остальых - сдвинем
            for (int i = getPosition(); i >= index; i--) {
                intArray[i + 1] = intArray[i];
            }
            intArray[index + 1] = temp;
            count++;
            return intArray[index];
        }
        throw new OutOfArrayException();
    }

    @Override
    public Integer set(int index, Integer item) {
        if (checkContent(item)) {
            throw new NullElementException();
        }
        if (checkLimits(index)) {
            intArray[index] = item;
            return intArray[index];
        }
        throw new OutOfArrayException();
    }

    @Override
    public Integer remove(Integer item) {
        if (checkContent(item)) {
            throw new NullElementException();
        }
        // перебираем всех и ищем нужный
        for (int i = 0; i <= getPosition(); i++) {
            if (intArray[i].equals(item)) {
                remove(i); // удаление по индексу
                return item;
            }
        }
        // цикл пройден, ничего не найдено
        throw new ElementNotPresentException();
    }

    @Override
    public Integer remove(int index) {
        // заполнен меньше, чем наполовину.
        if (getCount() < getLength() / 2) {
            resize();
        }
        if (checkLimits(index)) {
            // сдвигаем всех на один влево
            for (int i = index; i < getPosition(); i++) {
                intArray[i] = intArray[i + 1];
            }
            // @OKrylov обnullить бывший последний элемент, чтобы массив более не ссылался на него
            intArray[getPosition()] = null;
            count--;
            return intArray[index];
        }
        throw new OutOfArrayException();
    }

    /**
     * отсортировать КОПИЮ массива
     * найти методом двоичного поиска
     * Результаты теста сортировок на массиве из 100 000 элементов
     * Пузырьковая сортировка - 17742
     * Сортировка выбором - 7086
     * Сортировка вставкой - 912
     * Быстрая сортировка - 20
     * Сортировка слиянием - 27
     *
     * @param item - значение для поиска
     * @return boolean
     */
    @Override
    public boolean contains(Integer item) {
        int[] array = new int[getLength()];
        System.arraycopy(intArray, 0, array, 0, getLength());
        SortUtils.mergeSort(array);
        return contains(array, item);
    }

    @Override
    public int indexOf(Integer item) {
        // перебираем всех и ищем нужный
        for (int i = 0; i < getPosition(); i++) {
            if (intArray[i].equals(item)) {
                return i;
            }
        }
        // цикл пройден, ничего не найдено
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        // @OKrylov проверка на null по идее избыточна
        // перебираем всех и ищем нужный
        for (int i = getPosition(); i > 0; i--) {
            if (intArray[i].equals(item)) {
                return i;
            }
        }
        // цикл пройден, ничего не найдено
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (checkLimits(index)) {
            return intArray[index];
        }
        throw new OutOfArrayException();
    }

    /**
     * пройти цикл по размеру меньшего массива, сравнивая поэлементно
     *
     * @param otherList массив
     * @return boolean
     */
    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new NullElementException();
        }
        int min = Math.min(otherList.size(), getLength());
        for (int i = 0; i <= min; i++) {
            if ((intArray[i] == null && otherList.get(i) == null) ||
                    (intArray[i].equals(otherList.get(i)))) {
            } else {
                return false;
            }
        }
        return true;  // прошли весь цикл
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
        intArray = new Integer[getPosition()];
        count = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] array = new Integer[getLength()];
        System.arraycopy(intArray, 0, array, 0, getLength());
        return array;
    }
}

