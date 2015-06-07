package leilao_servidor;

import DAO.ParticipanteDAO;
import DAO.ProdutoDAO;
import Modelo.Participante;
import Modelo.Produto;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import rmi.Log;

public class LogServer extends UnicastRemoteObject implements Log {

    public LogServer() throws RemoteException {
        super();
    }

    @Override
    public Participante ValidaUsuario(Participante p) throws RemoteException {
        return ParticipanteDAO.ValidaLogin(p);
    }

    @Override
    public ArrayList<Produto> BuscaProdutos() throws RemoteException {
        return ProdutoDAO.BuscaProdutos();
    }

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("logserver", new LogServer());
            System.out.println("Servidor conectado...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
