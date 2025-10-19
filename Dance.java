
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Dance {

    public Header root;
    public Header[] headers;
    public Stack<Integer> answer;
    public Stack<Integer> pentIDS;
    public int duplicateSolutionsFound;
    public static int length = 33;
    public static int height = 8;
    public static int width = 5;
    public static boolean stop = false;
    public static int[][][] matrix = new int[width][height][length];

    public Dance(int columns) { //Constructor
        answer = new Stack<>();
        pentIDS = new Stack<>();

        duplicateSolutionsFound = 0;

        root = new Header(-1);
        headers = new Header[columns];
        for (int j = 0; j < columns; j++) {
            headers[j] = new Header(j);
            root.InsertLeft(headers[j]);
        }
    }

    public void AddRow(int row, int pentId, int[] ones, int[][][] piece) { //Second Algo
        int last = -1;
        Cell first = null;
        for (int x : ones) {
            Cell cell = new Cell(headers[x]);
            headers[x].InsertUp(cell);
            cell.row = row;
            cell.shape = piece;
            cell.id = pentId;

            headers[x].size++;

            if (x <= last) {
                throw new IllegalArgumentException("Column indexes must be in increasing order");
            }

            if (first == null) {
                first = cell;
            } else {
                first.InsertLeft(cell);
            }
        }
    }

    public int As = 0;   public int Ls = 0;
    public int Bs = 0;   public int Ts = 0;
    public int Cs = 0;   public int Ps = 0;

    public void dance(int step) { // Main Algo
        if (stop)
            return;
        List<PieceInfo> pieceInfo = new ArrayList<>();
        if (answer.size() >= 10) {
            int totalValue = 0;
            for (var ans : answer) {
                PieceInfo r = Search.pieceInfo.get(ans);
                pieceInfo.add(r);
            }

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    for (int k = 0; k < matrix[0][0].length; k++) {
                        matrix[i][j][k] = -1;
                    }
                }
            }

            for (var piece : pieceInfo) {
                totalValue += piece.value;
                switch (piece.parcelID) {
                    case 1 -> As++;
                    case 2 -> Bs++;
                    case 3 -> Cs++;
                    case 4 -> Ls++;
                    case 5 -> Ts++;
                    case 6 -> Ps++;
                }
                placeApiece(piece.piece, piece.x, piece.y, piece.z, matrix);
            }
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    for (int k = 0; k < matrix[0][0].length; k++) {
                        System.out.print(matrix[i][j][k]);
                    }
                    System.out.println();
                }
                System.out.println();
            }
            matrix = null;
            System.out.println("Total value: " + totalValue);
            System.out.println("Numbers 1,2,3,4,5,6 stand for id of the shapes 123 for abc, 456 for ltp check database");
            System.out.println("If Parcels " + " A: " + As + " B: " + Bs + " C: " + Cs);
            System.out.println("If Pentominos " + " L: "+ Ls + " T: " + Ts + " P: " + Ps);

            if (As != 0 || Bs != 0 || Cs != 0) {
                if (totalValue >= 236) {
                    stop = true;
                    return;
                }
            } else if (root.R == root) {
                stop = true;
                return;
            }
            matrix = new int[width][height][length];
            As = 0;   Ls = 0;
            Bs = 0;   Ts = 0;
            Cs = 0;   Ps = 0;

        }

        Header head = (Header) root.R;
        int min = head.size;
        for (Cell xCell = head; xCell != root; xCell = xCell.R) {
            if (((Header) xCell).size < min) {
                min = ((Header) xCell).size;
                head = (Header) xCell;

                if (head.C.size == 0) {
                    return;
                }
            }
        }
        cover(head);
        for (Cell rCell = head.D; rCell != head; rCell = rCell.D) {
            answer.push(rCell.row);

            for (Cell jCell = rCell.R; jCell != rCell; jCell = jCell.R) {
                cover(jCell.C);
            }
            dance(step + 1);
            answer.pop();

            for (Cell jCell = rCell.L; jCell != rCell; jCell = jCell.L) {
                uncover(jCell.C);
            }

        }
        uncover(head);
    }

    private void placeApiece(int[][][] piece, int x0, int y0, int z0, int[][][] area) { //Place a shape on the matrix
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[0].length; j++) {
                for (int k = 0; k < piece[0][0].length; k++) {
                    if (piece[i][j][k] != 0) {
                        area[x0 + k][y0 + j][z0 + i] = piece[i][j][k];
                    }
                }
            }
        }
    }

    private void cover(Header head) {
        head.R.L = head.L;
        head.L.R = head.R;

        for (Cell iCell = head.D; iCell != head; iCell = iCell.D) {
            for (Cell jCell = iCell.R; iCell != jCell; jCell = jCell.R) {
                jCell.D.U = jCell.U;
                jCell.U.D = jCell.D;
                jCell.C.size--;
            }
        }
    }

    private void uncover(Header head) {
        for (Cell iCell = head.U; iCell != head; iCell = iCell.U)
            for (Cell jCell = iCell.L; jCell != iCell; jCell = jCell.L) {
                jCell.D.U = jCell;
                jCell.U.D = jCell;
                jCell.C.size++;
            }
        head.R.L = head;
        head.L.R = head;
    }

}
