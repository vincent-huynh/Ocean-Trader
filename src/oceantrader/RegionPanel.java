package oceantrader;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

public class RegionPanel extends JPanel {

    protected JLabel coordDisp;
    private JLabel coordsLbl;
    protected JLabel distDisp;
    private JLabel distLbl;
    private JProgressBar healthBar;
    private JLabel jLabel1;
    private JProgressBar fuelBar;
    private JLabel lineLbl;
    private JLabel mntDockLbl;
    private JButton refuelBtn;
    private JLabel refuelLbl;
    private JButton refuelMaxBtn;
    private JSlider refuelSlider;
    private JTextField refuelSliderTxt;
    protected JLabel regionDisp;
    private JLabel regionLbl;
    protected JList<String> regionList;
    private JScrollPane regionScrollPane;
    private JButton repairBtn;
    private JLabel repairLbl;
    private JButton repairMaxBtn;
    private JSlider repairSlider;
    private JTextField repairSliderTxt;
    private JLabel shipHPLbl;
    protected JLabel techDisp;
    private JLabel techLbl;
    private JButton snapBtn;

    public RegionPanel() {
        initGUI();
    }

    protected void updateRegionList() {
        AbstractListModel regionListy = new AbstractListModel() {
            private String[] strings = Universe.getInstance().getRegionArray();

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public Object getElementAt(int i) {
                return strings[i];
            }

            private String[] getStrings() {
                return strings;
            }
        };
        regionList.setModel(regionListy);
    }

    protected void updateSliders() {
        if (OceanTrader.player != null) {
            Ship playerShip = OceanTrader.player.getShip();
            int repairMax = playerShip.getMaxHealth() - playerShip.getHealth();
            int refuelMax = playerShip.getMaxFuelCapacity() - playerShip.getFuelCapacity();
            repairSlider.setMaximum(repairMax);
            refuelSlider.setMaximum(refuelMax);
            toggle(false);
        } else {
            repairSlider.setMaximum(0);
            refuelSlider.setMaximum(0);
            toggle(true);
        }
    }

