# ☕ Java Collections Framework (JCF)

The Java Collections Framework (JCF) provides highly optimized data structures and algorithms, saving development time and ensuring performance.

## I. Framework Overview

The **Collection Framework** is the umbrella term, which includes:

1.  **`Collection` Interface:** The root of most hierarchical data structures (Lists, Sets, Queues).
2.  **`Map` Interface:** Separated from `Collection` because it stores **Key-Value pairs**, not single values.
3.  **`Iterator` Interface:** Used for traversing collections.

### **Core Interfaces & Properties**

| Interface | Type of Data Stored | Key Property | Common Implementations |
| :--- | :--- | :--- | :--- |
| **`List`** | Ordered sequence of elements. | Maintains **insertion order**; **duplicates allowed**. | `ArrayList`, `LinkedList`, `Stack`, `Vector` |
| **`Set`** | Unique elements. | **Duplicates are NOT allowed**; order is generally not guaranteed. | `HashSet`, `LinkedHashSet`, `TreeSet` |
| **`Queue`** | Elements processed in a specific order. | Defines element retrieval priority (**FIFO** or custom). | `PriorityQueue`, `LinkedList`, `ArrayDeque` |
| **`Map`** | Key-Value pairs. | Keys must be **unique**; values may be duplicated. | `HashMap`, `TreeMap` |

*(All collection objects are found in the `java.util` package.)*

-----

## II. `List` Implementations

### A. `ArrayList`

An `ArrayList` uses an internal array for storage but can **dynamically grow and shrink** its size, overcoming the limitations of standard Java arrays.

| Function | Time Complexity (TC) | Description |
| :--- | :--- | :--- |
| `add(E e)` | $O(1)$ amortized | Adds element to the end. |
| `add(int index, E e)` | $O(N)$ | Inserts element at `index`. Requires shifting elements. |
| `addAll(Collection c)` | $O(N)$ | Adds all elements from another collection to the end. |
| `get(int index)` | $O(1)$ | Retrieves element at `index` (direct memory access). |
| `set(int index, E e)` | $O(1)$ | **Updates/replaces** the element at `index`. |
| `remove(int index)` | $O(N)$ | Removes element at `index`. Requires shifting elements. |
| `contains(E e)` | $O(N)$ | Checks if element is present (linear scan required). |
| `clear()` | $O(N)$ | Removes all elements. |
| `size()` | $O(1)$ | Returns the number of elements. |

> **Internal Working:** When an `ArrayList`'s internal array is full, it automatically creates a new, larger array (e.g., $\approx N + N/2 + 1$) and copies all elements over. This is an expensive $O(N)$ operation, but happens rarely, leading to an **amortized $O(1)$** complexity for `add`.

### B. `Stack`

A `Stack` implements the **Last-In, First-Out (LIFO)** principle.

| Function | Description |
| :--- | :--- |
| `push(E e)` | Adds an element to the **top** of the stack. |
| `pop()` | **Removes and returns** the element from the **top**. |
| `peek()` | **Returns** the element at the **top** without removing it. |

### C. `LinkedList`

`LinkedList` implements both the `List` and `Queue` interfaces. Its $O(1)$ time complexity for insertions and deletions at the ends/middle (if the node is known) makes it useful for Queue implementations.

-----

## III. `Queue` Implementations

`Queue` implements the **First-In, First-Out (FIFO)** principle. Elements are added at the **rear** and removed from the **front**.

### A. Core `Queue` Methods (Used in `LinkedList`, `PriorityQueue`, `ArrayDeque`)

| Operation | Success/Failure Behavior | Purpose |
| :--- | :--- | :--- |
| **`offer(E e)`** | Returns `true`/`false`. **(Preferred in contests/interviews)** | Inserts element (non-exception). |
| **`add(E e)`** | Throws exception on failure. | Inserts element (exception on failure). |
| **`poll()`** | Returns element or **`null`** if empty. **(Preferred)** | Retrieves and removes head (non-exception). |
| **`remove()`** | Throws exception if empty. | Retrieves and removes head (exception if empty). |
| **`peek()`** | Returns element or **`null`** if empty. **(Preferred)** | Retrieves head but does not remove (non-exception). |
| **`element()`** | Throws exception if empty. | Retrieves head but does not remove (exception if empty). |

### B. `PriorityQueue`

Implements a min-heap by default. Elements are retrieved based on **priority** (the smallest element comes out first).

  * **Implementation:** Internally uses a **Heap** data structure.
  * **Default Behavior:** **Min-Heap** (smallest element has the highest priority).
  * **Max-Heap:** Can be achieved by passing a `Comparator.reverseOrder()` into the constructor.
    ```java
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    ```

### C. `ArrayDeque` (Double-Ended Queue)

Pronounced "Array Deck." Allows **insertion and deletion from both ends** (front and rear). Useful for algorithms like the **Sliding Window Technique**.

