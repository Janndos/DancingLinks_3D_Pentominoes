class Cell{

    public int row;
    public int id;
    public int[][][] shape;
    public Cell L;
    public Cell R;
    public Cell D;
    public Cell U;
    public Header C;
    public Cell(Header header)
    {
        L = this;
        R = this;
        U = this;
        D = this;
        C = header;
        row = -1;
        id = 0;
        // this.name = name;
    }

    public void InsertLeft ( Cell cell)
    {
        cell.L = L;
        L.R = cell;
        L = cell;
        cell.R = this;
    }

    public void InsertUp( Cell cell)
    {
        cell.U = U;
        U.D = cell;
        U = cell;
        cell.D = this;
    }
}
class Header extends Cell {

    public Header(int name) {
        super(null);
        this.name = name;
        size = 0;
        C = this;

    }
    public int name;
    public int size;
    
}
