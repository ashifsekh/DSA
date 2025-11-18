# ⚙️ DSA Notes: Basic Sorting Techniques (Comparison Sorts)

The primary goal of these algorithms is to **place elements in their correct, sorted position** one by one.

## I. Selection Sort

**Goal:** Repeatedly find the minimum element from the unsorted part of the array and place it at the beginning of the unsorted part.

### 🧠 The Thinking (Select Minimums)

The core idea is to break the array into a **Sorted section (Left)** and an **Unsorted section (Right)**.

1.  **Iteration:** Start a large loop (outer loop) that moves from the first element to the last element ($i=0$ to $N-1$). This index $i$ marks the **start of the unsorted section** where the next minimum element should be placed. In this implementation the outer loop includes the final index; the final iteration is effectively a no-op swap when the minimum is at `i`.
2.  **Selection:** In the unsorted section (from index $i$ to $N-1$), use an inner loop to find the **absolute minimum value** and record its index (`minIndex`). Note the inner loop here starts at `j = i` (not `i+1`) because we consider `i` as a candidate for the minimum as well.
3.  **Placement:** After the inner loop, swap the element at `minIndex` with the element at `i`. This places the smallest remaining element at the start of the unsorted section, growing the sorted section by one.

| Loop      | Range        | Rationale (Why this range?)                                                                                                                                                                                   |
| --------- | ------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Outer (i) | i = 0 to N−1 | i marks the start of the unsorted section. The implementation runs i through N−1; the last iteration will compare the final element and may swap it with itself (no-op), keeping the code simple and uniform. |
| Inner (j) | j = i to N−1 | j scans the remaining unsorted array including the element at i to find the true minimum element's index (minIndex).                                                                                          |

### 📝 Algorithm (implementation-style)

| Step    | Outer Loop (I)                                                               | Inner Loop (J)                                             | Action                                                                                    |
| :------ | :--------------------------------------------------------------------------- | :--------------------------------------------------------- | :---------------------------------------------------------------------------------------- |
| **1.**  | $i=0$                                                                        | J: $0 \rightarrow N-1$                                     | Initialize `minIndex = 0`, find the **minimum** in the entire array (j scans from 0).     |
| **2.**  |                                                                              | Swap $\mathbf{A[0]}$ with $\mathbf{A[\text{minIndex}]}$.   | Array is now sorted up to index 0.                                                        |
| **3.**  | $i=1$                                                                        | J: $1 \rightarrow N-1$                                     | Initialize `minIndex = 1`, find the minimum in the remaining array (starting at index 1). |
| **4.**  |                                                                              | Swap $\\mathbf{A[1]}$ with $\\mathbf{A[\text{minIndex}]}$. | Array is now sorted up to index 1.                                                        |
| **...** | Continues until $i = N-1$ (final iteration may swap an element with itself). |                                                            |                                                                                           |

### ⏱ Complexity Analysis

- **Time Complexity:** $\mathbf{O(N^2)}$ (Best, Average, and Worst Case).
  - The two nested loops run the maximum number of times regardless of the input array's state: $N + (N-1) + (N-2) + \dots + 1 \approx N^2/2$.
- **Space Complexity:** $O(1)$ (In-place sort, only uses temporary variables for swapping).

### 💻 Java Code Example (implementation matches provided class)

```java
class Selection_sort {
    public static void main(String[] args) {
        int arr[] = {45,3,245,98,10};
        int n = arr.length;

        for (int i = 0; i <= n-1 ; i++) {
            int minIndex = i;
            for (int j = i; j <= n-1; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        for (int k = 0; k<=n-1; k++){
            System.out.println(arr[k]);
        }
    }

}
```

## II. Bubble Sort

**Goal:** Repeatedly "bubble up" the largest element to its correct position at the end of the array through adjacent swapping.

### 🧠 The Thinking (Push Maximums)

In each pass (outer loop), we compare adjacent elements and swap when out of order. After each pass, the largest element of the remaining unsorted section reaches its correct position at the end.

Implementation note: the provided `Bubble_sort` class uses a simple, standard loop structure without the early-exit optimization. The outer loop runs from `i = 0` to `n-2`, and the inner loop compares adjacent pairs up to `n-2-i`.

1.  **Passes:** The outer loop runs `n-1` passes (`i = 0` to `n-2`).
2.  **Adjacent Comparison:** In pass `i`, the inner loop runs `j = 0` to `n-2-i`, comparing `A[j]` and `A[j+1]` and swapping when `A[j] > A[j+1]`.

### 📝 Algorithm (implementation-style)

