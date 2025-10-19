import java.util.*;

public class Search {

    public int length = 33; //16.5 * 2
    public int height = 8; // 4 * 2
    public int width = 5; // 2.5 * 2

    public static String[] Parcels = {"A", "C", "B"};
    public static String[] Pentominos = {"T", "P", "L"};

    Dance algorithmX = new Dance(length * width * height);

    public static List<PieceInfo> pieceInfo = new ArrayList<>();

    public static void main(String[] args) {
        Search search = new Search();
        search.start();
    }

    public void start()
    {
        int counter = 0;
        for(int i = 0; i < Pentominos.length; i++)
        {
            String type = Pentominos[i]; //Change Pentominos to Parcels if wanna use ABC, just here, for the beauty change on the "for" above(just there)
            for(int j = 0; j < DataBase.getRotationNumber(type); j++)
            {
                int[][][] piece = DataBase.getRotation(type, j);
                for(int z = 0; z < length; z++)
                {
                    for(int y = 0; y < height; y++)
                    {
                        for(int x = 0; x < width; x++)
                        {
                            if(!canPlace(x, y, z, piece))
                            {
                                continue;
                            }

                            ArrayList<Integer> listX = getXCells(piece, x);
                            ArrayList<Integer> listY = getYCells(piece, y);
                            ArrayList<Integer> listZ = getZCells(piece, z);
                            int[] coordsLibrary;
                            
                            if(type == "L" || type == "P" || type == "T")
                            {
                                coordsLibrary = new int[5];
                            }
                            else{
                                    coordsLibrary = new int[piece.length * piece[0].length * piece[0][0].length];

                            }
                            for(int t = 0; t < coordsLibrary.length; t++)
                                {
                                    coordsLibrary[t] = length * height * listX.get(t) + length * listY.get(t) + listZ.get(t);
                                }
                            pieceInfo.add(new PieceInfo(counter, x, y, z, DataBase.getId(type), piece, DataBase.getValue(type)));
                            algorithmX.AddRow(counter, DataBase.getId(type), coordsLibrary, piece);


                            counter++;

                            System.out.println(Arrays.toString(coordsLibrary));
                        }
                        }
                    }
                }
            }
            algorithmX.dance(0);
        }

    public boolean canPlace(int x, int y, int z, int[][][] piece) {

        int shapeWidth = piece[0][0].length;
        int shapeHeight = piece[0].length;
        int shapeDepth = piece.length;
    
        if(x + shapeWidth > width){
            return false;
        }
    
        if(y + shapeHeight > height){
            return false;
        }
    
        return z + shapeDepth <= length;
    }

    public ArrayList<Integer> getXCells(int[][][] piece, int x)
    {
        ArrayList<Integer> listX = new ArrayList<>();
        for(int i = 0; i < piece.length; i++)
        {
            for(int j = 0; j < piece[0].length; j++)
            {
                for(int k = 0; k < piece[0][0].length; k++)
                {
                    if(piece[i][j][k] != 0)
                    {
                        listX.add(k + x);
                    }
                }
            }
        }
        return listX;
    }
    public ArrayList<Integer> getYCells(int[][][] piece, int y)
    {
        ArrayList<Integer> listY = new ArrayList<>();
        for(int i = 0; i < piece.length; i++)
        {
            for(int j = 0; j < piece[0].length; j++)
            {
                for(int k = 0; k < piece[0][0].length; k++)
                {
                    if(piece[i][j][k] != 0)
                    {
                        listY.add(j + y);
                    }
                }
            }
        }
        return listY;
    }
    public ArrayList<Integer> getZCells(int[][][] piece, int z)
    {
        ArrayList<Integer> listZ = new ArrayList<>();
        for(int i = 0; i < piece.length; i++)
        {
            for(int j = 0; j < piece[0].length; j++)
            {
                for(int k = 0; k < piece[0][0].length; k++)
                {
                    if(piece[i][j][k] != 0)
                    {
                        listZ.add(i + z);
                    }
                }
            }
        }
        return listZ;
    }
}
