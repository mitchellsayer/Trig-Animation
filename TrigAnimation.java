/*
    Mitchell Sayer
    4/29/2015
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

public class TrigAnimation extends JFrame implements ActionListener
{

	private static final long serialVersionUID = 1L;
	private static final int Y_CENTER = 250;
    private static final int X_SCALE = ((360)/4);
    private static final int BORDER = 20;
    private static final int Y_SCALE = (Y_CENTER/2)-BORDER;
    int x = BORDER;
    Timer t = new Timer(15,this);

    public TrigAnimation()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationByPlatform(true);
        setSize(900, 500);
        setTitle("Dat Sexy Trig Animation");
        setBackground(Color.white);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {	
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawLine(BORDER, Y_CENTER, 360 + BORDER, Y_CENTER);
        for (int i=BORDER;i<360+2*BORDER;i+=X_SCALE) {
            g2d.drawLine(i, Y_CENTER + 5, i, Y_CENTER - 5); //Draw X hash Marks
        }
        g2d.drawLine(BORDER, 502 - 2 * BORDER, BORDER, 2 * BORDER);
        for (int j=2*BORDER;j<360+BORDER;j+=Y_SCALE) {
            g2d.drawLine(15, j, 25, j);  //Draw Y hash marks
        }
        g2d.setColor(Color.green);
            int sinY = (int) (Y_CENTER-Math.sin(Math.toRadians(x-BORDER))*Y_SCALE); //Sine graph
            g2d.drawRect(x, sinY, 1, 1);
        g2d.setColor(Color.RED);
            int cosY = (int) (Y_CENTER-Math.cos(Math.toRadians(x - BORDER))*Y_SCALE); //Cosine graph
            g2d.drawRect(x, cosY, 1, 1);
        if (x>380){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            g2d.setColor(Color.white); //Reset after one iteration
            g2d.fillRect(0,0,750,500);
            removeAll();
            x=BORDER;
        }

        g2d.setColor(Color.blue);
        g2d.drawOval(500, 250 - Y_SCALE, 210, 210); //Draw circle
        g2d.setColor(Color.pink);
        g2d.drawLine(605, Y_CENTER, 710, Y_CENTER);  //Draw circle reference marks
        g2d.drawLine(605,Y_CENTER,605,Y_CENTER+Y_SCALE);
        g2d.drawLine(605,Y_CENTER,605,Y_CENTER-Y_SCALE);
        g2d.drawLine(500, Y_CENTER, 710, Y_CENTER);
        g2d.setColor(Color.black);
        g2d.setStroke(new BasicStroke(2));
        Line2D lin = new Line2D.Float(605,Y_CENTER,(int)(605+105*Math.cos(Math.toRadians(-x+BORDER))),(int)(Y_CENTER+105*Math.sin(Math.toRadians(-x+BORDER))));
        g2d.draw(lin);
        //Draw rotating line
        g2d.setColor(Color.white);
        Line2D lin2 = new Line2D.Float(605,Y_CENTER,(int)(605+105*Math.cos(Math.toRadians((-x+BORDER)+1))),(int)(Y_CENTER+105*Math.sin(Math.toRadians((-x + BORDER) + 1))));
        g2d.draw(lin2);
        t.start();
    }

    public static void main(String[] args)
    {
        TrigAnimation t = new TrigAnimation();
        t.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        x++;
        repaint();
    }
}
