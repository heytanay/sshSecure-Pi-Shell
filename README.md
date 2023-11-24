# SecurePiShell - Version 1.0.0


## Improvements in this Release

### Security and Password Handling

- **Secure Password Handling:** Passwords are now handled securely by working directly with the character array returned by `getPassword()`. This enhances the overall security of the application.

### Input Validation

- **Enhanced Input Validation:** Improved input validation ensures that users provide valid information for hostname, username, and password. This helps prevent issues related to missing or incorrect input.

### Visual Indicators

- **Connection Status Indicators:** Visual indicators have been added to display the current connection status (connected/disconnected). The connection status label dynamically updates its color based on the connection state, providing a clear visual cue to users.

### User Interface

- **UI Enhancements:** The user interface has been enhanced with a clear layout for input fields, buttons, and the output area. This improves the overall user experience and makes the application more user-friendly.

SecurePiShell is a Java application that provides a graphical user interface for connecting to a Raspberry Pi using SSH.

## Features

- Securely connect to a Raspberry Pi using SSH
- Execute custom commands remotely
- Dark theme for improved visibility

## Prerequisites

- J2SE (version 1.4.0 or later)
- jsch library (version 0.1.55)

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/selzlett/sshSecure-Pi-Shell.git
    ```

2. Navigate to the project directory:

    ```bash
    cd ssh-connector-gui
    ```

3. Compile and run the application:

    ```bash
    javac -cp .:jsch-0.1.55.jar SSHConnectorApp.java
    java -cp .:jsch-0.1.55.jar SSHConnectorApp
    ```

## Usage

1. Enter the host, username, and password.
2. Click the "Connect" button to establish an SSH connection.
3. Enter a custom command in the "Command" field.
4. Click the "Execute" button to run the command remotely.

## License

This project is licensed under the GNU General Public License - see the [LICENSE.txt](LICENSE.txt) file for details.

## Acknowledgments

- [JSch Library](http://www.jcraft.com/jsch/)
- [Nimbus Look and Feel](https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/nimbus.html)
