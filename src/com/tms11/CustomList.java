package com.tms11;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Представим, что в Java нет коллекции типа ArrayList.
 * Создать свой класс, симулирующий работу класса динамической коллекции - т.е. создать свою кастомную коллекцию.
 * В основе коллекции будет массив.
 * Кастомная коллекция должна хранить элементы разных классов(т.е. это generic).
 * Предусмотреть операции добавления элемента, удаления элемента, получение элемента по индексу,
 * проверка есть ли элемент в коллекции, метод очистки все коллеции.
 * Предусмотреть конструктор без параметров - создает массив размером по умолчанию.
 * Предусмотреть конструктор с задаваемым размером внутреннего массива.
 * Предусамотреть возможность автоматического расширения коллекции при добавлении элемента в том случае,
 * когда коллекция уже заполнена.
 */
public class CustomList<E> {

    private Object[] myList;
    private int idx;

    public CustomList() {
        this(10);
    }

    public CustomList(int initialcapacity) {
        myList = new Object[initialcapacity];
    }

    public int size() {
        return idx;
    }

    public void add(E element) {
        add(idx, element);
    }

    public void add(int index, E element) {
        if (index < 0 || index > idx) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (idx > myList.length - 1) {
            increaseSize();
        }
        if (index < idx) {
            if (idx + 1 - index >= 0) System.arraycopy(myList, index, myList, index + 1, idx + 1 - index);
            myList[index] = element;
            idx++;
        } else {
            myList[idx++] = element;
        }
    }

    public boolean isContains(E element) {
        return Arrays.stream(myList).anyMatch((Predicate<? super Object>) element);
    }

    public E get(int index) {
        if (index < 0 || index > idx) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E) myList[index];
    }

    public void remove(E element) {
        for (int i = 0; i < idx; i++) {
            if (myList[i].equals(element)) {
                remove(i);
            }
        }
    }

    public void removeAll() {
        for (int i = 0; i < idx; i++) remove(i);
    }

    public void remove(int index) {
        if (index < 0 || index > idx) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (idx - 1 - index >= 0) System.arraycopy(myList, index + 1, myList, index, idx - 1 - index);

        myList[idx - 1] = null;
        idx--;
    }

    private void increaseSize() {
        int newcapacity = (myList.length * 3 / 2) + 1;
        myList = Arrays.copyOf(myList, newcapacity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < idx; i++) {
            sb.append(myList[i].toString() + ",");
        }

        return "[" + sb.toString().substring(0, sb.length() - 1) + "]";
    }

}