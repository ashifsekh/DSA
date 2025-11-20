# 🎯 DSA Notes: Basic Array Problems (Step 3)

## I. Array Fundamentals

- **Definition:** An array is a **data structure** that stores a collection of **similar elements** (all of the same data type: integer, character, string, etc.).
- **Indexing:** Arrays are **0-indexed**. The indices range from $0$ to $(\text{Size} - 1)$.
- **Memory Storage:** Arrays occupy **contiguous memory blocks** in the computer's memory. This is why random access by index is very fast ($O(1)$).
- **Declaration Limits (Java/C++):**
  - **Local (inside `main`):** Max size $\approx 10^6$. Initialized to **garbage values**.
  - **Global (outside `main`):** Max size $\approx 10^7$. Initialized to **zeros**.

---

## II. Problem 1: Largest Element in an Array

**Goal:** Find the maximum value in the given array.

### A. Brute Force Approach (Sorting)

- **Thinking:** The easiest way to guarantee the largest element is to put the array in **ascending order**.
- **Approach:** Sort the entire array. The largest element will be at the last index, $\mathbf{A[N-1]}$.
- **Time Complexity:** $O(N \log N)$ (due to sorting).

### B. Optimal Approach (Single Pass)

- **Thinking:** We need to find the maximum in a single scan, updating a variable whenever we find a larger element.
- **Approach:**
  1.  Initialize a variable $\mathbf{largest}$ to the first element $\mathbf{A[0]}$.
  2.  Iterate through the rest of the array. If $\mathbf{A[i]}$ is greater than $\mathbf{largest}$, update $\mathbf{largest} = \mathbf{A[i]}$.
- **Time Complexity:** $O(N)$.

#### 💻 Java Code Example (Optimal)

```java
public static int findLargest(int[] arr) {
    if (arr.length == 0) return -1;

    int largest = arr[0];

    for (int i = 1; i < arr.length; i++) {
        if (arr[i] > largest) {
            largest = arr[i];
        }
    }
    return largest;
}
```

---

## III. Problem 2: Second Largest and Second Smallest Elements

**Goal:** Find the second largest and second smallest unique elements without sorting.

### A. Brute Force Approach (Sorting)

- **Approach:** Sort the array ($O(N \log N)$). Then, scan backwards from $N-2$ to find the first element not equal to the largest ($A[N-1]$). This might take $O(N)$ extra time in the worst case (e.g., array is $[1, 7, 7, 7, 7]$).
- **Total Time Complexity:** $O(N \log N)$.

### B. Optimal Approach (Single Pass)

- **Thinking:** We can track the largest/smallest element and the second largest/smallest element simultaneously in a single pass.

- **Approach (Second Largest):**

  1.  Initialize $\mathbf{largest} = \mathbf{A[0]}$ and $\mathbf{secondLargest} = \mathbf{-1}$ (or `Integer.MIN_VALUE`).
  2.  Iterate through the array:
      - If $\mathbf{A[i]} > \mathbf{largest}$: The old `largest` becomes the `secondLargest`, and $\mathbf{A[i]}$ becomes the new `largest`.
      - Else if $\mathbf{A[i]} < \mathbf{largest}$ AND $\mathbf{A[i]} > \mathbf{secondLargest}$: Update $\mathbf{secondLargest} = \mathbf{A[i]}$.

- **Time Complexity:** $O(N)$ (One pass).

#### 💻 Java Code Example (Optimal Second Largest)

```java
public static int findSecondLargest(int[] arr) {
    if (arr.length < 2) return -1;

    int largest = arr[0];
    // Assuming non-negative integers (as per problem)
    int secondLargest = -1;

    for (int i = 1; i < arr.length; i++) {
        if (arr[i] > largest) {
            secondLargest = largest; // Old largest is now second largest
            largest = arr[i];        // New largest
        } else if (arr[i] < largest && arr[i] > secondLargest) {
            secondLargest = arr[i];
        }
    }
    return secondLargest;
}
```

---

## IV. Problem 3: Check if Array is Sorted

**Goal:** Determine if the array is strictly increasing (every element is less than the next).

### A. Approach (Single Pass — index-based forward check)

