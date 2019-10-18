package oceantrader;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;

public class CostDisplay {

    protected JPanel panel;
    private JTextField basePriceLbl;
    private JTextPane basePriceTF;
    private JLabel finalPriceLbl;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JTextField finalPriceTF;
    private JTextField timeLbl;
    private JTextPane timeTF;
    private JTextField tradeSkillLbl;
    private JTextPane tradeTF;
    private JLabel valueCalcLbl;
    private JLabel semiColonLbl;

    public CostDisplay() {
        panel = new JPanel();
        initGUI();
    }

    private void initGUI() {
        basePriceLbl = new JTextField();
        timeLbl = new JTextField();
        valueCalcLbl = new JLabel();
        tradeSkillLbl = new JTextField();
        jScrollPane1 = new JScrollPane();
        basePriceTF = new JTextPane();
        jScrollPane2 = new JScrollPane();
        timeTF = new JTextPane();
        jScrollPane3 = new JScrollPane();
        tradeTF = new JTextPane();
        finalPriceLbl = new JLabel();
        finalPriceTF = new JTextField();
        semiColonLbl = new JLabel();

        Font bestFont = new Font("Tahoma", 0, 14);
        Color backgroundColor = new Color(238, 238, 238);

        basePriceLbl.setEditable(false);
        basePriceLbl.setFont(bestFont); 
        basePriceLbl.setText("Base Price:");
        basePriceLbl.setBorder(null);

        timeLbl.setEditable(false);
        timeLbl.setFont(bestFont); 
        timeLbl.setText("Peak Time:");
        timeLbl.setBorder(null);

        valueCalcLbl.setFont(new Font("Cantarell Extra Bold", 1, 24)); 
        valueCalcLbl.setText("Value Calculation");

        tradeSkillLbl.setEditable(false);
        tradeSkillLbl.setFont(bestFont); 
        tradeSkillLbl.setText("Trade Skill:");
        tradeSkillLbl.setBorder(null);

        basePriceTF.setEditable(false);
        basePriceTF.setBackground(backgroundColor);
        basePriceTF.setBorder(null);
        basePriceTF.setFont(bestFont); 
        jScrollPane1.setViewportView(basePriceTF);

        timeTF.setEditable(false);
        timeTF.setBackground(backgroundColor);
        timeTF.setBorder(null);
        timeTF.setFont(bestFont); 
        jScrollPane2.setViewportView(timeTF);

        tradeTF.setEditable(false);
        tradeTF.setBackground(backgroundColor);
        tradeTF.setBorder(null);
        tradeTF.setFont(bestFont);
        jScrollPane3.setViewportView(tradeTF);

        finalPriceLbl.setFont(new Font("Tahoma", 1, 18));
        finalPriceLbl.setText("Final Price:");

        finalPriceTF.setEditable(false);
        finalPriceTF.setFont(new Font("Tahoma", 0, 18));
        finalPriceTF.setBorder(javax.swing.BorderFactory
                .createMatteBorder(1, 1, 1, 1, new Color(102, 0, 0)));

        semiColonLbl.setFont(new java.awt.Font("Cantarell Light", 0, 48));
        semiColonLbl.setText("}");

        forbiddenGUIFormat();
    }

    private void forbiddenGUIFormat() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
        panel.setLayout(layout);
            layout.setHorizontalGroup(layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap().addGroup(layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addGap(25, 25, 25)
            .addComponent(valueCalcLbl).addContainerGap(javax.swing.GroupLayout
            .DEFAULT_SIZE, Short.MAX_VALUE)).addGroup(layout
            .createSequentialGroup().addGroup(layout.createParallelGroup(javax
            .swing.GroupLayout.Alignment.LEADING).addGroup(layout
            .createSequentialGroup().addComponent(tradeSkillLbl,
            javax.swing.GroupLayout.PREFERRED_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout
            .PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle
            .ComponentPlacement.UNRELATED).addComponent(jScrollPane3))
            .addGroup(layout.createSequentialGroup().addComponent(timeLbl,
            javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout
            .DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18).addComponent(jScrollPane2)).addGroup(layout
            .createSequentialGroup().addComponent(basePriceLbl,
            javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout
            .DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18).addComponent(jScrollPane1,
            javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout
            .PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle
            .ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE,
            Short.MAX_VALUE))).addGap(3, 3, 3).addComponent(semiColonLbl)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout
            .Alignment.LEADING).addComponent(finalPriceLbl).addGroup(layout
            .createSequentialGroup().addGap(12, 12, 12)
            .addComponent(finalPriceTF, javax.swing.GroupLayout.PREFERRED_SIZE,
            80, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap())))
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment
            .LEADING).addGroup(layout.createSequentialGroup().addGap(6, 6, 6)
            .addComponent(valueCalcLbl).addPreferredGap(javax.swing.LayoutStyle
            .ComponentPlacement.RELATED).addGroup(layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup().addComponent(finalPriceLbl)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(finalPriceTF, javax.swing.GroupLayout.PREFERRED_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout
            .PREFERRED_SIZE)).addComponent(semiColonLbl, javax.swing.GroupLayout
            .PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup().addGroup(layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout
            .PREFERRED_SIZE).addComponent(basePriceLbl, javax.swing.GroupLayout
            .PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing
            .LayoutStyle.ComponentPlacement.RELATED).addGroup(layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout
            .PREFERRED_SIZE).addComponent(timeLbl, javax.swing.GroupLayout
            .PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(javax.swing
            .LayoutStyle.ComponentPlacement.RELATED).addGroup(layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tradeSkillLbl, javax.swing.GroupLayout.PREFERRED_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout
            .PREFERRED_SIZE).addComponent(jScrollPane3, javax.swing.GroupLayout
            .PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.PREFERRED_SIZE)))).addContainerGap(javax
            .swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
}