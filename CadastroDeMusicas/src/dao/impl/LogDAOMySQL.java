package dao.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bd.BDUtil;
import classesbasicas.Log;
import dao.LogDAO;
import exceptions.DataException;

public class LogDAOMySQL implements LogDAO {

	public int cadastrarLog(Log log) throws DataException {
		try {
			String sql = "INSERT INTO log (chaveUnica, tipoOperacao, classeObjeto, objeto, data, chaveUnicaObjeto) VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql);

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        ObjectOutputStream oos;

			oos = new ObjectOutputStream(bos);
	        oos.writeObject(log.getObjeto());
	        oos.flush();
	        oos.close();
	        bos.close();
	        byte[] objeto = bos.toByteArray();
	        
	        ps.setString(1, log.getChaveUnica());
			ps.setInt(2, log.getTipoOperacao().ordinal());
	        ps.setString(3, log.getClasseObjeto());
			ps.setObject(4, objeto); 
			ps.setTimestamp(5, new Timestamp(log.getData().getTime()));
			if (log.getChaveUnicaObjeto() == null) {
				ps.setNull(6, java.sql.Types.VARCHAR);
			} else {
				ps.setString(6, log.getChaveUnicaObjeto());	
			}			
			
			System.out.println(ps);
			
			if (ps.executeUpdate() > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				int codigo = rs.getInt("GENERATED_KEY");			
				log.setIdLog(codigo);
				return log.getIdLog();
			} else {
				System.out.println("Erro ao executar a inserção");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return 0;
	} 
	
	private List<Log> listarLogsPorConsulta(String sql) throws DataException {
		List<Log> lista = new ArrayList<Log>();
		
		try {
			Statement s = BDUtil.getConexao().createStatement();
			ResultSet r = s.executeQuery(sql);
			
			while (r.next()) {
				Log l = new Log();
				
				l.setIdLog(r.getInt("idLog"));
				l.setChaveUnica(r.getString("chaveUnica"));
				int tipoOp = r.getInt("tipoOperacao");
				Log.TipoOperacao tipoOperacao = null;
				if (tipoOp == Log.TipoOperacao.CADASTRO.ordinal()) {
					tipoOperacao = Log.TipoOperacao.CADASTRO;
				} else if (tipoOp == Log.TipoOperacao.ALTERACAO.ordinal()) {
					tipoOperacao = Log.TipoOperacao.ALTERACAO;
				} else if (tipoOp == Log.TipoOperacao.DELECAO.ordinal()) {
					tipoOperacao = Log.TipoOperacao.DELECAO;
				} else if (tipoOp == Log.TipoOperacao.ADICAO_CANTOR_A_MUSICA.ordinal()) {
					tipoOperacao = Log.TipoOperacao.ADICAO_CANTOR_A_MUSICA;
				}					
				l.setTipoOperacao(tipoOperacao);
				l.setClasseObjeto(r.getString("classeObjeto"));
				l.setChaveUnicaObjeto(r.getString("chaveUnicaObjeto"));
				
				ByteArrayInputStream bais = new ByteArrayInputStream((byte[]) r.getObject("objeto"));
				ObjectInputStream ois;
				try {
					ois = new ObjectInputStream(bais);
					l.setObjeto(ois.readObject());
					ois.close();
					bais.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				
				l.setData(new Date(r.getTimestamp("data").getTime()));
				
				lista.add(l);
			}
			
			return lista;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível realizar a operação");
		}
	}
	
	// lista todos os logs em ordem de data
	public List<Log> listarLogs() throws DataException {
		String sql = "SELECT * FROM log WHERE (excluido <> 1) ORDER BY data, idLog";
		return listarLogsPorConsulta(sql);
	}

	public List<Log> listarLogs(String chaveUnicaObjeto) throws DataException {
		String sql = "SELECT * FROM log WHERE (excluido <> 1) AND chaveUnicaObjeto LIKE '" + chaveUnicaObjeto + "' ORDER BY data, idLog";
		return listarLogsPorConsulta(sql);
	}
}
