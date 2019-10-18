package oceantrader;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InvMarketDisplay {

    protected JPanel panel;
    protected JButton buyBtn;
    protected JButton sellBtn;

    private JScrollPane inventoryScroll;
    private JTable inventoryTable;
    private JTextField inventoryTextField;
    private JScrollPane marketScroll;
    private JTable marketTable;
    private JTextField marketTextField;
    private DefaultTableModel inventoryModel;
    private DefaultTableModel marketModel;
    private JTextField playerBalanceDisplay;
    private JTextField playerBalanceLbl;

    private Item buyItem;
    private Item sellItem;

    protected InvMarketDisplay() {
        panel = new JPanel();
        initGUI();
    }

    private void initGUI() {

        inventoryScroll = new JScrollPane();
        inventoryTable = new JTable();
        inventoryTextField = new JTextField();
        marketScroll = new JScrollPane();
        marketTable = new JTable();
        marketTextField = new JTextField();
        sellBtn = new JButton();
        buyBtn = new JButton();
        playerBalanceDisplay = new JTextField();
        playerBalanceLbl = new JTextField();

        panel.setPreferredSize(new java.awt.Dimension(350, 600));

        inventoryModel = new javax.swing.table.DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "Item Name", "Price", "Type", ""
                }
        ) {
            private Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Integer.class,
                java.lang.String.class, Item.class
            };

            private boolean[] canEdit = new boolean[] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        inventoryTable.setFont(new java.awt.Font("Tahoma", 0, 14));
        inventoryTable.setModel(inventoryModel);
        inventoryScroll.setViewportView(inventoryTable);
        inventoryTable.getTableHeader().setReorderingAllowed(false);
        inventoryTable.getTableHeader().setResizingAllowed(false);
        TableColumnModel tcm = inventoryTable.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(120);
        tcm.getColumn(0).setMinWidth(120);
        tcm.getColumn(1).setPreferredWidth(10);
        tcm.getColumn(2).setPreferredWidth(10);
        inventoryTable.setSelectionModel(new SingleSelectionModel());

        tcm.getColumn(3).setMaxWidth(0);
        tcm.getColumn(3).setMinWidth(0);
        tcm.getColumn(3).setPreferredWidth(0);

        inventoryTextField.setEditable(false);
        inventoryTextField.setFont(new java.awt.Font("Tahoma", 0, 18));
        inventoryTextField.setText("Inventory");
        inventoryTextField.setBorder(null);

        marketModel = new javax.swing.table.DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "Item Name", "Price", "Type", ""
                }
        ) {
            private Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Integer.class,
                java.lang.String.class, Item.class
            };

            private boolean[] canEdit = new boolean[] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        marketTable.setFont(new java.awt.Font("Tahoma", 0, 14));
        marketTable.setModel(marketModel);
        marketScroll.setViewportView(marketTable);
        marketTable.getTableHeader().setReorderingAllowed(false);
        marketTable.getTableHeader().setResizingAllowed(false);
        TableColumnModel tcm2 = marketTable.getColumnModel();
        tcm2.getColumn(0).setPreferredWidth(120);
        tcm2.getColumn(0).setMinWidth(120);
        tcm2.getColumn(1).setPreferredWidth(5);
        tcm2.getColumn(2).setPreferredWidth(10);

        tcm2.getColumn(3).setMaxWidth(0);
        tcm2.getColumn(3).setMinWidth(0);
        tcm2.getColumn(3).setPreferredWidth(0);

        marketTable.setSelectionModel(new SingleSelectionModel());

        marketTextField.setEditable(false);
        marketTextField.setFont(new java.awt.Font("Tahoma", 0, 18));
        marketTextField.setText("Market");
        marketTextField.setBorder(null);

        sellBtn.setText("Sell Item");
        buyBtn.setText("Buy Item");


        playerBalanceLbl.setEditable(false);
        playerBalanceLbl.setText("Player Balance:");
        playerBalanceLbl.setBorder(null);

        playerBalanceDisplay.setEditable(false);
        playerBalanceDisplay.setBorder(null);



        inventoryTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 1) {
                    final JTable target = (JTable) mouseEvent.getSource();
                    final int row = target.getSelectedRow();
                    final Item selectedItem = (Item) target.getValueAt(row, 3);
                    ghettoSellItem(selectedItem);
                }
            }
        });

        marketTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 1) {
                    final JTable target = (JTable) mouseEvent.getSource();
                    final int row = target.getSelectedRow();
                    final Item selectedItem = (Item) target.getValueAt(row, 3);
                    ghettoBuyItem(selectedItem);
                }
            }
        });

        sellBtn.addActionListener(e -> {
            Transaction.processTransactionSell(sellItem);
            updateInventory();
            updateMarket();
            updateCurrencyDisplay();
        });

        buyBtn.addActionListener(e -> {
            Transaction.processTransactionBuy(new Item(buyItem));
            updateInventory();
            updateMarket();
            updateCurrencyDisplay();
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout
            .Alignment.LEADING).addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout
            .Alignment.LEADING).addComponent(inventoryScroll,
            javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(marketScroll, javax.swing.GroupLayout.PREFERRED_SIZE,
            0, Short.MAX_VALUE).addGroup(layout.createSequentialGroup()
            .addGap(138, 138, 138).addComponent(marketTextField,
            javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout
            .DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0,
            Short.MAX_VALUE))).addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
            .createSequentialGroup().addGap(0, 117, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout
            .Alignment.LEADING).addComponent(sellBtn).addComponent(buyBtn))
            .addGap(126, 126, 126)))).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(inventoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(141, 141, 141))
                .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(playerBalanceLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(playerBalanceDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addContainerGap()
            .addComponent(inventoryTextField, javax.swing.GroupLayout
            .PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.PREFERRED_SIZE).addGap(5, 5, 5)
            .addComponent(inventoryScroll, javax.swing.GroupLayout
            .PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18).addComponent(sellBtn)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(playerBalanceLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playerBalanceDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                    .addComponent(buyBtn)
                    .addGap(20, 20, 20)
                    .addComponent(marketTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(marketScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );

        if (OceanTrader.player != null) {
            updateInventory();
            updateMarket();
        }
    }

    protected void updateInventory() {
        inventoryModel.setRowCount(0);
        for (int i = 0; i < OceanTrader.player.getShip().getCargoSize(); ++i) {
            Object[] row = OceanTrader.player.getShip().getCargoList().get(i)
                    .tableizer();
            inventoryModel.addRow(row);
        }
    }

    protected void updateMarket() {
        marketModel.setRowCount(0);
        for (Item i : OceanTrader.player.getRegion().getMarketItems()) {
            marketModel.addRow(i.tableizer());
        }
    }

    private void ghettoBuyItem(Item it) {
        buyItem = it;
    }

    private void ghettoSellItem(Item it) {
        sellItem = it;
    }

    protected void updateCurrencyDisplay() {
        if (OceanTrader.player != null) {
            playerBalanceDisplay.setText("" + OceanTrader.player.getCurrency());
        }
    }
}