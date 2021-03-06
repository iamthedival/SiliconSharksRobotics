package com.SiliconSharks.Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Switch extends JPanel {
    private boolean switchstate;
    private Font font30Pt = new Font("Helvetica" , Font.PLAIN, 15);
    private FontMetrics fontMetrics;
    private double position;
    private String label;
    private int start,height;
    private boolean checkedstate;
    private String txtoff, txton;
    public Switch(String label){
        this(label,false, "OFF","ON");
    }
    public Switch(String label, boolean switchstate) { this(label,switchstate,"OFF","ON");}
    public Switch(String label, boolean switchstate, String txtoff, String txton){
        this.switchstate = switchstate;
        checkedstate =switchstate;
        if(switchstate) position = 0;
        else position = 1;
        this.label = label;
        fontMetrics = getFontMetrics(font30Pt);
        //start = fontMetrics.stringWidth(label)+3;
        start = 0;
        height = fontMetrics.getHeight();
        addMouseListener(new MouseEventListener());
        this.txtoff = txtoff;
        this.txton = txton;
    }
    public void updateposition(){
        double idealposition = 1;
        if(switchstate) idealposition--;
        boolean positionchanged = !(position == idealposition);
        double change = (idealposition - position)/5;
        if(Math.abs(change) > 0.05){
            change = 0.05 * Math.abs(change)/change;
            position += change;
        }else if(Math.abs(change) < 0.005){
            position = idealposition;
        }else{
            position += change;
        }
        if(positionchanged) repaint(start,0,84,height+12);
    }
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);
        g2.fillRoundRect(start,0,84,height+8,10,10);
        g2.setColor(Color.RED);
        g2.fillRoundRect(start+2,2,45,height+4,8,8);
        g2.setColor(Color.GREEN);
        g2.fillRoundRect(start+37,2,45,height+4,8,8);
        g2.setColor(Color.RED);
        g2.fillRect(start+32,2,10,height+4);
        g2.setColor(Color.BLACK);
        g2.setFont(font30Pt);
        g2.setColor(Color.WHITE);
        g2.drawString(label,85,height);
        g2.setColor(Color.BLACK);
        g2.drawString(txtoff,start+21-fontMetrics.stringWidth(txtoff)/2,height);
        g2.drawString(txton,start+60-fontMetrics.stringWidth(txton)/2,height);
        g2.fillRoundRect(start+(int)(position*42),0,42,height+8,10,10);
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRoundRect(start+2+(int)(position*42),2,38,height+4,8,8);
    }
    public boolean getState(){
        return switchstate;
    }
    private class MouseEventListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            switchstate = !switchstate;
        }
    }
}
