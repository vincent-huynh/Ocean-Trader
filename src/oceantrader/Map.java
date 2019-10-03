package oceantrader;

import javafx.scene.shape.Circle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

public class Map extends JPanel {

    private Graphics gMain;
    private int widthSize = 700;
    private int heightSize = 950;
    private int mapWidth = widthSize - 100;
    private int mapHeight = heightSize - 100;

    protected JLabel xCoor;
    protected JLabel yCoor;
    protected JLabel regionName;
    protected Ellipse2D selected;

    public Map() {
        this.xCoor = new JLabel();
        this.yCoor = new JLabel();
        this.regionName = new JLabel();
        this.add(xCoor);
        this.add(yCoor);
        this.add(regionName);
        this.setBackground(Color.YELLOW);
        this.setPreferredSize(new Dimension(mapWidth, mapHeight));
        LoadMap();
    }

    public void LoadMap() {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println("mouse clicked");
                mouseClickedEvent(mouseEvent);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {}

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                mouseClickedEvent(mouseEvent);
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {}

            @Override
            public void mouseExited(MouseEvent mouseEvent) {}
        });
    }

    public void mouseClickedEvent(MouseEvent e) {
        double x = e.getX() * 400.0 / mapWidth - 200;
        double y = e.getY() * 400.0 / mapHeight - 200;
        for (Region region : Universe.getInstance().regions) {
            //TODO: Change to distBetween instead of current method
            double distance = Universe.getInstance().distBetween(x, y, region.getxCoord(), region.getyCoord());
//            if (region.getxCoord() / x <= approxFactor && region.getyCoord() / y <= approxFactor) {
            if (distance < 10) {
                if (selected != null) {
                    drawCircle(gMain, (int) selected.getCenterX(), (int) selected.getCenterY(), 10, Color.RED);
                }
                this.xCoor.setText("x is " + region.getxCoord());
                this.yCoor.setText("y is " + region.getyCoord());
                drawCircle(gMain, region.getxCoord(), region.getyCoord(), 10, Color.BLUE);
                this.regionName.setText(region.getName());
            }
        }
    }

    public void paintComponent(Graphics g) {
        this.setGraphics(g);
        super.paintComponent(g);
        int radius = 10; //Modify this to change the size of the dots
        g.setColor(Color.RED);
        double scaleWidthFactor = (mapWidth / 400.0);
        double scaleHeightFactor = (mapHeight / 400.0);
        Universe uni = Universe.getInstance();
        for (Region region : uni.regions) {
            if (region != null) {
                Shape circle = new Ellipse2D.Double(((int)((region.getxCoord() + 200) * scaleWidthFactor)), (int)((region.getyCoord() + 200) * scaleHeightFactor), radius, radius);
                ((Graphics2D) g).fill(circle);
            }
        }
    }

    public void drawCircle(Graphics g, int x, int y, int radius, Color color) {
        super.paintComponent(g);
        double scaleWidthFactor = (mapWidth / 400.0);
        double scaleHeightFactor = (mapHeight / 400.0);
        g.setColor(color);
        this.selected = new Ellipse2D.Double(((int)((x + 200) * scaleWidthFactor)), (int)((y + 200) * scaleHeightFactor), radius, radius);
        ((Graphics2D) g).fill(this.selected);
    }

    public int getWidthSize() {
        return this.widthSize;
    }

    public int getHeightSize() {
        return this.heightSize;
    }

    public void setGraphics(Graphics g) {
        this.gMain = g;
    }

//    public static void main (String[] args) {
//        JFrame frame = new JFrame();
//        Map2 map = new Map2();
//        frame.add(map);
//        frame.setVisible(true);
//        frame.setBackground(Color.BLUE);
//        frame.setSize(map.getWidthSize(), map.getHeightSize());
//    }
}
