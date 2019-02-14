/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import javax.swing.*;
//import javax.swing.GroupLayout;
import java.util.ArrayList;
import data.DataAccess;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author cerfe
 */
public class MainMenu extends JFrame{
    private JScrollPane centralButtons_scrollpane;
    private JPanel centralButtons;
    private JButton create_new;

    private ArrayList<JPanel> condominium_panel = new ArrayList<>();
    private ArrayList<JLabel> condominium_label = new ArrayList<>();
    private ArrayList<JButton> select = new ArrayList<>();
    private ArrayList<JButton> delete = new ArrayList<>();

    static MainMenu mainmenu;
    private MouseHandler mousehandler;

    public MainMenu() {
        super("Main Menu");
        mainmenu = this;
        //////////////////////////
        //Listener
        mousehandler = new MouseHandler();
        //////////////////////////
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Sceglie l'operazione quando si chiude la finestra

        
        centralButtons = new JPanel();
        centralButtons.setPreferredSize(new Dimension(200, 501));
        
        centralButtons_scrollpane = new JScrollPane(centralButtons, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        centralButtons_scrollpane.setPreferredSize(new Dimension(200, 500));
        add(centralButtons_scrollpane);

        FlowLayout flowLayout = new FlowLayout();
        centralButtons.setLayout(flowLayout);
        
        
        this.getContentPane().setBackground( Color.decode("#0b111d"));
        centralButtons.setBackground( Color.decode("#0b111d"));
        refreshPanels();
        
        setVisible(true);
      
        ///////////////
        //CreateCond asd = new CreateCond();
    }
    /**
     * Returns the height centralButtons_scrollpane has to be to fit all the condominium panels 
     * @return height centralButtons_scrollpane has to be
     */
    private int getScrollpaneHeight(){
        int height = (int) ((13) * (condominium_panel.size()+1));
        return height;
    }
    /**
     * Refreshes all the condominium panels so that they match exactly the amount of instantiated Condominiums in DataAccess.c
     */
    public void refreshPanels() {
        centralButtons.removeAll();

        condominium_panel.clear();
        condominium_label.clear();
        select.clear();
        delete.clear();
        create_new = null;

        int dim = 150;
        for (int n = 0; n <= DataAccess.c.size() - 1; n++) {
            condominium_panel.add(new JPanel());
            condominium_label.add(new JLabel("' " + DataAccess.c.get(n).getName() + " '"));
            select.add(new JButton("SELECT"));
            delete.add(new JButton("DELETE"));
            condominium_panel.get(n).add(condominium_label.get(n));
            condominium_panel.get(n).add(select.get(n));
            condominium_panel.get(n).add(delete.get(n));
            centralButtons.add(condominium_panel.get(n));

        }
        int n1 = 0;

        condominium_panel.forEach((g) -> g.setPreferredSize(new Dimension(dim, dim)));
        condominium_label.forEach((g) -> g.setVisible(true));
        condominium_label.forEach((g) -> g.setForeground(Color.decode("#ffcc00")));
        condominium_panel.forEach((g) -> g.setBackground( Color.decode("#3f5ca2") ));
        
        select.forEach((g) -> g.setPreferredSize(new Dimension(145, 30)));
        select.forEach((g) -> g.setVisible(false));
        delete.forEach((g) -> g.setPreferredSize(new Dimension(145, 30)));
        delete.forEach((g) -> g.setVisible(false));
        
        select.forEach((g) -> g.setBackground( Color.decode("#44b93c")));
        delete.forEach((g) -> g.setBackground( Color.decode("#ff0000")));
        
        select.forEach((g) -> g.addMouseListener(mousehandler));
        delete.forEach((g) -> g.addMouseListener(mousehandler));
        condominium_panel.forEach((g) -> g.addMouseListener(mousehandler));

        create_new = new JButton("+");
        create_new.setFont(new Font("Arial", Font.PLAIN, 130));
        create_new.setPreferredSize(new Dimension(dim, dim));
        create_new.addMouseListener(mousehandler);
        create_new.setBackground( Color.decode("#3f5ca2") );
        create_new.setForeground(Color.decode("#ffea00"));
        centralButtons.add(create_new);
        centralButtons.addMouseListener(mousehandler);
        centralButtons.setPreferredSize(new Dimension(200,getScrollpaneHeight()));
        revalidate();
        repaint();
    }

    private class MouseHandler extends MouseAdapter {

        
        public void mouseClicked(MouseEvent e){
            if (select.contains(e.getSource())) {
                CondMenu condmenu;
                condmenu = new CondMenu(DataAccess.c.get(select.indexOf(e.getSource())));
            } else if (delete.contains(e.getSource())){
                int opt= JOptionPane.showConfirmDialog(null, "Do you really wanna delete it?");
                switch(opt){
                    case 0:
                        DataAccess.delCondominium(delete.indexOf(e.getSource()));
                        refreshPanels();
                        revalidate();
                        break;
                }
            }else if (e.getSource().equals(create_new)) {
                CreateCond createcond = new CreateCond();
                createcond.setVisible(true);
            }
        }
        
        public void mouseEntered(MouseEvent e) {
            if (condominium_panel.contains(e.getSource()) /*|| select.contains(e.getSource()) || delete.contains(e.getSource())*/) {
                condominium_label.get(condominium_panel.indexOf(e.getSource())).setVisible(false);
                select.get(condominium_panel.indexOf(e.getSource())).setVisible(true);
                delete.get(condominium_panel.indexOf(e.getSource())).setVisible(true);
                validate();
            }
            else if (e.getSource().equals(centralButtons)) {
                condominium_label.forEach((g)-> g.setVisible(true));
                select.forEach((g)->g.setVisible(false));
                delete.forEach((g)->g.setVisible(false));
                validate();
            }
        }
    }
}
