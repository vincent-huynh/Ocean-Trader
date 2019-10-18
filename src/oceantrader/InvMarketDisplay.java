package oceantrader;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

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

        panel.setPreferredSize(new java.awt.Dimension(350, 600));

        inventoryModel = new javax.swing.table.DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "Item Name", "Price", "Type"
                }
        ) {
            private Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Integer.class,
                java.lang.String.class
            };

            private boolean[] canEdit = new boolean[] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        inventoryTable.setFont(new java.awt.Font("Dialog", 0, 14)); //NOI18N
        inventoryTable.setModel(inventoryModel);
        inventoryScroll.setViewportView(inventoryTable);
        inventoryTable.getTableHeader().setReorderingAllowed(false);
        TableColumnModel tcm = inventoryTable.getColumnModel();
        tcm.getColumn(1).setPreferredWidth(20);

        inventoryTable.setSelectionModel(new SingleSelectionModel());

        inventoryTextField.setEditable(false);
        inventoryTextField.setFont(new java.awt.Font("Dialog", 0, 14)); //NOI18N
        inventoryTextField.setText("Inventory");
        inventoryTextField.setBorder(null);

        marketModel = new javax.swing.table.DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                    "Item Name", "Price", "Type"
                }
        ) {
            private Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Integer.class,
                java.lang.String.class
            };

            private boolean[] canEdit = new boolean[] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        marketTable.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        marketTable.setModel(marketModel);
        marketScroll.setViewportView(marketTable);
        marketTable.getTableHeader().setReorderingAllowed(false);
        TableColumnModel tcm2 = marketTable.getColumnModel();
        tcm2.getColumn(1).setPreferredWidth(20);

        marketTable.setSelectionModel(new SingleSelectionModel());

        marketTextField.setEditable(false);
        marketTextField.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        marketTextField.setText("Market");
        marketTextField.setBorder(null);

        sellBtn.setText("Sell Item");
        buyBtn.setText("Buy Item");

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
            .addGap(126, 126, 126)))).addGroup(javax.swing.GroupLayout.Alignment
            .TRAILING, layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short
            .MAX_VALUE).addComponent(inventoryTextField, javax.swing.GroupLayout
            .PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.PREFERRED_SIZE).addGap(141, 141, 141))
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
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
            71, Short.MAX_VALUE).addComponent(buyBtn).addGap(20, 20, 20)
            .addComponent(marketTextField, javax.swing.GroupLayout
            .PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement
            .UNRELATED).addComponent(marketScroll, javax.swing.GroupLayout
            .PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
        );

        if (OceanTrader.player != null) {
            updateInventory();
            updateMarket();
        }
    }

    protected void updateInventory() {
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
}