| Function | Description |
| :--- | :--- |
| `offerFirst(E e)` | Inserts element at the **head/front**. |
| `offerLast(E e)` | Inserts element at the **tail/rear** (same as `offer()`). |
| `pollFirst()` | **Removes and returns** element from the **head/front**. |
| `pollLast()` | **Removes and returns** element from the **tail/rear**. |
| `peekFirst()` | Retrieves element from the **head/front** without removing. |
| `peekLast()` | Retrieves element from the **tail/rear** without removing. |

-----

## IV. `Set` Implementations (Unique Elements)

### A. `HashSet`

Stores unique elements using **hashing**. **Order is not guaranteed** and may change.

| Function | Time Complexity (TC) | Description |
| :--- | :--- | :--- |
| `add(E e)` | $O(1)$ | Adds the element if it's not already present. |
| `remove(E e)` | $O(1)$ | Removes the element. |
| `contains(E e)` | $O(1)$ | Checks for presence. |

> **Custom Class Usage:** To use a custom class (e.g., `Student`) in a `HashSet`, you **must override `equals()` and `hashCode()`**. This tells the `HashSet` how to uniquely identify objects (e.g., based on `rollNo` instead of memory address).

### B. `LinkedHashSet`

Maintains the `HashSet` property (unique elements) but also preserves the **insertion order**.

### C. `TreeSet`

Stores unique elements and maintains them in a **sorted order** (ascending by default).

  * **Implementation:** Internally uses a **Binary Search Tree (BST)**.
  * **Time Complexity:** All major operations (`add`, `remove`, `contains`) are $O(\log N)$ due to the BST structure.

-----

## V. `Map` Implementations (Key-Value Pairs)

Maps store data as entries, where **keys are unique** and map to a value.

### A. `HashMap`

Stores key-value pairs using **hashing**. **Order is not guaranteed**.

| Function | Time Complexity (TC) | Description |
| :--- | :--- | :--- |
| `put(K key, V value)` | $O(1)$ | Inserts a pair. If the key exists, the value is **overwritten**. |
| `putIfAbsent(K key, V value)` | $O(1)$ | Inserts only if the key is **not** already present. |
| `containsKey(K key)` | $O(1)$ | Checks if key exists. |
| `containsValue(V value)` | $O(N)$ | Checks if value exists (must scan values). |
| `remove(K key)` | $O(1)$ | Removes the entry mapped by the key. |

#### **Iteration Methods**

1.  **By Entry Set (Best for Key & Value access):**
    ```java
    for (Map.Entry<String, Integer> entry : numbers.entrySet()) {
        entry.getKey();
        entry.getValue();
    }
    ```
2.  **By Key Set (For Key-only access):**
    ```java
    for (String key : numbers.keySet()) { /* ... */ }
    ```

### B. `TreeMap`

Stores key-value pairs and keeps the entries **sorted by the key's natural order** (or by a provided `Comparator`).

  * **Implementation:** Internally uses a **Binary Search Tree (BST)**.
  * **Time Complexity:** All major operations (`put`, `remove`, `containsKey`) are $O(\log N)$.

-----

## VI. Utility Classes

### A. `Arrays` Class

Provides static methods for manipulating **primitive arrays** (e.g., `int[]`).

  * **`Arrays.sort(array)`:** Sorts the array.
  * **`Arrays.binarySearch(array, key)`:** Performs binary search (requires the array to be sorted first).
  * **`Arrays.fill(array, value)`:** Fills all elements with a single value (useful for dynamic programming initialization).

### B. `Collections` Class

Provides static methods for manipulating **Collection implementations** (Lists, Sets, Queues).

  * **`Collections.min(collection)` / `max(collection)`:** Returns the minimum or maximum element.
  * **`Collections.frequency(collection, key)`:** Returns the number of occurrences of an element.
  * **`Collections.sort(List list)`:** Sorts the list (uses merge sort or Timsort, highly optimized).

#### **Custom Sorting: `Comparable` vs. `Comparator`**

| Feature | `Comparable` | `Comparator` |
| :--- | :--- | :--- |
| **Location** | **Inside** the class (`implements Comparable`). | **Outside** the class (passed to `Collections.sort`). |
| **Method** | `compareTo(Object other)` | `compare(Object o1, Object o2)` |
| **Flexibility** | **Single** natural sorting order (e.g., sort `Student` only by `rollNo`). | **Multiple, dynamic** sorting orders (e.g., sort `Student` by `name` today, by `age` tomorrow). |

-----




-----

# ☕ Java Collections Framework Code Examples

The Java Collections Framework (JCF) provides highly optimized data structures and algorithms, saving development time and ensuring performance.

## I. Framework Overview

The **Collection Framework** is the umbrella term, which includes:

1.  **`Collection` Interface:** The root of most hierarchical data structures (Lists, Sets, Queues).
2.  **`Map` Interface:** Separated from `Collection` because it stores **Key-Value pairs**, not single values.
3.  **`Iterator` Interface:** Used for traversing collections.

-----

## II. `List` Implementations (Ordered, Duplicates Allowed)