| Step   | Outer Loop (I)   | Inner Loop (J)         | Action                                                                              |
| :----- | :--------------- | :--------------------- | :---------------------------------------------------------------------------------- |
| **1.** | $i=0$ (1st pass) | $J: 0 \rightarrow N-2$ | Compare $A[j]$ and $A[j+1]$. Swap if $A[j] > A[j+1]$.                               |
| **2.** |                  |                        | **Maximum element** of the array will be at $\mathbf{A[N-1]}$ after the first pass. |
| **3.** | $i=1$ (2nd pass) | $J: 0 \rightarrow N-3$ | Compare adjacent pairs up to the newly established boundary.                        |
| **4.** |                  |                        | **Second maximum** will be at $\mathbf{A[N-2]}$ after the second pass.              |

### ⏱ Complexity Analysis

| Case                   | Time Complexity   | Notes                                                                                                              |
| :--------------------- | :---------------- | :----------------------------------------------------------------------------------------------------------------- |
| **Worst/Average Case** | $\mathbf{O(N^2)}$ | Occurs when the array is sorted in reverse order.                                                                  |
| **Best Case**          | $\mathbf{O(N^2)}$ | The basic implementation without early-exit still performs the nested loops even when the array is already sorted. |

- **Space Complexity:** $O(1)$ (in-place, uses only a temp variable for swapping).

### 💻 Java Code Example (matches provided class)

```java
public class Bubble_sort {

    public static void main(String[] args) {
        int arr[] = {45,3,245,98,10};
        int n = arr.length;

        for (int i = 0; i <= n-2; i++) {
            for (int j = 0; j <= n-2-i; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        for (int k = 0; k<=n-1; k++){
            System.out.println(arr[k]);
        }
    }

}
```

### 💻 Java Code Example (Optimized)

```java
public static void bubbleSort(int[] arr, int n) {
    // Outer loop determines the number of passes
    for (int i = 0; i < n - 1; i++) {
        boolean didSwap = false; // Flag for optimization

        // Inner loop performs adjacent comparison and swapping
        // Goes up to n-i-1 because the last 'i' elements are already in place
        for (int j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                // Swap arr[j] and arr[j+1]
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                didSwap = true;
            }
        }

        // OPTIMIZATION: If no two elements were swapped by inner loop, array is sorted
        if (!didSwap) {
            break;
        }
    }
}
```

---

## III. Insertion Sort

**Goal:** Take an element from the unsorted part and **insert it into its correct position** within the sorted part on the left.

### 🧠 The Thinking (Place Correctly)

We assume the first element ($\mathbf{A[0]}$) is sorted. The outer loop picks the next element to be inserted. The inner loop shifts elements on the left to create a space for the picked element.

1.  **Pick Element:** The outer loop starts from $i=1$ to $N-1$. $A[i]$ is the current element to be inserted.
2.  **Comparison and Shift:** The inner loop ($j$) compares $A[i]$ with elements on its left ($\mathbf{A[j-1]}$).
3.  **Shifting:** If the element on the left is **greater** than the picked element, shift the left element one position to the right. This opens up space.
4.  **Insertion:** The process stops when the inner loop finds an element smaller than the picked element (or reaches the start of the array). The picked element is inserted into the empty slot.

### 📝 Algorithm

| Step    | Outer Loop (I)             | Action (Inner Loop J)                                                                         | Result                                                  |
| :------ | :------------------------- | :-------------------------------------------------------------------------------------------- | :------------------------------------------------------ |
| **1.**  | $i=1$                      | Start $J=i$. While $J>0$ and $\mathbf{A[J-1] > A[J]}$: swap $A[J-1]$ and $A[J]$, decrement J. | $A[1]$ is inserted into the correct place in $A[0..1]$. |
| **2.**  | $i=2$                      | Start $J=i$. Compare $A[2]$ with $A[1]$, then $A[0]$. Shift larger elements right.            | $A[2]$ is inserted into the correct place in $A[0..2]$. |
| **...** | Continues until $i = N-1$. |                                                                                               |                                                         |

### ⏱ Complexity Analysis

| Case                   | Time Complexity   | Notes                                                                                                                             |
| :--------------------- | :---------------- | :-------------------------------------------------------------------------------------------------------------------------------- |
| **Worst/Average Case** | $\mathbf{O(N^2)}$ | Occurs when the array is sorted in reverse order (maximum comparisons and shifting required).                                     |
| **Best Case**          | $\mathbf{O(N)}$   | Occurs when the array is already sorted (the inner loop runs only once per outer iteration for comparison and immediately stops). |

### 💻 Java Code Example

```java
public static void insertionSort(int[] arr, int n) {
    // Outer loop iterates from the second element (index 1) to the end
    for (int i = 1; i < n; i++) {
        int current = arr[i]; // The element to be inserted
        int j = i; // Start position for comparison/shifting

        // Inner loop: Check elements to the left (j-1) and shift them right if they are larger
        // Loop condition: j > 0 (don't go past the start) AND left element > current element
        while (j > 0 && arr[j - 1] > current) {
            arr[j] = arr[j - 1]; // Shift the larger element right
            j--;                 // Move one position left
        }

        // Insert the current element into its correct, open position
        arr[j] = current;
    }
}
```
