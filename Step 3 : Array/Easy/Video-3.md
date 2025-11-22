# 🎯 DSA Notes: Array Problems - Hashing and XOR Techniques

This session focuses on leveraging mathematical properties (summation, XOR) and hashing to solve common array problems efficiently, always progressing from the simplest approach to the most optimal one.

## I. Problem 1: Find the Missing Number

**Goal:** Given an array of $N-1$ numbers containing values from $1$ to $N$, find the single missing number.

### A. Brute Force Approach (Linear Search)

  * **Thinking:** For every number $i$ from $1$ to $N$, check the entire array linearly to see if $i$ exists.
  * **Approach:** Outer loop runs from $i=1$ to $N$. Inner loop searches the array for $i$. If the number is not found after the inner loop completes, $i$ is the missing number.
  * **Time Complexity:** $O(N^2)$.
  * **Space Complexity:** $O(1)$.

### B. Better Approach (Hashing/Frequency Array)

  * **Thinking:** Use an auxiliary data structure to keep track of which numbers are present.
  * **Approach:**
    1.  Create a **Hash Array** of size $\mathbf{N+1}$ (to store frequencies for numbers $1$ to $N$).
    2.  Iterate through the input array and mark the presence (or frequency) in the Hash Array.
    3.  Iterate through the Hash Array from index $1$ to $N$. The index that holds a frequency of **zero** is the missing number.
  * **Time Complexity:** $O(N) + O(N) \approx O(N)$.
  * **Space Complexity:** $O(N)$ (for the Hash Array).

### C. Optimal Approach 1 (Summation)

  * **Thinking:** Calculate the expected sum of the complete set of numbers (1 to $N$) and subtract the actual sum of the given array. The difference is the missing number.
  * **Approach:**
    1.  Calculate the **Expected Sum ($\mathbf{S_1}$)** using the formula for the sum of the first $N$ natural numbers: $\mathbf{S_1} = \frac{N(N+1)}{2}$. (Note: Use `long` data type for larger $N$ to prevent overflow).
    2.  Calculate the **Actual Sum ($\mathbf{S_2}$)** by iterating through the input array.
    3.  The missing number is $\mathbf{S_1 - S_2}$.
  * **Time Complexity:** $O(N)$ (Single pass).
  * **Space Complexity:** $O(1)$.

#### 💻 Java Code Example (Summation)

```java
public static int findMissingNumberSum(int[] arr, int N) {
    // 1. Calculate Expected Sum (S1)
    // Use long to prevent integer overflow for large N
    long expectedSum = (long)N * (N + 1) / 2; 

    // 2. Calculate Actual Sum (S2)
    long actualSum = 0;
    for (int num : arr) {
        actualSum += num;
    }

    // 3. Difference is the missing number
    return (int)(expectedSum - actualSum);
}
```

