package utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class RoundedPanel extends JPanel {
    private int radius = 60; 

    public RoundedPanel() {
        setOpaque(false);
        setBorder(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d.setColor(getBackground());
//        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape bendungan = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radius, radius);
        g2d.clip(bendungan);
        g2d.setColor(getBackground());
        g2d.fill(bendungan);
        
        super.paintComponent(g);
    }
}