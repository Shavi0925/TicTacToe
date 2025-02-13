import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.*;

public class TicTacToeMain extends Application {
    public static int turn = 1;
    public static Label gameStatus;
    public static void main(String[] args) {
        launch(args);
    } //end main

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Play Tic Tac Toe!");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Label gameStatus = new Label("Player X goes");
        grid.add(gameStatus, 0, 4, 3, 1);

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                    grid.add(createButton(i, j), j, i);
            }
        }

        primaryStage.show();
    }

    public static boolean checkWinner(String[][] pieces, int bRow, int bCol) {
        if (pieces[bRow][0].equals(pieces[bRow][1]) && pieces[bRow][0].equals(pieces[bRow][2])) {
            return true;
        } else if (pieces[0][bCol].equals(pieces[1][bCol]) && pieces[0][bCol].equals(pieces[2][bCol])) {
            return true;
        } else if (pieces[0][0].equals(pieces[1][1]) && pieces[0][0].equals(pieces[2][2])) {
            return true;
        } else if (pieces[0][2].equals(pieces[1][1]) && pieces[0][2].equals(pieces[2][0])) {
            return true;
        } else {
            return false;
        }
    }

    private static Button createButton(int i, int j) {
        //instantiates button objects with the same properties
        Button b = new Button(" ");
        String[][] playerPiece = new String[3][3];
        for (int row = 0; row < playerPiece.length; row++) {
            for (int col = 0; playerPiece[row].length < 3; col++) {
                playerPiece[row][col] = " ";
            }
        }

        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                //implemennt Tic Tac Toe logic
                if (turn % 2 != 0) {
                    gameStatus.setText("Player X goes");
                    b.setText("X");
                    playerPiece[i][j] = "X";
                } else {
                    gameStatus.setText("Player O goes");
                    b.setText("O");
                    playerPiece[i][j] = "O";
                }
                int row = GridPane.getRowIndex(b);
                int col = GridPane.getColumnIndex(b);
                //outputs to the terminal
                System.out.println("Turn = " + turn + " Row = " + row + " Col = " + col);
                b.setDisable(true); //after button is clicked, disable it
                if (checkWinner(playerPiece, i, j)) {
                    gameStatus.setText(playerPiece[i][j] + " Wins! Game over");
                }
                turn++;
            }
        });
        return b;

    }
}