- **Thinking:** Scan adjacent pairs from left to right. If any element is greater than or equal to the next element, the array is not strictly increasing and we can stop early.
- **Approach:**
  1.  Let `n = arr.length` and iterate `j` from `0` to `n-2`.
  2.  In each step check if `arr[j] >= arr[j+1]`.
  3.  If the condition holds, the array is not sorted (strictly increasing) — report and stop.
  4.  If the loop completes, the array is strictly increasing.
- **Time Complexity:** $O(N)$ (Best case $O(1)$ if the first adjacent check fails).

#### 💻 Java Code Example (strict-increasing check)

```java
public static void checkSortedStrict(int[] arr) {
    int n = arr.length;
    for (int j = 0; j <= n - 2; j++) {
        if (arr[j] >= arr[j + 1]) {
            System.out.println("not sorted");
            return;
        }
    }
    System.out.println("sorted");
}
```

---

## V. Problem 4: Remove Duplicates from a Sorted Array (In Place)

**Goal:** Modify a **sorted** array such that the unique elements occupy the first indices, and return the count of unique elements.

### A. Brute Force Approach (Set)

- **Approach:**
  1.  Use a **Set** data structure ($O(N)$ space) to store all unique elements from the array.
  2.  Iterate through the Set and overwrite the original array starting from index 0.
  3.  Return the size of the Set.
- **Time Complexity:** $O(N \log N)$ (due to set operations/copying).

### B. Optimal Approach (Two Pointers)

- **Thinking:** Since the array is sorted, duplicates are always adjacent. We can use one pointer ($\mathbf{i}$) to track the position of the last confirmed **unique** element, and another pointer ($\mathbf{j}$) to **scan** for the next unique element.

- **Approach:**

  1.  Initialize $\mathbf{i = 0}$ (The first element $A[0]$ is always unique).
  2.  Initialize $\mathbf{j = 1}$ (The scanner).
  3.  Iterate $j$ from 1 to $N-1$.
  4.  If $\mathbf{A[j]} \neq \mathbf{A[i]}$ (found a new unique element):
      - Increment $i$.
      - Overwrite $A[i]$ with $A[j]$.
  5.  Return $\mathbf{i + 1}$ (number of unique elements).

- **Time Complexity:** $O(N)$ (Single pass).

- **Space Complexity:** $O(1)$ (In-place modification).

#### Dry Run: $\mathbf{A = [1, 1, 2, 2, 3, 3]}$

| Step        |  i  |  j  | A[i] | A[j] | A[j] $\ne$ A[i]? | Action                                | Array State                   |
| :---------- | :-: | :-: | :--: | :--: | :--------------: | :------------------------------------ | :---------------------------- |
| **Initial** |  0  |  -  |  1   |  -   |        -         | -                                     | $[1, 1, 2, 2, 3, 3]$          |
| **1**       |  0  |  1  |  1   |  1   |      False       | $j$ moves                             | $[1, 1, 2, 2, 3, 3]$          |
| **2**       |  0  |  2  |  1   |  2   |     **True**     | $i \rightarrow 1$, $A[1] = 2$         | $[1, \mathbf{2}, 2, 2, 3, 3]$ |
| **3**       |  1  |  3  |  2   |  2   |      False       | $j$ moves                             | $[1, 2, 2, 2, 3, 3]$          |
| **4**       |  1  |  4  |  2   |  3   |     **True**     | $i \rightarrow 2$, $A[2] = 3$         | $[1, 2, \mathbf{3}, 2, 3, 3]$ |
| **5**       |  2  |  5  |  3   |  3   |      False       | $j$ moves                             | $[1, 2, 3, 2, 3, 3]$          |
| **End**     |  2  |  6  |  -   |  -   |        -         | Loop ends. Return $\mathbf{i+1 = 3}$. | -                             |

#### 💻 Java Code Example (Optimal)

```java
public static int removeDuplicates(int[] arr) {
    if (arr.length == 0) return 0;

    int i = 0; // Pointer for the last unique element

    // j scans for the next unique element
    for (int j = 1; j < arr.length; j++) {
        if (arr[j] != arr[i]) {
            // Found a new unique element:
            i++;
            arr[i] = arr[j]; // Place it at the next unique position
        }
    }

    // i is the index of the last unique element (0-indexed).
    // The total number of unique elements is i + 1.
    return i + 1;
}
```
