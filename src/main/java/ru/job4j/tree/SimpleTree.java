package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> father = findBy(parent);
        Optional<Node<E>> kid = findBy(child);
        boolean rsl = father.isPresent() && kid.isEmpty();
        if (rsl) {
            Node<E> newChild = new Node<>(child);
            father.get().children.add(newChild);
        }
        return rsl;
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(e -> e.value.equals(value));
    }

    public boolean isBinary() {
        Optional<Node<E>> rsl = findByPredicate(e -> e.children.size() > 2);
        return rsl.isEmpty();
    }
}