package oceantrader;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Dimension;

public class RegionPanel {

    protected JPanel panel;
    protected AbstractListModel regionListy;
    protected JList regionList;

    public RegionPanel() {
        panel = new JPanel();
        initGUI();
    }

    @SuppressWarnings("unchecked")
    private void initGUI() {

        regionList = new JList();
        JScrollPane jScrollPane1 = new JScrollPane();
        JLabel regionNameLbl = new JLabel("Region:");
        JLabel regionTechLbl = new JLabel("Tech Level:");
        JLabel regionCoordsLbl = new JLabel("Coordinates:");
        JLabel distanceLbl = new JLabel("Distance:");

        JTextField regionName = new JTextField();
        regionName.setEditable(false);
        JTextField regionTech = new JTextField();
        regionTech.setEditable(false);
        JTextField regionCoords = new JTextField();
        regionCoords.setEditable(false);
        JTextField distance = new JTextField();
        distance.setEditable(false);

        panel.setMaximumSize(new Dimension(350, 950));
        panel.setPreferredSize(new Dimension(350, 950));

        updateRegionList();

        regionList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                Region selected = Universe.getInstance()
                        .regions.get(regionList.getSelectedIndex());
                regionName.setText(selected.getName());
                regionTech.setText(selected.getTechLevel().toString());
                regionCoords.setText("X: " + selected.getxCoord()
                        + " | Y: " + selected.getyCoord());
                distance.setText(String.format("%.2f nautical miles",
                        selected.calcDistance(OceanTrader.player, selected)));
            }
        });

        jScrollPane1.setViewportView(regionList);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addContainerGap()
            .addGroup(layout.createParallelGroup(GroupLayout
                    .Alignment.LEADING).addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup().addGap(11, 11, 11)
                    .addGroup(layout.createParallelGroup(GroupLayout
                            .Alignment.LEADING).addGroup(layout
                            .createSequentialGroup()
                            .addComponent(regionCoordsLbl).addPreferredGap(
                                    LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(regionCoords,
                                    GroupLayout.DEFAULT_SIZE, 218,
                                    Short.MAX_VALUE))

                    .addGroup(layout.createSequentialGroup()
                            .addComponent(regionNameLbl).addPreferredGap(
                                    LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(regionName))

                    .addGroup(layout.createSequentialGroup()
                            .addComponent(distanceLbl).addPreferredGap(
                                    LayoutStyle.ComponentPlacement
                                        .UNRELATED).addComponent(distance))

                    .addGroup(layout.createSequentialGroup()
                                    .addComponent(regionTechLbl)
                            .addPreferredGap(LayoutStyle.ComponentPlacement
                                    .UNRELATED).addComponent(regionTech)))))
                    .addContainerGap())
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 486,
                    GroupLayout.PREFERRED_SIZE).addGap(53, 53, 53)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment
                    .BASELINE).addComponent(regionNameLbl)
                    .addComponent(regionName, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout
                                    .PREFERRED_SIZE)).addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment
                    .BASELINE).addComponent(regionTechLbl)
                    .addComponent(regionTech, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout
                                    .PREFERRED_SIZE)).addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment
                    .BASELINE).addComponent(regionCoordsLbl)
                    .addComponent(regionCoords, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout
                                    .PREFERRED_SIZE)).addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment
                    .BASELINE).addComponent(distanceLbl)
                    .addComponent(distance, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout
                                    .PREFERRED_SIZE))
            .addContainerGap(283, Short.MAX_VALUE))
        );
    }

    protected void updateRegionList() {
        regionListy = new AbstractListModel() {
            private String[] strings = Universe.getInstance().getRegionArray();

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public Object getElementAt(int i) {
                return strings[i];
            }

            public String[] getStrings() {
                return strings;
            }
        };
        regionList.setModel(regionListy);
    }
}