The `ArrayList` is the most common `List` implementation, providing dynamic array functionality.

### Core `ArrayList` Functions

| Function | Description | Time Complexity (TC) |
| :--- | :--- | :--- |
| `add(E e)` | Adds element to the end. | $O(1)$ amortized |
| `get(int index)` | Retrieves element at `index`. | $O(1)$ |
| `set(int index, E e)` | Updates/replaces element at `index`. | $O(1)$ |
| `remove(int index)` | Removes element at `index`. | $O(N)$ |

### 💻 Java Code Example (`ArrayList`)

```java
import java.util.ArrayList;

ArrayList<String> students = new ArrayList<>();

// 1. add(E e)
students.add("Anuj"); 
students.add("Ramesh"); 

// 2. get(int index)
String firstStudent = students.get(0); // Returns "Anuj"

// 3. set(int index, E e)
students.set(1, "Shivam"); // Replaces "Ramesh" with "Shivam"

// 4. remove(int index)
students.remove(0); // Removes "Anuj". students is now ["Shivam"]
```

-----

## III. `Set` Implementations (Unique Elements Only)

`Set` ensures no duplicate elements. `HashSet` is fastest, while `TreeSet` is sorted.

### Core `HashSet` Functions

| Function | Description | Time Complexity (TC) |
| :--- | :--- | :--- |
| `add(E e)` | Adds the element if it's not already present. | $O(1)$ |
| `remove(E e)` | Removes the element. | $O(1)$ |
| `contains(E e)` | Checks for presence. | $O(1)$ |

### 💻 Java Code Example (`HashSet`)

```java
import java.util.HashSet;

HashSet<Integer> uniqueIDs = new HashSet<>();

// 1. add(E e) - Duplicates are ignored
uniqueIDs.add(10); 
uniqueIDs.add(20); 
uniqueIDs.add(10); // Ignored. Set size remains 2.

// 2. contains(E e)
boolean has20 = uniqueIDs.contains(20); // Returns true

// 3. remove(E e)
uniqueIDs.remove(10); // Removes 10
```

-----

## IV. `Queue` Implementations (Ordered Retrieval)

`Queue` defines retrieval order. `ArrayDeque` is versatile for both FIFO and LIFO.

### Core `ArrayDeque` (Double-Ended Queue) Functions

| Function | Description | Time Complexity (TC) |
| :--- | :--- | :--- |
| `offer(E e)` | Inserts element at the **tail/rear**. | $O(1)$ |
| `poll()` | Retrieves and removes element from the **head/front** (FIFO). | $O(1)$ |
| `peek()` | Retrieves element from the **head/front** without removing. | $O(1)$ |
| `pollLast()` | Retrieves and removes element from the **tail/rear** (LIFO behavior). | $O(1)$ |

### 💻 Java Code Example (`ArrayDeque` / Queue)

```java
import java.util.ArrayDeque;
import java.util.Queue;

// ArrayDeque is commonly referenced as the Queue interface
Queue<Integer> ticketLine = new ArrayDeque<>();

// 1. offer(E e)
ticketLine.offer(101); // Adds to rear
ticketLine.offer(102); 

// 2. peek()
int nextTicket = ticketLine.peek(); // Returns 101 (front element)

// 3. poll()
int servedTicket = ticketLine.poll(); // Removes and returns 101

// 4. pollLast() - ArrayDeque specific
// You can use the ArrayDeque object directly for this LIFO behavior
ArrayDeque<Integer> stackSim = new ArrayDeque<>();
stackSim.push(1); // LIFO add
stackSim.push(2);
int lastIn = stackSim.pollLast(); // Returns 1 (from rear)
```

-----

## V. `Map` Implementations (Key-Value Pairs)

Maps store unique keys associated with values. `HashMap` is fastest, while `TreeMap` is sorted by key.

### Core `HashMap` Functions

| Function | Description | Time Complexity (TC) |
| :--- | :--- | :--- |
| `put(K key, V value)` | Inserts key-value pair. **Overwrites** value if key exists. | $O(1)$ |
| `get(K key)` | Retrieves the value mapped to the key. | $O(1)$ |
| `remove(K key)` | Removes the entry mapped by the key. | $O(1)$ |
| `containsKey(K key)` | Checks if the key is present. | $O(1)$ |

### 💻 Java Code Example (`HashMap`)

```java
import java.util.HashMap;
import java.util.Map;

// Map maps a String key (Country Code) to a String value (Full Name)
Map<String, String> countryMap = new HashMap<>();

// 1. put(K key, V value)
countryMap.put("US", "United States");
countryMap.put("BR", "Brazil");
countryMap.put("BR", "Brazil - Updated"); // Value is overwritten

// 2. get(K key)
String fullName = countryMap.get("US"); // Returns "United States"

// 3. containsKey(K key)
boolean hasBR = countryMap.containsKey("BR"); // Returns true

// 4. Iteration by Entry Set (Common)
for (Map.Entry<String, String> entry : countryMap.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
```


