package oceantrader;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TraderEncounter extends JPanel implements IEncounter {

    private JButton buyBtn;
    private JTextArea explainArea;
    private JButton ignoreBtn;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JButton negotiateBtn;
    private JButton robBtn;
    private JTable traderItems;
    private JLabel traderTop;
    private DefaultTableModel traderModel;
    private Trader trader;
    private Item buyItem;

    public TraderEncounter() {
        initGUI();
    }

    private void initGUI() {

        traderTop = new JLabel();
        traderItems = new JTable();
        buyBtn = new JButton();
        ignoreBtn = new JButton();
        robBtn = new JButton();
        negotiateBtn = new JButton();
        jScrollPane1 = new JScrollPane();
        jScrollPane2 = new JScrollPane();
        explainArea = new JTextArea();
        trader = new Trader();

        traderTop.setFont(new java.awt.Font("Dialog", 1, 18));
        traderTop.setText("A trader would like to trade!");

        traderModel = new javax.swing.table.DefaultTableModel(
            new Object[][]{
            },
            new String[]{
                "Item Name", "Price", "Type", ""
            }
        ) {
            private Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, Item.class
            };

            private boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        DefaultTableCellRenderer centerizer = new DefaultTableCellRenderer();
        centerizer.setHorizontalAlignment(JLabel.CENTER);

        //traderItems.setFont(smallFont);
        traderItems.setModel(traderModel);
        jScrollPane1.setViewportView(traderItems);
        traderItems.getTableHeader().setReorderingAllowed(false);
        traderItems.getTableHeader().setResizingAllowed(false);

        TableColumnModel tcm = traderItems.getColumnModel();

        tcm.getColumn(0).setPreferredWidth(120);
        tcm.getColumn(0).setMinWidth(120);
        tcm.getColumn(1).setPreferredWidth(10);
        tcm.getColumn(2).setPreferredWidth(10);
        traderItems.setSelectionModel(new SingleSelectionModel());

        tcm.getColumn(3).setMaxWidth(0);
        tcm.getColumn(3).setMinWidth(0);
        tcm.getColumn(3).setPreferredWidth(0);

        traderItems.getColumnModel().getColumn(0).setCellRenderer(centerizer);
        traderItems.getColumnModel().getColumn(1).setCellRenderer(centerizer);
        traderItems.getColumnModel().getColumn(2).setCellRenderer(centerizer);

        buyBtn.setText("Buy");
        buyBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                updatePanel(); //to update the trader inventory
                if (buyItem == null) {
                    JOptionPane.showMessageDialog(traderItems, "No Item Selected!");
                } else {
                    switch (trader.sellItems(buyItem)) {
                        case "broke":
                            JOptionPane.showMessageDialog(traderItems,
                                    "You lack sufficient funds!");
                            break;
                        case "space":
                            JOptionPane.showMessageDialog(traderItems, "No space available!");
                            break;
                        case "success":
                            JOptionPane.showMessageDialog(traderItems,
                                    buyItem.getName() + " was bought!");
                            OceanTrader.player.getShip().getCargoList().add(buyItem);
                            OceanTrader.encounterFrame.setVisible(false);
                            OceanTrader.regionDisplay.invMarketDisplay.updateInventory();
                            OceanTrader.regionDisplay.invMarketDisplay.updateCurrencyDisplay();
                            NPCEncounter.modifyKarma(-1, "lost");
                            Travel.updateFuel((int) Travel.getCost());
                            Travel.travel();
                            break;
                        default:
                            break;
                    }
                    buyItem = null;
                    trader = new Trader();
                }
            }
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                buyDisc();
            }
        });

        ignoreBtn.setText("Ignore");
        ignoreBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                OceanTrader.encounterFrame.setVisible(false);
                trader = new Trader();
                Travel.updateFuel((int) Travel.getCost());
                Travel.travel();
            }
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                ignoreDisc();
            }
        });

        robBtn.setText("Rob");
        robBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                Player player = OceanTrader.player;
                Ship ship = player.getShip();
                ArrayList<Item> stolen = trader.robbed();
                if (stolen.size() == 0) {
                    NPCEncounter.damageShip();
                    JOptionPane.showMessageDialog(traderItems,
                            "You were not able to steal anything and the "
                                    + "trader inflicted damage on your ship!");
                } else {
                    for (Item item : stolen) {
                        if (ship.getCargoSize() == ship.getMaxCargoSpace()) {
                            JOptionPane.showMessageDialog(traderItems,
                                    "Your inventory is full, cannot take " + item.getName() + "!");
                        } else {
                            JOptionPane.showMessageDialog(traderItems,
                                    "You have gained: " + item.getName());
                            ship.getCargoList().add(item);
                        }
                    }
                }
                trader = new Trader();
                OceanTrader.regionDisplay.invMarketDisplay.updateInventory();
                OceanTrader.regionDisplay.regionPanel.update();
                OceanTrader.encounterFrame.setVisible(false);
                NPCEncounter.modifyKarma(1, "gained");
                Travel.updateFuel((int) Travel.getCost());
                Travel.travel();
            }
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                robDisc();
            }
        });

        negotiateBtn.setText("Negotiate");
        negotiateBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                double dis = trader.negotiate();
                if (dis > 0) {
                    JOptionPane.showMessageDialog(traderItems,
                            "You were able to haggle down the price by " + dis + "%!");
                    updatePanel();
                } else if (dis == -12345) {
                    JOptionPane.showMessageDialog(traderItems,
                            "You cannot negotiate with the trader again!");
                } else if (dis < 0) {
                    JOptionPane.showMessageDialog(traderItems,
                            "The trader got angry and increased prices by " + (dis * -1) + "%!");
                    updatePanel();
                } else {
                    JOptionPane.showMessageDialog(traderItems,
                            "You were unsuccessful in negotiating.");
                }
            }
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                negotiateDisc();
            }
        });

        traderItems.getSelectionModel().addListSelectionListener(listSelectionEvent -> {
            if (traderItems.getSelectedRow() != -1) {
                buyItem = (oceantrader.Item) traderItems
                        .getValueAt(traderItems.getSelectedRow(), 3);
                oceantrader.RegionDisplay.costDisplay
                        .updateBuyDisplay(oceantrader.Transaction.getPriceValues(buyItem));
            }
        });

        explainArea.setEditable(false);
        explainArea.setBackground(null);
        explainArea.setColumns(20);
        explainArea.setRows(5);
        explainArea.setBorder(null);
        explainArea.setWrapStyleWord(true);
        explainArea.setLineWrap(true);
        explainArea.setFont(new java.awt.Font("Dialog", 1, 14));
        jScrollPane1.setViewportView(explainArea);
        doNotTouch();
    }

    private void buyDisc() {
        String text = "Purchase item, then continue traveling.";
        explainArea.setText(text);
    }

    private void ignoreDisc() {
        String text = "Ignore the Trader and continue traveling.";
        explainArea.setText(text);
    }

    private void robDisc() {
        String text = "Attempt to rob the Trader. Success is based on your "
                + "Fighter Skill. If unsuccessful, your ship will be damaged.";
        explainArea.setText(text);
    }

    private void negotiateDisc() {
        String text = "Attempt to negotiate for a better price."
                + " Success based on your Trading Skill.";
        explainArea.setText(text);
    }

    public void updatePanel() {
        traderModel.setRowCount(0);
        for (Item item : trader.getCargoList()) {
            traderModel.addRow(item.tableizer());
        }
    }

    private void doNotTouch() {
        jScrollPane2.setViewportView(traderItems);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment
                        .LEADING).addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing
                                .GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(buyBtn)
                                        .addGap(18, 18, 18)
                                        .addComponent(ignoreBtn)
                                        .addPreferredGap(javax.swing.LayoutStyle
                                                .ComponentPlacement.UNRELATED)
                                        .addComponent(robBtn)
                                        .addGap(12, 12, 12)
                                        .addComponent(negotiateBtn)
                                        .addGap(0, 28, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout
                                                .createParallelGroup(javax.swing
                                                        .GroupLayout.Alignment.LEADING)
                                                .addGroup(layout
                                                        .createSequentialGroup()
                                                        .addComponent(traderTop)
                                                        .addGap(0, 0, Short
                                                                .MAX_VALUE))
                                                .addComponent(jScrollPane1,
                                                        javax.swing.GroupLayout
                                                                .Alignment.TRAILING)
                                                .addComponent(jScrollPane2,
                                                        javax.swing.GroupLayout
                                                                .PREFERRED_SIZE, 0,
                                                        Short.MAX_VALUE))
                                        .addContainerGap())))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment
                        .LEADING).addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(traderTop)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout
                                .PREFERRED_SIZE, 96, javax.swing.GroupLayout
                                .PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout
                                .PREFERRED_SIZE, javax.swing.GroupLayout
                                .DEFAULT_SIZE, javax.swing.GroupLayout
                                .PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle
                                .ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing
                                .GroupLayout.Alignment.BASELINE)
                                .addComponent(buyBtn)
                                .addComponent(ignoreBtn)
                                .addComponent(robBtn)
                                .addComponent(negotiateBtn))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE))
        );
    }
}