    private void initGUI() {
        declareVars();
        Font godFont = new java.awt.Font("Comic Sans MS", 0, 18);
        Font jesusFont = new java.awt.Font("Comic Sans MS", 1, 14);
        regionList.setFont(new java.awt.Font("Comic Sans MS", 0, 20));
        updateRegionList();
        updateSliders();
        setLabels(godFont, jesusFont);

        regionList.addListSelectionListener(listSelectionEvent -> {
            if (regionList.getSelectedIndex() >= 0) {
                oceantrader.Region selected = oceantrader.Universe.getInstance()
                        .regions.get(regionList.getSelectedIndex());
                updateList(selected, regionDisp, techDisp, coordDisp, distDisp);
                OceanTrader.regionDisplay.map.reloadGraphics(selected);
            }
        });

        snapBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                snapBtnPerformed();
            }
        });
        refuelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refuelBtnActionPerformed();
            }
        });

        refuelMaxBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                refuelMaxBtnActionPerformed();
            }
        });

        repairBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                repairBtnActionPerformed();
            }
        });

        repairMaxBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                repairMaxBtnActionPerformed();
            }
        });

        repairSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                if (repairSlider.getValueIsAdjusting()) {
                    int val = repairSlider.getValue();
                    repairSliderTxt.setText(val + "");
                }
            }
        });

        refuelSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                if (refuelSlider.getValueIsAdjusting()) {
                    int val = refuelSlider.getValue();
                    refuelSliderTxt.setText(val + "");
                }
            }
        });
        doNotTouch();
    }

    protected void updateHealthBar() {
        int max = OceanTrader.player.getShip().getMaxHealth();
        int curr = OceanTrader.player.getShip().getHealth();
        healthBar.setMaximum(max);
        healthBar.setValue(curr);
        healthBar.setString(String.format("%d / %d", curr, max));
    }

    protected void updateFuelBar() {
        int max = OceanTrader.player.getShip().getMaxFuelCapacity();
        int curr = OceanTrader.player.getShip().getFuelCapacity();
        fuelBar.setMaximum(max);
        fuelBar.setValue(curr);
        fuelBar.setString(String.format("%d / %d", curr, max));
    }

    protected void update() {
        updateFuelBar();
        updateHealthBar();
        updateSliders();
    }

    private void doNotTouch() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.
                    createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout
                    .Alignment.LEADING).addGroup(layout.createSequentialGroup()
                            .addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.
                                    Alignment.LEADING)
                                .addComponent(lineLbl, GroupLayout.DEFAULT_SIZE, 326,
                                    Short.MAX_VALUE)
                                .addComponent(regionScrollPane)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(regionLbl)
                                    .addGap(18, 18, 18)
                                    .addComponent(regionDisp, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(techLbl)
                                    .addGap(18, 18, 18)
                                    .addComponent(techDisp, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(distLbl)
                                    .addGap(18, 18, 18)
                                    .addComponent(distDisp, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(coordsLbl)
                                    .addGap(18, 18, 18)
                                    .addComponent(coordDisp, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(GroupLayout.
                                    Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(69, 69, 69)
                                    .addComponent(mntDockLbl))
                                .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(GroupLayout
                                        .Alignment.LEADING)
                                        .addComponent(shipHPLbl)
                                        .addGroup(layout.createParallelGroup(GroupLayout
                                            .Alignment.TRAILING)
                                            .addComponent(repairLbl)
                                            .addComponent(jLabel1)))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement
                                            .RELATED)
                                    .addGroup(layout.createParallelGroup(GroupLayout
                                            .Alignment.LEADING, false)
                                        .addComponent(healthBar, GroupLayout
                                            .DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                        .addComponent(fuelBar, GroupLayout
                                            .DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                            Short.MAX_VALUE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(22, 22, 22)
                                    .addGroup(layout.createParallelGroup(GroupLayout
                                        .Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(refuelSlider, GroupLayout
                                                .PREFERRED_SIZE, GroupLayout
                                                .DEFAULT_SIZE, GroupLayout
                                                .PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle
                                                .ComponentPlacement.RELATED)
                                            .addComponent(refuelSliderTxt, GroupLayout
                                                .PREFERRED_SIZE, 50, GroupLayout
                                                .PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(repairSlider, GroupLayout
                                                .PREFERRED_SIZE, GroupLayout
                                                .DEFAULT_SIZE, GroupLayout
                                                .PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle
                                                .ComponentPlacement.RELATED)
                                            .addComponent(repairSliderTxt, GroupLayout
                                                .PREFERRED_SIZE, 50, GroupLayout
                                                .PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(repairBtn).addGap(18, 18, 18)
                                            .addComponent(repairMaxBtn))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(refuelBtn).addGap(18, 18, 18)
                                            .addComponent(refuelMaxBtn))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(37, 37, 37).addComponent(refuelLbl)))
                            .addGap(0, 0, Short.MAX_VALUE))).addContainerGap())
                .addGroup(layout.createSequentialGroup().addGap(132, 132, 132)
                .addComponent(snapBtn).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(regionScrollPane, GroupLayout.PREFERRED_SIZE, 306,
                        GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(regionLbl).addComponent(regionDisp))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(techLbl).addComponent(techDisp))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(coordsLbl).addComponent(coordDisp))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(distLbl).addComponent(distDisp))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lineLbl).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(mntDockLbl).addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(shipHPLbl, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(healthBar, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING,
                        false)
                        .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fuelBar, GroupLayout.DEFAULT_SIZE,
                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup().addComponent(repairLbl)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(repairSlider, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(repairSliderTxt, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(repairBtn).addComponent(repairMaxBtn))
                        .addGap(35, 35, 35).addComponent(refuelLbl)
                        .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(refuelSlider, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(refuelSliderTxt, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(refuelBtn).addComponent(refuelMaxBtn))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                        62, Short.MAX_VALUE).addComponent(snapBtn).addContainerGap())
        );
    }

    private void declareVars() {
        regionScrollPane = new JScrollPane();
        regionList = new JList<>();
        regionLbl = new JLabel();
        techLbl = new JLabel();
        coordsLbl = new JLabel();
        distLbl = new JLabel();
        regionDisp = new JLabel();
        techDisp = new JLabel();
        coordDisp = new JLabel();
        distDisp = new JLabel();
        lineLbl = new JLabel();
        mntDockLbl = new JLabel();
        shipHPLbl = new JLabel();
        healthBar = new JProgressBar();
        jLabel1 = new JLabel();
        fuelBar = new JProgressBar();
        repairLbl = new JLabel();
        repairSlider = new JSlider();
        repairSliderTxt = new JTextField();
        refuelLbl = new JLabel();
        refuelSlider = new JSlider();
        refuelSliderTxt = new JTextField();
        refuelBtn = new JButton();
        repairBtn = new JButton();
        repairMaxBtn = new JButton();
        refuelMaxBtn = new JButton();
        snapBtn = new JButton();
    }

    private void setLabels(Font godFont, Font jesusFont) {
        regionScrollPane.setViewportView(regionList);
        regionLbl.setFont(godFont);
        regionLbl.setText("Region:");

        techLbl.setFont(godFont);
        techLbl.setText("Tech Level:");

        coordsLbl.setFont(godFont);
        coordsLbl.setText("Coordinates:");

        distLbl.setFont(godFont);
        distLbl.setText("Distance:");

        regionDisp.setFont(godFont);
        regionDisp.setText(" ");

        techDisp.setFont(godFont);
        techDisp.setText(" ");

        coordDisp.setFont(godFont);
        coordDisp.setText(" ");

        distDisp.setFont(godFont);
        distDisp.setText(" ");

        lineLbl.setText("______________________________________________________");

        mntDockLbl.setFont(new java.awt.Font("Comic Sans MS", 1, 24));
        mntDockLbl.setText("Maintenance Dock");

        shipHPLbl.setFont(jesusFont);
        shipHPLbl.setText("Ship Health:");

        healthBar.setFont(jesusFont);
        healthBar.setStringPainted(true);

        jLabel1.setFont(jesusFont);
        jLabel1.setText("Ship Fuel:");

        fuelBar.setFont(jesusFont);
        fuelBar.setStringPainted(true);

        repairLbl.setFont(jesusFont);
        repairLbl.setText("Repair");

        repairSliderTxt.setFont(new java.awt.Font("Comic Sans MS", 0, 14));
        repairSliderTxt.setText(repairSlider.getValue() + "");
        repairSliderTxt.setEditable(false);

        refuelLbl.setFont(jesusFont);
        refuelLbl.setText("Refuel");

        refuelSliderTxt.setFont(new java.awt.Font("Comic Sans MS", 0, 14));
        refuelSliderTxt.setText(refuelSlider.getValue() + "");
        refuelSliderTxt.setEditable(false);

        repairBtn.setText("Repair");
        refuelBtn.setText("Refuel");
        repairMaxBtn.setText("Repair Max");
        refuelMaxBtn.setText("Refuel Max");

        refuelSlider.setMinimum(0);
        repairSlider.setMinimum(0);

        snapBtn.setText("Snap");
        snapBtn.setFont(jesusFont);
        snapBtn.setVisible(false);
    }

    protected void updateList(Region selected, JLabel regionDisp, JLabel techDisp,
                              JLabel coordDisp, JLabel distDisp) {
        regionDisp.setText(selected.getName());
        techDisp.setText(selected.getTechLevel().toString());
        coordDisp.setText("X: " + selected.getxCoord() + " | Y: " + selected.getyCoord());
        distDisp.setText(String.format("%.2f Nautical Miles",
                Region.calcDistance(OceanTrader.player, selected)));
    }

    private void refuelBtnActionPerformed() {
        int val = refuelSlider.getValue();
        ShipHandler.refuelShip(val);
    }

    private void refuelMaxBtnActionPerformed() {
        ShipHandler.refuelShip(-1);
    }

    private void repairBtnActionPerformed() {
        int val = repairSlider.getValue();
        ShipHandler.repairShip(val);
    }

    private void repairMaxBtnActionPerformed() {
        ShipHandler.repairShip(-1);
    }

    private void toggleRepair() {
        if (OceanTrader.player != null) {
            Ship playerShip = OceanTrader.player.getShip();
            int diff = playerShip.getMaxHealth() - playerShip.getHealth();
            if (diff == 0) {
                repairBtn.setEnabled(false);
                repairMaxBtn.setEnabled(false);
            } else {
                repairBtn.setEnabled(true);
                repairMaxBtn.setEnabled(true);
            }
        }
    }

    private void toggleRefuel() {
        if (OceanTrader.player != null) {
            Ship playerShip = OceanTrader.player.getShip();
            int diff = playerShip.getMaxFuelCapacity() - playerShip.getFuelCapacity();
            if (diff == 0) {
                refuelBtn.setEnabled(false);
                refuelMaxBtn.setEnabled(false);
            } else {
                refuelBtn.setEnabled(true);
                refuelMaxBtn.setEnabled(true);
            }
        }
    }

    private void toggle(boolean start) {
        if (!start) {
            toggleRefuel();
            toggleRepair();
        } else {
            refuelBtn.setEnabled(false);
            refuelMaxBtn.setEnabled(false);
            repairBtn.setEnabled(false);
            repairMaxBtn.setEnabled(false);
        }
    }

    private void snapBtnPerformed() {
        OceanTrader.endGame(0);
    }

    protected void enableSnap() {
        snapBtn.setVisible(true);
    }
}