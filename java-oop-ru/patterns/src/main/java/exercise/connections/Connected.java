package exercise.connections;

import exercise.TcpConnection;

// BEGIN
class Connected implements Connection {
    private final String description = "connected";
    TcpConnection connection;

    public Connected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public String getCurrentState() {
        System.out.println(description);
        return description;
    }

    @Override
    public void connect() {
      System.out.println("Error! I am already connected");
    }

    @Override
    public void disconnect() {
      TcpConnection c = this.connection;
      c.setConnection(new Disconnected(c));
      System.out.println("Disconnecting...");
    }

    @Override
    public void write(String data) {
      System.out.println("Writing data...because I am connected");
    }
}
// END
