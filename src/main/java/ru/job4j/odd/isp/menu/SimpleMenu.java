package ru.job4j.odd.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean rsl = false;
        if (findItem(childName).isEmpty()) {
            SimpleMenuItem menuItem = new SimpleMenuItem(childName, actionDelegate);
            if (Objects.equals(parentName, Menu.ROOT)) {
                rsl = rootElements.add(menuItem);
            } else {
                Optional<ItemInfo> itemInfo = findItem(parentName);
                if (itemInfo.isPresent()) {
                    rsl = itemInfo.get().menuItem.getChildren().add(menuItem);
                }
            }
        }
        return rsl;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return findItem(itemName).map(x -> new MenuItemInfo(x.menuItem, x.number));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<>() {
            Iterator<ItemInfo> iterator = new DFSIterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo next = iterator.next();
                return new MenuItemInfo(next.menuItem, next.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> rsl = Optional.empty();
        DFSIterator dfsIterator = new DFSIterator();
        while (dfsIterator.hasNext()) {
            ItemInfo next = dfsIterator.next();
            if (name.equals(next.menuItem.getName())) {
                rsl = Optional.of(next);
                break;
            }
        }
        return rsl;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }

}