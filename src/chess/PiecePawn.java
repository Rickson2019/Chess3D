package chess;

import java.util.ArrayList;

import javax.sound.midi.Soundbank;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Chess PiecePawn is a extended class of Piece.
 * 
 * @author Judao Zhong
 *
 */
public class PiecePawn extends Piece {
    private Image image;

//    private Tile initSQR=getTile();
    public double xPos;
    public double yPos;
//    private static final double  leftGridValues = 1;
//    private static final double  rightGridValues = 75.02;
//    private static final double  upGridValues = 1;
//    private static final double  downGridValues = 75.02;
    private ImageView iv;

    public PiecePawn(Player type, int x, int y) {
        super(type, x, y);
        String img;
        if (type.getColour() == Color.WHITE) {
            // image = new Image("file:src/ChessPiece/White_Bishop.png");
            img = "file:src/ChessPiece/White_Pawn.png";
            // ImageView imageView = new ImageView();
            // imageView.setImage(image);
            // setSquare(initSQR);
//            imageView.fitHeightProperty();
//            imageView.fitWidthProperty();
//            imageView.setPreserveRatio(true);
//            imageView.setSmooth(true);
//            imageView.setCache(true);

            xPos = x * square.SQRsize + 1;
            yPos = y * square.SQRsize + 1;
        } else {
            img = "file:src/ChessPiece/Black_Pawn.png";
            // ImageView imageView = new ImageView();
            // imageView.setImage(image);
//            imageView.fitHeightProperty();
//            imageView.fitWidthProperty();
//            imageView.setPreserveRatio(true);
//            imageView.setSmooth(true);
//            imageView.setCache(true);

            xPos = x * square.SQRsize;
            yPos = y * square.SQRsize;
        }
        this.image = new Image(img);
        setIv(new ImageView(image));
//        imageView.setImage(image);
//        imageView.fitHeightProperty();
//        imageView.fitWidthProperty();
//        imageView.setPreserveRatio(true);
//        imageView.setSmooth(true);
//        imageView.setCache(true);
//        iv.setX(getSquare().getX()*80);
        getIv().setY(yPos);
        getIv().setX(xPos);
        getChildren().add(getIv());
    }

    @Override
//    public boolean validMove() {
//        // TODO Auto-generated method stub
//        return true;
//    }
    public void move(int SQR_X, int SQR_Y,Board board) {
        System.out.println("In Move()");
        if(board.boardLv == 1) {
            iv.setX(SQR_X * Board.SQRsize+0.1);
            iv.setY(SQR_Y * Board.SQRsize+0.1);
        }
        if(board.boardLv ==2) {
            System.out.println("yidongzhong");
            iv.setX(SQR_X * Board.SQRsize + Game.xCoor_Board2L+0.1);
            iv.setY(SQR_Y * Board.SQRsize+0.1);
        }
        if(board.boardLv ==3) {
            System.out.println("yidongzhong");
            iv.setX(SQR_X * Board.SQRsize + Game.xCoor_Board3L+0.1);
            iv.setY(SQR_Y * Board.SQRsize+0.1);
        }
    }

