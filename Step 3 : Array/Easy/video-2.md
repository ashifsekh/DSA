# 🚀 DSA Notes: Advanced Basic Array Problems (Step 3.1)

## I. Problem 1: Left Rotate Array by One Place

**Goal:** Shift every element one position to the left, moving the first element to the end.

### 🧠 Optimal Approach (In-Place, Single Pass)

  * **Thinking:** The first element ($A[0]$) is the only one that leaves its spot and goes to the end. Every other element moves one spot left. We must **store $A[0]$ temporarily** before overwriting it.
  * **Approach:**
    1.  Store $\mathbf{temp = A[0]}$.
    2.  Shift elements: Loop from $i=1$ to $N-1$ and set $\mathbf{A[i-1] = A[i]}$. (This moves $A[1]$ to $A[0]$, $A[2]$ to $A[1]$, etc.)
    3.  Place back: Set the last element $\mathbf{A[N-1] = temp}$.

#### Dry Run: $\mathbf{[1, 2, 3, 4, 5]}$

| Step | i | Operation | Array State | Temp |
| :--- | :-: | :--- | :--- | :---: |
| **Initial** | - | - | $[1, 2, 3, 4, 5]$ | 1 |
| **1.** | 1 | $A[0] = A[1] \rightarrow 2$ | $[\mathbf{2}, 2, 3, 4, 5]$ | 1 |
| **2.** | 2 | $A[1] = A[2] \rightarrow 3$ | $[2, \mathbf{3}, 3, 4, 5]$ | 1 |
| **3.** | 3 | $A[2] = A[3] \rightarrow 4$ | $[2, 3, \mathbf{4}, 4, 5]$ | 1 |
| **4.** | 4 | $A[3] = A[4] \rightarrow 5$ | $[2, 3, 4, \mathbf{5}, 5]$ | 1 |
| **Final** | - | $A[4] = temp \rightarrow 1$ | $[2, 3, 4, 5, \mathbf{1}]$ | 1 |

  * **Time Complexity:** $O(N)$
  * **Space Complexity:** $O(1)$ (Extra space used).

#### 💻 Java Code Example (Optimal)

```java
public static void rotateLeftByOne(int[] arr) {
    if (arr.length <= 1) return;
    
    int temp = arr[0]; // Store the first element

    // Shift elements one position to the left
    for (int i = 1; i < arr.length; i++) {
        arr[i - 1] = arr[i];
    }
    
    // Place the stored element at the last position
    arr[arr.length - 1] = temp;
}
```

-----

## II. Problem 2: Left Rotate Array by D Places

**Goal:** Left shift the array by $D$ positions. (The first $D$ elements move to the end).

### A. Pre-Processing D

The number of required rotations is always $D \pmod N$ (where $N$ is the array size). This accounts for redundant full-cycle rotations (e.g., rotating a 7-size array by 15 is the same as rotating by $15 \pmod 7 = 1$).

### B. Brute Force Approach

  * **Approach:** Use a temporary array of size $D$ to store the first $D$ elements. Shift the remaining $N-D$ elements to the front. Copy the $D$ elements from the temporary array to the end of the original array.
  * **Time Complexity:** $O(N + D + N-D) \approx O(N)$.
  * **Extra Space:** $O(D)$ (for the temporary array).

### C. Optimal Approach (Three Reversals)

  * **Thinking:** Observation shows that rotating an array by $D$ places is equivalent to performing three strategic reversals. This allows for an in-place $O(1)$ extra space solution.
  * **Approach:**
    1.  **Reverse the first D elements:** $\mathbf{[1, \dots, D]}$ becomes $\mathbf{[D, \dots, 1]}$.
    2.  **Reverse the remaining N-D elements:** $\mathbf{[D+1, \dots, N]}$ becomes $\mathbf{[N, \dots, D+1]}$.
    3.  **Reverse the entire array:** $\mathbf{[D, \dots, 1, N, \dots, D+1]}$ becomes $\mathbf{[D+1, \dots, N, 1, \dots, D]}$.

#### Dry Run: $\mathbf{[1, 2, 3, 4, 5, 6, 7]}$ with $D=3$.

