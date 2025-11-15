# ♾️ DSA Notes: Basic Recursion Concepts and Problems

Recursion is a technique where a **function calls itself** to solve a problem. It continues until a **specified condition (Base Case)** is met, preventing infinite loops and **Stack Overflow**.

## I. Fundamentals: Recursion, Base Case, and Stack Flow

### A. What is Recursion?

  * **Definition:** A function calling itself.
  * **Infinite Recursion:** Occurs when a function calls itself without a stop condition, leading to **Stack Overflow** (running out of memory).

### B. The Base Case

  * **Definition:** The **stop condition** in a recursive function. It's the point where the function stops calling itself and starts returning/completing.
  * **Purpose:** Prevents infinite recursion and defines the simplest possible answer to the problem.

### C. Stack Overflow

  * **Mechanism:** When a function calls itself, the current function call is paused and waits in the **Stack Space** (memory) to be completed.
  * **Stack Overflow:** If recursion is infinite, the stack space fills up with incomplete function calls, eventually causing a program crash (Segmentation Fault).
  * **Flow:** Calls go **down** the recursion path; returns/completion go **up** the stack.

### D. Recursion Tree

  * **Purpose:** A diagrammatic representation (often vertical) used to visualize the sequence of recursive calls and returns, helping track execution flow.

#### 💻 Code Example: Basic Structure & Base Case

```java
public class RecursionBasics {
    // Global counter (for illustration; usually avoided)
    static int count = 0; 

    public static void printFunction() {
        // 1. BASE CASE: The condition to stop
        if (count == 3) {
            return; // Stops execution and returns up the stack
        }

        System.out.println(count);
        count++; // Modify state
        
        // 2. RECURSIVE CALL: The function calls itself
        printFunction(); 
    }

    public static void main(String[] args) {
        printFunction(); // Output: 0, 1, 2
    }
}
```

-----

## II. Basic Unidirectional Recursion Problems

These problems use a single recursive call and typically modify a counter parameter (`i`) to define the direction.

### 1\. Print Name $N$ Times

  * **Logic:** Start an index `i` at 1. In each call, print the name and increment `i`. Stop when $i > N$.
  * **Base Case:** `if (i > N) return;`

#### 💻 Java Code Example

```java
public static void printName(int i, int N) {
    if (i > N) {
        return;
    }
    System.out.println("Raj");
    printName(i + 1, N); // Moving forward
}
// Call: printName(1, 4) -> Prints "Raj" 4 times
```

### 2\. Print Linearly from 1 to $N$

  * **Logic:** Start $i$ at 1. Print $i$, then call the function with $i+1$.
  * **Base Case:** `if (i > N) return;`

#### 💻 Java Code Example

```java
public static void print1ToN(int i, int N) {
    if (i > N) {
        return;
    }
    System.out.println(i);
    print1ToN(i + 1, N);
}
// Call: print1ToN(1, 4) -> Output: 1, 2, 3, 4
```

### 3\. Print Linearly from $N$ to 1

  * **Logic:** Start $i$ at $N$. Print $i$, then call the function with $i-1$.
  * **Base Case:** `if (i < 1) return;`

#### 💻 Java Code Example

```java
public static void printNTo1(int i) { // N is initial i
    if (i < 1) {
        return;
    }
    System.out.println(i);
    printNTo1(i - 1); // Moving backward
}
// Call: printNTo1(4) -> Output: 4, 3, 2, 1
```

-----

## III. Backtracking and Functional Recursion

### 1\. Printing 1 to $N$ Using Backtracking (Forward printing using Backward calls)

**Backtracking** refers to performing the required operation **after** the recursive call. This ensures the operations execute as the function calls return (move up the stack).

  * **Logic:** Start $i$ at $N$ and call the function with $i-1$. The base case is reached when $i=0$. The print statement is placed **after** the recursive call.
  * **Execution Flow:** Calls go $N \rightarrow 0$. Returns go $0 \rightarrow N$. Print happens on the return path.

#### 💻 Java Code Example

```java
public static void print1ToNBacktrack(int i, int N) {
    if (i < 1) { // Base case reached when i becomes 0
        return;
    }
    // 1. Recursive Call first (Stack builds up)
    print1ToNBacktrack(i - 1, N); 
    
    // 2. Operation after the call (Executes on the way UP the stack)
    System.out.println(i); 
}
// Call: print1ToNBacktrack(4, 4) -> Output: 1, 2, 3, 4
```

### 2\. Sum of First $N$ Numbers

#### A. Parameterized Recursion

The result is carried and modified through a parameter across recursive calls. The final answer is printed/handled when the base case is met.

  * **Logic:** Start $i$ at $N$ and `sum` at 0. In each call, pass $i-1$ and `sum + i`.
  * **Base Case:** `if (i < 1)` print `sum` and return.

#### 💻 Java Code Example (Parameterized)

```java
public static void sumParam(int i, int sum) {
    if (i < 1) {
        System.out.println("Sum is: " + sum); // Result handled at base case
        return;
    }
    // Passes the updated sum to the next call
    sumParam(i - 1, sum + i); 
}
// Call: sumParam(3, 0) -> Sum is: 6
```

