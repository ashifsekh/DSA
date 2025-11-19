# ⚡ DSA Notes: Quick Sort (Divide and Conquer)

Quick Sort is another **Divide and Conquer** sorting algorithm, generally considered one of the fastest in practice due to its superior average-case time complexity and **in-place** sorting nature (it doesn't use a large auxiliary array like Merge Sort).

## I. Core Algorithm: Pick, Place, Partition

The algorithm relies on placing a single element (the **pivot**) into its correct sorted position in the array. This partitions the array into two sub-problems.

1.  **Pick a Pivot:** Choose any element in the array to be the pivot. (Common choices: First, Last, Median, or Random element. We will use the **First Element**.)
2.  **Partition (Place Correctly):** Rearrange the array such that all elements **less than or equal to** the pivot are to its left, and all elements **greater than** the pivot are to its right. The pivot is now in its correct, final sorted position.
3.  **Recurse:** Recursively apply Quick Sort to the left sub-array and the right sub-array.

### 🧠 Partitioning Logic (How to Place the Pivot)

We use two pointers, $I$ and $J$, to traverse the array and enforce the partition rule.

1.  **Initialization:** Set **Pivot** to $A[\text{low}]$. Set pointer $I$ to $\text{low}$ and pointer $J$ to $\text{high}$.
2.  **Move I:** Move $I$ forward until $A[I]$ is **greater than** the pivot. ($A[I] > \text{Pivot}$)
3.  **Move J:** Move $J$ backward until $A[J]$ is **less than or equal to** the pivot. ($A[J] \le \text{Pivot}$)
4.  **Swap:** If $I$ has not crossed $J$ (i.e., $I < J$), it means $A[I]$ is in the wrong place (too large) and $A[J]$ is in the wrong place (too small). **Swap $A[I]$ and $A[J]$**.
5.  **Stop:** The inner loop stops when $I$ crosses $J$ ($I > J$).
6.  **Final Swap:** Swap the **Pivot** (which is still at $A[\text{low}]$) with $A[J]$. This places the pivot exactly between the smaller and larger sections. $J$ is the final **Partition Index**.

> Pivot and A[J] \< Pivot), and then the final swap of the pivot with A[J]]

-----

## II. Dry Run: Partitioning $\mathbf{[4, 6, 2, 5, 7, 9, 1, 3]}$

  * **Initial:** Low=0, High=7. **Pivot = 4**. $I=0$, $J=7$.

| Step | I Value | J Value | A[I] vs. Pivot | A[J] vs. Pivot | Swap (A[I], A[J]) | Array State | Stop? |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **0.** | 0 | 7 | $\le 4$ (4) | $\le 4$ (3) | - | $[4, 6, 2, 5, 7, 9, 1, 3]$ | - |
| **1.** | $I \rightarrow 1$ | $J \rightarrow 7$ | $6 > 4$ (Stop I) | $3 \le 4$ (Stop J) | **Swap (6, 3)** | $[4, 3, 2, 5, 7, 9, 1, 6]$ | $I < J$ |
| **2.** | $I \rightarrow 4$ | $J \rightarrow 6$ | $7 > 4$ (Stop I) | $1 \le 4$ (Stop J) | **Swap (7, 1)** | $[4, 3, 2, 5, 1, 9, 7, 6]$ | $I < J$ |
| **3.** | $I \rightarrow 5$ | $J \rightarrow 4$ | $9 > 4$ (Stop I) | $1 \le 4$ (Stop J) | $I=5, J=4$. **$I > J$.** | $\mathbf{J \text{ crossed } I}$. Stop inner loop. | **Yes** |
| **Final** | - | $J=4$ | - | - | **Swap Pivot (4, 1)** | $[\mathbf{1}, 3, 2, \mathbf{4}, 7, 9, 5, 6]$ | - |

  * **Result:** Pivot (4) is now at index 3. **Partition Index ($J$) = 3**.
  * **Next Recurse:** QuickSort(Low=0, High=2) and QuickSort(Low=4, High=7).

-----

## III. Java Code Implementation

### 💻 1. The Partition Function

This function places the pivot into its correct spot and returns the index.

```java
public static int partition(int[] arr, int low, int high) {
    int pivot = arr[low]; // We choose the first element as the pivot
    int i = low;
    int j = high;

    while (i < j) {
        // Move i: Find the first element GREATER than pivot (A[I] > P)
        // Note: Includes bound check (i <= high - 1) 
        while (i <= high - 1 && arr[i] <= pivot) {
            i++;
        }
        
        // Move j: Find the first element LESS THAN OR EQUAL to pivot (A[J] <= P)
        while (j >= low + 1 && arr[j] > pivot) {
            j--;
        }

        // Swap A[I] and A[J] if they haven't crossed
        if (i < j) {
            swap(arr, i, j);
        }
    }
    
    // Final Swap: Place the pivot (originally at arr[low]) into its correct spot (arr[j])
    swap(arr, low, j);
    
    return j; // Return the partition index
}

private static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}
```

### 💻 2. The QuickSort Function

This function implements the recursive calls using the partition index.

```java
public static void quickSort(int[] arr, int low, int high) {
    // Base Case: Only sort if the low index is less than the high index
    if (low < high) {
        // 1. Partition the array and get the pivot's final index
        int partitionIndex = partition(arr, low, high);
        
        // 2. Recurse Left: Sort elements smaller than the pivot
        quickSort(arr, low, partitionIndex - 1);
        
        // 3. Recurse Right: Sort elements larger than the pivot
        quickSort(arr, partitionIndex + 1, high);
    }
}
// Initial Call: quickSort(array, 0, array.length - 1);
```

-----

## IV. Complexity Analysis

| Measure | Value | Rationale |
| :--- | :--- | :--- |
| **Average/Best Time** | $\mathbf{O(N \log N)}$ | Similar to Merge Sort. The array is split roughly in half at every level, and $O(N)$ work is done during partitioning. |
| **Worst Time** | $\mathbf{O(N^2)}$ | Occurs when the pivot is consistently chosen as the smallest or largest element (e.g., sorted array). This leads to one sub-array of size $N-1$ and one of size 0, resulting in $N$ levels of recursion. |
| **Space Complexity** | $\mathbf{O(\log N)}$ (Avg) / $\mathbf{O(N)}$ (Worst) | Quick Sort is **in-place** (Auxiliary Space $O(1)$). However, the **Recursive Stack Space** is $O(\log N)$ on average, but can reach $O(N)$ in the worst-case scenario (due to unbalanced partitions). |