package me.friedhof.chess.util;

import net.minecraft.util.math.Direction;

public class RotationCalculations {




    public static int correctRotations(int itemRotation, Direction from, Direction to){

        return (itemRotation + correctRotationsToAdd(from, to)) % 8;

    }


    //für außenkanten
    private static int correctRotationsToAdd(Direction from, Direction to){

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
            case UP:

                n = alignRotationToItem(forwardNorth,data.itemRotation,true);

                break;
            case DOWN:

                n = alignRotationToItem(forwardNorth,data.itemRotation,true);

                switch(n){
                    case NORTH:
                        n = Direction.SOUTH;
                        break;
                    case SOUTH:
                        n = Direction.NORTH;
                        break;
                    case WEST:
                        n = Direction.WEST;
                        break;
                    case EAST:
                        n = Direction.EAST;
                        break;
                    default:
                        break;


                }




                break;
            case NORTH:
                n = alignRotationToItem(forwardNorth,data.itemRotation,true);

                switch(n){
                    case NORTH:
                        n = Direction.UP;
                        break;
                    case SOUTH:
                        n = Direction.DOWN;
                        break;
                    case WEST:
                        n = Direction.EAST;
                        break;
                    case EAST:
                        n = Direction.WEST;
                        break;
                    default:
                        break;


                }


                break;
            case SOUTH:
                n = alignRotationToItem(forwardNorth,data.itemRotation,true);

                switch(n){
                    case NORTH:
                        n = Direction.UP;
                        break;
                    case SOUTH:
                        n = Direction.DOWN;
                        break;
                    case WEST:
                        n = Direction.WEST;
                        break;
                    case EAST:
                        n = Direction.EAST;
                        break;
                    default:
                        break;


                }



                break;
            case EAST:
                n = alignRotationToItem(forwardNorth, data.itemRotation,true);

                switch(n){
                    case NORTH:
                        n = Direction.UP;
                        break;
                    case SOUTH:
                        n = Direction.DOWN;
                        break;
                    case WEST:
                        n = Direction.SOUTH;
                        break;
                    case EAST:
                        n = Direction.NORTH;
                        break;
                    default:
                        break;


                }



                break;
            case WEST:
                n = alignRotationToItem(forwardNorth, data.itemRotation,true);

                switch(n){
                    case NORTH:
                        n = Direction.UP;
                        break;
                    case SOUTH:
                        n = Direction.DOWN;
                        break;
                    case WEST:
                        n = Direction.NORTH;
                        break;
                    case EAST:
                        n = Direction.SOUTH;
                        break;
                    default:
                        break;

                }

                break;
            default:
                System.out.println("Direction of Chesspiece not defined");

        }
        return n;
    }

    private static Direction alignRotationToItem(Direction n, int itemRotation, boolean clockwise) {

        if(clockwise) {
            for (int i = 0; i < itemRotation / 2; i++) {
                n = n.rotateClockwise(Direction.Axis.Y);
            }
        }else{
            for (int i = 0; i < itemRotation / 2; i++) {
                n = n.rotateCounterclockwise(Direction.Axis.Y);
            }
        }





        return n;
    }
}
