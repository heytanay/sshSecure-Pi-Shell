# SecurePiShell

<<<<<<< HEAD
=======

>>>>>>> origin/main
SecurePiShell is a Java application that provides a graphical user interface for connecting to a Raspberry Pi using SSH.

## Features

- Securely connect to a Raspberry Pi using SSH
- Execute custom commands remotely
- Dark theme for improved visibility

## Prerequisites

- Java (version X.X.X)
- jsch library (version 0.1.55)

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/selzlett/ssh-connector-gui.git
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
