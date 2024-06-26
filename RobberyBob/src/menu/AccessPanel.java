package menu;

import display.SubPanels;
import sound.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.UUID;

public class AccessPanel extends JPanel {
    SoundManager soundManager;
    UserValidation userValidation;
    Frame mainFrame;
    MenuPanel menuPanel;
    SubPanels subPanels;
    JLabel loginButton, registerButton, resetButton, loginBG;
    JLabel usernameLabel, passwordLabel;
    public JTextField usernameField;
    public JPasswordField passwordField;
    ImageIcon loginBGImg;
    public String playerInUse;

    public AccessPanel(Frame mainFrame, SubPanels subPanels, SoundManager soundManager) {
        this.mainFrame = mainFrame;
        this.subPanels = subPanels;
        this.soundManager = soundManager;
        mainFrame.frame.setVisible(true);
        userValidation = new UserValidation();
        menuPanel = new MenuPanel(mainFrame, subPanels, this, soundManager);

        this.setSize(mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
        this.setLayout(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        Dimension textFieldSize = new Dimension((int) (screenWidth/2.9), (int) (screenHeight*.055));
        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds((((int) screenWidth/2))-(textFieldSize.width/2),(int) (screenHeight/4) , textFieldSize.width, textFieldSize.height);
        Font userNameText = new Font("DePixel", Font.BOLD, 25);
        usernameLabel.setFont(userNameText);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setMinimumSize(textFieldSize);
        usernameLabel.setMaximumSize(textFieldSize);
        this.add(usernameLabel);

        usernameField = new JTextField("admin");
        usernameField.setBounds((((int) screenWidth/2))-(textFieldSize.width/2),(int) (screenHeight/3.2), textFieldSize.width, textFieldSize.height);
        usernameField.setFont(new Font("DePixel", Font.PLAIN, 22));
        usernameField.setHorizontalAlignment(JTextField.LEFT);
        usernameField.setMinimumSize(textFieldSize);
        usernameField.setMaximumSize(textFieldSize);
        this.add(usernameField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds((((int) screenWidth/2))-(textFieldSize.width/2),(int) (screenHeight/2.5), textFieldSize.width, textFieldSize.height);
        Font passwordText = new Font("DePixel", Font.BOLD, 25);
        passwordLabel.setFont(passwordText);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setMinimumSize(textFieldSize);
        passwordLabel.setMaximumSize(textFieldSize);
        this.add(passwordLabel);

        passwordField = new JPasswordField("admin");
        passwordField.setBounds((((int) screenWidth/2))-(textFieldSize.width/2),(int) (screenHeight/2.2), textFieldSize.width, textFieldSize.height);
        passwordField.setFont(new Font("DePixel", Font.PLAIN, 22));
        passwordField.setHorizontalAlignment(JTextField.LEFT);
        passwordField.setMinimumSize(textFieldSize);
        passwordField.setMaximumSize(textFieldSize);
        this.add(passwordField);

        double buttonLabelWidth = screenWidth/7.5;
        double buttonLabelHeight = screenHeight/7.5;
        double y = screenHeight/1.9;

        loginButton = new JLabel();
        Image loginButtonNC = new ImageIcon("RobberyBob/resources/images/AccessPanel/loginNotClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        Image loginButtonC = new ImageIcon("RobberyBob/resources/images/AccessPanel/loginClicked.png"
        )   .getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        loginButton.setIcon(new ImageIcon(loginButtonNC));
        loginButton.setBounds((int) ((screenWidth-buttonLabelWidth)/2)-150, (int) y, (int) buttonLabelWidth, (int)buttonLabelHeight);
        loginButton.setMaximumSize(new Dimension((int) buttonLabelWidth, (int) buttonLabelHeight));
        loginButton.setMinimumSize(new Dimension((int) buttonLabelWidth, (int) buttonLabelHeight));

        resetButton = new JLabel();
        Image resetButtonNC = new ImageIcon("RobberyBob/resources/images/AccessPanel/resetNotClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        Image resetButtonC = new ImageIcon("RobberyBob/resources/images/AccessPanel/resetClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        resetButton.setIcon(new ImageIcon(resetButtonNC));
        resetButton.setBounds((int) ((screenWidth-buttonLabelWidth)/2)+150, (int) y, (int) buttonLabelWidth, (int)buttonLabelHeight);
        resetButton.setMinimumSize(new Dimension((int) buttonLabelWidth, (int) buttonLabelHeight));
        resetButton.setMaximumSize(new Dimension((int) buttonLabelWidth, (int) buttonLabelHeight));


        registerButton = new JLabel();
        Image registerButtonNC = new ImageIcon("RobberyBob/resources/images/AccessPanel/registerNotClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        Image registerButtonC = new ImageIcon("RobberyBob/resources/images/AccessPanel/registerClicked.png"
            ).getImage().getScaledInstance((int) buttonLabelWidth, (int) buttonLabelHeight, Image.SCALE_REPLICATE);
        registerButton.setIcon(new ImageIcon(registerButtonNC));
        registerButton.setBounds((int) (screenWidth-buttonLabelWidth)/2, (int) (screenHeight/2) + 150, (int) buttonLabelWidth, (int)buttonLabelHeight);
        registerButton.setMinimumSize(new Dimension((int) buttonLabelWidth, (int) buttonLabelHeight));
        registerButton.setMaximumSize(new Dimension((int) buttonLabelWidth, (int) buttonLabelHeight));

        loginBG = new JLabel();
        loginBGImg = new ImageIcon("RobberyBob/resources/images/AccessPanel/menuPanelBG.png");
        Image image = loginBGImg.getImage();
        Image scaledImage = image.getScaledInstance((int) screenWidth, (int) screenHeight, Image.SCALE_REPLICATE);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        loginBG.setIcon(scaledImageIcon);
        loginBG.setBounds(0, 0,  (int) screenWidth, (int) screenHeight);
        loginBG.setMinimumSize(new Dimension((int) screenWidth, (int) screenHeight));
        loginBG.setMaximumSize(new Dimension((int) screenWidth, (int) screenHeight));

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // will store true/false
                playerInUse = userValidation.login(usernameField.getText(), passwordField.getText(), usernameField, passwordField);
                soundManager.playPressed();

                if (playerInUse != null) {
                    mainFrame.frame.getContentPane().removeAll();
                    menuPanel.setBounds(0, 0, mainFrame.frame.getWidth(), mainFrame.frame.getHeight());
                    mainFrame.frame.add(menuPanel);
                    menuPanel.requestFocusInWindow();
                    mainFrame.update();
                    System.out.println("User Access Successful");
                } else {
                    System.out.println("User Access Failed.");
                    JOptionPane.showMessageDialog(null, "Invalid username or password.", "Warning", JOptionPane.WARNING_MESSAGE);
                }
                loginButton.setIcon(new ImageIcon(loginButtonNC));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                loginButton.setIcon(new ImageIcon(loginButtonC));
                soundManager.playHover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                loginButton.setIcon(new ImageIcon(loginButtonNC));
            }
        });

        registerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                soundManager.playPressed();
                boolean isRegisterSuccessful = userValidation.register(usernameField.getText(), passwordField.getText(), usernameField, passwordField);
                if (isRegisterSuccessful) {
                    System.out.println("Registration Successful");
                    JOptionPane.showMessageDialog(null, "Registration Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    System.out.println("Registration Failed");
                }

                registerButton.setIcon(new ImageIcon(registerButtonNC));

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                registerButton.setIcon(new ImageIcon(registerButtonC));
                soundManager.playHover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                registerButton.setIcon(new ImageIcon(registerButtonNC));
            }
        });


        resetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                soundManager.playPressed();
                resetButton.setIcon(new ImageIcon(resetButtonNC));
                usernameField.setText("");
                passwordField.setText("");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                resetButton.setIcon(new ImageIcon(resetButtonC));
                soundManager.playHover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                resetButton.setIcon(new ImageIcon(resetButtonNC));
            }
        });

        this.add(loginButton);
        this.add(resetButton);
        this.add(registerButton);
        this.add(loginBG);

    }

    static class UserValidation {
        static final String DB_UserCredentials = "RobberyBob/resources/Database/users.txt";
        static final String DB_UserData = "RobberyBob/resources/Database/playerdata.txt";
        JTextField userNameField;
        JPasswordField passwordField;

        public String login(String username, String password, JTextField userNameField, JPasswordField passwordField) {
            this.userNameField = userNameField;
            this.passwordField = passwordField;

            try (BufferedReader reader = new BufferedReader(new FileReader(DB_UserCredentials))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] user = line.split(":");
                    if (user[1].equals(username) && user[2].equals(password)) {
                        return user[0];
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        public boolean usernameExists(String username) {
            try (BufferedReader reader = new BufferedReader(new FileReader(DB_UserCredentials))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] user = line.split(":");
                    if (user.length > 1 && user[1].equals(username)) {
                        return true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        public boolean register(String username, String password, JTextField userNameField, JPasswordField passwordField) {
            this.userNameField = userNameField;
            this.passwordField = passwordField;

            UUID userID = UUID.randomUUID();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Field is empty.", "Warning", JOptionPane.WARNING_MESSAGE);
                return false;

            } else if (username.contains(",") || username.contains(":")) {
                JOptionPane.showMessageDialog(null, "Invalid username or password.", "Warning", JOptionPane.WARNING_MESSAGE);
                return false;
            } else if (usernameExists(username)) {
                JOptionPane.showMessageDialog(null, "Username already exists.", "Warning", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            else {
//
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(DB_UserCredentials, true))) {
                    System.out.println("Adding credentials to txt");
                    writer.write(userID + ":" + username + ":" + password); //add credentials to db
                    writer.newLine();

                    //add player data to txt
                    try (BufferedWriter writer2 = new BufferedWriter(new FileWriter(DB_UserData, true))) {
                        System.out.println("Adding player data to txt");
                        writer2.write(userID + ":" + "1,1,1" + ":" + "0,0,0" );
                        writer2.newLine();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    userNameField.setText("");
                    passwordField.setText("");

                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("NOT WORKING");
                }

                return false;
            }

        }



    }
}
