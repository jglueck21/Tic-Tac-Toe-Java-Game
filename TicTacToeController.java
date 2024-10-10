import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 * Die Klasse TicTacToeController steuert die Interaktion zwischen dem Modell (TicTacToeModel)
 * und der Ansicht (TicTacToeView). Sie verwaltet die Spielerzüge, prüft den Spielstatus und
 * steuert die Anzeige von Nachrichten.
 *
 * @author Julian Glück
 * @version 2024-09-28
 */
public class TicTacToeController {
    private TicTacToeModel model; // Referenz auf das Spielmodell
    private TicTacToeView view;   // Referenz auf die grafische Benutzeroberfläche

    // 1. Konstruktor: Verknüpft Model und View, und fügt den Buttons ActionListener hinzu
    public TicTacToeController(TicTacToeModel model, TicTacToeView view) {
        this.model = model;
        this.view = view;

        // Füge den Buttons in der View ActionListener hinzu
        view.addButtonListener(new ButtonClickListener());

        // Füge den Neustart-Button ActionListener hinzu
        view.addResetButtonListener(new ResetButtonListener());

        // Setze das Spielfeld beim Start
        view.updateBoard(model.getBoard());
    }

    // 2. Interne Klasse für das Handling der Button-Klicks
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Identifiziere den Button, der geklickt wurde
            JButton clickedButton = (JButton) e.getSource();

            // Finde die Position des Buttons im Spielfeld
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (view.getButton(i, j) == clickedButton) {
                        handlePlayerMove(i, j); // Verarbeite den Zug des Spielers
                        return;
                    }
                }
            }
        }
    }

    // 3. Methode zur Verarbeitung des Spielerzugs
    private void handlePlayerMove(int row, int col) {
        // Überprüfe, ob der Zug gültig ist
        if (model.makeMove(row, col)) {
            // Aktualisiere das Spielfeld in der View
            view.updateBoard(model.getBoard());

            // Überprüfe, ob jemand gewonnen hat
            if (model.checkWin()) {
                view.displayMessage("Spieler " + model.getCurrentPlayer() + " hat gewonnen!", false);
                view.disableBoard(); // Deaktiviere das Spielfeld
            } else if (model.checkTie()) {
                view.displayMessage("Unentschieden!", true);
                view.disableBoard(); // Deaktiviere das Spielfeld
            } else {
                // Spieler wechseln
                model.switchPlayer(); // Wechsel zu dem anderen Spieler
                view.setCurrentPlayer(model.getCurrentPlayer()); // Aktuellen Spieler anzeigen
            }
        }
    }

    // 4. Methode für den Neustart-Button
    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            resetGame(); // Setze das Spiel zurück
        }
    }

    // 5. Methode zum Neustart des Spiels
    public void resetGame() {
        model.resetGame(); // Setze das Modell zurück
        view.resetBoard(); // Setze die Ansicht zurück
        view.updateBoard(model.getBoard()); // Aktualisiere das Spielfeld
    }
}
