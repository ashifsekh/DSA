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

## 2. Example: Pyramid Star Pattern (Pattern6)

### Problem

Print a pyramid pattern of stars like this for `N = 5`:

        *
       ***
      *****
     *******
    *********


---

### Step 1: Break each row into chunks

Each row has three sections:


- Left spaces  
- Stars  
- Right spaces (optional for symmetry)

---

### Step 3: Determine formulas

Let `N` be total rows and `i` be current row index starting from 0:

| Section       | Count formula             |
| ------------- | ------------------------ |
| Left spaces   | `N - i - 1`              |
| Stars         | `2*i + 1`                |
| Right spaces  | `N - i - 1`              |

---

### Step 2: Count table per row

| Row (i) | Left Spaces | Stars | Right Spaces |
| -------- | ----------- | ----- | ------------ |
| 0        | 4           | 1     | 4            |
| 1        | 3           | 3     | 3            |
| 2        | 2           | 5     | 2            |
| 3        | 1           | 7     | 1            |
| 4        | 0           | 9     | 0            |

✅ Observation:

- Spaces decrease by 1 each row.  
- Stars increase by 2 each row.  
- This is the **space-star-space** logic.

---

### Step 4: Java Code

```java
public class Pattern6 {
    public static void main(String[] args) {
        int N = 5;
        for (int i = 0; i < N; i++) {
            // Left spaces
            for (int j = 0; j < N - i - 1; j++) {
                System.out.print(" ");
            }
            // Stars
            for (int k = 0; k < 2*i + 1; k++) {
                System.out.print("*");
            }
            // Right spaces
            for (int j = 0; j < N - i - 1; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
