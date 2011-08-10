package org.xxx.goodcode;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class PageLayout implements LayoutManager
{
    public final int width;
    public final int height;
    public final int pageHeight;

    private int whitespace; // the whitespace at the bottom
    
    /**
     * 
     * @param width 容器的初始宽度
     * @param height 容器的初始高度
     * @param pageHeight 每页的高度
     */
    public PageLayout(int width, int height, int pageHeight)
    {
        if (width < 0 || height < 0 || pageHeight <= 0)
            throw new IllegalArgumentException(
                    "they are illegal when: width < 0 or height < 0 or pageHeight <= 0");
        this.width = width;
        this.height = height;
        this.pageHeight = pageHeight;
        this.whitespace = height % pageHeight;
    }

    public void addLayoutComponent(String name, Component comp)
    {
        // noop
    }

    public void removeLayoutComponent(Component comp)
    {
        // noop
    }

    // core method
    public void layoutContainer(Container parent)
    {
        int y = 0;
        for (Component comp : parent.getComponents())
        {
            comp.setBounds(0, y, width, pageHeight);
            y += pageHeight;
        }
    }

    // When will this method be called? TODO It needs testing! 
    public Dimension minimumLayoutSize(Container parent)
    {
        int cc = parent.getComponentCount();
        int h = cc * pageHeight;
        if (h <= height)    h = height;
        
        return new Dimension(width, h);
    }

    public Dimension preferredLayoutSize(Container parent)
    {
        int cc = parent.getComponentCount();
        int h = cc * pageHeight;
        if (h > height)
            h += whitespace;
        else
            h = height;
        
        return new Dimension(width, h);
    }
}
