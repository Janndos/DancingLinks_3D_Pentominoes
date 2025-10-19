public class PieceInfo {
    int id;
    public int parcelID;
    public int[][][] piece;
    public int x;
    public int y;
    public int z;
    public int value;

    public PieceInfo(int id, int x, int y, int z, int parcelID, int[][][] piece, int value) 
    {
        
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.parcelID = parcelID;
        this.piece = piece;
        this.value = value;

    }

}
