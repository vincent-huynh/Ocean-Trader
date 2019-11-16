package oceantrader;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
                trader.concedable();
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
                trader.avertable();
                trader = new Trader();
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
                trader.fightable();
                trader = new Trader();

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
                trader.negotiable();
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

    public javax.swing.JButton getBuyBtn() {
        return buyBtn;
    }

    public void setBuyBtn(javax.swing.JButton buyBtn) {
        this.buyBtn = buyBtn;
    }

    public javax.swing.JTextArea getExplainArea() {
        return explainArea;
    }

    public void setExplainArea(javax.swing.JTextArea explainArea) {
        this.explainArea = explainArea;
    }

    public javax.swing.JButton getIgnoreBtn() {
        return ignoreBtn;
    }

    public void setIgnoreBtn(javax.swing.JButton ignoreBtn) {
        this.ignoreBtn = ignoreBtn;
    }

    public javax.swing.JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(javax.swing.JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public javax.swing.JScrollPane getjScrollPane2() {
        return jScrollPane2;
    }

    public void setjScrollPane2(javax.swing.JScrollPane jScrollPane2) {
        this.jScrollPane2 = jScrollPane2;
    }

    public javax.swing.JButton getNegotiateBtn() {
        return negotiateBtn;
    }

    public void setNegotiateBtn(javax.swing.JButton negotiateBtn) {
        this.negotiateBtn = negotiateBtn;
    }

    public javax.swing.JButton getRobBtn() {
        return robBtn;
    }

    public void setRobBtn(javax.swing.JButton robBtn) {
        this.robBtn = robBtn;
    }

    public javax.swing.JTable getTraderItems() {
        return traderItems;
    }

    public void setTraderItems(javax.swing.JTable traderItems) {
        this.traderItems = traderItems;
    }

    public javax.swing.JLabel getTraderTop() {
        return traderTop;
    }

    public void setTraderTop(javax.swing.JLabel traderTop) {
        this.traderTop = traderTop;
    }

    public javax.swing.table.DefaultTableModel getTraderModel() {
        return traderModel;
    }

    public void setTraderModel(javax.swing.table.DefaultTableModel traderModel) {
        this.traderModel = traderModel;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Item getBuyItem() {
        return buyItem;
    }

    public void setBuyItem(Item buyItem) {
        this.buyItem = buyItem;
    }
}