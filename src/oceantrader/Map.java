package oceantrader;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Iterator;

public class Map extends JPanel {

    private int widthSize = 600;
    private int heightSize = 850;
    private int mapWidth = widthSize;   // - 100;  This portion is here for
    // later to pretty up the program by adding borders
    private int mapHeight = heightSize; // - 100; This portion is here for
    // later to pretty up the program by adding borders
    private int radius = 15;  //Modify as needed to change dot size
    protected static HashMap<Region, Color> regions = new HashMap();
    private Universe instance;

    protected static final Color DEFAULT_POINT_COLOR = Color.GREEN;
    protected static final Color SELECTED_POINT_COLOR = new Color(255, 127, 0);
    protected static final Color CURR_POINT_COLOR = new Color(255, 255, 0);
    protected static final Color BACKGROUND_COLOR = new Color(79, 88, 138);
    private static final Font DEFAULT_FONT = new Font("Tahoma", Font.PLAIN, 20);

    protected JLabel current = new JLabel();
    protected JLabel xCoor = new JLabel();
    protected JLabel yCoor = new JLabel();
    protected JLabel regionName = new JLabel();
    protected static Region selected = null; //Curent region selected

    /**
     * Constructor for Map, sets up all variables and fields
     * Follows up by
     */
    protected Map() {
        this.add(current);
        this.add(regionName);
        this.add(xCoor);
        this.add(yCoor);
        this.setBackground(BACKGROUND_COLOR);
        this.setPreferredSize(new Dimension(mapWidth, mapHeight));
        this.instance = Universe.getInstance();
        setup();
        loadMap();
    }

    /**
     * An extension to the constructor to make code more readable
     * Sets up the intial Font, Color, and Pulls regions from Universe
     */
    private void setup() {
        this.current.setFont(DEFAULT_FONT);
        this.yCoor.setFont(DEFAULT_FONT);
        this.xCoor.setFont(DEFAULT_FONT);
        this.regionName.setFont(DEFAULT_FONT);
        this.current.setForeground(Color.YELLOW);
        this.yCoor.setForeground(Color.WHITE);
        this.xCoor.setForeground(Color.WHITE);
        this.regionName.setForeground(DEFAULT_POINT_COLOR);
        for (Region region : instance.regions) {
            regions.put(region, DEFAULT_POINT_COLOR);
        }
    }

    /**
     * Handles the backend logic behind recoloring the map points based
     * on what ths user selects, then reloads the Map
     * @param newChoice The new region the User selects
     */
    protected void reloadGraphics(Region newChoice) {
        regions.replace(selected, SELECTED_POINT_COLOR, DEFAULT_POINT_COLOR);
        regions.replace(newChoice, DEFAULT_POINT_COLOR, SELECTED_POINT_COLOR);
        regions.replace(OceanTrader.player.getRegion(), DEFAULT_POINT_COLOR, CURR_POINT_COLOR);
        selected = newChoice;
        this.repaint();
    }

    /**
     * Detects if the user clicks a point on the Map, and if so
     * passes onto the mouseClickedEvent()
     */
    private void loadMap() {
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                mouseClickedEvent(mouseEvent);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                mouseClickedEvent(mouseEvent);
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }

    /**
     * Custom MouseListener Event that checks if the user has clicked on or
     * within a reasonable distance around a point, if so, selects it, and calls
     * reloadGraphics() To highlight the point selected. Also updates xCoor,
     * yCoor, and regionName
     * @param e MouseEvent
     */
    private void mouseClickedEvent(MouseEvent e) {
        double x = e.getX() * 400.0 / mapWidth - 200;
        double y = e.getY() * 400.0 / mapHeight - 200;
        for (Region region : Universe.getInstance().regions) {
            double distance = Universe.getInstance().distBetween(x, y,
                    region.getxCoord(), region.getyCoord());
            if (distance < 10) {
                reloadGraphics(region);
                RegionPanel regionPanel = OceanTrader.regionDisplay.regionPanel;
                regionPanel.updateList(selected, regionPanel.regionDisp,
                        regionPanel.techDisp, regionPanel.coordDisp, regionPanel.distDisp);
                regionPanel.regionList.setSelectedIndex(Universe.getInstance()
                        .regions.indexOf(region));
            }
        }
    }

    protected void updateMapTitle(Region region) {
        this.current.setText("Your Current Location: ");
        this.regionName.setText(region.getName());
        this.xCoor.setText("[X: " + region.getxCoord());
        this.yCoor.setText("| Y: " + region.getyCoord() + "]");
    }

    /**
     * Overridden paintComponent method in JPanel to draw points based
     * on the regions in Universe
     * @param g Graphics
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double scaleWidthFactor = (mapWidth / 400.0);
        double scaleHeightFactor = (mapHeight / 400.0);
        Iterator it = regions.entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry<Region, Color> entryItem = (java.util.Map.Entry) it.next();
            Region region = entryItem.getKey();
            g.setColor(entryItem.getValue());
            Shape circle = new Ellipse2D.Double(((int) ((region.getxCoord()
                    + 200) * scaleWidthFactor)), (int) ((region.getyCoord()
                    + 200) * scaleHeightFactor), radius, radius);
            ((Graphics2D) g).fill(circle);
        }
    }
}