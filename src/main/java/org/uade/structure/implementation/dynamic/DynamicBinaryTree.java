package org.uade.structure.implementation.dynamic;

import org.uade.structure.exception.EmptyADTException;
import org.uade.structure.definition.BinaryTreeADT;

public class DynamicBinaryTree implements BinaryTreeADT {

    private int rootValue;
    private DynamicBinaryTree left, right;
    private boolean isEmpty;
    private DynamicSetADT values;

    public DynamicBinaryTree() {
        this.left = null;
        this.right = null;
        this.isEmpty = true;
        this.values = new DynamicSetADT();
    }

    private DynamicBinaryTree(int value, DynamicSetADT values) {
        this.rootValue = value;
        this.left = null;
        this.right = null;
        this.isEmpty = false;
        this.values = values;
    }

    @Override
    public int getRoot() {
        if (isEmpty) throw new EmptyADTException("This structure is empty");
        return rootValue;
    }

    @Override
    public DynamicBinaryTree getLeft() {
        return left;
    }

    @Override
    public DynamicBinaryTree getRight() {
        return right;
    }

    @Override
    public void add(int value) {
        if (isEmpty) {
            isEmpty = false;
            rootValue = value;
            values.add(value);
            return;
        }

        if (!values.exist(value)) {
            values.add(value);
            insertRecursive(this, value);
        }
    }

    private void insertRecursive(DynamicBinaryTree node, int value) {
        if (value < node.rootValue) {
            if (node.left == null)
                node.left = new DynamicBinaryTree(value, this.values);
            else
                insertRecursive(node.left, value);
        } else {
            if (node.right == null)
                node.right = new DynamicBinaryTree(value, this.values);
            else
                insertRecursive(node.right, value);
        }
    }
    public void remove(int value) {
        if (isEmpty) throw new EmptyADTException("This structure is empty");

        DynamicBinaryTree result = removeNode(this, value);

        if (result == null) {
            this.isEmpty = true;
            this.left = null;
            this.right = null;
        } else {
            this.rootValue = result.getRoot();
            this.left = result.getLeft();
            this.right = result.getRight();
            this.isEmpty = result.isEmpty();
        }
    }

    private DynamicBinaryTree removeNode(DynamicBinaryTree node, int target) {
        if (node == null) return null;

        if (target < node.getRoot()) {
            node.left = removeNode(node.getLeft(), target);
        } else if (target > node.getRoot()) {
            node.right = removeNode(node.getRight(), target);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                DynamicBinaryTree minRight = findMin(node.right);
                node.rootValue = minRight.rootValue;
                node.right = removeNode(node.right, minRight.rootValue);
            }
        }
        return node;
    }

    private DynamicBinaryTree findMin(DynamicBinaryTree node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    @Override
    public boolean isEmpty() {
        return isEmpty;
    }
}