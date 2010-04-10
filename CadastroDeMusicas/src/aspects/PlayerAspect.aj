package aspects;

import gui.player.ExportarPlaylistJDialog;

public aspect PlayerAspect {
	pointcut cadastrarOuAlterarColecaoPlayer() : call(void ExportarPlaylistJDialog.salvarNovaColecao()) || call(void ExportarPlaylistJDialog.salvarNaColecaoSelecionada());
	
	// cadastro ou atualização de coleção pelo player
	after() : cadastrarOuAlterarColecaoPlayer() {
		
	}	
}
