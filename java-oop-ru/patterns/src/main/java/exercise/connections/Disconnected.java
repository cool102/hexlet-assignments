package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {
    private final String description = "disconnected";
    TcpConnection connection;

    public Disconnected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public String getCurrentState() {
        System.out.println(description);
        return description;
    }

    @Override
    public void connect() {
        TcpConnection conn = this.connection;
        conn.setConnection(new Connected(conn));
        System.out.println("Connection...");
    }

    @Override
    public void disconnect() {
        System.out.println("Error! I am already disconnected");
    }

    @Override
    public void write(String data) {
        System.out.println("Error! I can't write when disconnected");
    }

}
// END