    @Override
    public ArrayList<Square> movePattern(Player player, Square thisSquare, Board board) {
        ArrayList<Square> availableSquares = new ArrayList<>();
        int i = 0;
        // Black Player

        if (player.getColour() == Color.BLACK && thisSquare.returnPiece()!= null) {
            int XcolNum = thisSquare.getSQR_X();
            int YrowNum = thisSquare.getSQR_Y();
            // X direction right take out X+1 Y+1
            System.out.println("X" + XcolNum + "Y " +YrowNum + "============");
            if (XcolNum!=7  &&
                    board.getSquare(XcolNum + 1, YrowNum + 1).isOccupied
                    && board.getSquare(XcolNum + 1, YrowNum + 1)
                            .returnPiece().player.colour != thisSquare.returnPiece().player.colour) {
                availableSquares.add(board.getSquare(XcolNum + 1, YrowNum + 1));
                availableSquares.get(i).setSQR_X(XcolNum + 1);
                availableSquares.get(i).setSQR_Y(YrowNum + 1);
                i++;
            }

            // X direction take out left
            System.out.println("X" + XcolNum + "Y " +YrowNum + "=====--------------=======");
            if ( XcolNum!=0 && board.getSquare(XcolNum - 1, YrowNum + 1) !=null &&
                    board.getSquare(XcolNum - 1, YrowNum + 1).isOccupied
                    && board.getSquare(XcolNum - 1, YrowNum + 1)
                            .returnPiece().player.colour != thisSquare.returnPiece().player.colour) {
                availableSquares.add(board.getSquare(XcolNum - 1, YrowNum + 1));
                availableSquares.get(i).setSQR_X(XcolNum - 1);
                availableSquares.get(i).setSQR_Y(YrowNum + 1);
                i++;
            }

            // Specific move at the start
            if (thisSquare.getSQR_Y() == 1) {
                // MOVE ONE STEP
                if (!board.getSquare(thisSquare.getSQR_X(), thisSquare.getSQR_Y() + 1).isOccupied) {
                    availableSquares.add(board.getSquare(thisSquare.getSQR_X(), thisSquare.getSQR_Y() + 1));
                    availableSquares.get(i).setSQR_X(thisSquare.getSQR_X());
                    availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y() + 1);
                    i++;
                }

                if (!board.getSquare(thisSquare.getSQR_X(), thisSquare.getSQR_Y() + 1).isOccupied
                        && !board.getSquare(thisSquare.getSQR_X(), thisSquare.getSQR_Y() + 2).isOccupied) {
                    // MOVE TWO STEPS
                    availableSquares.add(board.getSquare(thisSquare.getSQR_X(), thisSquare.getSQR_Y() + 2));
                    availableSquares.get(i).setSQR_X(thisSquare.getSQR_X());
                    availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y() + 2);
                    i++;
                }
            }

            if (thisSquare.getSQR_Y() >= 2 && thisSquare.getSQR_Y() < 8) {
                // Y direction Can only move once (Move Down 1 step)
                if (!board.getSquare(thisSquare.getSQR_X(), thisSquare.getSQR_Y() + 1).isOccupied()) {
                    availableSquares.add(board.getSquare(thisSquare.getSQR_X(), thisSquare.getSQR_Y() + 1));
                    availableSquares.get(i).setSQR_X(thisSquare.getSQR_X());
                    availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y() + 1);
                    i++;
                }

                // Move either left or right only if it is at a grid X>=4 && X<=7
                if (thisSquare.getSQR_Y() >= 4 && thisSquare.getSQR_Y() < 8) {

                    // Move Left
                    if (!board.getSquare(thisSquare.getSQR_X() - 1, thisSquare.getSQR_Y()).isOccupied()) {
                        availableSquares.add(board.getSquare(thisSquare.getSQR_X() - 1, thisSquare.getSQR_Y()));
                        availableSquares.get(i).setSQR_X(thisSquare.getSQR_X() - 1);
                        availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y());
                        i++;
                    }

