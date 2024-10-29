import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainMenu implements ActionListener{
    private JFrame window;
    private Window panel;
    private STATE state;
    public JPanel menuPanel;

    public JComponent mainPage;
    public JComponent settingPage;

    private JTextField nameInput;
    private JTextField encryptKeyInput;
    private JTextField decryptKeyInput;
    private JTextField addressInput;
    private JTextField portInput;

    public MainMenu(Window panel, JFrame window, JLayeredPane card){
        menuPanel = new JPanel();
        this.panel = panel;
        this.window = window;
        
        // main menu
        menuPanel.setPreferredSize(new Dimension(panel.width, panel.height));
        menuPanel.setFocusable(true);

        mainPage = setMainPage();
        settingPage = new JLayeredPane();

        mainPage.setFocusable(true);

        menuPanel.add(mainPage);
        menuPanel.add(settingPage);
        settingPage.setVisible(false);
        menuPanel.setBounds(0, 0, panel.width, panel.height);
        

        // Add menu to window
        card.add(menuPanel, JLayeredPane.POPUP_LAYER);
    }

    private void drawSettingPage() {
        settingPage.removeAll();
        // Layers
        settingPage.setPreferredSize(new Dimension(panel.width, panel.height));
        
        // Input for Username
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(panel.width / 2 - 150, 50, 100, 30);
        nameInput = new JTextField();
        nameInput.setBounds(panel.width / 2 - 150, 80, 300, 30);
    
        // Input for Encryption Key
        JLabel encryptKeyLabel = new JLabel("Encryption Key:");
        encryptKeyLabel.setBounds(panel.width / 2 - 150, 120, 100, 30);
        encryptKeyInput = new JTextField();
        encryptKeyInput.setBounds(panel.width / 2 - 150, 150, 300, 30);
    
        // Input for Decryption Key
        JLabel decryptKeyLabel = new JLabel("Decryption Key:");
        decryptKeyLabel.setBounds(panel.width / 2 - 150, 190, 100, 30);
        decryptKeyInput = new JTextField();
        decryptKeyInput.setBounds(panel.width / 2 - 150, 220, 300, 30);
        
        // Input for Address
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(panel.width / 2 - 150, 260, 100, 30);
        addressInput = new JTextField();
        addressInput.setBounds(panel.width / 2 - 150, 290, 300, 30);
    
        // Input for Port
        JLabel portLabel = new JLabel("Port:");
        portLabel.setBounds(panel.width / 2 - 150, 330, 100, 30);
        portInput = new JTextField();
        portInput.setBounds(panel.width / 2 - 150, 360, 300, 30);
    
        JButton start;
        if(state == STATE.HOST){
            start = new JButton("START SESSION");
            start.setActionCommand("start");
            start.addActionListener(this);
            start.setBounds(panel.width / 2 - 125, 400, 250, 50);
        }else{
            start = new JButton("CONNECT TO SESSION");
            start.setActionCommand("start");
            start.addActionListener(this);
            start.setBounds(panel.width / 2 - 125, 400, 250, 50);
        }
        
        // Exit button
        JButton back = new JButton("BACK");
        back.setActionCommand("back");
        back.addActionListener(this);
        back.setBounds(panel.width / 2 - 100, 460, 200, 50);
        
        // Add components to layers
        settingPage.add(nameLabel, JLayeredPane.PALETTE_LAYER);
        settingPage.add(nameInput, JLayeredPane.PALETTE_LAYER);
        settingPage.add(encryptKeyLabel, JLayeredPane.PALETTE_LAYER);
        settingPage.add(encryptKeyInput, JLayeredPane.PALETTE_LAYER);
        settingPage.add(decryptKeyLabel, JLayeredPane.PALETTE_LAYER);
        settingPage.add(decryptKeyInput, JLayeredPane.PALETTE_LAYER);
        settingPage.add(addressLabel, JLayeredPane.PALETTE_LAYER);
        settingPage.add(addressInput, JLayeredPane.PALETTE_LAYER);
        settingPage.add(portLabel, JLayeredPane.PALETTE_LAYER);
        settingPage.add(portInput, JLayeredPane.PALETTE_LAYER);
        settingPage.add(start, JLayeredPane.PALETTE_LAYER);
        settingPage.add(back, JLayeredPane.PALETTE_LAYER);

        settingPage.setVisible(true);
    }

    private JComponent setMainPage(){
        // Layers
        JLayeredPane layers = new JLayeredPane();
        layers.removeAll();
        layers.setPreferredSize(new Dimension(panel.width, panel.height));
        
        // Start game button
        JButton startNewGame = new JButton("START HOST");
        startNewGame.setActionCommand("host");
        startNewGame.addActionListener(this);
        startNewGame.setBounds(panel.width/2 - 100, 300, 200, 50);

        // Load saved game
        JButton loadGame = new JButton("CONNECT TO HOST");
        loadGame.setActionCommand("client");
        loadGame.addActionListener(this);
        loadGame.setBounds(panel.width/2 - 100, 350, 200, 50);

        // Exit
        JButton exit = new JButton("EXIT");
        exit.setActionCommand("exit");
        exit.addActionListener(this);
        exit.setBounds(panel.width/2 - 100, 400, 200, 50);
        
        layers.add(startNewGame, JLayeredPane.POPUP_LAYER);
        layers.add(loadGame, JLayeredPane.POPUP_LAYER);
        layers.add(exit, JLayeredPane.POPUP_LAYER);
        layers.setVisible(true);
        
        return layers;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionStr = e.getActionCommand();
        switch (actionStr) {
            case "host":
                System.out.println("host");
                drawSettingPage();
                mainPage.setVisible(false);
                settingPage.setVisible(true);
                state = STATE.HOST;
                break;
            case "client":
                System.out.println("client");
                drawSettingPage();
                mainPage.setVisible(false);
                settingPage.setVisible(true);
                state = STATE.CLIENT;
                break;
            case "start":
                System.out.println("start session!");

                String name = nameInput.getText().trim();
                String d = decryptKeyInput.getText().trim();
                String eKey = encryptKeyInput.getText().trim(); 
                String address = addressInput.getText().trim(); 
                String port = portInput.getText().trim();

                if (name.isEmpty()) {
                    showMessage("Name cannot be empty!");
                    return;
                }
                if (eKey.isEmpty() || !isInteger(eKey)) {
                    showMessage("Encryption Key must be a valid integer!");
                    return;
                }
                if (d.isEmpty() || !isInteger(d)) {
                    showMessage("Decryption Key must be a valid integer!");
                    return;
                }
                if (address.isEmpty()) {
                    showMessage("Address cannot be empty!");
                    return;
                }
                if (port.isEmpty() || !isInteger(port)) {
                    showMessage("Port must be a valid integer!");
                    return;
                }

                menuPanel.setVisible(false);
                panel.startSession(state, name, Integer.valueOf(eKey), Integer.valueOf(d), address, Integer.valueOf(port));
                break;
            case "back":
                System.out.println("back");
                mainPage.setVisible(true);
                settingPage.setVisible(false);
                break;
            case "exit":
                System.out.println("exit");
                window.dispose();
                window.setVisible(false);
                System.exit(0);
            default:
                break;
        }
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(window, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
}
