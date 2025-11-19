# 🌳 DSA Notes: Merge Sort (Divide and Conquer)

Merge Sort is a **highly optimized** comparison-based sorting algorithm that uses the **Divide and Conquer** paradigm. It is preferred over $O(N^2)$ sorts (Selection, Bubble, Insertion) due to its superior time complexity.

## I. Core Algorithm: Divide and Merge

The algorithm involves two main steps:

1.  **Divide:** Recursively split the array into two halves until a single element is left.
2.  **Merge:** Combine the smaller, sorted arrays into larger, single sorted arrays.

### 🧠 The Thinking Process (Recursive Breakdown)

The goal is to sort the array **in place** by playing with indices (low, high, mid) rather than creating new physical arrays at every step.

1.  **Divide Step (Base Case):** Use recursion to repeatedly call `mergeSort(low, high)` and `mergeSort(mid+1, high)`.
      * **Stop Condition:** The recursion stops when the array size is one, i.e., when $\mathbf{low == high}$. A single element array is always sorted.
2.  **Conquer/Merge Step:** Once two adjacent halves are sorted (guaranteed by the recursive returns), call the `merge()` function to combine them into one larger sorted array.

### 📝 MergeSort Pseudo Code Flow

```
// Mergesort Function: Sorts the array from index low to high
function mergeSort(arr, low, high):
    // Base Case: Array has only 1 element (or is empty)
    if low >= high:
        return
    
    // 1. DIVIDE: Find the midpoint
    mid = (low + high) / 2
    
    // 2. RECURSE LEFT: Sort the left half (low to mid)
    mergeSort(arr, low, mid)
    
    // 3. RECURSE RIGHT: Sort the right half (mid + 1 to high)
    mergeSort(arr, mid + 1, high)
    
    // 4. MERGE: Combine the two sorted halves
    merge(arr, low, mid, high)
```

-----

## II. Dry Run: The Recursive Flow

Let's dry run the flow for an array $A = [4, 1, 3, 2]$ (Size $N=4$). Initial call: `mergeSort(A, 0, 3)`.

| Call Stack | Low | High | Mid | Action (Divide) | Action (Merge on Return) |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **1.** `MS(0, 3)` | 0 | 3 | 1 | Divide: Calls Left (0, 1) | Awaits `M(0, 1, 3)` |
| **2.** `MS(0, 1)` | 0 | 1 | 0 | Divide: Calls Left (0, 0) | Awaits `M(0, 0, 1)` |
| **3.** `MS(0, 0)` | 0 | 0 | - | **Base Case:** Return | |
| **4.** `MS(1, 1)` | 1 | 1 | - | **Base Case:** Return | |
| **\<- Return to 2.** | | | | Left & Right done. | Call `M(0, 0, 1)`. Merges $\mathbf{[4]}$ and $\mathbf{[1]}$ to get $\mathbf{[1, 4]}$. |
| **5.** `MS(2, 3)` | 2 | 3 | 2 | Divide: Calls Left (2, 2) | Awaits `M(2, 2, 3)` |
| **6.** `MS(2, 2)` | 2 | 2 | - | **Base Case:** Return | |
| **7.** `MS(3, 3)` | 3 | 3 | - | **Base Case:** Return | |
| **\<- Return to 5.** | | | | Left & Right done. | Call `M(2, 2, 3)`. Merges $\mathbf{[3]}$ and $\mathbf{[2]}$ to get $\mathbf{[2, 3]}$. |
| **\<- Return to 1.** | | | | Left(2) & Right(5) done. | Call `M(0, 1, 3)`. Merges $\mathbf{[1, 4]}$ and $\mathbf{[2, 3]}$ to get $\mathbf{[1, 2, 3, 4]}$. |

being split down to single elements and then merged back up to [1, 2, 3, 4]]

-----

## III. The Merge Algorithm (Conquer Step)

The `merge` function takes two adjacent, already sorted sub-arrays and combines them into a single sorted sub-array.

### 🧠 Merge Logic

1.  **Pointers:** Initialize two pointers: `left` (at `low`) and `right` (at `mid + 1`).
2.  **Temporary Storage:** Create a **temporary array** (`temp`) to store the merged elements.
3.  **Comparison:** While both `left` and `right` pointers are within their respective bounds, compare $A[\text{left}]$ and $A[\text{right}]$. Add the smaller element to `temp` and advance its pointer.
4.  **Copy Remaining:** After one sub-array is exhausted, copy all remaining elements from the other sub-array directly into `temp`.
5.  **Place Back:** Copy all elements from the `temp` array back into the original array $A$ in the range $\mathbf{[low, high]}$.

### 💻 Java Code Example

```java
// Helper function to merge two sorted subarrays
public static void merge(int[] arr, int low, int mid, int high) {
    // 1. Create temporary list and pointers
    ArrayList<Integer> temp = new ArrayList<>();
    int left = low;
    int right = mid + 1;

    // 2. Comparison and addition to temp
    while (left <= mid && right <= high) {
        if (arr[left] <= arr[right]) {
            temp.add(arr[left]);
            left++;
        } else {
            temp.add(arr[right]);
            right++;
        }
    }

    // 3. Copy remaining elements (if any)
    while (left <= mid) {
        temp.add(arr[left]);
        left++;
    }
    while (right <= high) {
        temp.add(arr[right]);
        right++;
    }

    // 4. Copy temp back to the original array (A)
    for (int i = low; i <= high; i++) {
        arr[i] = temp.get(i - low); // i - low maps temp's 0-index to A's low-index
    }
}

// Main MergeSort Function
public static void mergeSort(int[] arr, int low, int high) {
    if (low >= high) {
        return;
    }
    int mid = (low + high) / 2;
    mergeSort(arr, low, mid);
    mergeSort(arr, mid + 1, high);
    merge(arr, low, mid, high);
}
```

-----

## IV. Complexity Analysis

| Measure | Value | Rationale |
| :--- | :--- | :--- |
| **Time Complexity** | $\mathbf{O(N \log N)}$ | **Division:** The array is halved $\log_2 N$ times. **Merging:** At every level of the recursion tree, the total work done to merge all sub-arrays is $O(N)$. Total Time $= O(\text{Levels}) \times O(\text{Work per Level}) = O(\log N) \times O(N)$. |
| **Space Complexity** | $\mathbf{O(N)}$ | The algorithm requires $O(N)$ auxiliary space (for the temporary array used during the merge step) and $O(\log N)$ space for the recursive call stack. |