                    // Move Right
                    if (!board.getSquare(thisSquare.getSQR_X() + 1, thisSquare.getSQR_Y()).isOccupied()) {
                        availableSquares.add(board.getSquare(thisSquare.getSQR_X() + 1, thisSquare.getSQR_Y()));
                        availableSquares.get(i).setSQR_X(thisSquare.getSQR_X() + 1);
                        availableSquares.get(i).setSQR_Y(thisSquare.getSQR_Y());
                        i++;
                    }
                }

            }

        }
        // White Player

        else if (player.getColour() == Color.WHITE && thisSquare.returnPiece()!= null) {
            int XcolNum = thisSquare.getSQR_X();
            int YrowNum = thisSquare.getSQR_Y();
            // take out enemy pieces
            // Take out the One on the Right
            try {
                if (XcolNum !=7 && board.getSquare(XcolNum + 1, YrowNum - 1).isOccupied
                        && board.getSquare(XcolNum + 1, YrowNum - 1)
                                .returnPiece().player.colour != thisSquare.returnPiece().player.colour) {
                    availableSquares.add(board.getSquare(XcolNum + 1, YrowNum - 1));
                    // right
                    availableSquares.get(i).setSQR_X(XcolNum + 1);
                    // up
                    availableSquares.get(i).setSQR_Y(YrowNum - 1);
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("some may be out of bound");
            }
            // Take out the One on the Left
            try {
                if (XcolNum !=0 && board.getSquare(XcolNum - 1, YrowNum - 1)!=null &&
                        board.getSquare(XcolNum - 1, YrowNum - 1).isOccupied
                        && board.getSquare(XcolNum - 1, YrowNum - 1)
                                .returnPiece().player.colour != thisSquare.returnPiece().player.colour) {
                    availableSquares.add(board.getSquare(XcolNum - 1, YrowNum - 1));
                    availableSquares.get(i).setSQR_X(XcolNum - 1);
                    availableSquares.get(i).setSQR_Y(YrowNum - 1);
                    i++;
                }
            } catch (Exception e) {
                System.out.println("some may be out of bound");
                e.printStackTrace();
            }

            // Move either two grids or just one for the first movement
            if (YrowNum == 6) {
                // Move 2 grids
                if (!board.getSquare(XcolNum, YrowNum - 2).isOccupied
                        && !board.getSquare(XcolNum, YrowNum - 1).isOccupied) {
                    availableSquares.add(board.getSquare(XcolNum, YrowNum - 2));
                    availableSquares.get(i).setSQR_X(XcolNum);
                    availableSquares.get(i).setSQR_Y(YrowNum - 2);
                    i++;
                }

                if (!board.getSquare(XcolNum, YrowNum - 1).isOccupied) {
                    // Move 1 grid
                    availableSquares.add(board.getSquare(XcolNum, YrowNum - 1));
                    availableSquares.get(i).setSQR_X(XcolNum);
                    availableSquares.get(i).setSQR_Y(YrowNum - 1);
                    i++;
                }
            }

            // Move only one grid forward after the first move
            if (YrowNum > 0) {
                
                if (!board.getSquare(XcolNum, YrowNum - 1).isOccupied) {
                    availableSquares.add(board.getSquare(XcolNum, YrowNum - 1));
                    availableSquares.get(i).setSQR_X(XcolNum);
                    availableSquares.get(i).setSQR_Y(YrowNum - 1);
                    i++;
                }

                // Move either left or right
                if (YrowNum <= 4) {
                    // Move Left
                    if (!board.getSquare(XcolNum - 1, YrowNum).isOccupied()) {
                        availableSquares.add(board.getSquare(XcolNum - 1, YrowNum));
                        availableSquares.get(i).setSQR_X(XcolNum - 1);
                        availableSquares.get(i).setSQR_Y(YrowNum);
                        i++;
                    }

                    // Move Right
                    if (!board.getSquare(XcolNum + 1, YrowNum).isOccupied()) {
                        availableSquares.add(board.getSquare(XcolNum + 1, YrowNum));
                        availableSquares.get(i).setSQR_X(XcolNum + 1);
                        availableSquares.get(i).setSQR_Y(YrowNum);
                        i++;
                    }
                }

            }
        }

        return availableSquares;
    }

    public void setCaptured() {
        setSquare(null);

        if (getOwner().getColour() == Color.WHITE) {
            getIv().setX(30);
            getIv().setY(510);
        } else {
            getIv().setX(40);
            getIv().setY(510);
        }

    }

    public ImageView getIv() {
        return iv;
    }

    public void setIv(ImageView iv) {
        this.iv = iv;
    }

//    @Override
//    public ArrayList<Square> movePattern(Player player, Square thisSquare,Board board) {
//        ArrayList<Square> availableSquares = new ArrayList<>();
//        int i = 0;
//        if(player.getColour()==Color.BLACK) {
//            if(thisSquare.getRow()<4) {
//                availableSquares.add(board.getSquare(thisSquare.getX(),thisSquare.getY()));
//            }
//    }
//        return availableSquares;
//    
}
