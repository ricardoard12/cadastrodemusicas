package dao;

import classesbasicas.Playlist;
import exceptions.DataException;

public interface PlaylistDAO {
	public int cadastrarPlaylist(Playlist playlist) throws DataException;
	public void alterarPlaylist(Playlist playlist) throws DataException;
	public Playlist getPlaylist(String nome) throws DataException;
}
