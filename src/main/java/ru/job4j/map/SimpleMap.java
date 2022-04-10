package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 16;

    private int modCount = 0;

    private int size = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (size >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            rsl = true;
            size++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> kvMapEntry : table) {
            if (kvMapEntry != null) {
                int newIndex = indexFor(hash(kvMapEntry.key.hashCode()));
                newTable[newIndex] = kvMapEntry;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        V rsl;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null && table[index].key.equals(key)) {
            rsl = table[index].value;
        } else {
            rsl = null;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl;
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            rsl = true;
            size--;
            modCount++;
        } else {
            rsl = false;
        }
        return rsl;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<K> iterator() {
        int iterMod = modCount;
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (iterMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] != null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
