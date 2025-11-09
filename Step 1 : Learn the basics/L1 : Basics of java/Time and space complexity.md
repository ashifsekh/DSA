## ⏰ DSA Notes: Time & Space Complexity (Basics)

### **I. Time Complexity: Definition & Notation**

* **Goal:** To analyze code performance independently of the machine configuration.
* **Time Taken $\ne$ Time Complexity:** Time taken (in seconds) is system-dependent. Time complexity is the **rate** at which the execution time increases relative to the **input size ($N$)**.
* **Notation:** Expressed using **Big O Notation** (e.g., $O(N)$, $O(N^2)$). Big O represents the **Worst Case** complexity (Upper Bound).

---

### **II. Three Golden Rules for Computing Time Complexity**

Time complexity is computed in terms of the **Worst Case Scenario** and simplified using these rules:

#### **1. Worst Case Scenario (Highest Complexity)**
* Always compute the time complexity for the input that causes the **maximum number of operations**.
* **Example:** In an $\text{if/else if/else}$ block, we consider the path that executes the most checks.

#### **2. Avoid Constants**
* **Rule:** Ignore multiplicative constants.
* **Example:** $O(3N)$ simplifies to $O(N)$.
* **Justification:** For very large input $N$, constants have negligible impact.

#### **3. Avoid Lower Order Terms**
* **Rule:** Only keep the term with the highest power of $N$.
* **Example:** $O(4N^3 + 3N^2 + 8)$ simplifies to $O(N^3)$.
* **Justification:** The highest power term (e.g., $N^3$) dominates the execution time as $N$ grows large.

---

### **III. Time Complexity Examples**

| Code Snippet | Analysis | Time Complexity (Simplified) |
| :--- | :--- | :--- |
| **Single Loop** | `for (i=1; i<=N; i++)` | $N$ iterations | $O(N)$ |
| **Nested Loop 1** | `for (i=0; i<N; i++) { for (j=0; j<N; j++) }` | $N \times N = N^2$ iterations | $O(N^2)$ |
| **Nested Loop 2** | `for (i=0; i<N; i++) { for (j=0; j<=i; j++) }` | Sum of $1 + 2 + \dots + N \approx N^2/2$ | $O(N^2)$ |

---

### **IV. Space Complexity**

* **Definition:** The total **memory space** your program takes, expressed in Big O notation.
* **Formula:** **Space Complexity** = **Auxiliary Space** + **Input Space**

#### **Components**

1.  **Input Space:** Space required to store the **input** data.
    * *Example:* An array of size $N$ takes $O(N)$ space.
2.  **Auxiliary Space:** **Extra space** taken by your solution (extra variables, arrays, etc.) to solve the problem.
    * *Example:* Declaring an extra variable $C$ for summation takes $O(1)$ space.

#### **Best Practice: Do Not Tamper with Input**

* **Crucial Rule:** Always use **auxiliary space** (extra variables, arrays) for computation or storage.
* **Avoid:** Manipulating the original input variables (e.g., $\text{B} = \text{A} + \text{B}$).
* **Reason:** In real-world software engineering, the input data might be needed elsewhere, and changing it is a bad practice.

---

### **V. Competitive Programming Note (Time Limit)**

* **Rule of Thumb:** Most online servers (LeetCode, etc.) execute $\approx 10^8$ operations per second.
* **Time Limit 1 Second:** If a problem has a time limit of 1 second, your optimal algorithm's total operations should be $\approx O(10^8)$ or less, given the maximum input size $N$.

