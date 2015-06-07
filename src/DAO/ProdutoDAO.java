package DAO;

import Modelo.Produto;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ProdutoDAO {
    public static ArrayList<Produto> BuscaProdutos (){
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        Produto prod;
        try {
            Statement comando = ConFactory.conectar();
            ResultSet rs = comando.executeQuery("SELECT * FROM Produto WHERE DataHoraTermino > now() order by DataHoraTermino;");
            while (rs.next()) {
                prod = new Produto();
                prod.setIdProduto(rs.getInt("idProduto"));
                prod.setNome(rs.getString("Produto"));
                prod.setLance(rs.getInt("PrecoInicial"));
                prod.setDataHoraInicio(rs.getTimestamp("DataHoraInicio"));
                prod.setDataHoraTermino(rs.getTimestamp("DataHoraTermino"));
                prod.setImagem(rs.getString("Imagem"));
                listaProdutos.add(prod);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listaProdutos;
    }
}
