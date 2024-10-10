/**
 * Die Klasse TicTacToeApp ist der Einstiegspunkt für das Tic-Tac-Toe-Spiel.
 * Sie initialisiert das Modell, die Ansicht und den Controller,
 * um die Spielinteraktionen zu ermöglichen.
 *
 * @author Julian Glück
 * @version 2024-09-28
 */
public class TicTacToeApp {
    public static void main(String[] args) {
        // Erstelle eine Instanz des Modells für das Tic-Tac-Toe-Spiel
        TicTacToeModel model = new TicTacToeModel();

        // Erstelle eine Instanz der Ansicht für das Tic-Tac-Toe-Spiel
        TicTacToeView view = new TicTacToeView();

        // Erstelle eine Instanz des Controllers, der Modell und Ansicht verbindet
        TicTacToeController controller = new TicTacToeController(model, view);
    }
}
