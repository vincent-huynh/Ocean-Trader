package oceantrader;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class EndGame extends JPanel {

    private JButton closeBtn;
    private JTextArea deathTxt;
    private JScrollPane deathTxtPane;
    private JButton titleBtn;
    
    public EndGame() {
        initComponents();
    }
                      
    private void initComponents() {
        deathTxtPane = new JScrollPane();
        deathTxt = new JTextArea();
        titleBtn = new JButton();
        closeBtn = new JButton();

        deathTxtPane.setBorder(BorderFactory.createBevelBorder(border.BevelBorder.RAISED));

        deathTxt.setEditable(false);
        deathTxt.setBackground(new java.awt.Color(204, 204, 204));
        deathTxt.setColumns(20);
        deathTxt.setFont(new java.awt.Font("Comic Sans MS", 1, 18));
        deathTxt.setLineWrap(true);
        deathTxt.setRows(5);
        deathTxt.setText("You have died!");
        deathTxt.setWrapStyleWord(true);
        deathTxt.setBorder(null);
        deathTxtPane.setViewportView(deathTxt);

        titleBtn.setFont(new java.awt.Font("Comic Sans MS", 1, 14));
        titleBtn.setText("Return to Title Screen");

        closeBtn.setFont(new java.awt.Font("Comic Sans MS", 1, 14));
        closeBtn.setText("End Game");

        
    }                       
    
    private void doNotTouch() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(deathTxtPane)
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(titleBtn)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                                                .addComponent(closeBtn)
                                                .addGap(36, 36, 36))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(deathTxtPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(titleBtn)
                                        .addComponent(closeBtn))
                                .addGap(93, 93, 93))
        );
    }
                 
    
                 
}
