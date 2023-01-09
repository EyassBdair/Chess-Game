package Piecees;

public class Position {
    private final int X;
    private final int Y;

    public Position(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public Position(String position) {
        this(56 - position.charAt(1), position.charAt(0) - 65);
     /*
       int x = Character.getNumericValue(position.charAt(0))-10;
        int y = Character.getNumericValue(position.charAt(1))-1;
        System.out.println(x + " : "  + y);
        this.X = x;
        this.Y = y;
*/
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

}

