package org.hxzon.swing.components.ext;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class JOutLookLabel extends JLabel // implements MouseListener
{

	public JOutLookLabel(String str, int width, int height)
	{
		setHorizontalAlignment(JLabel.CENTER);
		setPreferredSize(new Dimension(width, height));
		this.setText(str);

		this.addFocusListener(new FocusListener()
		{
			public void focusLost(FocusEvent e)
			{
				mouseIn = false;
				repaint();
			}

			public void focusGained(FocusEvent e)
			{
				mouseIn = true;
				repaint();
			}
		});

		this.addMouseListener(new MouseListener()
		{
			public void mouseReleased(MouseEvent e)
			{
				actionPerformed();
			}

			public void mousePressed(MouseEvent e)
			{
			}

			public void mouseExited(MouseEvent e)
			{
				if (hasFocus())
					mouseIn = true;
				else
					mouseIn = false;
				repaint();
			}

			public void mouseEntered(MouseEvent e)
			{
				mouseIn = true;
				repaint();
			}

			public void mouseClicked(MouseEvent e)
			{
				mouseIn = false;
				JOutLookLabel.this.requestFocus();

			}
		});
		this.setBackground(Color.white);
	}

	public void actionPerformed()
	{

	}

	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		if (mouseIn)
		{
			g.setColor(Color.gray);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			g2d.setPaint(new GradientPaint(1, 1, selectStartBgColor, 1, this.getHeight(), selectEndBgColor));
			g2d.fillRect(1, 1, this.getWidth() - 1, this.getHeight() - 1);

			g2d.setColor(Color.red);
			g2d.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);

		}
		else
		{
			g.setColor(Color.white);
			g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

			if (disColor != null)
			{
				g2d.setPaint(new GradientPaint(1, 1, disColor, 1, this.getHeight(), disEndColor));
				g2d.fillRect(1, 1, this.getWidth() - 1, this.getHeight() - 1);

				g2d.setColor(disBorderColor);
				g2d.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
			}
		}

		super.paint(g);
	}

	public Color disColor = null;
	public Color disEndColor = null;
	public Color disBorderColor = null;

	Color selectStartBgColor = new Color(255, 211, 137);
	Color selectEndBgColor = new Color(255, 176, 89);

	public boolean mouseIn = false;

	/*

	public JOutLookLabel(String text)
	{
		setHorizontalAlignment(JLabel.CENTER);
		setPreferredSize(new Dimension(40, 20));
		addMouseListener(this);
		setText(text);
		this.setOpaque(true);
	}

	public void mousePressed(MouseEvent e)
	{
	}

	public void mouseClicked(MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{

	}

	public void mouseEntered(MouseEvent e)
	{
		mouse = true;
		repaint();
	}

	public void mouseExited(MouseEvent e)
	{
		mouse = false;
		repaint();
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		if (mouse)
		{
			g.setColor(Color.gray);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			g2d.setPaint(new GradientPaint(1, 1, selectStartBgColor, 1, this.getHeight(), selectEndBgColor));
			g2d.fillRect(1, 1, this.getWidth() - 1, this.getHeight() - 1);

			g2d.setColor(Color.red);
			g2d.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
		}
		else
		{
			g.setColor(Color.white);
			g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		
	}

	Color selectStartBgColor = new Color(255, 211, 137);
	Color selectEndBgColor = new Color(255, 176, 89);
	boolean mouse = false;*/
}