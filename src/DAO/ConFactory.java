package DAO;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConFactory {

    public static final int MYSQL = 0;
    private static Connection con;
    private static Statement comando;

    private static Connection conexao() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/leilao", "root", "191288");
    }

    public static Statement conectar() {
        try {
            con = conexao();
            comando = (Statement) con.createStatement();
            System.out.println("Conectado");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao carregar o driver! \n o sistema será fechado " + e.getMessage(), "ATENCAO",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro ao conectar!, \n o sistema será fechado" + e.getMessage(),
                    "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return comando;
    }

    public static void fecharConexao() {
        try {
            comando.close();
            con.close();
            System.out.println("Conexão fechada");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexao! \n o sistema será fechado" + e.getMessage(),
                    "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}
