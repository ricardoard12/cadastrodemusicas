package aspects;

import java.util.ArrayList;
import java.util.Date;

import bd.BDUtil;
import classesbasicas.Assunto;
import classesbasicas.Cantor;
import classesbasicas.Log;
import classesbasicas.Musica;
import classesbasicas.Tipo;
import dao.AssuntoDAO;
import dao.CantorDAO;
import dao.LogDAO;
import dao.MusicaDAO;
import dao.TipoDAO;
import dao.impl.LogDAOMySQL;
import exceptions.DataException;
import fachada.Fachada;

public aspect BDAspect {

	// chamada dos métodos de inserção
	pointcut cadastrarMusica(MusicaDAO mdao, Musica m) : call(int MusicaDAO.cadastrarMusica(Musica)) && args(m) && target(mdao);	
	pointcut cadastrarRitmo(TipoDAO tdao, Tipo t) : call(int TipoDAO.cadastrarTipo(Tipo)) && args(t) && target(tdao);	
	pointcut cadastrarAssunto(AssuntoDAO adao, Assunto a) : call(int AssuntoDAO.cadastrarAssunto(Assunto)) && args(a) && target(adao);
	pointcut cadastrarCantor(CantorDAO cdao, Cantor c) : call(int CantorDAO.cadastrarCantor(Cantor)) && args(c) && target(cdao);
	pointcut adicionarCantorAMusica(MusicaDAO mdao, Musica m, Cantor c) : call(void MusicaDAO.adicionarCantor(Musica, Cantor)) && args(m, c) && target(mdao);

	// chamada dos métodos de alteração
	pointcut alterarMusica(MusicaDAO mdao, Musica m) : call(void MusicaDAO.alterarMusica(Musica)) && args(m) && target(mdao);
	pointcut dispararAlteracaoArquivoMusica(Musica m) : call(void Fachada.dispararAlteracaoArquivoMusica(Musica)) && args(m);
	pointcut alterarCantor(CantorDAO cdao, Cantor c) : call(void CantorDAO.alterarCantor(Cantor)) && args(c) && target(cdao);
	pointcut alterarRitmo(TipoDAO tdao, Tipo t) : call(void TipoDAO.alterarTipo(Tipo)) && args(t) && target(tdao);
	pointcut alterarAssunto(AssuntoDAO adao, Assunto a) : call(void AssuntoDAO.alterarAssunto(Assunto)) && args(a) && target(adao);
	
	// chamada dos métodos de exclusão
	pointcut removerMusica(MusicaDAO mdao, Musica m) : call(void MusicaDAO.removerMusica(Musica)) && args(m) && target(mdao);
	pointcut removerCantor(CantorDAO cdao, Cantor c) : call(void CantorDAO.removerCantor(Cantor)) && args(c) && target(cdao);
	pointcut removerRitmo(TipoDAO tdao, Tipo t) : call(void TipoDAO.removerTipo(Tipo)) && args(t) && target(tdao);
	pointcut removerAssunto(AssuntoDAO adao, Assunto a) : call(void AssuntoDAO.removerAssunto(Assunto)) && args(a) && target(adao);

	// depois de cadastro de Musica
	after(MusicaDAO mdao, Musica m) : cadastrarMusica(mdao, m) {
		if (!BDUtil.isSincronizacaoAtiva()) return;
		
		Log log = new Log();
		log.setClasseObjeto("Musica");
		log.setData(new Date());
		log.setObjeto(m);
		log.setChaveUnicaObjeto(m.getChaveUnica());
		log.setTipoOperacao(Log.TipoOperacao.CADASTRO);
		
		LogDAO dao = new LogDAOMySQL();
		try {
			dao.cadastrarLog(log);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	// depois de adicionar Cantor a Musica
	after(MusicaDAO mdao, Musica m, Cantor c) : adicionarCantorAMusica(mdao, m, c) {
		if (!BDUtil.isSincronizacaoAtiva()) return;
		
		Log log = new Log();
		log.setClasseObjeto("Musica");
		log.setData(new Date());
		
		// verificando se a música tem o cantor como atributo
		m.setCantores(new ArrayList<Cantor>());
		m.getCantores().add(c);
		
		log.setObjeto(m);
		log.setChaveUnicaObjeto(m.getChaveUnica());
		log.setTipoOperacao(Log.TipoOperacao.ADICAO_CANTOR_A_MUSICA);
		
		LogDAO dao = new LogDAOMySQL();
		try {
			dao.cadastrarLog(log);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}

	
	// depois de cadastro de Tipo (Ritmo)
	after(TipoDAO tdao, Tipo t) : cadastrarRitmo(tdao, t) {
		if (!BDUtil.isSincronizacaoAtiva()) return;
		
		Log log = new Log();
		log.setClasseObjeto("Tipo");		
		log.setData(new Date());
		log.setObjeto(t);
		log.setChaveUnicaObjeto(t.getChaveUnica());
		log.setTipoOperacao(Log.TipoOperacao.CADASTRO);
		
		LogDAO dao = new LogDAOMySQL();
		try {
			dao.cadastrarLog(log);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	// depois de cadastro de Assunto
	after(AssuntoDAO adao, Assunto a) : cadastrarAssunto(adao, a) {
		if (!BDUtil.isSincronizacaoAtiva()) return;
		
		Log log = new Log();
		log.setClasseObjeto("Assunto");		
		log.setData(new Date());
		log.setObjeto(a);
		log.setChaveUnicaObjeto(a.getChaveUnica());
		log.setTipoOperacao(Log.TipoOperacao.CADASTRO);
		
		LogDAO dao = new LogDAOMySQL();
		try {
			dao.cadastrarLog(log);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	// depois de cadastro de Cantor
	after(CantorDAO cdao, Cantor c) : cadastrarCantor(cdao, c) {
		if (!BDUtil.isSincronizacaoAtiva()) return;
		
		Log log = new Log();
		log.setClasseObjeto("Cantor");		
		log.setData(new Date());
		log.setObjeto(c);
		log.setChaveUnicaObjeto(c.getChaveUnica());
		log.setTipoOperacao(Log.TipoOperacao.CADASTRO);
		
		LogDAO dao = new LogDAOMySQL();
		try {
			dao.cadastrarLog(log);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	// depois da alteração de Musica
	after(MusicaDAO mdao, Musica m) : alterarMusica(mdao, m) {
		if (!BDUtil.isSincronizacaoAtiva()) return;
		
		Log log = new Log();
		log.setClasseObjeto("Musica");
		log.setData(new Date());
		log.setObjeto(m);
		log.setChaveUnicaObjeto(m.getChaveUnica());
		log.setTipoOperacao(Log.TipoOperacao.ALTERACAO);
		
		LogDAO dao = new LogDAOMySQL();
		try {
			dao.cadastrarLog(log);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	// depois da alteração do arquivo de uma música
	after(Musica m) : dispararAlteracaoArquivoMusica(m) {
		if (!BDUtil.isSincronizacaoAtiva()) return;
		
		Log log = new Log();
		log.setClasseObjeto("Musica");
		log.setData(new Date());
		log.setObjeto(m);
		log.setChaveUnicaObjeto(m.getChaveUnica());
		log.setTipoOperacao(Log.TipoOperacao.ALTERACAO_ARQUIVO_MUSICA);
		
		LogDAO dao = new LogDAOMySQL();
		try {
			dao.cadastrarLog(log);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	// depois da alteração de Cantor
	after(CantorDAO cdao, Cantor c) : alterarCantor(cdao, c) {
		if (!BDUtil.isSincronizacaoAtiva()) return;
		
		Log log = new Log();
		log.setClasseObjeto("Cantor");		
		log.setData(new Date());
		log.setObjeto(c);
		log.setChaveUnicaObjeto(c.getChaveUnica());
		log.setTipoOperacao(Log.TipoOperacao.ALTERACAO);
		
		LogDAO dao = new LogDAOMySQL();
		try {
			dao.cadastrarLog(log);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	// depois da alteração de Tipo (Ritmo)
	after(TipoDAO tdao, Tipo t) : alterarRitmo(tdao, t) {
		if (!BDUtil.isSincronizacaoAtiva()) return;
		
		Log log = new Log();
		log.setClasseObjeto("Tipo");		
		log.setData(new Date());
		log.setObjeto(t);
		log.setChaveUnicaObjeto(t.getChaveUnica());
		log.setTipoOperacao(Log.TipoOperacao.ALTERACAO);
		
		LogDAO dao = new LogDAOMySQL();
		try {
			dao.cadastrarLog(log);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	// depois da alteração de Assunto
	after(AssuntoDAO adao, Assunto a) : alterarAssunto(adao, a) {
		if (!BDUtil.isSincronizacaoAtiva()) return;
		
		Log log = new Log();
		log.setClasseObjeto("Assunto");		
		log.setData(new Date());
		log.setObjeto(a);
		log.setChaveUnicaObjeto(a.getChaveUnica());
		log.setTipoOperacao(Log.TipoOperacao.ALTERACAO);
		
		LogDAO dao = new LogDAOMySQL();
		try {
			dao.cadastrarLog(log);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	// depois da exclusão de Musica
	after(MusicaDAO mdao, Musica m) : removerMusica(mdao, m) {
		if (!BDUtil.isSincronizacaoAtiva()) return;
		
		Log log = new Log();
		log.setClasseObjeto("Musica");
		log.setData(new Date());
		log.setObjeto(m);
		log.setChaveUnicaObjeto(m.getChaveUnica());
		log.setTipoOperacao(Log.TipoOperacao.DELECAO);
		
		LogDAO dao = new LogDAOMySQL();
		try {
			dao.cadastrarLog(log);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	// depois da exclusão de Cantor
	after(CantorDAO cdao, Cantor c) : removerCantor(cdao, c) {
		if (!BDUtil.isSincronizacaoAtiva()) return;
		
		Log log = new Log();
		log.setClasseObjeto("Cantor");		
		log.setData(new Date());
		log.setObjeto(c);
		log.setChaveUnicaObjeto(c.getChaveUnica());
		log.setTipoOperacao(Log.TipoOperacao.DELECAO);
		
		LogDAO dao = new LogDAOMySQL();
		try {
			dao.cadastrarLog(log);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	// depois da exclusão de Tipo (Ritmo)
	after(TipoDAO tdao, Tipo t) : removerRitmo(tdao, t) {
		if (!BDUtil.isSincronizacaoAtiva()) return;
		
		Log log = new Log();
		log.setClasseObjeto("Tipo");		
		log.setData(new Date());
		log.setObjeto(t);
		log.setChaveUnicaObjeto(t.getChaveUnica());
		log.setTipoOperacao(Log.TipoOperacao.DELECAO);
		
		LogDAO dao = new LogDAOMySQL();
		try {
			dao.cadastrarLog(log);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
	
	// depois da exclusão de Assunto
	after(AssuntoDAO adao, Assunto a) : removerAssunto(adao, a) {
		if (!BDUtil.isSincronizacaoAtiva()) return;
		
		Log log = new Log();
		log.setClasseObjeto("Assunto");		
		log.setData(new Date());
		log.setObjeto(a);
		log.setChaveUnicaObjeto(a.getChaveUnica());
		log.setTipoOperacao(Log.TipoOperacao.DELECAO);
		
		LogDAO dao = new LogDAOMySQL();
		try {
			dao.cadastrarLog(log);
		} catch (DataException e) {
			e.printStackTrace();
		}
	}
}
