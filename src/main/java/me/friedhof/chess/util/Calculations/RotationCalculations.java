package me.friedhof.chess.util.Calculations;

import me.friedhof.chess.util.GlobalChessData;
import net.minecraft.util.math.Direction;

public class RotationCalculations {




    public static int correctRotationsOuterEdges(int itemRotation, Direction from, Direction to){

        return (itemRotation + correctRotationsToAddOuterEdges(from, to)) % 8;

    }


    public static int correctRotationsInnerEdges(int itemRotation, Direction from, Direction to){

        return (itemRotation + correctRotationsToAddInnerEdges(from, to)) % 8;

    }




    //für außenkanten
    private static int correctRotationsToAddOuterEdges(Direction from, Direction to){

        int[][] mapping = new int[6][6];

        mapping[0][2] = 4;
        mapping[2][0] = 4;
        mapping[0][3]= 0;
        mapping[3][0] = 0;
        mapping[1][2] = 4;
        mapping[2][1]= 4;
        mapping[1][3]= 0;
        mapping[3][1] = 0;

        mapping[2][5] = 0;
        mapping[5][2] = 0;
        mapping[2][4]= 0;
        mapping[4][2] = 0;
        mapping[3][4] = 0;
        mapping[4][3] = 0;
        mapping[3][5] = 0;
        mapping[5][3]= 0;

        mapping[5][0] = 2;
        mapping[0][5] = 6;
        mapping[5][1] = 6;
        mapping[1][5] = 2;

        mapping[4][0] = 6;
        mapping[0][4] = 2;
        mapping[4][1] = 2;
        mapping[1][4] = 6;

        return mapping[from.getId()][to.getId()];

    }



    private static int correctRotationsToAddInnerEdges(Direction from, Direction to){

        int[][] mapping = new int[6][6];

        mapping[0][2] = 4;
        mapping[2][0] = 4;
        mapping[0][3]= 0;
        mapping[3][0] = 0;
        mapping[1][2] = 4;
        mapping[2][1]= 4;
        mapping[1][3]= 0;
        mapping[3][1] = 0;

        mapping[2][5] = 0;
        mapping[5][2] = 0;
        mapping[2][4]= 0;
        mapping[4][2] = 0;
        mapping[3][4] = 0;
        mapping[4][3] = 0;
        mapping[3][5] = 0;
        mapping[5][3]= 0;

        mapping[5][0] = 2;
        mapping[0][5] = 6;
        mapping[5][1] = 6;
        mapping[1][5] = 2;

        mapping[4][0] = 6;
        mapping[0][4] = 2;
        mapping[4][1] = 2;
        mapping[1][4] = 6;

        return mapping[from.getId()][to.getId()];

    }
    public static Direction relativeToAbsolute(GlobalChessData data, Direction forwardNorth){
        Direction n = Direction.NORTH;
        switch (data.directionWall) {
            case UP -> n = alignRotationToItem(forwardNorth, data.itemRotation);
            case DOWN -> {
                n = alignRotationToItem(forwardNorth, data.itemRotation);
                switch (n) {
                    case NORTH -> n = Direction.SOUTH;
                    case SOUTH -> n = Direction.NORTH;
                    case WEST -> n = Direction.WEST;
                    case EAST -> n = Direction.EAST;
                    default -> {
                    }
                }
            }
            case NORTH -> {
                n = alignRotationToItem(forwardNorth, data.itemRotation);
                switch (n) {
                    case NORTH -> n = Direction.UP;
                    case SOUTH -> n = Direction.DOWN;
                    case WEST -> n = Direction.EAST;
                    case EAST -> n = Direction.WEST;
                    default -> {
                    }
                }
            }
            case SOUTH -> {
                n = alignRotationToItem(forwardNorth, data.itemRotation);
                switch (n) {
                    case NORTH -> n = Direction.UP;
                    case SOUTH -> n = Direction.DOWN;
                    case WEST -> n = Direction.WEST;
                    case EAST -> n = Direction.EAST;
                    default -> {
                    }
                }
            }
            case EAST -> {
                n = alignRotationToItem(forwardNorth, data.itemRotation);
                switch (n) {
                    case NORTH -> n = Direction.UP;
                    case SOUTH -> n = Direction.DOWN;
                    case WEST -> n = Direction.SOUTH;
                    case EAST -> n = Direction.NORTH;
                    default -> {
                    }
                }
            }
            case WEST -> {
                n = alignRotationToItem(forwardNorth, data.itemRotation);
                switch (n) {
                    case NORTH -> n = Direction.UP;
                    case SOUTH -> n = Direction.DOWN;
                    case WEST -> n = Direction.NORTH;
                    case EAST -> n = Direction.SOUTH;
                    default -> {
                    }
                }
            }
            default -> System.out.println("Direction of Chesspiece not defined");
        }
        return n;
    }

    private static Direction alignRotationToItem(Direction n, int itemRotation) {

        for (int i = 0; i < itemRotation / 2; i++) {
            n = n.rotateClockwise(Direction.Axis.Y);
        }

        return n;
    }
}
