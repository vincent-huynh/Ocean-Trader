//package oceantrader;
//
//import javax.swing.AbstractListModel;
//import javax.swing.BorderFactory;
//import javax.swing.GroupLayout;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextField;
//import javax.swing.LayoutStyle;
//import java.awt.Dimension;
//import java.awt.Font;
//
//public class RegionPanelOld {
//
//    protected static JList regionList;
//    protected static AbstractListModel regionListy;
//    protected static JTextField regionName;
//    protected static JTextField regionTech;
//    protected static JTextField regionCoords;
//    protected static JTextField distance;
//    protected JPanel panel;
//
//    protected RegionPanelOld() {
//        panel = new JPanel();
//        initGUI();
//    }
//
//    protected static void updateList(Region selected, JTextField regionName,
//                                     JTextField regionTech, JTextField regionCoords,
//                                     JTextField distance) {
//
//        regionName.setText(selected.getName());
//        regionTech.setText(selected.getTechLevel().toString());
//        regionCoords.setText("X: " + selected.getxCoord() + " | Y: " + selected.getyCoord());
//        distance.setText(String.format("%.2f Nautical Miles",
//                Region.calcDistance(OceanTrader.player, selected)));
//    }
//
//    private static void jTextFieldEdit(JTextField jTextField) {
//        jTextField.setEditable(false);
//        jTextField.setBorder(BorderFactory.createEmptyBorder());
//        jTextField.setFont(new Font("Tahoma", Font.PLAIN, 18));
//    }
//
//    private static void jLabelEdit(JLabel jLabel) {
//        jLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
//    }
//
//    protected static void updateRegionList() {
//        regionListy = new AbstractListModel() {
//            private String[] strings = Universe.getInstance().getRegionArray();
//
//            @Override
//            public int getSize() {
//                return strings.length;
//            }
//
//            @Override
//            public Object getElementAt(int i) {
//                return strings[i];
//            }
//
//            private String[] getStrings() {
//                return strings;
//            }
//        };
//        regionList.setModel(regionListy);
//    }
//
//    private void initGUI() {
//
//        regionList = new JList();
//        JScrollPane jScrollPane1 = new JScrollPane();
//
//        JLabel regionNameLbl = new JLabel("Region:");
//        jLabelEdit(regionNameLbl);
//
//        JLabel regionTechLbl = new JLabel("Tech Level:");
//        jLabelEdit(regionTechLbl);
//
//        JLabel regionCoordsLbl = new JLabel("Coordinates:");
//        jLabelEdit(regionCoordsLbl);
//
//        JLabel distanceLbl = new JLabel("Distance:");
//        jLabelEdit(distanceLbl);
//
//        regionName = new JTextField();
//        jTextFieldEdit(regionName);
//
//        regionTech = new JTextField();
//        jTextFieldEdit(regionTech);
//
//        regionCoords = new JTextField();
//        jTextFieldEdit(regionCoords);
//
//        distance = new JTextField();
//        jTextFieldEdit(distance);
//
//        panel.setMaximumSize(new Dimension(350, 950));
//        panel.setPreferredSize(new Dimension(350, 950));
//
//        updateRegionList();
//
//        regionList.setFont(new Font("Tahoma", Font.PLAIN, 20));
//
//        regionList.addListSelectionListener(listSelectionEvent -> {
//            if (regionList.getSelectedIndex() >= 0) {
//                oceantrader.Region selected = oceantrader.Universe.getInstance()
//                        .regions.get(regionList.getSelectedIndex());
//                updateList(selected, regionName, regionTech, regionCoords, distance);
//                oceantrader.OceanTrader.regionDisplay.map.reloadGraphics(selected);
//            }
//        });
//
//        jScrollPane1.setViewportView(regionList);
//
//        GroupLayout layout = new GroupLayout(panel);
//        panel.setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                .addGroup(layout.createSequentialGroup().addContainerGap()
//                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment
//                        .LEADING).addComponent(jScrollPane1).addGroup(layout
//                        .createSequentialGroup().addGap(11, 11, 11).addGroup(layout
//                            .createParallelGroup(GroupLayout.Alignment.LEADING)
//                            .addGroup(layout.createSequentialGroup()
//                                .addComponent(regionCoordsLbl).addPreferredGap(LayoutStyle
//                                        .ComponentPlacement.RELATED).addComponent(regionCoords,
//                                        GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
//                            .addGroup(layout.createSequentialGroup()
//                                .addComponent(regionNameLbl).addPreferredGap(LayoutStyle
//                                        .ComponentPlacement.RELATED).addComponent(regionName))
//                        .addGroup(layout.createSequentialGroup()
//                            .addComponent(distanceLbl).addPreferredGap(LayoutStyle
//                            .ComponentPlacement.UNRELATED).addComponent(distance))
//                            .addGroup(layout.createSequentialGroup()
//                                    .addComponent(regionTechLbl).addPreferredGap(LayoutStyle
//                                    .ComponentPlacement.UNRELATED).addComponent(regionTech)))))
//                    .addContainerGap())
//        );
//
//        layout.setVerticalGroup(
//            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                .addGroup(layout.createSequentialGroup().addContainerGap()
//                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 486,
//                        GroupLayout.PREFERRED_SIZE).addGap(53, 53, 53).addGroup(layout
//                        .createParallelGroup(GroupLayout.Alignment.BASELINE)
//                        .addComponent(regionNameLbl).addComponent(regionName,
//                            GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
//                            GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18)
//                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment
//                        .BASELINE).addComponent(regionTechLbl).addComponent(regionTech,
//                        GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
//                        GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(layout
//                        .createParallelGroup(GroupLayout.Alignment.BASELINE)
//                        .addComponent(regionCoordsLbl).addComponent(regionCoords,
//                            GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
//                            GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(layout
//                        .createParallelGroup(GroupLayout.Alignment.BASELINE)
//                        .addComponent(distanceLbl).addComponent(distance,
//                            GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
//                            GroupLayout.PREFERRED_SIZE)).addContainerGap(283,
//                        Short.MAX_VALUE))
//        );
//    }
//}