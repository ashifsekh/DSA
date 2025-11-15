# 🔑 DSA Notes: Basic Hashing and Frequency Counting

**Hashing** is a technique of **pre-storing** the count (or any relevant information) of array/string elements so that future queries can be answered quickly, typically in $O(1)$ time, avoiding repeated linear scans ($O(N)$).

## I. Problem Introduction & Brute Force Analysis

### The Problem: Frequency Counting

Given an array (or string) and $Q$ queries, find the frequency of each queried element.

  * **Brute Force:** For every query, iterate through the entire array/string to count the occurrences.
  * **Time Complexity:** If the array size is $N$, the total time is $O(Q \times N)$.
      * *Example:* For $Q=10^5$ queries on an $N=10^5$ array, $O(10^{10})$ operations are needed, which results in a **Time Limit Exceeded (TLE)** error.

### The Solution: Pre-Computation (Hashing)

1.  **Pre-Store:** Iterate through the input *once* and store the frequency of every element in a separate data structure (the **Hash/Frequency Array** or **Map**).
2.  **Fetch:** For every query, directly fetch the pre-stored count in $O(1)$ time.

<!-- end list -->

  * **Time Complexity (Hashing):** $O(N)$ (pre-computation) $+ O(Q)$ (fetching) $\approx \mathbf{O(N + Q)}$. This is significantly faster.

-----

## II. Hashing Integers: Array Hashing

This method uses a dedicated array where the **index represents the number** and the **value represents the frequency**.

### A. Constraints and Limitations

The size of the hash array depends on the **maximum element value** in the input array.

| Context | Max Integer Array Size (for Hashing) |
| :--- | :--- |
| **Inside `main()` (Local)** | $\approx 10^6$ |
| **Globally Declared** | $\approx 10^7$ |

> **Limitation:** If the maximum element value in the input array exceeds $10^7$ (e.g., $10^9$ or $10^{18}$), you **cannot** use array hashing due to memory constraints.

### 💻 Java Code Example (Array Hashing)

Assuming the maximum element value is $\le 12$.

```java
import java.util.Scanner;

public class ArrayHashing {
    public static void hashNumbers(int[] arr) {
        // Hash array must be size (Max Element + 1). Size 13 for max element 12.
        int[] hash = new int[13]; 
        
        // 1. PRE-COMPUTATION (O(N))
        for (int num : arr) {
            hash[num]++; // Increment frequency at the index corresponding to the number
        }

        // 2. FETCH (O(Q))
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of queries (Q): ");
        int Q = scanner.nextInt();
        
        while (Q-- > 0) {
            System.out.print("Enter number to query: ");
            int number = scanner.nextInt();
            // Fetch the frequency directly
            System.out.println("Frequency of " + number + ": " + hash[number]);
        }
    }
}
```

-----

## III. Hashing Characters: Array Hashing

Characters are easily mapped to array indices using their **ASCII values**.

### A. ASCII Mapping

  * Every character has a unique integer **ASCII value** (e.g., 'a' is 97).
  * **Max Size:** Since there are only 256 standard ASCII characters, a hash array of size 256 is sufficient for any character-hashing problem.

### B. Index Calculation

1.  **Lowercase Characters (a-z):** Use `char - 'a'` to map 'a' to 0, 'b' to 1, etc.
      * *Required Hash Array Size:* 26.
2.  **All ASCII Characters:** Use the character directly as the index (e.g., `hash[S.charAt(i)]++`).
      * *Required Hash Array Size:* 256.

### 💻 Java Code Example (Character Hashing)

```java
public class CharacterHashing {
    public static void hashChars(String s) {
        // Use an array of size 26 for only lowercase English letters
        int[] hash = new int[26]; 
        
        // 1. PRE-COMPUTATION (O(N))
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // c - 'a' maps 'a' to index 0, 'b' to 1, etc.
            hash[c - 'a']++; 
        }

        // 2. FETCH (O(Q))
        char queryChar = 'c';
        // Fetch frequency of 'c' directly
        System.out.println("Frequency of 'c': " + hash[queryChar - 'a']);
    }
}
```

-----

## IV. Hashing Large Numbers: Maps (Collections)

When the maximum element value is too large ($> 10^7$), we must use **Map-based Hashing** provided by standard libraries (Collections in Java).

| Java Collection | Key Property |
| :--- | :--- |
| **`TreeMap`** | Stores Key-Value pairs in **sorted order** (by key). |
| **`HashMap`** | Stores Key-Value pairs in **arbitrary order**. |

### A. Map Structure

  * **Key:** The number/string/character being counted (can be a very large data type, e.g., `Long`).
  * **Value:** The frequency (e.g., `Integer`).
  * **Benefit:** Only stores data for elements that actually exist in the input, saving memory.

### B. Time Complexity Comparison (Maps)

| Map Type | Store/Fetch TC (Best/Average) | Store/Fetch TC (Worst) | Order |
| :--- | :--- | :--- | :--- |
| **`HashMap`** | **$O(1)$** | $O(N)$ (Due to **Collision**) | Unordered (Fastest) |
| **`TreeMap`** | $O(\log N)$ | $O(\log N)$ | Sorted (Slower but safer) |

> **Recommendation:** Always use **`HashMap`** first, as $O(1)$ is incredibly fast. If you encounter a TLE (Time Limit Exceeded) error, it might be due to a worst-case $O(N)$ collision, so switch to the safer **`TreeMap`**.

### 💻 Java Code Example (Map Hashing)

```java
import java.util.HashMap;
import java.util.Map;

public class MapHashing {
    public static void hashLargeNumbers(int[] arr) {
        // Key: Integer (Number), Value: Integer (Frequency)
        Map<Integer, Integer> freqMap = new HashMap<>(); 
        
        // 1. PRE-COMPUTATION (O(N))
        for (int num : arr) {
            // getOrDefault(key, 0) returns the current count, or 0 if not found.
            // It is then incremented and put back.
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // 2. FETCH (O(Q))
        int queryNum = 3;
        // Fetch: If key exists, return frequency; otherwise, getOrDefault returns 0.
        int frequency = freqMap.getOrDefault(queryNum, 0);
        System.out.println("Frequency of " + queryNum + ": " + frequency);
    }
}
```