#### B. Functional Recursion

The function itself returns the answer by breaking the problem into $F(N) = N + F(N-1)$.

  * **Logic:** Each function call waits for the return value of $F(N-1)$ before calculating its own return value.
  * **Base Case:** `if (N == 0) return 0;`

#### 💻 Java Code Example (Functional)

```java
public static int sumFunc(int N) {
    if (N == 0) {
        return 0; // Base case returns 0
    }
    // Return N + (sum of remaining numbers)
    return N + sumFunc(N - 1); 
}
// Call: int result = sumFunc(3); // result = 6
```

### 3\. Factorial of $N$

Similar to summation, but uses multiplication. $F(N) = N \times F(N-1)$.

  * **Base Case Consideration:** The base case must return **1** (the identity element for multiplication) to ensure the final product is not zero. `if (N == 0) return 1;`

#### 💻 Java Code Example (Functional)

```java
public static int factorial(int N) {
    if (N == 0) {
        return 1; // Returns 1 to maintain product integrity
    }
    return N * factorial(N - 1);
}
// Call: int result = factorial(4); // result = 24
```

-----

## IV. Complex Recursion: Arrays and Palindromes

### 1\. Reverse an Array

  * **Logic:** Swap elements using two pointers, **Left (L)** and **Right (R)**. L starts at 0, R starts at $N-1$.
  * **Recursive Task:** Swap $\text{Array}[L]$ and $\text{Array}[R]$. Then call for $L+1$ and $R-1$.
  * **Base Case:** Stop when $L \ge R$.

#### 💻 Java Code Example (Single Pointer Approach)

The right index can be calculated from the left index: $R = N - 1 - L$.

```java
// Note: Arrays are passed by reference in Java, so changes are permanent.
public static void reverseArray(int L, int[] arr, int N) {
    // Base Case: Stop when the left pointer reaches the middle
    if (L >= N / 2) { 
        return;
    }
    
    int R = N - 1 - L; // Calculate Right index
    
    // Swap the elements
    int temp = arr[L];
    arr[L] = arr[R];
    arr[R] = temp;
    
    // Recursive Call: move to the next inner pair
    reverseArray(L + 1, arr, N); 
}
// Call: int[] arr = {1, 2, 3, 4, 5}; reverseArray(0, arr, 5); // arr is now {5, 4, 3, 2, 1}
```

### 2\. Check if a String is Palindrome

  * **Logic:** Use a functional approach to check if the character at the **Left index ($i$)** equals the character at the **Right index ($N-1-i$)**.
  * **Recursive Task:** Check $\text{String}[i] == \text{String}[N-1-i]$.
      * If **unequal**, immediately return `false`.
      * If **equal**, recurse for $i+1$.
  * **Base Case:** If $i$ reaches the middle ($\ge N/2$), all necessary checks passed, so return `true`.

#### 💻 Java Code Example (Functional)

```java
public static boolean isPalindrome(int i, String s) {
    int N = s.length();
    
    // Base Case: All checks passed (i reached the middle)
    if (i >= N / 2) {
        return true; 
    }
    
    // Check: If the pair is unequal, it's not a palindrome
    if (s.charAt(i) != s.charAt(N - 1 - i)) {
        return false; 
    }
    
    // Recursive Call: Check the next inner pair
    return isPalindrome(i + 1, s);
}
// Call: isPalindrome(0, "MADAM") -> true
// Call: isPalindrome(0, "MADAD") -> false
```

-----

## V. Multiple Recursion Calls (Fibonacci)

Multiple recursion calls occur when a function calls itself two or more times in its body. This is common in problems that naturally split into multiple subproblems.

### Fibonacci Numbers ($F(N)$)

  * **Rule:** $F(N) = F(N-1) + F(N-2)$ (Sum of the two preceding numbers).
  * **Execution:** Multiple calls happen **sequentially**. The first call ($F(N-1)$) must complete and return before the second call ($F(N-2)$) is made.

| Index (N) | 0 | 1 | 2 | 3 | 4 | 5 |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **F(N)** | 0 | 1 | 1 | 2 | 3 | 5 |

  * **Base Case:** `if (N <= 1) return N;` (Handles $F(0)=0$ and $F(1)=1$).

#### 💻 Java Code Example

```java
public static int fibonacci(int N) {
    // Base Case: Returns 0 for F(0) and 1 for F(1)
    if (N <= 1) { 
        return N;
    }
    
    // First call (F(N-1)) executes fully, then the second call (F(N-2)) executes.
    int last = fibonacci(N - 1);     // Calculates F(N-1)
    int secondLast = fibonacci(N - 2); // Calculates F(N-2)
    
    return last + secondLast;
}
// Call: fibonacci(4) -> 3
```

### ⏱ Time Complexity (Multiple Calls)

  * **Nature:** The recursion tree branches exponentially.
  * **Time Complexity:** $O(2^N)$ (Near exponential). This is highly inefficient but is used to demonstrate the recursive structure. (The iterative solution is $O(N)$).