| Step | Operation | Resulting Array |
| :--- | :--- | :--- |
| **Initial** | - | $[1, 2, 3, 4, 5, 6, 7]$ |
| **1. Reverse D** | Reverse $\mathbf{[1, 2, 3]}$ (0 to D-1) | $[\mathbf{3, 2, 1}, 4, 5, 6, 7]$ |
| **2. Reverse N-D** | Reverse $\mathbf{[4, 5, 6, 7]}$ (D to N-1) | $[3, 2, 1, \mathbf{7, 6, 5, 4}]$ |
| **3. Reverse All** | Reverse $\mathbf{[3, 2, 1, 7, 6, 5, 4]}$ (0 to N-1) | $[\mathbf{4, 5, 6, 7, 1, 2, 3}]$ |

  * **Time Complexity:** $O(N)$ (Three passes over the array: $O(D) + O(N-D) + O(N)$).
  * **Space Complexity:** $O(1)$ (In-place).

#### 💻 Java Code Example (Optimal)

```java
public static void rotateLeftByD(int[] arr, int d) {
    int n = arr.length;
    d = d % n; // Handle cases where d >= n
    
    if (d == 0) return;

    // Helper method for reversing a segment of the array
    reverse(arr, 0, d - 1); // 1. Reverse the first D elements
    reverse(arr, d, n - 1);  // 2. Reverse the rest (from D to N-1)
    reverse(arr, 0, n - 1);  // 3. Reverse the entire array
}

private static void reverse(int[] arr, int start, int end) {
    while (start < end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        start++;
        end--;
    }
}
```

-----

## III. Problem 3: Move Zeros to the End

**Goal:** Modify the array to place all non-zero elements at the front and all zeros at the end. The relative order of non-zero elements must be preserved.

### A. Brute Force Approach

  * **Approach:** Create a temporary list to store all non-zero elements. Copy the non-zero elements to the front of the original array. Fill the remaining slots with zeros.
  * **Time Complexity:** $O(N)$.
  * **Extra Space:** $O(N)$ (for the temporary list).

### B. Optimal Approach (Two Pointers - In-Place Swapping)

  * **Thinking:** We need to find the first zero and then swap it with the next non-zero element we encounter.
  * **Approach:**
    1.  Find the index of the **first zero** and call it $\mathbf{J}$. If no zero exists, the array is already sorted, so stop.
    2.  Start another pointer $\mathbf{I}$ at $\mathbf{J+1}$.
    3.  Iterate $\mathbf{I}$ from $J+1$ to $N-1$:
          * **If $\mathbf{A[I]}$ is non-zero:** Swap $\mathbf{A[I]}$ and $\mathbf{A[J]}$. This moves a non-zero element into the spot of the first zero.
          * **Advance $\mathbf{J}$:** After the swap, $A[J]$ now contains the non-zero element, and $A[J+1]$ contains the zero that was just swapped. Thus, advance $J$ to $J+1$ to point to the *next* zero element.
          * **If $\mathbf{A[I]}$ is zero:** Do nothing, just move $I$ ahead.
  * **Time Complexity:** $O(N)$.
  * **Space Complexity:** $O(1)$ (In-place).

#### Dry Run: $\mathbf{[1, 0, 3, 2, 0, 4]}$

| Step | I | J | A[I] (Current) | A[J] (First Zero) | A[I] $\ne$ 0? | Action | Array State |
| :--- | :-: | :-: | :---: | :---: | :---: | :--- | :--- |
| **Initial** | - | 1 | - | 0 | - | - | $[1, \mathbf{0}, 3, 2, 0, 4]$ |
| **1.** | 2 | 1 | 3 | 0 | **Yes** | Swap (3, 0). $J \rightarrow 2$. | $[1, \mathbf{3}, 0, 2, 0, 4]$ |
| **2.** | 3 | 2 | 2 | 0 | **Yes** | Swap (2, 0). $J \rightarrow 3$. | $[1, 3, \mathbf{2}, 0, 0, 4]$ |
| **3.** | 4 | 3 | 0 | 0 | **No** | $I$ moves, $J$ stays. | $[1, 3, 2, \mathbf{0}, 0, 4]$ |
| **4.** | 5 | 3 | 4 | 0 | **Yes** | Swap (4, 0). $J \rightarrow 4$. | $[1, 3, 2, \mathbf{4}, 0, 0]$ |
| **End** | 6 | - | - | - | - | Stop. | $[1, 3, 2, 4, 0, 0]$ |

#### 💻 Java Code Example (Optimal)

```java
public static void moveZeros(int[] arr) {
    int n = arr.length;
    int j = -1; // Pointer to the index of the first zero

    // Find the first zero (j)
    for (int i = 0; i < n; i++) {
        if (arr[i] == 0) {
            j = i;
            break; 
        }
    }
    
    // If no zero is found, array is already sorted.
    if (j == -1) return; 

    // Pointer i scans for non-zero elements
    for (int i = j + 1; i < n; i++) {
        if (arr[i] != 0) {
            // Swap non-zero (A[i]) with the first zero (A[j])
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            j++; // Advance j to the next zero
        }
    }
}
```

