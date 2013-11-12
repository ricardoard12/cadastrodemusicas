package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bd.BDUtil;
import classesbasicas.Musica;
import classesbasicas.Playlist;
import dao.MusicaDAO;
import dao.PlaylistDAO;
import exceptions.DataException;

public class PlaylistDAOMySQL implements PlaylistDAO {

	public void alterarPlaylist(Playlist playlist) throws DataException {
		String sql = "UPDATE playlist SET nome=? WHERE idPlaylist=?";

		PreparedStatement ps;
		try {
			ps = BDUtil.getConexao().prepareStatement(sql);
			
			ps.setString(1, playlist.getNome());
			ps.setInt(2, playlist.getIdPlaylist());
			
			ps.execute();
			ps.close();
			
			Statement s = BDUtil.getConexao().createStatement();
			sql = "DELETE FROM playlistitem WHERE idPlaylist = " + playlist.getIdPlaylist();
			s.execute(sql);
			
			for (int i = 1; i <= playlist.getItens().size(); i++) {
				Musica m = playlist.getItens().get(i - 1);
				sql = "INSERT INTO playlistitem (idPlaylist, idMusica, ordem) VALUES (" + playlist.getIdPlaylist() + 
					  ", " + m.getIdMusica() + ", " + i + ")";
				s.execute(sql);
			}
			
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível alterar o cantor");
		}
	}

	public int cadastrarPlaylist(Playlist playlist) throws DataException {
		String sql = "INSERT INTO playlist(nome) VALUES(?)";
		
		try {
			PreparedStatement ps = BDUtil.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, playlist.getNome());
			ps.execute();

			System.out.println("Playlist cadastrada");
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			
			int codigo = rs.getInt("GENERATED_KEY");			
			playlist.setIdPlaylist(codigo);
			
			Statement s = BDUtil.getConexao().createStatement();
			for (int i = 1; i <= playlist.getItens().size(); i++) {
				Musica m = playlist.getItens().get(i - 1);
				sql = "INSERT INTO playlistitem (idPlaylist, idMusica, ordem) VALUES (" + playlist.getIdPlaylist() + 
					  ", " + m.getIdMusica() + ", " + i + ")";
				s.execute(sql);
			}
			s.close();
			
			rs.close();
			ps.close();
			
			return codigo;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível cadastrar a Playlist.");
		}
	}

	public Playlist getPlaylist(String nome) throws DataException {
		String sql = "SELECT * FROM playlist WHERE nome = '" + nome + "'";
		
		try {
			Statement s = BDUtil.getConexao().createStatement();
			ResultSet r = s.executeQuery(sql);
			
			if (r.next()) {
				Playlist p = new Playlist();
				
				p.setIdPlaylist(r.getInt("idPlaylist"));
				p.setNome(r.getString("nome"));
				p.setItens(new ArrayList<Musica>());
				p.setItens(new ArrayList<Musica>());
				
				sql = "SELECT * FROM playlistitem WHERE idPlaylist = " + p.getIdPlaylist() + " ORDER BY ordem";
				
				s = BDUtil.getConexao().createStatement();
				r = s.executeQuery(sql);
				
				MusicaDAO mDAO = new MusicaDAOMySQL();
				while (r.next()) {
					int id = r.getInt("idMusica");
					p.getItens().add(mDAO.getMusica(id));
				}
				
				r.close();
				s.close();
				
				return p;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível realizar a operação");
		}
	}

	public List<Playlist> listarPlaylists() throws DataException {
		String sql = "SELECT * FROM playlist WHERE nome <> '" + Playlist.NOME_PLAYLIST_PADRAO + "' ORDER BY nome";
		List<Playlist> playlists = new ArrayList<Playlist>();
		
		try {
			Statement s = BDUtil.getConexao().createStatement();
			ResultSet r = s.executeQuery(sql);
			
			while (r.next()) {
				Playlist p = new Playlist();
				
				p.setIdPlaylist(r.getInt("idPlaylist"));
				p.setNome(r.getString("nome"));
				p.setItens(new ArrayList<Musica>());
				p.setItens(new ArrayList<Musica>());
				
				sql = "SELECT * FROM playlistitem WHERE idPlaylist = " + p.getIdPlaylist() + " ORDER BY ordem";
				
				s = BDUtil.getConexao().createStatement();
				ResultSet mus = s.executeQuery(sql);
				
				MusicaDAO mDAO = new MusicaDAOMySQL();
				while (mus.next()) {
					int id = mus.getInt("idMusica");
					p.getItens().add(mDAO.getMusica(id));
				}
				
				playlists.add(p);
			}
			
			r.close();
			s.close();
			
			return playlists;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException("Não foi possível realizar a operação");
		}
	}

	public void removerPlaylist(Playlist playlist) throws DataException {
		if (playlist.getIdPlaylist() <= 0) {
			return;
		}
		
		Statement s;
		try {
			s = BDUtil.getConexao().createStatement();
			String sql = "DELETE FROM playlistitem WHERE idPlaylist = " + playlist.getIdPlaylist();
			s.execute(sql);
			sql = "DELETE FROM playlist WHERE idPlaylist = " + playlist.getIdPlaylist();
			s.execute(sql);
			
			s.close();
		} catch (SQLException e) {
			throw new DataException("Não foi possível remover a Playlist.");
		}		
	}
	
	

}
