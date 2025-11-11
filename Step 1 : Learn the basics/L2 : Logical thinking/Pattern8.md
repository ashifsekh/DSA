````markdown
# Star Patterns Cheat Sheet

This note explains how to approach classic pyramid/triangle/diamond star patterns using the **space-star-space** method. Includes step-by-step logic, formulas, and code.

---

## 1. Approach to Pattern-Based Questions

1. **Use nested loops** for printing patterns.
   - Outer loop → counts rows/lines.
   - Inner loop → counts columns based on row logic.
2. **Connect inner loop to outer loop**: Form a logic such that each row prints the required number of columns.
3. **Print the stars (`*`) inside the inner loop**.
4. **Observe symmetry** in the pattern or see if the pattern is a combination of multiple smaller patterns.

---

## 2. Pyramid Star Pattern (Normal)

### Problem

Print a pyramid pattern of stars like this for `N = 5`:

         *********
          *******
           *****
            ***
             *

### Step 1: Break each row into chunks

- Left spaces
- Stars
- Right spaces (optional for symmetry)

---

### Step 2: Determine formulas

| Section      | Count formula (for row index i, 0-based) |
| ------------ | ---------------------------------------- |
| Left spaces  | `i`                                      |
| Stars        | `2*(N - i) - 1`                          |
| Right spaces | `i`                                      |

---

### Step 3: Count table per row

| Row (i) | Left Spaces | Stars | Right Spaces |
| ------- | ----------- | ----- | ------------ |
| 0       | 0           | 9     | 0            |
| 1       | 1           | 7     | 1            |
| 2       | 2           | 5     | 2            |
| 3       | 3           | 3     | 3            |
| 4       | 4           | 1     | 4            |

---

### Step 4: Java Code

```java
// Using a 0-based row index i (i = 0 .. N-1), this prints an inverted pyramid
int N = 5;
for (int i = 0; i < N; i++) {
    // left spaces
    for (int j = 0; j < i; j++) {
        System.out.print(" ");
    }

    // stars for the row: 2*(N - i) - 1
    for (int k = 0; k < 2 * (N - i) - 1; k++) {
        System.out.print("*");
    }

    // optional right spaces (not needed for console output symmetry)
    System.out.println();
}
```
````
