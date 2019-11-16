package oceantrader;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InvMarketDisplay {

    protected static Item buyItem;
    protected static Item sellItem;
    protected JPanel panel;
    protected JButton buyBtn;
    protected JButton sellBtn;
    private JScrollPane inventoryScroll;
    private JTable invTable;
    private JTextField invTextField;
    private JScrollPane marketScroll;
    private JTable marketTable;
    private JTextField marketTextField;
    private DefaultTableModel inventoryModel;
    private DefaultTableModel marketModel;
    private JTextField playerBalanceDisplay;
    private JTextField playerBalanceLbl;

    protected InvMarketDisplay() {
        panel = new JPanel();
        initGUI();
    }

    private void initGUI() {
        inventoryScroll = new JScrollPane();
        invTable = new JTable();
        invTextField = new JTextField();
        marketScroll = new JScrollPane();
        marketTable = new JTable();
        marketTextField = new JTextField();
        sellBtn = new JButton();
        buyBtn = new JButton();
        playerBalanceDisplay = new JTextField();
        playerBalanceLbl = new JTextField();

        Font bigFont = new Font("Tahoma", 0, 18);
        Font smallFont = new Font("Tahoma", 0, 14);
        panel.setPreferredSize(new java.awt.Dimension(350, 600));
        panel.setBackground(RegionDisplay.mainGUIColor);

        inventoryModel = new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Item Name", "Price", "Type", ""}
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

        invTable.setFont(smallFont);
        invTable.setModel(inventoryModel);
        inventoryScroll.setViewportView(invTable);
        invTable.getTableHeader().setReorderingAllowed(false);
        invTable.getTableHeader().setResizingAllowed(false);

        TableColumnModel tcm = invTable.getColumnModel();

        tcm.getColumn(0).setPreferredWidth(120);
        tcm.getColumn(0).setMinWidth(120);
        tcm.getColumn(1).setPreferredWidth(10);
        tcm.getColumn(2).setPreferredWidth(10);
        invTable.setSelectionModel(new SingleSelectionModel());

        tcm.getColumn(3).setMaxWidth(0);
        tcm.getColumn(3).setMinWidth(0);
        tcm.getColumn(3).setPreferredWidth(0);

        invTable.getColumnModel().getColumn(0).setCellRenderer(centerizer);
        invTable.getColumnModel().getColumn(1).setCellRenderer(centerizer);
        invTable.getColumnModel().getColumn(2).setCellRenderer(centerizer);

        invTextField.setEditable(false);
        invTextField.setFont(bigFont);
        invTextField.setText("Inventory");
        invTextField.setBorder(null);

        marketModel = new javax.swing.table.DefaultTableModel(
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

        marketTable.setFont(smallFont);
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

        marketTable.getColumnModel().getColumn(0).setCellRenderer(centerizer);
        marketTable.getColumnModel().getColumn(1).setCellRenderer(centerizer);
        marketTable.getColumnModel().getColumn(2).setCellRenderer(centerizer);

        marketTable.setSelectionModel(new SingleSelectionModel());

        marketTextField.setEditable(false);
        marketTextField.setFont(bigFont);
        marketTextField.setText("Market");
        marketTextField.setBorder(null);

        sellBtn.setText("Sell Item");
        buyBtn.setText("Buy Item");

        playerBalanceLbl.setEditable(false);
        playerBalanceLbl.setText("Player Balance:");
        playerBalanceLbl.setFont(bigFont);
        playerBalanceLbl.setBorder(null);

        playerBalanceDisplay.setEditable(false);
        playerBalanceDisplay.setFont(bigFont);
        playerBalanceDisplay.setBorder(null);

        invTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
            }
        });

        invTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if (invTable.getSelectedRow() != -1) {
                    sellItem = (Item) invTable.getValueAt(invTable.getSelectedRow(), 3);
                }
            }
        });

        marketTable.getSelectionModel().addListSelectionListener(listSelectionEvent -> {
            if (marketTable.getSelectedRow() != -1) {
                buyItem = (oceantrader.Item) marketTable
                    .getValueAt(marketTable.getSelectedRow(), 3);
                oceantrader.RegionDisplay.costDisplay.updateBuyDisplay(oceantrader.Transaction
                    .getPriceValues(buyItem));
            }
        });

        sellBtn.addActionListener(e -> {
            if (sellItem == null) {
                JOptionPane.showMessageDialog(OceanTrader.window,
                    "No Item Selected!");
            } else {
                Transaction.processTransactionSell(sellItem);
                updateInventory();
                updateMarket();
                updateCurrencyDisplay();
            }
        });

        buyBtn.addActionListener(e -> {
            if (buyItem == null) {
                JOptionPane.showMessageDialog(OceanTrader.window,
                    "No Item Selected!");
            } else {
                Transaction.processTransactionBuy(new Item(buyItem));
                updateInventory();
                updateMarket();
                updateCurrencyDisplay();
            }
        });

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.
                createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment
                    .LEADING).addComponent(inventoryScroll, GroupLayout.PREFERRED_SIZE, 0,
                    Short.MAX_VALUE).addComponent(marketScroll, GroupLayout.PREFERRED_SIZE, 0,
                    Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addGap(
                    138, 138, 138).addComponent(marketTextField, GroupLayout.PREFERRED_SIZE,
                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short
                    .MAX_VALUE))).addContainerGap()).addGroup(GroupLayout.Alignment.TRAILING, layout
                .createSequentialGroup().addGap(0, 117, Short.MAX_VALUE).addGroup(layout.
                    createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(sellBtn)
                    .addComponent(buyBtn)).addGap(126, 126, 126)))).addGroup(javax.swing
                .GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(
                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(invTextField, GroupLayout
                .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141)).addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46).addComponent(playerBalanceLbl, GroupLayout
                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18).addComponent(playerBalanceDisplay,
                    GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE).
                    addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(layout
            .createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addContainerGap()
                .addComponent(invTextField, GroupLayout
                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE).addGap(5, 5, 5)
                .addComponent(inventoryScroll, GroupLayout
                    .PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18).addComponent(sellBtn).addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout
                    .Alignment.BASELINE).addComponent(playerBalanceLbl,
                    GroupLayout.PREFERRED_SIZE, GroupLayout
                        .DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerBalanceDisplay, GroupLayout
                        .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
            38, Short.MAX_VALUE).addComponent(buyBtn).addGap(20, 20, 20)
                .addComponent(marketTextField, GroupLayout
                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement
                    .UNRELATED).addComponent(marketScroll, GroupLayout
                    .PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
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
            Object[] row = OceanTrader.player.getShip().getCargoList().get(i).tableizer();
            inventoryModel.addRow(row);
        }
    }

    protected void updateMarket() {
        marketModel.setRowCount(0);
        for (Item i : OceanTrader.player.getRegion().getMarketItems()) {
            marketModel.addRow(i.tableizer());
        }
    }

    protected void updateCurrencyDisplay() {
        if (OceanTrader.player != null) {
            playerBalanceDisplay.setText("" + OceanTrader.player.getCurrency());
        }
    }
}