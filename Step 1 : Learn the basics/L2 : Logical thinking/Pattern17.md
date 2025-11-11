````markdown
# Alphabet Pyramid (Pattern 17)

This note explains how to print a centered alphabet triangle that increases letters from 'A' up to a peak and then decreases back to 'A' on each row. The explanation follows the same structure used for previous pattern notes: break rows into chunks, give formulas, show a per-row table, and include the Java implementation (matching `Pattern17.java`).

---

### Problem

For N = 5, print:

     A     
    ABA    
   ABCBA   
  ABCDCBA  
 ABCDEDCBA 

Each row i (0-based) contains characters that increase from 'A' up to 'A' + i, then decrease back to 'A'. Rows are centered by printing left (and optional right) spaces.

---

### Step 1: Break each row into chunks

- Left spaces
- Characters (increasing then decreasing)
- Right spaces (optional for console symmetry)

---

### Step 2: Determine formulas (0-based indexing)

Use row index i = 0 .. N-1.

| Section     | Count / rule                                                        |
| ----------- | ------------------------------------------------------------------- |
| Left spaces | `N - i - 1`                                                         |
| Characters  | `2*i + 1` total characters                                          |
| Peak letter | `char peak = (char)('A' + i)`                                       |
| Breakpoint  | `i` (or `(2*i+1)/2`) — where increasing stops and decreasing starts |

Character sequence rule (two equivalent approaches — choose the clearer one for you):

- Two-phase (explicit and easy to follow):

  1. Print increasing letters from k = 0 .. i: `(char)('A' + k)`.
  2. Then print decreasing letters from k = i-1 .. 0: `(char)('A' + k)`.

- Single-pass with breakpoint (matches the implementation below):
  - Initialize `ch = 'A'` and `breakpoint = i` (note `(2*i+1)/2` simplifies to `i`).
  - For j = 1 .. 2\*i+1: print `ch`; then if `j <= breakpoint` do `ch++`, else do `ch--`.

Both approaches produce the same sequence: A, B, ... up to the peak letter, then back down to A.

---

### Step 3: Count table per row (example: N = 5)

| Row (i) | Left Spaces | Sequence  | Total chars |
| ------- | ----------- | --------- | ----------- |
| 0       | 4           | A         | 1           |
| 1       | 3           | ABA       | 3           |
| 2       | 2           | ABCBA     | 5           |
| 3       | 1           | ABCDCBA   | 7           |
| 4       | 0           | ABCDEDCBA | 9           |

---

### Step 4: Java code (matching `Pattern17.java`)

```java
public class Pattern17 {

    static void pattern17(int N){

        for(int i=0;i<=N-1;i++){

            // left padding spaces
            for(int j=0; j<=N-i-1; j++){
                System.out.print(" ");
            }

            // characters: increase from 'A' to 'A'+i, then decrease
            char ch = 'A';
            int breakpoint = (2*i+1)/2; // equals i for integer arithmetic

            for(int j=1; j<=(2*i)+1; j++){
                System.out.print(ch);

                if (j <= breakpoint) ch++;
                else ch--;
            }

            // right padding spaces (optional)
            for(int j=0; j<=N-i-1; j++){
                System.out.print(" ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        int N = 5;
        pattern17(N);
    }
}
```

---

### Notes & tips

- The `breakpoint` formula `(2*i+1)/2` simplifies to `i` due to integer division, so you can treat it as `i` conceptually.
- The code prints the current character then updates `ch` for the next position — this keeps the logic simple and avoids computing positions separately for the increasing and decreasing phases.
- To accept runtime input, replace the fixed `N = 5` in `main` with a `Scanner` read.
- This pattern is a compact exercise in nested loops and managing a small local state (`ch`).
````
