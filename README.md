# 🧩 Dancing Links 3D Pentomino Solver

> A Java implementation of **Donald Knuth’s Dancing Links (Algorithm X)** designed to solve the **3D Pentomino exact cover problem** efficiently.

---

## 🧠 Overview

This project demonstrates the use of **Dancing Links (DLX)** to solve **3D tiling and packing problems**—specifically, fitting pentomino pieces into a 3D grid without overlaps or gaps.  
The implementation follows the **exact cover problem formulation**, where each valid placement of a piece corresponds to a row in a binary matrix, and each constraint (such as “a cell must be covered exactly once”) corresponds to a column.

---

## ⚙️ Core Idea

Dancing Links is an efficient backtracking algorithm that uses a **circular doubly linked list** to represent the constraint matrix.  
When a choice is made (selecting a row), the related columns are temporarily removed, and then restored during backtracking—making the process fast and memory-efficient.

In this project:
- Each **pentomino piece** and its possible rotations/translations are encoded into the matrix.
- The **DLX algorithm** explores combinations until all constraints are satisfied (full 3D board coverage).

---

## 🏗️ Project Structure

```
phase-3-Knapsack_Eugene_ABC/
│
├── Cell.java          # Node structure for the DLX linked list (up/down/left/right pointers)
├── Dance.java         # Core Dancing Links algorithm (Algorithm X implementation)
├── DataBase.java      # Builds and manages the exact cover matrix for the pentomino puzzle
├── PieceInfo.java     # Defines 3D pentomino piece shapes and transformations
├── Search.java        # Entry point for running the solver and outputting solutions
└── README.md          # Project documentation
```

---

## 🚀 Usage

### 1. Compile the project
```bash
javac *.java
```

### 2. Run the solver
```bash
java Search
```

> The program will attempt to find all valid configurations of 3D pentominoes that fill the given space.

### 3. Modify the puzzle
Edit `PieceInfo.java` or `DataBase.java` to:
- Change the dimensions of the 3D grid.
- Add or remove specific pentomino shapes.
- Adjust symmetry or rotation constraints.

---

## 🧩 Algorithm Highlights

- **Algorithm X** — Knuth’s backtracking algorithm for exact cover problems.  
- **Dancing Links (DLX)** — a fast matrix manipulation technique using linked nodes.  
- **3D Pentomino Modeling** — each pentomino can have multiple orientations in 3D space.  
- **Recursive Search** — explores all valid placements until full coverage is achieved.  

---

## 📊 Example Output (conceptual)

```
Solution #1:
Layer 1: F L P ...
Layer 2: N T U ...
...
Found 2 solutions in 0.53s
```

> (Output format depends on your `Search.java` implementation.)

---

## 🧰 Requirements

- Java 17 or higher  
- Any IDE or command-line compiler (`javac`, IntelliJ, VS Code, etc.)

---

## 🧱 Possible Extensions

- Visualize the 3D board and piece placements.  
- Add CLI parameters to select grid size or number of solutions.  
- Compare performance with other exact cover algorithms.  
- Extend to n-dimensional tiling or Sudoku variants.

---

## 🧑‍💻 Author

**Eugeniu Gheorghiță**  
Bachelor Student — Computer Science, Maastricht University  
📍 Maastricht, Netherlands  
📧 [mreugen123@gmail.com](mailto:mreugen123@gmail.com)  
🔗 [LinkedIn](https://www.linkedin.com/in/eugen-gheorghita-378314253/)

---

## 🧠 References

- Knuth, D. E. *“Dancing Links.”* (2000)  
- Polyform puzzles and pentomino theory resources  
- Algorithm X and exact cover tutorials

---

## 🗂️ License

This project is open for educational and research use.  
Feel free to fork, modify, and extend it.

---

*Last updated: October 2025*
