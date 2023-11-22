import com.jcraft.jsch.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SSHConnectorApp extends JFrame {

    private JTextField hostField, userField, commandField;
    private JPasswordField passwordField;
    private JButton connectButton, executeButton, clearButton;
    private JTextArea outputArea;
    private JLabel connectionStatusLabel;
    private Color connectedColor = Color.GREEN;
    private Color disconnectedColor = Color.RED;

    private Session session;

    public SSHConnectorApp() {
        super("SSH Connector");

        // Create components
        hostField = new JTextField("raspberrypi.local", 20);
        userField = new JTextField("pi", 20);
        passwordField = new JPasswordField(20);
        commandField = new JTextField("ls", 30);

        connectButton = new JButton("Connect");
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectSSH();
            }
        });

        executeButton = new JButton("Execute");
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeCommand();
            }
        });

        clearButton = new JButton("Clear Output");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("");
            }
        });

        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        connectionStatusLabel = new JLabel("Disconnected");
        connectionStatusLabel.setForeground(disconnectedColor);

        // Set layout
        setLayout(new BorderLayout());

        // Create panels
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Host:"));
        inputPanel.add(hostField);
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(userField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);
        inputPanel.add(new JLabel("Command:"));
        inputPanel.add(commandField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(connectButton);
        buttonPanel.add(executeButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(connectionStatusLabel);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void connectSSH() {
        try {
            String host = hostField.getText();
            String user = userField.getText();
            String password = new String(passwordField.getPassword());

            JSch jsch = new JSch();
            session = jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no"); // Disable host key checking

            // Connect to the SSH server
            session.connect();

            setStatus("Connected to " + host);
            updateConnectionStatus(true); // Update the visual indicator
        } catch (JSchException e) {
            setStatus("Connection failed. Check credentials and try again.");
            updateConnectionStatus(false); // Update the visual indicator
            e.printStackTrace();
        }
    }

    private void executeCommand() {
        try {
            if (session != null && session.isConnected()) {
                String customCommand = commandField.getText();

                Channel channel = session.openChannel("exec");
                ((ChannelExec) channel).setCommand(customCommand);
                channel.setInputStream(null);
                ((ChannelExec) channel).setErrStream(System.err);

                InputStream in = channel.getInputStream();
                channel.connect();

                // Read the command output
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }

                // Display the command output in the JTextArea
                outputArea.append("\n" + output.toString());

                // Disconnect the channel
                channel.disconnect();

                setStatus("Command executed successfully.");
            } else {
                setStatus("Not connected. Please connect first.");
            }
        } catch (JSchException | IOException e) {
            setStatus("Command execution failed.");
            e.printStackTrace();
        }
    }

    private void setStatus(String status) {
        outputArea.append("\n" + status);
    }

    private void updateConnectionStatus(boolean isConnected) {
        if (isConnected) {
            connectionStatusLabel.setText("Connected");
            connectionStatusLabel.setForeground(connectedColor);
        } else {
            connectionStatusLabel.setText("Disconnected");
            connectionStatusLabel.setForeground(disconnectedColor);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SSHConnectorApp();
            }
        });
    }
}
