````markdown
# Alternating 1/0 Triangle (Pattern 11)

This note explains how to print rows of alternating 1s and 0s where each row starts either with 1 or 0 depending on the row index. The approach follows the style used for other pattern notes in the repository: break a row into logical chunks, derive simple formulas, show a per-row count table, and include the Java implementation.

---

### Problem

For N = 5 print:

1
01
101
0101
10101

Each row i (0-based) contains i+1 digits. Rows alternate starting digits: row 0 starts with 1, row 1 starts with 0, row 2 starts with 1, etc.

---

### Step 1: Break each row into chunks

- Starting digit (depends on row index)
- Alternating sequence of digits (flip between 1 and 0)

---

### Step 2: Determine the rules (0-based indexing)

- Row index: i = 0 .. N-1
- Number of digits in row i: length = i + 1
- Starting digit for row i:
  - If i is even → start = 1
  - If i is odd → start = 0
- Each subsequent digit is the opposite of previous: next = 1 - current

Algorithm contract (short):

- Input: integer N (number of rows, N >= 0)
- Output: N lines printed to stdout, where line i contains i+1 alternating bits starting with start(i)

Edge cases:

- N = 0 → print nothing
- Large N → output grows O(N^2) characters; fine for small practice runs

---

### Step 3: Count table per row (example N = 5)

| Row (i) | Start | Sequence | Length |
| ------- | ----- | -------- | ------ |
| 0       | 1     | 1        | 1      |
| 1       | 0     | 01       | 2      |
| 2       | 1     | 101      | 3      |
| 3       | 0     | 0101     | 4      |
| 4       | 1     | 10101    | 5      |

---

### Step 4: Java code (matching `Pattern11.java`)

```java
//  Q11 -

// 1
// 01
// 101
// 0101
// 10101


 class Pattern11 {

   static void pattern11(int N)
{
     // First row starts by printing a single 1.
      int start =1;

      // Outer loop for the no. of rows
      for(int i=0;i<N;i++){

          // if the row index is even then 1 is printed first
          // in that row.
          if(i%2 ==0) start = 1;

          // if odd, then the first 0 will be printed in that row.
          else start = 0;

          // We alternatively print 1's and 0's in each row by using
          // the inner for loop.
          for(int j=0;j<=i;j++){
              System.out.print(start);
              start = 1-start;
          }


        // As soon as the numbers for each iteration are printed, we move to the
        // next row and give a line break otherwise all numbers
        // would get printed in 1 line.
        System.out.println();
      }
}

    public static void main(String[] args) {

        // Here, we have taken the value of N as 5.
        // We can also take input from the user.
        int N = 5;
        pattern11(N);
    }
}
```

