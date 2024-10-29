import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Window extends JPanel implements ActionListener{
    public int width = 800;
    public int height = 600;

    private int SERVER_PORT;
    private String SERVER_ADRESS;

    public MainMenu mainMenu;
    private JPanel sessionPanel;

    public STATE state;

	public Window(JLayeredPane card){
        this.state = STATE.MENU;
        
        sessionPanel = new JPanel();

        this.setPreferredSize(new Dimension(width, height));
        this.setFocusable(true);

        sessionPanel.setPreferredSize(new Dimension(this.width, this.height));
        sessionPanel.setFocusable(true);

        card.add(sessionPanel, JLayeredPane.POPUP_LAYER);
    }

    public void addMenu(MainMenu menu){
        this.mainMenu = menu;
    }

    public void startSession(STATE state, String name, int e, int d, String adress, int port) {
        this.state = state;
        this.SERVER_ADRESS = adress;
        this.SERVER_PORT = port;

        this.removeAll();
        this.setLayout(null);

        sessionPanel.setVisible(false);
        JLabel label;
        if (state == STATE.HOST) {
            label = new JLabel("HOST SESSION", SwingConstants.CENTER);
        } else {
            label = new JLabel("CLIENT SESSION", SwingConstants.CENTER);
        }
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(this.width / 2 - 100, 20, 200, 50);

        JButton back = new JButton("BACK TO MENU");
        back.setActionCommand("back");
        back.addActionListener(this);
        back.setBounds(10, 10, 150, 30);

        JTextArea conversationArea = new JTextArea();
        conversationArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(conversationArea);
        scrollPane.setBounds(this.width / 2 - 150, 150, 300, 200);

        JTextField inputField = new JTextField();
        inputField.setBounds(this.width / 2 - 100, 370, 200, 30);

        JButton sendButton = new JButton("SEND");
        sendButton.setActionCommand("send");
        sendButton.addActionListener(this);
        sendButton.setBounds(this.width / 2 + 110, 370, 80, 30);

        this.add(label);
        this.add(back);
        this.add(scrollPane);
        this.add(inputField);
        this.add(sendButton);

        this.setLayout(null); 
        this.setVisible(true);

        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionStr = e.getActionCommand();
        switch (actionStr) {
            case "back":
                System.out.println("back");
                mainMenu.menuPanel.setVisible(true);
                break;
            case "send":
                System.out.println("send");
                break;
            default:
                break;
        }
    }
}
