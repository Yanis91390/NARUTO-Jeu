import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import java.awt.BorderLayout;

public class Panneau extends JPanel
{
	public Panneau()
	{
		super();
		this.setLayout(null);
		this.setBackground(new Color(200,200,200));
		this.setForeground(Color.black);
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}
}