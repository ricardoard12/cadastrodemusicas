package gui.player;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.jdbc.EscapeTokenizer;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ConfiguracoesPlayerDialog extends javax.swing.JDialog {
	public static int OPCAO_OK = 1;
	public static int OPCAO_CANCEL = 2;
	
	private JLabel LabelIntervalorEntreMusicas;
	private JTextField textFieldIntervaloEntreMusicas;
	private JButton buttonOK;
	private JButton buttonCancel;
	private int opcaoEscolhida = OPCAO_CANCEL;
	private int intervaloEntreMusicas = 0;
	
	public ConfiguracoesPlayerDialog(JFrame frame, int intervaloEntreMusicas) {
		super(frame);
		this.intervaloEntreMusicas = intervaloEntreMusicas;
		initGUI();
	}
	
	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1};
				thisLayout.rowHeights = new int[] {7, 7, 7};
				thisLayout.columnWeights = new double[] {0.1, 0.1};
				thisLayout.columnWidths = new int[] {7, 7};
				getContentPane().setLayout(thisLayout);
				this.setTitle("Configurações");
				{
					LabelIntervalorEntreMusicas = new JLabel();
					getContentPane().add(LabelIntervalorEntreMusicas, new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets(0, 4, 0, 0), 0, 0));
					LabelIntervalorEntreMusicas.setText("Intervalo entre as Músicas");
				}
				{
					textFieldIntervaloEntreMusicas = new JTextField();
					textFieldIntervaloEntreMusicas.setText("" + this.intervaloEntreMusicas);
					getContentPane().add(textFieldIntervaloEntreMusicas, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 4, 0, 4), 0, 0));
				}
				{
					buttonOK = new JButton();
					getContentPane().add(buttonOK, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 7), 0, 0));
					buttonOK.setText("OK");
					buttonOK.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							opcaoEscolhida = OPCAO_OK;
							try {
								Integer intervalo = Integer.decode(textFieldIntervaloEntreMusicas.getText());
								intervaloEntreMusicas = intervalo;
								dispose();	
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(ConfiguracoesPlayerDialog.this, "Por favor, informe um valor válido para o intervalo entre as Músicas.", "Intervalo Inválido", JOptionPane.ERROR_MESSAGE);
							}
						}
					});
				}
				{
					buttonCancel = new JButton();
					getContentPane().add(buttonCancel, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 7, 0, 0), 0, 0));
					buttonCancel.setText("Cancelar");
					buttonCancel.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							dispose();
						}
					});
				}
			}
			this.setSize(226, 100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getOpcaoEscolhida() {
		return opcaoEscolhida;
	}
	
	public int getIntervaloEntreMusicas() {
		return intervaloEntreMusicas;
	}

}
