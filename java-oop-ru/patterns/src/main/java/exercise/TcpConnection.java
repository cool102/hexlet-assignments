package exercise;

import exercise.connections.Connection;
import exercise.connections.Disconnected;

// BEGIN
public class TcpConnection implements Connection {
    private final String host;
    private final int port;
    Connection connection;

    public TcpConnection(String host, int port) {
        this.host = host;
        this.port = port;
        this.connection = new Disconnected(this);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String getCurrentState() {
        return connection.getCurrentState();
    }

    @Override
    public void connect() {
        connection.connect();
    }

    @Override
    public void disconnect() {
        connection.disconnect();
    }

    @Override
    public void write(String data) {
        connection.write(data);
    }
}
// END
