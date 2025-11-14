# 🧮 DSA Notes: Basic Mathematics for Problem Solving

This section covers essential mathematical concepts used frequently in Data Structures and Algorithms problems.

## I. Core Concept: Digit Extraction

The ability to extract individual digits from an integer is fundamental to solving many number-based problems. Extraction happens in **reverse order** (Last Digit $\rightarrow$ First Digit).

| Operation | Purpose | Explanation |
| :--- | :--- | :--- |
| **Modulo 10 ($\text{N} \% 10$)** | Extracts the **Last Digit**. | Returns the remainder when $N$ is divided by 10. |
| **Division by 10 ($\text{N} / 10$)** | **Removes** the Last Digit. | Returns the integer quotient, truncating the decimal part. |

### 🧠 Logic

The process continues within a `while` loop until the number $N$ becomes 0.

```java
// Example N = 7789
while (N > 0) {
    int lastDigit = N % 10;   // 1. lastDigit = 9, 2. lastDigit = 8, 3. lastDigit = 7, ...
    // Perform operations with lastDigit (e.g., print, sum, reverse)
    N = N / 10;               // 1. N = 778, 2. N = 77, 3. N = 7, ...
}
```

### ⏱ Time Complexity

The loop runs once for every digit in $N$. Since $N$ is repeatedly divided by 10, the complexity is **Logarithmic**.

$$O(\log_{10} N)$$

-----

## II. Problems Solved with Digit Extraction

### 1\. Count Digits

**Problem:** Given a number $N$, return the total count of digits present in it.

#### 🧠 Logic