-----

## IV. Problem 4: Linear Search

**Goal:** Find the first occurrence of a given number (Key) in the array and return its index.

### A. Approach (Simple Iteration)

  * **Thinking:** The definition of linear search is to check every element sequentially from the start.
  * **Approach:** Iterate from $i=0$ to $N-1$. If $A[i]$ equals the Key, return $i$ immediately. If the loop finishes, the Key was not found, so return $-1$.
  * **Time Complexity:** $O(N)$.

#### 💻 Java Code Example

```java
public static int linearSearch(int[] arr, int key) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == key) {
            return i; // Return the 0-based index of the first occurrence
        }
    }
    return -1; // Key not found
}
```

-----

## V. Problem 5: Union and Intersection of Two Sorted Arrays

### A. Union of Two Sorted Arrays

**Goal:** Combine all unique elements from both sorted arrays $A$ and $B$, maintaining sorted order.

#### 🧠 Optimal Approach (Two Pointers)

  * **Thinking:** Since both arrays are sorted, we can use two pointers ($I$ for $A$, $J$ for $B$) to compare elements and add the smallest one to the union. We only add an element if it's not the same as the **last element added** to the union.
  * **Time Complexity:** $O(N_1 + N_2)$ (where $N_1$ and $N_2$ are the sizes of $A$ and $B$).
  * **Space Complexity:** $O(N_1 + N_2)$ (required to store the union list for returning the answer).

#### 💻 Java Code Example (Optimal)

```java
import java.util.ArrayList;

public static ArrayList<Integer> findUnion(int[] a, int[] b) {
    int n1 = a.length;
    int n2 = b.length;
    int i = 0, j = 0; // Pointers for arrays A and B
    ArrayList<Integer> union = new ArrayList<>();

    while (i < n1 && j < n2) {
        // 1. Array A's element is smaller
        if (a[i] <= b[j]) {
            // Check for uniqueness against the last element added to 'union'
            if (union.size() == 0 || union.get(union.size() - 1) != a[i]) {
                union.add(a[i]);
            }
            i++;
        } 
        // 2. Array B's element is smaller
        else { 
            if (union.size() == 0 || union.get(union.size() - 1) != b[j]) {
                union.add(b[j]);
            }
            j++;
        }
    }

    // 3. Copy remaining elements from A
    while (i < n1) {
        if (union.get(union.size() - 1) != a[i]) {
            union.add(a[i]);
        }
        i++;
    }

    // 4. Copy remaining elements from B
    while (j < n2) {
        if (union.get(union.size() - 1) != b[j]) {
            union.add(b[j]);
        }
        j++;
    }

    return union;
}
```

### B. Intersection of Two Sorted Arrays

**Goal:** Find elements common to both arrays, including duplicates if they are present in both (i.e., match occurrences).

#### 🧠 Optimal Approach (Two Pointers)

  * **Thinking:** Use two pointers ($I$ for $A$, $J$ for $B$). Since both are sorted, we can only find a match by comparing the smallest remaining elements.
      * If $A[I] < B[J]$, $A[I]$ can never match anything else, so advance $I$.
      * If $A[I] > B[J]$, $B[J]$ can never match anything else, so advance $J$.
      * If $A[I] = B[J]$, we found a match. Add it to the intersection list and advance **both** $I$ and $J$.
  * **Time Complexity:** $O(N_1 + N_2)$.
  * **Space Complexity:** $O(1)$ (Auxiliary space, excluding the return list).

#### 💻 Java Code Example (Optimal)

```java
public static ArrayList<Integer> findIntersection(int[] a, int[] b) {
    int n1 = a.length;
    int n2 = b.length;
    int i = 0, j = 0; // Pointers for arrays A and B
    ArrayList<Integer> intersection = new ArrayList<>();

    while (i < n1 && j < n2) {
        // A[i] is smaller, so it can't be in B[j] or beyond. Advance A.
        if (a[i] < b[j]) {
            i++;
        } 
        // B[j] is smaller, so it can't be in A[i] or beyond. Advance B.
        else if (b[j] < a[i]) {
            j++;
        } 
        // Match found (a[i] == b[j])
        else { 
            intersection.add(a[i]); // Add the match
            i++;
            j++; // Advance both pointers
        }
    }
    return intersection;
}
```