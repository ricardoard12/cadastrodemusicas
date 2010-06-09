package gui.about;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.MatteBorder;

import classesbasicas.Constantes;

import exceptions.ConfiguracaoInexistenteException;
import exceptions.DataException;

import bd.BDUtil;

public class AboutPanel extends JPanel implements ActionListener
{
  /**
	 * 
	 */
	private static final long serialVersionUID = -649463810582615935L;
	
	private Icon imagem = loadImageIcon("/figuras/music-notes-about.jpg");

  private FontMetrics fonte;
  private Vector vector;
  private int inc = -200;
  private int MaxX, MaxY, width;
  private Timer timer;

  public AboutPanel()
  {
    this.setFont(new Font("Arial", Font.PLAIN, 11));

    fonte = this.getFontMetrics(this.getFont());

    this.setBorder(new MatteBorder(1, 1, 1, 1, Color.gray));

    vector = this.openFile();
    timer = new Timer(70, this);
    timer.start();
  }

  public void paintComponent(Graphics graph)
  {
    imagem.paintIcon(this, graph, 1, 1);

    MaxX = (int)this.getSize().getWidth();
    MaxY = (int)this.getSize().getHeight();

    String programa = "DMD - Sistema de Cadastro de Arquivos de Áudio (MP3)";
    String versao = "";
    try {
		 versao = "Versão " + BDUtil.getConfiguracao("versao");
	} catch (ConfiguracaoInexistenteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DataException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    String desenvolvidoPor = "Desenvolvido por: Fábio Delicato";
    String fones = "Fones: (81)9174-9666 / (81)3429-6414";
    String email = "E-Mail: fabiodfmelo@gmail.com";
    String dataRelease = "Data Release";
    try {
		dataRelease = BDUtil.getConfiguracao(Constantes.CONFIGURACAO_DATA_RELEASE);
	} catch (ConfiguracaoInexistenteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DataException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    graph.drawString(programa, (MaxX - fonte.stringWidth(programa) - 6),(24));
    graph.drawString(versao, (MaxX - fonte.stringWidth(programa) - 6),(38));
    
    graph.drawString(desenvolvidoPor, (MaxX - fonte.stringWidth(programa) - 6),(66));
    graph.drawString(fones, (MaxX - fonte.stringWidth(programa) - 6),(80));
    graph.drawString(email, (MaxX - fonte.stringWidth(programa) - 6),(94));
    graph.drawString(dataRelease, (MaxX - fonte.stringWidth(dataRelease) - 6), MaxY - 6);

    graph = graph.create( (MaxX - width) / 2, 120, width, MaxY - 160);

    int lines = (int)this.getSize().getHeight() / fonte.getHeight();
    int first = inc / fonte.getHeight();
    int height = fonte.getHeight() - inc % fonte.getHeight();

    for (int i = 0; i <= lines; i++)
    {
      if (i + first >= 0 && i + first < vector.size())
      {
        String line = (String) vector.get(i + first);
        graph.drawString(line, (width - fonte.stringWidth(line)) / 2, height);
      }
      height += fonte.getHeight();
    }
  }

  public Dimension getPreferredSize()
  {
    return new Dimension(imagem.getIconWidth() + 1, imagem.getIconHeight() + 1);
  }

  public void actionPerformed(ActionEvent evento)
  {
    if (evento.getSource() == timer)
    {
      if (inc < (vector.size() * fonte.getHeight()))
      {
        inc += 2;
        this.repaint();
      }
      else
      {
        timer.stop();
        timer = null;
      }
    }
  }

  private Vector openFile()
  {
    File file = new File("license.txt");
    Vector<String> vector = new Vector<String>(30, 10);
    try
    {
      BufferedReader input = new BufferedReader(new FileReader(file));
      String text = new String();
      while ( (text = input.readLine()) != null) {
        vector.addElement(text + "\n");
        width = Math.max(width, fonte.stringWidth(text) + 10);
      }
    }
    catch (FileNotFoundException excecao)
    {
    }
    catch (IOException excecao)
    {
    }
    vector.trimToSize();
    return vector;
  }
  
  private static ImageIcon loadImageIcon(String path) {
		java.net.URL imgURL = AboutPanel.class.getResource(path);
		if (imgURL != null) {
			System.out.println("Loading from: " + imgURL);
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}