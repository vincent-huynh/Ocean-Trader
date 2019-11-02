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

        traderTop.setFont(new java.awt.Font("Dialog", 1, 18));
        traderTop.setText("A trader would like to trade!");

        traderModel = new javax.swing.table.DefaultTableModel(
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
                //YOUR CODE HERE
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
                //YOUR CODE HERE
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
                //YOUR CODE HERE
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
                //YOUR CODE HERE
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                negotiateDisc();
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
//        for (int i = 0; i < [!TRADERMODEL ITEM COUNT]; ++i) {
//            Object[] row = OceanTrader.player.getShip().getCargoList().get(i)
//                    .tableizer();
////      [MAKE A TABLEIZER METHOD FOR THE TRADER'S INVENTORY LIST]
//            traderModel.addRow(row);
//        }
    }

    private void doNotTouch() {
        jScrollPane2.setViewportView(traderItems);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment
                    .LEADING).addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout
                            .Alignment.LEADING)
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
                        .addGroup(layout.createParallelGroup(javax.swing
                                .GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                        .addComponent(traderTop)
                        .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout
                                .Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout
                                .PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement
                            .UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout
                            .Alignment.BASELINE)
                    .addComponent(buyBtn)
                    .addComponent(ignoreBtn)
                    .addComponent(robBtn)
                    .addComponent(negotiateBtn))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                            Short.MAX_VALUE))
        );
    }
}