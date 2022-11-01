package exercise.connections;

import exercise.TcpConnection;

public class App {
    public static void main(String[] args) {

        TcpConnection tcpConnection = new TcpConnection("192.168.7.10", 80);
        tcpConnection.connect();
        tcpConnection.getCurrentState();
        tcpConnection.write("Data");
        tcpConnection.disconnect();
        tcpConnection.write("Once more data");
        tcpConnection.getCurrentState();
        tcpConnection.disconnect();
    }
}
