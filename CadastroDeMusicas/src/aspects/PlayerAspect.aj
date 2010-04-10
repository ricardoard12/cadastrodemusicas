package aspects;

import gui.musicas.ProcurarMusicasPanel;
import gui.player.ExportarPlaylistJDialog;
import gui.musicas.MusicasInternalFrame;

public aspect PlayerAspect {
	ProcurarMusicasPanel procurarMusicasPanel = null;
	
	pointcut cadastrarOuAlterarColecaoPlayer() : call(void ExportarPlaylistJDialog.salvarNovaColecao()) || call(void ExportarPlaylistJDialog.salvarNaColecaoSelecionada());
	pointcut abrirMusicasInternalFrame(MusicasInternalFrame mif) : call(void MusicasInternalFrame.initialize()) && target(mif);
	pointcut fecharMusicasInternalFrame(MusicasInternalFrame mif) : call(void MusicasInternalFrame.fechar()) && target(mif);
	
	// cadastro ou atualização de coleção pelo player
	after() : cadastrarOuAlterarColecaoPlayer() {
		if (procurarMusicasPanel != null) {
			procurarMusicasPanel.novaColecaoCadastrada();	
		}
	}	
	
	after(MusicasInternalFrame mif) : abrirMusicasInternalFrame(mif) {
		this.procurarMusicasPanel = mif.getProcurarMusicasPanel();
	}
	
	after(MusicasInternalFrame mif) : fecharMusicasInternalFrame(mif) {
		this.procurarMusicasPanel = null;
	}
}
