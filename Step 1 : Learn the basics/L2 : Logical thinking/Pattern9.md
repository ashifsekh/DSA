````markdown
# Diamond Star Pattern (Pattern 9)

This note explains how to print a symmetric diamond (pyramid + inverted pyramid) of stars. The examples and formulas use a 0-based row index for clarity and match the `Pattern9.java` implementation in the same folder.

---

### Problem

Print a diamond like this for `N = 4` (N is the number of rows in the top half, including the middle):

       *
      ***
     *****
    *******
     *****
      ***
       *

This diamond has 2\*N - 1 total rows. The top half (including middle) increases stars; the bottom half mirrors the top.

---

### Step 1: Break each printed row into chunks

- Left spaces
- Stars
- Right spaces (optional for symmetry — not required for console output)

---

### Step 2: Determine formulas (0-based indexing)

We use row index i where the upper half rows are i = 0 .. N-1. For upper half row i:

| Section      | Count formula (upper half, i = 0..N-1) |
| ------------ | -------------------------------------- |
| Left spaces  | `N - i - 1`                            |
| Stars        | `2*i + 1`                              |
| Right spaces | `N - i - 1`                            |

For the lower half the code in `Pattern9.java` iterates with a decreasing index i from `N-2` down to `0` (mirror of upper rows). For such a row i the formulas are the same:

- Left spaces = `N - i - 1`
- Stars = `2*i + 1`

---

### Step 3: Count table per row (example: N = 4)

Upper half (i = 0 .. 3):

| Row (i) | Left Spaces | Stars | Right Spaces |
| ------- | ----------- | ----- | ------------ |
| 0       | 3           | 1     | 3            |
| 1       | 2           | 3     | 2            |
| 2       | 1           | 5     | 1            |
| 3       | 0           | 7     | 0            |

Lower half (r = 2 .. 0):

| Row (r) | Left Spaces | Stars | Right Spaces |
| ------- | ----------- | ----- | ------------ |
| 2       | 1           | 5     | 1            |
| 1       | 2           | 3     | 2            |
| 0       | 3           | 1     | 3            |

---

### Step 4: Java code (matching `Pattern9.java`)

```java
import java.util.Scanner;

public class Pattern9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of rows for the diamond: ");
        int N = sc.nextInt();

        if (N <= 0) {
            System.out.println("Please enter a positive integer greater than 0.");
            sc.close();
            return;
        }

        // -------------------------
        // Upper half logic (i = 0 .. N-1)
        // For each row i:
        //   left spaces = N - i - 1
        //   stars      = 2*i + 1
        // -------------------------
        for (int i = 0; i < N; i++) {
            // left spaces
            for (int j = 0; j < N - i - 1; j++) System.out.print(' ');

            // stars
            for (int j = 0; j < 2 * i + 1; j++) System.out.print('*');

            System.out.println();
        }

        // -------------------------
        // Lower half logic (i = N-2 .. 0)
        // For each row i:
        //   left spaces = N - i - 1
        //   stars      = 2*i + 1
        // The code iterates i from N-2 down to 0 to print the mirrored rows.
        // -------------------------
        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j < N - i - 1; j++) System.out.print(' ');
            for (int j = 0; j < 2 * i + 1; j++) System.out.print('*');
            System.out.println();
        }

        sc.close();
    }
}
```
````
