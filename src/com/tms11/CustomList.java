package com.tms11;

import java.util.ArrayList;

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
public class CustomList<C> {

    private ArrayList<C> list;

    @Override
    public String toString() {
        return "{CustomList : " + list + '}';
    }

    /**
     * Конструктор коллекции, размер по умолчанию = 10
     */
    public CustomList() {
        this.list = new ArrayList<>(10);
    }

    /**
     * @param collectionSize - задаваемый размер коллекции
     */
    public CustomList(int collectionSize) {
        this.list = new ArrayList<>(collectionSize);
    }

    /**
     * Метод для добавления элемента в коллецкию
     * @param element - добавляемый элемент
     */
    public void addElement(C element) {
        this.list.add(element);
    }

    /**
     * Метод для удаления элемента из коллекции
     * @param element - элемент который хотим удалить
     */
    public void removeElement(C element) {
        this.list.remove(element);
    }

    /**
     * Метод очистки всей коллеции
     */
    public void removeAllElements() {
        this.list.clear();
    }

    /**
     * Метод получения элемента по индексу
     * @param index - индекс эелемнта, который хотим "достать" из коллекции
     * @return элемент коллекции
     */
    public C getElementByIndex(int index) {
        return this.list.get(index);
    }

    /**
     * Проверка на наличие элемента в коллекции
     * @param element - проверяемый элемент
     * @return true/false
     */
    public boolean isCustomListContainsAnElement(C element) {
        return this.list.contains(element);
    }

    public int size() {
        return this.list.size();
    }

}
