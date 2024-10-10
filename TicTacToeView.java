import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Die Klasse TicTacToeView ist verantwortlich für die grafische Benutzeroberfläche des Tic-Tac-Toe-Spiels.
 * Sie stellt das Spielfeld, Statusmeldungen und Steuerungselemente wie den Neustart-Button bereit.
 *
 * @author Julian Glück
 * @version 2024-09-28
 */
public class TicTacToeView {
    private JFrame frame;                 // Hauptfenster für die Anwendung
    private JButton[][] buttons;          // 3x3 Spielfeld-Buttons
    private JLabel statusLabel;           // Status-Nachricht (Wer hat gewonnen, Unentschieden)
    private JButton resetButton;          // Neustart-Button

    // Konstruktor zur Initialisierung der Benutzeroberfläche
    public TicTacToeView() {
        // 1. Initialisiere das JFrame (Fenster)
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // 2. Erstelle das Spielfeld (3x3 GridLayout)
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        buttons = new JButton[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(""); // Erstelle einen neuen Button
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60)); // Setze Schriftart und Größe
                buttons[i][j].setFocusPainted(false); // Entfernt den Fokusrahmen
                boardPanel.add(buttons[i][j]); // Füge den Button zum Spielfeld hinzu
            }
        }

        // 3. Status Label (oben oder unten) für Spielinformationen
        statusLabel = new JLabel("Spieler X beginnt", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statusLabel.setOpaque(true); // Erlaubt Hintergrundfarben

        // 4. Neustart-Button
        resetButton = new JButton("Neustart");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 16));
        resetButton.setEnabled(false); // Der Button ist am Anfang deaktiviert

        // 5. Füge das Spielfeld und das Status-Label dem Frame hinzu
        frame.add(statusLabel, BorderLayout.NORTH);
        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(resetButton, BorderLayout.SOUTH); // Neustart-Button unten

        // 6. Passe die Größe des Fensters an und zeige es an
        frame.setSize(400, 450); // Platz für den Neustart-Button einplanen
        frame.setVisible(true);
    }

    // 7. Aktualisiere das Spielfeld mit den Werten aus dem Model
    public void updateBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char value = board[i][j];
                buttons[i][j].setText(String.valueOf(value)); // Setze den Text des Buttons

                // Setze die Farbe basierend auf X oder O
                if (value == 'X') {
                    buttons[i][j].setForeground(Color.BLUE); // X ist Blau
                } else if (value == 'O') {
                    buttons[i][j].setForeground(Color.ORANGE); // O ist Orange
                } else {
                    buttons[i][j].setForeground(Color.BLACK); // Leere Felder
                }
            }
        }
    }

    // 8. Zeige eine Nachricht (Gewinner oder Unentschieden) an
    public void displayMessage(String message, boolean isTie) {
        if (isTie) {
            statusLabel.setText("Unentschieden!");
            statusLabel.setForeground(Color.RED); // Unentschieden in Rot
        } else {
            statusLabel.setText(message);
            statusLabel.setForeground(Color.GREEN); // Gewinnnachricht in Grün
        }
        resetButton.setEnabled(true); // Aktiviere den Neustart-Button
    }

    // 9. Füge jedem Spielfeld-Button einen ActionListener hinzu
    public void addButtonListener(ActionListener listener) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].addActionListener(listener); // Füge ActionListener zu jedem Button hinzu
            }
        }
    }

    // 10. Füge einen ActionListener für den Neustart-Button hinzu
    public void addResetButtonListener(ActionListener listener) {
        resetButton.addActionListener(listener); // Füge ActionListener zum Neustart-Button hinzu
    }

    // 11. Deaktiviere das Spielfeld nach einem Gewinn/Unentschieden
    public void disableBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false); // Deaktiviere alle Buttons
            }
        }
    }

    // 12. Setze das Spielfeld für ein neues Spiel zurück
    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(""); // Setze den Text jedes Buttons zurück
                buttons[i][j].setEnabled(true); // Aktiviere die Buttons wieder
            }
        }
        statusLabel.setText("Spieler X beginnt"); // Setze die Status-Nachricht zurück
        statusLabel.setForeground(Color.BLACK); // Standardtextfarbe
        resetButton.setEnabled(false); // Neustart-Button deaktivieren
    }

    // 13. Methode zum Abrufen eines Buttons für den Controller
    public JButton getButton(int row, int col) {
        return buttons[row][col]; // Gibt den Button an der angegebenen Position zurück
    }

    // Setze die aktuelle Spielerinformation im Status-Label
    public void setCurrentPlayer(char player) {
        statusLabel.setText("Spieler " + player + " ist am Zug");
        statusLabel.setForeground(Color.BLACK); // Standardfarbe für den Text
    }
}