### D. Optimal Approach 2 (XOR)

  * **Thinking:** The XOR property states: $\mathbf{A \oplus A = 0}$ and $\mathbf{A \oplus 0 = A}$. If we XOR all numbers from $1$ to $N$ ($\mathbf{X_1}$) and XOR all numbers in the array ($\mathbf{X_2}$), all paired numbers will cancel out, leaving only the missing number.
  * **Approach:**
    1.  Initialize $\mathbf{X_1}$ (for $1$ to $N$) and $\mathbf{X_2}$ (for array) to 0.
    2.  Iterate from $i=0$ to $N-2$ (array length) and perform $\mathbf{X_2} = \mathbf{X_2} \oplus \text{Arr}[i]$ and $\mathbf{X_1} = \mathbf{X_1} \oplus (i+1)$.
    3.  Finally, XOR the remaining $N$ (since it wasn't covered in the $i+1$ loop): $\mathbf{X_1} = \mathbf{X_1} \oplus N$.
    4.  The missing number is $\mathbf{X_1 \oplus X_2}$.
  * **Advantage:** **Prevents overflow** for very large $N$, as the XOR result never exceeds the largest number.
  * **Time Complexity:** $O(N)$.
  * **Space Complexity:** $O(1)$.

#### 💻 Java Code Example (XOR)

```java
public static int findMissingNumberXOR(int[] arr, int N) {
    int x1 = 0; // XOR of numbers from 1 to N (expected set)
    int x2 = 0; // XOR of all numbers in the array (actual set)

    // XOR elements in the array (X2) and 1 to N-1 (part of X1)
    for (int i = 0; i < N - 1; i++) {
        x2 = x2 ^ arr[i];
        x1 = x1 ^ (i + 1); // XORs 1 to N-1
    }
    
    // Complete X1 by XORing the last number N
    x1 = x1 ^ N; 

    // The result is the single unmatched number
    return x1 ^ x2;
}
```

-----

## II. Problem 2: Maximum Consecutive Ones

**Goal:** Find the maximum number of consecutive 1s in a binary array (containing 0s and 1s).

### 🧠 Optimal Approach (Single Pass)

  * **Thinking:** We need to track the current count of consecutive ones and simultaneously track the maximum count found so far.
  * **Approach:**
    1.  Initialize $\mathbf{maxCount} = 0$ and $\mathbf{currentCount} = 0$.
    2.  Iterate through the array:
          * If $\mathbf{A[i] = 1}$: Increment $\mathbf{currentCount}$. Update $\mathbf{maxCount} = \max(\mathbf{maxCount}, \mathbf{currentCount})$.
          * If $\mathbf{A[i] = 0}$: Reset $\mathbf{currentCount} = 0$ (the consecutive chain is broken).
  * **Time Complexity:** $O(N)$.
  * **Space Complexity:** $O(1)$.

#### 💻 Java Code Example

```java
public static int maxConsecutiveOnes(int[] arr) {
    int maxCount = 0;
    int currentCount = 0;
    
    for (int num : arr) {
        if (num == 1) {
            currentCount++;
            // Update the maximum count found so far
            maxCount = Math.max(maxCount, currentCount); 
        } else {
            // Reset count if a zero is encountered
            currentCount = 0;
        }
    }
    return maxCount;
}
```

-----

## III. Problem 3: Find the Number That Appears Once

**Goal:** Given an array where every number appears twice except for one, find that single number.

### A. Brute Force Approach (Linear Search with Counting)

  * **Thinking:** For every element, check the entire array to count its occurrences.
  * **Approach:** Outer loop iterates through $A$. Inner loop iterates through $A$ to count the occurrences of $A[i]$. If count is 1, return $A[i]$.
  * **Time Complexity:** $O(N^2)$.
  * **Space Complexity:** $O(1)$.

### B. Better Approach (Hashing/Map)

  * **Thinking:** Use a Map to store the frequency of every element.
  * **Approach:**
    1.  Iterate through the array and store the frequency of each element in a $\mathbf{HashMap}$.
    2.  Iterate through the $\mathbf{HashMap}$ (keys or entries). The key with a value (frequency) of **1** is the single number.
  * **Time Complexity:** $O(N)$ (Map operations are $O(1)$ on average).
  * **Space Complexity:** $O(N/2 + 1) \approx O(N)$ (to store the map).

### C. Optimal Approach (XOR)

  * **Thinking:** The XOR property $\mathbf{A \oplus A = 0}$ and $\mathbf{A \oplus 0 = A}$ means that XORing all elements together will cause every number appearing twice to cancel itself out (result is 0), leaving only the number that appeared once.
  * **Approach:** Initialize a result variable to 0. Iterate through the array, XORing the result with every element.
  * **Time Complexity:** $O(N)$.
  * **Space Complexity:** $O(1)$.

#### 💻 Java Code Example (XOR)

```java
public static int findSingleNumber(int[] arr) {
    int result = 0;
    
    // XORing all elements together
    for (int num : arr) {
        result = result ^ num; 
    }
    
    // The final result is the single number
    return result; 
}
```