The number of digits is equal to the number of times the **digit extraction loop** runs. We use a counter initialized to zero and increment it inside the loop.

  * **Alternative Logarithmic Solution:** $Count = \lfloor \log_{10} N \rfloor + 1$ (This is $O(1)$ complexity if we ignore the log function's underlying calculation, but the iterative $O(\log_{10} N)$ approach is often preferred in interviews.)

#### 💻 Java Code Example

```java
public static int countDigits(int N) {
    int count = 0;
    
    // Store original N to use in the loop
    int num = N; 
    
    while (num > 0) {
        num = num / 10; // Removes one digit per iteration
        count++;        // Increments count for each digit removed
    }
    return count;
}
```

-----

### 2\. Reverse a Number

**Problem:** Generate the reverse of a given number $N$. (e.g., $123 \rightarrow 321$).

#### 🧠 Logic

We extract the digits one by one and **construct the reverse number** by multiplying the current reverse number by 10 and adding the new last digit.

$$\text{ReverseNum} = (\text{ReverseNum} \times 10) + \text{LastDigit}$$

  * *Analogy:* To turn `9` into `90` so you can add the next digit `8` to get `98`, you multiply `9` by 10.

#### 💻 Java Code Example

```java
public static int reverseNumber(int N) {
    int reverseNum = 0;
    int num = N;
    
    while (num > 0) {
        int lastDigit = num % 10;
        
        // Construct the reverse number
        reverseNum = (reverseNum * 10) + lastDigit; 
        
        num = num / 10;
    }
    return reverseNum;
}
```

-----

### 3\. Check Palindrome

**Problem:** Determine if a number is a palindrome (reads the same forwards and backwards).

#### 🧠 Logic

1.  Store a copy of the original number ($N$) in a `duplicate` variable.
2.  Use the **Reverse Number** logic (from problem 2) to generate the reverse.
3.  Compare the generated `reverseNum` with the `duplicate` variable.

> **Crucial Step:** You must store a copy of $N$ because the digit extraction process modifies $N$ until it becomes 0.

#### 💻 Java Code Example

```java
public static boolean isPalindrome(int N) {
    int duplicate = N; // Store the original value
    int reverseNum = 0;
    int num = N;
    
    while (num > 0) {
        int lastDigit = num % 10;
        reverseNum = (reverseNum * 10) + lastDigit;
        num = num / 10;
    }
    
    return duplicate == reverseNum; // Compare original with reverse
}
```

-----

### 4\. Armstrong Number

**Problem:** Check if a number is equal to the sum of its digits, each raised to the power of the number of digits. (For a 3-digit number: $371 = 3^3 + 7^3 + 1^3$).

#### 🧠 Logic

1.  Store a copy of the original number ($N$) in a `duplicate` variable.
2.  Initialize a `sum` variable to 0.
3.  Use the digit extraction loop. For each extracted digit, calculate $digit^k$ (where $k$ is the number of digits) and add it to `sum`.
4.  Compare `sum` with `duplicate`.

#### 💻 Java Code Example (For 3-digit number case)

```java
public static boolean isArmstrong(int N) {
    int duplicate = N;
    int sum = 0;
    int num = N;
    
    // Note: For a general case, we'd first count the digits (k) 
    // and use Math.pow(lastDigit, k). 
    
    while (num > 0) {
        int lastDigit = num % 10;
        // For 3-digit numbers, calculate the cube:
        sum += (lastDigit * lastDigit * lastDigit); 
        num = num / 10;
    }
    
    return duplicate == sum;
}
```

-----

## III. Divisors and Prime Numbers

### 1\. Print All Divisors

**Problem:** Find all divisors (factors) of a number $N$. (e.g., $36: 1, 2, 3, 4, 6, 9, 12, 18, 36$).

#### A. Brute Force Approach

Loop from $i=1$ to $N$ and check if $N$ is perfectly divisible by $i$ (i.e., $N \% i == 0$).

  * **Time Complexity:** $O(N)$

#### B. Optimal Approach

Factors appear in pairs $(i, N/i)$. We only need to check up to the square root of $N$.

  * **Loop:** Iterate from $i=1$ up to $\sqrt{N}$ (or $i \times i \le N$).

  * **Pair Generation:** If $i$ divides $N$, then $i$ is a factor, and $N/i$ is the corresponding pair factor.

  * **Optimization:** Ensure you don't print $N/i$ if $i = N/i$ (e.g., when $i=6$ for $N=36$).

  * **Sorting:** Store factors in a list and sort the list to print in order.

  * **Time Complexity:** $O(\sqrt{N} + F \log F)$ (where $F$ is the number of factors, needed for sorting).

#### 💻 Java Code Example (Optimal)

```java
import java.util.ArrayList;
import java.util.Collections;

public static void printDivisors(int N) {
    ArrayList<Integer> factors = new ArrayList<>();
    
    // Loop only up to the square root of N
    for (int i = 1; i * i <= N; i++) {
        if (N % i == 0) {
            factors.add(i);       // Add the factor 'i'
            
            int complementaryFactor = N / i;
            
            // Add the complementary factor only if it's not the same as 'i'
            if (i != complementaryFactor) {
                factors.add(complementaryFactor);
            }
        }
    }
    Collections.sort(factors);
    System.out.println(factors);
}
```

-----

### 2\. Check for Prime

**Definition:** A prime number is an integer greater than 1 that has **exactly two factors**: 1 and itself.

#### 🧠 Logic

Use the **Optimal Divisors approach** to efficiently count the factors.

1.  Loop from $i=2$ up to $\sqrt{N}$.
2.  If any $i$ divides $N$, $N$ is not prime (it has at least 3 factors: 1, $i$, and $N$).
3.  The only factors remaining are 1 and $N$, which is exactly two factors.

<!-- end list -->

  * **Time Complexity:** $O(\sqrt{N})$

#### 💻 Java Code Example

```java
public static boolean isPrime(int N) {
    if (N <= 1) return false; // 0 and 1 are not prime

    // Check divisibility from 2 up to the square root of N
    for (int i = 2; i * i <= N; i++) {
        if (N % i == 0) {
            return false; // Found a factor other than 1 and N
        }
    }
    // If no factors were found, it must be prime.
    return true; 
}
```

-----

## IV. Greatest Common Divisor (GCD) / Highest Common Factor (HCF)

**Definition:** The largest number that divides two integers, $N_1$ and $N_2$, without leaving a remainder.

### 1\. Brute Force Approach

Loop backward from $\min(N_1, N_2)$ down to 1. The first number $i$ that divides both $N_1$ and $N_2$ is the GCD.

  * **Time Complexity:** $O(\min(N_1, N_2))$

### 2\. Optimal Approach: Euclidean Algorithm

This algorithm is based on the property: $\text{GCD}(a, b) = \text{GCD}(a - b, b)$ (if $a > b$). Using the modulo operator drastically speeds this up:

$$\text{GCD}(a, b) = \text{GCD}(b, a \pmod b)$$

We repeat this process until one number becomes 0. The non-zero number is the GCD.

  * **Time Complexity:** $O(\log \min(N_1, N_2))$

#### 💻 Java Code Example (Euclidean Algorithm)

```java
public static int gcd(int N1, int N2) {
    // The algorithm continues until one number (N2 in this case) becomes 0
    while (N2 != 0) {
        int temp = N2;
        // The new N2 is the remainder of N1 divided by N2
        N2 = N1 % N2; 
        // The new N1 is the old N2
        N1 = temp;    
    }
    return N1; // N1 holds the GCD when N2 is 0
}
```