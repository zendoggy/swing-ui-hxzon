package org.hxzon.swing.components.ext;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class ButtonSep extends JComponent
{
	public ButtonSep(Color dd)
	{
		this.setBackground(dd);
	}

	public ButtonSep()
	{
		this.setBackground(Color.gray);
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(this.getBackground());
		g.drawLine(0, 0, this.getWidth(), 0);
	}
}
