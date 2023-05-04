package application.database;

import application.models.Entity_Manager.repositories.GetConn;
import lombok.Getter;

import java.sql.Connection;

public abstract class ConnectDB implements AutoCloseable, GetConn {
    protected final String url;
    protected static String user;
    protected static String password;
    @Getter
    protected Connection connection; //la conexión

    public ConnectDB(String url) {
        this.url = url;
    }
    public abstract void setUser(String user, String password);
    public abstract void connectTo();
}
