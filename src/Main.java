import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 * Main class that uses main function to start the mainwindow and sets up the fram of the app.
 * 
 * @author Linus Markström
 * @version 2024-10-29
 * 
 */
public class Main {
    public static void main(String[] args) {
        // window object
        
        JFrame window = new JFrame();
        JLayeredPane card = new JLayeredPane();
        Window contentPanel = new Window(card);

        // panel object
        card.setPreferredSize(new Dimension(contentPanel.width, contentPanel.height));
        contentPanel.setBounds(0, 0, contentPanel.width, contentPanel.height);
        MainMenu mainMenu = new MainMenu(contentPanel, window, card);
        contentPanel.addMenu(mainMenu);
        card.add(contentPanel, JLayeredPane.DEFAULT_LAYER);
        
        
        // window setUp
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Crypto Chat App"); // Window title
        window.add(card);
        window.pack();
        window.setLocationRelativeTo(null); // Places the window in the middle of the screen
        window.setVisible(true);
    }
}