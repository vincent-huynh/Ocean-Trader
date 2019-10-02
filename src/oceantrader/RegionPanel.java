package oceantrader;

import javax.swing.*;
import java.awt.*;
import java.util.AbstractList;

public class RegionPanel {
    protected JPanel panel;
    protected AbstractListModel regionListy;
    public RegionPanel() {
        panel = new JPanel();

        initGUI();
    }

    @SuppressWarnings("unchecked")
    private void initGUI() {
        JScrollPane jScrollPane1 = new JScrollPane();
        JList regionList = new JList();
        JLabel regionNameLbl = new JLabel("Region:");
        JLabel regionTechLbl = new JLabel("Tech Level:");
        JLabel regionCoordsLbl = new JLabel("Coordinates:");

        JTextField regionName = new JTextField();
        regionName.setEditable(false);
        JTextField regionTech = new JTextField();
        regionTech.setEditable(false);
        JTextField regionCoords = new JTextField();
        regionCoords.setEditable(false);

        panel.setMaximumSize(new Dimension(350, 950));
        panel.setPreferredSize(new Dimension(350, 950));

        regionListy = new AbstractListModel() {
            String[] strings = Universe.getInstance().getRegionArray();
            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public Object getElementAt(int i) {
                return strings[i];
            }
        };

        regionList.setModel(regionListy);

        jScrollPane1.setViewportView(regionList);

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1)
                .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addComponent(regionCoordsLbl)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regionCoords, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                .addComponent(regionNameLbl)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regionName))
                .addGroup(layout.createSequentialGroup()
                .addComponent(regionTechLbl)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(regionTech)))))
                .addContainerGap())
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(regionNameLbl)
                .addComponent(regionName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(regionTechLbl)
                .addComponent(regionTech, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(regionCoordsLbl)
                .addComponent(regionCoords, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(303, Short.MAX_VALUE))
        );
    }
}
