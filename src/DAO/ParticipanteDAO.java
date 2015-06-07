package DAO;

import Modelo.Participante;
import java.sql.ResultSet;
import java.sql.Statement;

public class ParticipanteDAO {
    //INSERT INTO `leilao`.`Participante` (`Nome`, `CPF`, `Endereco`, `Cidade`, `Estado`, `CEP`, `CartaoNum`, `CartaoNome`, `CartaoCodVerificacao`, `CartaoValidade`, `Email`, `Senha`) VALUES ('', '', '', '', '', '', ' ', '', '', '', '', '');

    public static Participante ValidaLogin (Participante p){
        Participante part = null;
        try {
            Statement comando = ConFactory.conectar();
            ResultSet rs = comando.executeQuery("SELECT * FROM Participante WHERE Email = '" + p.getEmail() + "' AND Senha = '" + String.valueOf(p.getSenha()) + "'");
            while (rs.next()) {
                part = new Participante();
                part.setEmail(p.getEmail());
                part.setIdParticipante(rs.getInt("idParticipante"));
                part.setNome(rs.getString("Nome"));
                part.setCPF(rs.getString("CPF").toCharArray());
                part.setEndereco(rs.getString("Endereco"));
                part.setCidade(rs.getString("Cidade"));
                part.setEstado(rs.getString("Estado").toCharArray());
                part.setCEP(rs.getString("CEP").toCharArray());
                part.setNumCartao(rs.getString("CartaoNum").toCharArray());
                part.setNomeCartao(rs.getString("CartaoNome"));
                part.setCodCartao(rs.getInt("CartaoCodVerificacao"));
                part.setValidadeCartao(rs.getDate("CartaoValidade"));
                part.setLances(rs.getInt("Lances"));
            }
        } catch (Exception e) {
            part = null;
            System.out.println(e);
        }
        return part;
    }
}
