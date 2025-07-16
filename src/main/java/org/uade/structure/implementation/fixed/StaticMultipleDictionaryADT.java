package org.uade.structure.implementation.fixed;

import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.definition.SetADT;

public class StaticMultipleDictionaryADT implements MultipleDictionaryADT {

    private static final int MAX_KEYS = 100;
    private static final int MAX_VALUES_PER_KEY = 100;

    private int[] keys;
    private int[][] values;
    private int[] valueCounts;
    private int keyCount;

    public StaticMultipleDictionaryADT() {
        keys = new int[MAX_KEYS];
        values = new int[MAX_KEYS][MAX_VALUES_PER_KEY];
        valueCounts = new int[MAX_KEYS];
        keyCount = 0;
    }

    private int findKeyIndex(int key) {
        for (int i = 0; i < keyCount; i++) {
            if (keys[i] == key) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void add(int key, int value) {
        int keyIdx = findKeyIndex(key);
        if (keyIdx == -1) {
            if (keyCount < MAX_KEYS) {
                keys[keyCount] = key;
                values[keyCount][0] = value;
                valueCounts[keyCount] = 1;
                keyCount++;
            }
        } else {
            if (valueCounts[keyIdx] < MAX_VALUES_PER_KEY) {
                values[keyIdx][valueCounts[keyIdx]] = value;
                valueCounts[keyIdx]++;
            }
        }
    }

    @Override
    public void remove(int key) {
        int keyIdx = findKeyIndex(key);
        if (keyIdx != -1) {
            for (int i = keyIdx; i < keyCount - 1; i++) {
                keys[i] = keys[i + 1];
                valueCounts[i] = valueCounts[i + 1];
                for (int j = 0; j < MAX_VALUES_PER_KEY; j++) {
                    values[i][j] = values[i + 1][j];
                }
            }
            keyCount--;
        }
    }

    @Override
    public int[] get(int key) {
        int keyIdx = findKeyIndex(key);
        if (keyIdx != -1) {
            int[] result = new int[valueCounts[keyIdx]];
            System.arraycopy(values[keyIdx], 0, result, 0, valueCounts[keyIdx]);
            return result;
        }
        return new int[0];
    }

    @Override
    public SetADT getKeys() {
        return new StaticSetADT(keys, keyCount);
    }

    @Override
    public boolean isEmpty() {
        return keyCount == 0;
    }

    @Override
    public void remove(int key, int value) {
        int keyIdx = findKeyIndex(key);
        if (keyIdx != -1) {
            int valueIdx = -1;
            for (int i = 0; i < valueCounts[keyIdx]; i++) {
                if (values[keyIdx][i] == value) {
                    valueIdx = i;
                    break;
                }
            }
            if (valueIdx != -1) {
                for (int i = valueIdx; i < valueCounts[keyIdx] - 1; i++) {
                    values[keyIdx][i] = values[keyIdx][i + 1];
                }
                valueCounts[keyIdx]--;
                if (valueCounts[keyIdx] == 0) {
                    remove(key);
                }
            }
        }
    }
}