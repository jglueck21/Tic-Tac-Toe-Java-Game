/**
 * Die Klasse TicTacToeModel repräsentiert das Modell für das Tic-Tac-Toe-Spiel.
 * Sie enthält die Spiellogik, das Spielfeld, den aktuellen Spieler und den Spielstatus.
 *
 * @author Julian Glück
 * @version 2024-09-28
 */
public class TicTacToeModel {
    private char[][] board;         // Das Spielfeld, ein 2D-Array für die X- und O-Zeichen
    private char currentPlayer;     // Der aktuelle Spieler ('X' oder 'O')
    private boolean gameOver;       // Status, ob das Spiel beendet ist

    // Konstruktor, der das Spielfeld initialisiert und den aktuellen Spieler festlegt
    public TicTacToeModel() {
        board = new char[3][3];    // Erstelle ein 3x3 Spielfeld
        currentPlayer = 'X';        // Setze den ersten Spieler auf 'X'
        initializeBoard();          // Initialisiere das Spielfeld
        gameOver = false;           // Setze das Spiel auf nicht beendet
    }

    // Getter für das Spielfeld
    public char[][] getBoard() {
        return board;
    }

    // Getter für den aktuellen Spieler
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    // Initialisiert das Spielfeld mit '-' Zeichen (leere Felder)
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';    // Setze jedes Feld auf '-'
            }
        }
    }

    // Führt einen Zug aus, wenn das Feld leer ist und das Spiel nicht beendet ist
    public boolean makeMove(int row, int col) {
        if(!gameOver && board[row][col] == '-') {
            board[row][col] = currentPlayer; // Setze das Zeichen des aktuellen Spielers
            return true; // Zug erfolgreich
        }
        return false; // Zug fehlgeschlagen
    }

    // Wechselt den aktuellen Spieler
    public void switchPlayer() {
        if(currentPlayer == 'X') {
            currentPlayer = 'O'; // Wechsel zu Spieler 'O'
        }
        else {
            currentPlayer = 'X'; // Wechsel zu Spieler 'X'
        }
    }

    // Überprüft, ob der aktuelle Spieler gewonnen hat
    public boolean checkWin() {
        // Horizontale Überprüfung
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                gameOver = true; // Spiel ist beendet
                return true; // Spieler hat gewonnen
            }
        }
        // Vertikale Überprüfung
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                gameOver = true; // Spiel ist beendet
                return true; // Spieler hat gewonnen
            }
        }
        // Diagonale Überprüfung
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            gameOver = true; // Spiel ist beendet
            return true; // Spieler hat gewonnen
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            gameOver = true; // Spiel ist beendet
            return true; // Spieler hat gewonnen
        }
        return false; // Kein Gewinner
    }

    // Überprüft, ob das Spiel unentschieden ist
    public boolean checkTie() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false; // Es gibt noch leere Felder
                }
            }
        }
        gameOver = true; // Spiel ist beendet
        return true; // Unentschieden
    }

    // Setzt das Spiel zurück und initialisiert das Spielfeld erneut
    public void resetGame() {
        initializeBoard(); // Initialisiere das Spielfeld
        currentPlayer = 'X'; // Setze den aktuellen Spieler auf 'X'
        gameOver = false; // Setze das Spiel auf nicht beendet
    }
}
