/*
 * ImageButton.java
 *
 * Created on 2007年11月11日, 下午6:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package demo.swing.button;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.util.HashMap;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

/**
 * 为需要具备专业外观的按钮提供方便的实现。通过此类提供的若干方法可轻松实现专业外观。
 * @author 电玩
 */
public final class ImageButton extends JComponent implements
        MouseInputListener {
    /**
     * 通常状态下的背景图片
     */
    private Icon backgroundImage;
    
    /**
     * 通常状态下的最佳尺寸
     */
    private Dimension preferredSize;
    
    /**
     * 鼠标光标在上方时的背景图片
     */
    private Icon rolloverBackgroundImage;
    
    /**
     * 鼠标光标在上方时的最佳尺寸
     */
    private Dimension rolloverPreferredSize;
    
    /**
     * 按钮被按下时的背景图片
     */
    private Icon pressedBackgroundImage;
    
    /**
     * 按钮被按下时的最佳尺寸
     */
    private Dimension pressedPreferredSize;
    
    /**
     * 按钮被禁止时的背景图片
     */
    private Icon disabledBackgroundImage;
    
    /**
     * 按钮被禁止时的最佳尺寸
     */
    private Dimension disabledPreferredSize;
    
    /**
     * 当前按钮的最佳尺寸
     */
    private volatile Dimension currentSize;
    
    /**
     * 通常状态下按钮的图标
     */
    private Icon icon;
    
    /**
     * 按钮被禁止时的图标
     */
    private Icon disabledIcon;
    
    /**
     * 按钮图标出现的位置
     */
    private IconOrientation iconOrientation = IconOrientation.WEST;
    
    /**
     * 按钮的默认尺寸
     */
    private static final Dimension DEFAULT_SIZE = new Dimension(100, 25);
    
    /**
     * 水平偏移量
     */
    private int horizontalOffset = DEFAULT_HORIZONTAL_OFFSET;
    /**
     * 默认的水平偏移量
     */
    private static final int DEFAULT_HORIZONTAL_OFFSET = 4;
    
    /**
     * 垂直偏移量
     */
    private int verticalOffset = DEFALUT_VERTICAL_OFFSET;
    
    /**
     * 默认的垂直偏移量
     */
    private static final int DEFALUT_VERTICAL_OFFSET = 2;
    
    /**
     * 显示在按钮上的文字
     */
    private String text;
    
    /**
     * 按钮文本的字体
     */
    private Font font;
    
    /**
     * 按钮文本的默认字体
     */
    private static final Font DEFAULT_FONT = Font.decode("Dialog-Plain-14");
    
    /**
     * 按钮被禁止时候文字的颜色
     */
    private Color disabledForeground;
    
    /**
     * 默认的按钮被禁止时文本的颜色
     */
    private final Color DEFAULT_DISABLED_FOREGROUND = new Color(192, 192, 192);
    
    /**
     * 按钮的状态
     */
    private volatile Status status = Status.DEFAULT;
    
    /**
     * 按钮的颜色透明度
     */
    private volatile float alpha = 1.0f;
    
    private static HashMap<RenderingHints.Key, Object> renderingHints;
    
    /**
     * 按钮执行的动作
     */
    private Action action;
    
    static {
        renderingHints = new HashMap();
        renderingHints.put(RenderingHints.KEY_ALPHA_INTERPOLATION,
                RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        renderingHints.put(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        renderingHints.put(RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        renderingHints.put(RenderingHints.KEY_DITHERING,
                RenderingHints.VALUE_DITHER_DISABLE);
        renderingHints.put(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        renderingHints.put(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC );
        renderingHints.put(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);
        renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }
    
    /** 创建一个ImageButton按钮的实例 */
    public ImageButton() {
        currentSize = DEFAULT_SIZE;
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    /**
     * 设置常规状态下按钮的背景。调用该方法会影响到按钮在常规状态下的最佳尺寸，
     * 由传入的图标参数对应的图标尺寸决定
     * @param backgroundImage 常规状态下背景图标对象
     * @throws java.lang.IllegalArgumentException 如果传入的参数为null
     */
    public void setBackgroundImage(Icon backgroundImage) throws IllegalArgumentException {
        if (backgroundImage == null) {
            throw new IllegalArgumentException("backgroundImage can't be null");
        }
        this.backgroundImage = backgroundImage;
        preferredSize = new Dimension(backgroundImage.getIconWidth(),
                backgroundImage.getIconHeight());
        currentSize = preferredSize;
    }
    
    /**
     * 设置被禁用状态下按钮的背景。调用该方法会影响到按钮在禁用状态下的最佳尺寸，
     * 由传入的图标参数对应的图标尺寸决定
     * @param disabledBackgroundImage 禁用状态下背景图标对象
     * @throws java.lang.IllegalArgumentException 如果传入的参数为null
     */
    public void setDisabledBackgroundImage(Icon disabledBackgroundImage) throws IllegalArgumentException {
        if (disabledBackgroundImage == null) {
            throw new IllegalArgumentException(
                    "disabledBackgroundImage can't be null");
        }
        this.disabledBackgroundImage = disabledBackgroundImage;
        disabledPreferredSize = new Dimension(disabledBackgroundImage
                .getIconWidth(), disabledBackgroundImage.getIconHeight());
    }
    
    /**
     * 设置在被按下状态时按钮的背景。调用该方法会影响到按钮在被按下状态时的最佳尺寸，
     * 由传入的图标参数对应的图标尺寸决定
     * @param pressedBackgroundImage 被按下时的背景图标对象
     * @throws java.lang.IllegalArgumentException 如果传入的参数为null
     */
    public void setPressedBackgroundImage(Icon pressedBackgroundImage)throws IllegalArgumentException {
        if (pressedBackgroundImage == null) {
            throw new IllegalArgumentException(
                    "pressedBackgroundImage can't be null");
        }
        this.pressedBackgroundImage = pressedBackgroundImage;
        pressedPreferredSize = new Dimension(pressedBackgroundImage
                .getIconWidth(), pressedBackgroundImage.getIconHeight());
    }
    /**
     * 设置鼠标指针在其上方时按钮的背景。调用该方法会影响到按钮在鼠标指针在其上方
     * 时的最佳尺寸，由传入的图标参数对应的图标尺寸决定
     * @param rolloverBackgroundImage 鼠标指针在其上方时的背景图标对象
     * @throws java.lang.IllegalArgumentException 如果传入的参数为null
     */
    public void setRolloverBackgroundImage(Icon rolloverBackgroundImage) throws IllegalArgumentException {
        if (rolloverBackgroundImage == null) {
            throw new IllegalArgumentException(
                    "rolloverBackgroundImage can't be null");
        }
        this.rolloverBackgroundImage = rolloverBackgroundImage;
        rolloverPreferredSize = new Dimension(rolloverBackgroundImage
                .getIconWidth(), rolloverBackgroundImage.getIconHeight());
    }
    
    /**
     * 设置水平偏移量。
     * @param horizontalOffset 水平偏移量
     * @throws java.lang.IllegalArgumentException 如果参数小于0，抛出此异常
     */
    public void setHorizontalOffset(int horizontalOffset) throws IllegalArgumentException{
        if(horizontalOffset < 0) {
            throw new IllegalArgumentException("horizontalOffset must >=0");
        }
        this.horizontalOffset = horizontalOffset;
    }
    
    /**
     * 设置垂直偏移量
     * @param verticalOffset 垂直偏移量
     * @throws java.lang.IllegalArgumentException 如果参数小于0，抛出此异常
     */
    public void setVerticalOffset(int verticalOffset) throws IllegalArgumentException{
        if(verticalOffset < 0) {
            throw new IllegalArgumentException("verticalOffset must >=0");
        }
        this.verticalOffset = verticalOffset;
    }
    
    /**
     * 设置按钮的图标
     * @param icon 图标对象
     * @throws java.lang.IllegalArgumentException 如果传入的参数为null，抛出此异常
     */
    public synchronized void setIcon(Icon icon) throws IllegalArgumentException {
        if(icon == null) {
            throw new IllegalArgumentException(
                    "icon can't be null");
        }
        this.icon = icon;
    }
    
    /**
     * 设置按钮在被禁用时显示的图标
     * @param disabledIcon 图标对象
     * @throws java.lang.IllegalArgumentException 如果传入的参数为null，抛出此异常
     */
    public synchronized void setDisabledIcon(Icon disabledIcon) throws IllegalArgumentException {
        if(disabledIcon == null) {
            throw new IllegalArgumentException(
                    "disabledIcon can't be null");
        }
        this.disabledIcon = disabledIcon;
    }
    
    public void setIconOrientation(IconOrientation iconOrientation) throws IllegalArgumentException{
        if(iconOrientation == null) {
            throw new IllegalArgumentException(
                    "iconOrientation can't be null");
        }
        this.iconOrientation = iconOrientation;
    }
    
    public void setDisabledForeground(Color disabledForeground) {
        this.disabledForeground = disabledForeground;
    }
    
    /**
     * 设置按钮的透明度
     * @param alpha 透明度。范围在0.0f~1.0f之间。0.0f为完全透明，1.0f为完全显示
     * @throws java.lang.IllegalArgumentException 如果不在0.0f~1.0f之间会抛出此异常
     */
    public synchronized void setAlpha(float alpha)throws IllegalArgumentException{
        if (alpha < 0f || alpha > 1.0f) {
            throw new IllegalArgumentException(
                    "alpha value must between 0.0 and 1.0");
        }
        this.alpha = alpha;
        repaint();
    }
    
    /**
     * 设置按钮显示的文本
     * @param text 显示的文本字符串
     */
    public synchronized  void setText(String text) {
        if (text != null) {
            this.text = text;
            repaint();
        }
    }
    
    /**
     * 返回按钮的当前字体，如果之前没有设置字体，则返回默认字体
     * @return 按钮的当前字体
     */
    public Font getFont() {
        if(font == null) {
            return DEFAULT_FONT;
        }
        return font;
    }
    
    /**
     * 设置按钮的当前字体
     * @param font 字体实例
     */
    public void setFont(Font font) {
        this.font = font;
        super.setFont(font);
    }
    /**
     * 指定这个按钮的<CODE>动作</CODE>
     * @param action 按钮的<CODE>动作</CODE>
     */
    public void setAction(Action action) {
        this.action = action;
    }
    
    /**
     * 覆盖<CODE>JComponent.setEnabled(boolean enabled)</CODE>，设置是否按钮可用或被禁止
     * @param enabled 如果按钮可用则为true，否则为false
     * @see java.awt.Component#isEnabled
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (isEnabled() != enabled) {
            if (disabledPreferredSize != null
                    && !disabledPreferredSize.equals(currentSize)) {
                currentSize = disabledPreferredSize;
                revalidate();
            }
        }
    }
    
    /**
     * 绘制按钮边框
     * @param g 图形上下文
     * @deprecated 此方法只在内部被调用，外部不要显式调用
     */
    @Override
    protected void paintBorder(Graphics g) {
        
    }
    /**
     * 绘制子组件
     * @param g 图形上下文
     * @deprecated 此方法只在内部被调用，外部不要显式调用
     */
    @Override
    protected void paintChildren(Graphics g) {
        
    }
    /**
     * 绘制按钮
     * @param g 图形上下文
     * @deprecated 此方法只在内部被调用，外部不要显式调用
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.addRenderingHints(renderingHints);
        if(alpha < 1.0f) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                    alpha));
        }
        drawBackgroundImage(g2d);
        drawIcon(g2d);
        drawText(g2d);
        g2d.dispose();
    }
    
    private void drawIcon(final Graphics2D g2d) {
        Icon currentIcon = isEnabled() ? icon : disabledIcon;
        if(currentIcon == null) {
            return;
        }
        int w = currentIcon.getIconWidth();
        int h = currentIcon.getIconHeight();
        int offsetX = 0;
        int offsetY = 0;
        switch (iconOrientation) {
            case WEST:
                offsetX = horizontalOffset;
                offsetY = getHeight() / 2 - h / 2;
                break;
            case EAST:
                offsetX = getWidth() - horizontalOffset - w;
                offsetY = getHeight() / 2 - h / 2;
                break;
            case NORTH:
                offsetX = getWidth() / 2 - w / 2;
                offsetY = verticalOffset;
                break;
            case SOUTH:
                offsetX = getWidth() / 2 - w / 2;
                offsetY = getHeight() - verticalOffset - h;
                break;
            case NORTH_WEST:
                offsetX = horizontalOffset;
                offsetY = verticalOffset;
                break;
            case NORTH_EAST:
                offsetX = getWidth() - horizontalOffset - w;
                offsetY = verticalOffset;
                break;
            case SOUTH_WEST:
                offsetX = horizontalOffset;
                offsetY = getHeight() - verticalOffset - h;
                break;
            case SOUTH_EAST:
                offsetX = getWidth() - horizontalOffset - w;
                offsetY = getHeight() - verticalOffset - h;
                break;
            case CENTER:
                offsetX = getWidth() / 2 - w / 2;
                offsetY = getHeight() / 2 - h / 2;
                break;
        }
        currentIcon.paintIcon(this,g2d,offsetX,offsetY);
    }
    
    private void drawBackgroundImage(final Graphics2D g2d) {
        if (!isEnabled()) {
            if (disabledBackgroundImage != null) {
                disabledBackgroundImage.paintIcon(this, g2d, 0, 0);
            }
            return;
        }
        switch (status) {
            case ROLLOVER:
                if (rolloverBackgroundImage != null) {
                    rolloverBackgroundImage.paintIcon(this, g2d, 0, 0);
                } else if (backgroundImage != null) {
                    backgroundImage.paintIcon(this, g2d, 0, 0);
                }
                break;
            case PRESSED:
                if (pressedBackgroundImage != null) {
                    pressedBackgroundImage.paintIcon(this, g2d, 0, 0);
                } else if (backgroundImage != null) {
                    backgroundImage.paintIcon(this, g2d, 0, 0);
                }
                break;
            case PRESSED_EXIT:
            default:
                if (backgroundImage != null) {
                    backgroundImage.paintIcon(this, g2d, 0, 0);
                }
                break;
        }
    }
    
    private void drawText(final Graphics2D g2d) {
        if(text == null || text.isEmpty()) {
            return;
        }
        Font font = getFont();
        FontMetrics fm = getFontMetrics(font);
        TextLayout textLayout = new TextLayout(text, font, g2d
                .getFontRenderContext());
        AffineTransform affineTransform = AffineTransform
                .getTranslateInstance(
                (getWidth() - fm.stringWidth(text)) / 2,
                getHeight() / 2 + fm.getHeight() / 4);
        Shape textShape = textLayout.getOutline(affineTransform);
        if(isEnabled()) {
            g2d.setPaint(getForeground());
        } else {
            g2d.setPaint(disabledForeground != null ? disabledForeground
                    : DEFAULT_DISABLED_FOREGROUND);
        }
        g2d.fill(textShape);
        g2d.draw(textShape);
    }
    
    /**
     * 鼠标单击事件的处理
     * @param e 鼠标事件
     * @deprecated 此方法只在内部被调用，外部不要显式调用
     */
    public void mouseClicked(MouseEvent e) {
        
    }
    
    /**
     * 按钮按下时的处理
     * @param e 鼠标事件
     * @deprecated 此方法只在内部被调用，外部不要显式调用
     */
    public void mousePressed(MouseEvent e) {
        if (!isEnabled()) {
            return;
        } else if(!SwingUtilities.isLeftMouseButton(e)) {
            return;
        }
        status = Status.PRESSED;
        repaint();
        if (pressedPreferredSize != null
                && !pressedPreferredSize.equals(currentSize)) {
            currentSize = pressedPreferredSize;
            revalidate();
        }
    }
    
    /**
     * 鼠标抬起时的处理
     * @param e 鼠标事件
     * @deprecated 此方法只在内部被调用，外部不要显式调用
     */
    public void mouseReleased(MouseEvent e) {
        if (!isEnabled()) {
            return;
        } else if(!SwingUtilities.isLeftMouseButton(e)) {
            return;
        }
        if (e.getX() > 0 && e.getY() > 0 && e.getX() < getPreferredSize().width
                && e.getY() < getPreferredSize().height) {
            status = Status.ROLLOVER;
            if (rolloverPreferredSize != null
                    && !rolloverPreferredSize.equals(currentSize)) {
                currentSize = rolloverPreferredSize;
            }
            try {
                doAction(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            status = Status.DEFAULT;
            if (preferredSize != null && !preferredSize.equals(currentSize)) {
                currentSize = preferredSize;
            }
        }
        repaint();
        revalidate();
    }
    
    /**
     * 鼠标进入时的处理
     * @param e 鼠标事件
     * @deprecated 此方法只在内部被调用，外部不要显式调用
     */
    public void mouseEntered(MouseEvent e) {
        if (!isEnabled()) {
            return;
        }
        status = (status == Status.PRESSED_EXIT) ? Status.PRESSED
                : Status.ROLLOVER;
        repaint();
        if (rolloverPreferredSize != null
                && !rolloverPreferredSize.equals(currentSize)) {
            currentSize = rolloverPreferredSize;
            revalidate();
        }
    }
    
    /**
     * 鼠标离开时的处理
     * @param e 鼠标事件
     * @deprecated 此方法只在内部被调用，外部不要显式调用
     */
    public void mouseExited(MouseEvent e) {
        if (!isEnabled()) {
            return;
        }
        status = (status == Status.PRESSED) ? Status.PRESSED_EXIT
                : Status.DEFAULT;
        repaint();
        if (preferredSize != null && !preferredSize.equals(currentSize)) {
            currentSize = preferredSize;
            revalidate();
        }
    }
    
    /**
     * 鼠标拖拽时的处理
     * @param e 鼠标事件
     * @deprecated 此方法只在内部被调用，外部不要显式调用
     */
    public void mouseDragged(MouseEvent e) {
    }
    
    /**
     * 鼠标在按钮上方移动时的处理
     * @param e 鼠标事件
     * @deprecated 此方法只在内部被调用，外部不要显式调用
     */
    public void mouseMoved(MouseEvent e) {
    }
    
    /**
     * 执行按钮的动作
     * @param e 鼠标事件
     * @throws java.lang.Exception 如果执行的业务代码抛出的异常
     */
    private void doAction(MouseEvent e) throws Exception{
        if (!isEnabled()) {
            return;
        }
        if (action != null) {
            ActionEvent ae = new ActionEvent(e.getSource(), e.getID(), "", e
                    .getWhen(), e.getModifiers());
            action.actionPerformed(ae);
        }
    }
    
    /**
     * 返回当前鼠标状态的最佳尺寸。这个值是不固定的，随着鼠标针对按钮的不同状态这个值会随时
     * 改变，一般地这个方法只被布局管理器所调用。
     * @return 当前鼠标状态的最佳尺寸
     */
    @Override
    public Dimension getPreferredSize() {
        return currentSize;
    }
    
    /**
     * 按钮各个状态的枚举
     *
     * @author 刘一童
     *
     */
    private enum Status {
        /**
         * 默认状态
         */
        DEFAULT,
        /**
         * 鼠标移动到按钮之上
         */
        ROLLOVER,
        /**
         * 在按钮上按下鼠标左键
         */
        PRESSED,
        /**
         * 在按钮上按下鼠标左键后鼠标光标移开按钮区域
         */
        PRESSED_EXIT;
    }
    
    /**
     * 图标在按钮上出现位置的枚举，此按钮只允许放置一个图标，这也符合大多数按钮的规范。
     * @author 刘一童
     */
    public enum IconOrientation {
        /**
         * 图标位于按钮的正左
         */
        WEST,
        /**
         * 图标位于按钮的正北
         */
        NORTH,
        /**
         * 图标位于按钮的正右
         */
        EAST,
        /**
         * 图标位于按钮的正南
         */
        SOUTH,
        /**
         * 图标位于按钮的左上方
         */
        NORTH_WEST,
        /**
         * 图标位于按钮的左下方
         */
        SOUTH_WEST,
        /**
         * 图标位于按钮的右上方
         */
        NORTH_EAST,
        /**
         * 图标位于按钮的右下方
         */
        SOUTH_EAST,
        /**
         * 图标位于按钮的中心
         */
        CENTER;
    }
}