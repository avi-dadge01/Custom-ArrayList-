import java.util.Iterator;
import java.util.Arrays;

/* ===================== CUSTOM EXCEPTIONS ===================== */

class UserNoSuchElementException extends RuntimeException {
    UserNoSuchElementException() {
        super();
    }
}

class UserIndexOutOfBoundsException extends RuntimeException {
    UserIndexOutOfBoundsException(String msg) {
        super(msg);
    }
}

/* ===================== MY ARRAY LIST ===================== */

class MyArrayList<E> implements Cloneable, Iterable<E> {

    private static final int INITIAL_CAPACITY = 10;
    private E[] arr;
    private int size;

    /* ===================== CONSTRUCTOR ===================== */

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        arr = (E[]) new Object[INITIAL_CAPACITY];
    }

    /* ===================== BASIC METHODS ===================== */

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /* ===================== ADD METHODS ===================== */

    public boolean add(E ele) {
        if (size == arr.length) {
            ensureCapacity(newCapacity(arr.length));
        }
        arr[size++] = ele;
        return true;
    }

    public void add(int index, E ele) {
        if (index < 0 || index > size) {
            throw new UserIndexOutOfBoundsException(
                "Index " + index + " out of bounds for length " + size
            );
        }

        if (size == arr.length) {
            ensureCapacity(newCapacity(arr.length));
        }

        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }

        arr[index] = ele;
        size++;
    }

    public boolean addAll(MyArrayList<? extends E> list) {
        if (list == null || list.size() == 0) return false;

        ensureCapacity(size + list.size());

        for (E ele : list) {
            arr[size++] = ele;
        }
        return true;
    }

    /* ===================== GET METHODS ===================== */

    public E get(int index) {
        checkIndex(index);
        return arr[index];
    }

    public E getFirst() {
        if (isEmpty()) throw new UserNoSuchElementException();
        return arr[0];
    }

    public E getLast() {
        if (isEmpty()) throw new UserNoSuchElementException();
        return arr[size - 1];
    }

    /* ===================== SET METHOD ===================== */

    public E set(int index, E ele) {
        checkIndex(index);
        E old = arr[index];
        arr[index] = ele;
        return old;
    }

    /* ===================== REMOVE METHODS ===================== */

    public E remove(int index) {
        checkIndex(index);

        E removed = arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[--size] = null;
        return removed;
    }

    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == null ? arr[i] == null : o.equals(arr[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    /* ===================== SEARCH METHODS ===================== */

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == null ? arr[i] == null : o.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (o == null ? arr[i] == null : o.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /* ===================== ARRAY METHODS ===================== */

    public Object[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(arr, size, a.getClass());
        }
        System.arraycopy(arr, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    /* ===================== CAPACITY METHODS ===================== */

    private int newCapacity(int oldCap) {
        return oldCap + (oldCap >> 1); // 1.5x
    }

    @SuppressWarnings("unchecked")
    public void ensureCapacity(int cap) {
        if (cap <= arr.length) return;
        arr = Arrays.copyOf(arr, cap);
    }

    @SuppressWarnings("unchecked")
    public void trimToSize() {
        if (size < arr.length) {
            arr = Arrays.copyOf(arr, size);
        }
    }

    /* ===================== SUBLIST ===================== */

    public MyArrayList<E> subList(int from, int to) {
        if (from < 0 || to > size || from > to) {
            throw new UserIndexOutOfBoundsException(
                "from " + from + " to " + to + " size " + size
            );
        }

        MyArrayList<E> sub = new MyArrayList<>();
        for (int i = from; i < to; i++) {
            sub.add(arr[i]);
        }
        return sub;
    }

    /* ===================== ITERATOR ===================== */

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new UserNoSuchElementException();
                }
                return arr[cursor++];
            }
        };
    }

    /* ===================== CLONE ===================== */

    @Override
    public Object clone() {
        MyArrayList<E> copy = new MyArrayList<>();
        copy.ensureCapacity(this.size);
        for (int i = 0; i < size; i++) {
            copy.add(arr[i]);
        }
        return copy;
    }

    /* ===================== EQUALS & HASHCODE ===================== */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyArrayList)) return false;

        MyArrayList<?> other = (MyArrayList<?>) o;
        if (this.size != other.size) return false;

        for (int i = 0; i < size; i++) {
            if (!(arr[i] == null ? other.arr[i] == null : arr[i].equals(other.arr[i]))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        for (int i = 0; i < size; i++) {
            hash = 31 * hash + (arr[i] == null ? 0 : arr[i].hashCode());
        }
        return hash;
    }

    /* ===================== TOSTRING ===================== */

    @Override
    public String toString() {
        if (size == 0) return "[]";

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(arr[i]).append(", ");
        }
        sb.append(arr[size - 1]).append("]");
        return sb.toString();
    }

    /* ===================== UTILITY ===================== */

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new UserIndexOutOfBoundsException(
                "Index " + index + " out of bounds for length " + size
            );
        }
    }
}


public class MyArrayListDriver {

    public static void main(String[] args) {

        MyArrayList<Integer> list = new MyArrayList<>();

        System.out.println("Is Empty: " + list.isEmpty());

        // add elements
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        System.out.println("List after add: " + list);
        System.out.println("Size: " + list.size());

        // add at index
        list.add(2, 25);
        System.out.println("After add at index 2: " + list);

        // get methods
        System.out.println("First Element: " + list.getFirst());
        System.out.println("Last Element: " + list.getLast());
        System.out.println("Element at index 3: " + list.get(3));

        // set
        list.set(1, 15);
        System.out.println("After set index 1: " + list);

        // search
        System.out.println("Contains 30: " + list.contains(30));
        System.out.println("IndexOf 40: " + list.indexOf(40));
        System.out.println("LastIndexOf 25: " + list.lastIndexOf(25));

        // remove
        list.remove(2);
        System.out.println("After remove index 2: " + list);

        list.removeFirst();
        System.out.println("After removeFirst: " + list);

        list.removeLast();
        System.out.println("After removeLast: " + list);

        // addAll
        MyArrayList<Integer> list2 = new MyArrayList<>();
        list2.add(100);
        list2.add(200);

        list.addAll(list2);
        System.out.println("After addAll: " + list);

        // subList
        MyArrayList<Integer> sub = list.subList(1, 3);
        System.out.println("SubList (1,3): " + sub);

        // iterator
        System.out.print("Iterating: ");
        for (Integer i : list) {
            System.out.print(i + " ");
        }
        System.out.println();

        // clone
        MyArrayList<Integer> cloned = (MyArrayList<Integer>) list.clone();
        System.out.println("Cloned List: " + cloned);

        // equals & hashCode
        System.out.println("Equals cloned: " + list.equals(cloned));
        System.out.println("HashCode original: " + list.hashCode());
        System.out.println("HashCode cloned: " + cloned.hashCode());

        // toArray
        Object[] arr = list.toArray();
        System.out.print("toArray(): ");
        for (Object o : arr) {
            System.out.print(o + " ");
        }
        System.out.println();

        // clear
        list.clear();
        System.out.println("After clear: " + list);
        System.out.println("Is Empty: " + list.isEmpty());
    }
}
