/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;
import data.DataAccess;
import static frames.MainMenu.mainmenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 *
 * @author cerfe
 */
public class CondMenu extends JFrame{
    private data.Condominium link;
    static CondMenu condmenu;
    private ActionHandler actionhandler;
    private JPanel center_panel;
    
      private JPanel apartment_panel;
        private ArrayList<JButton> apartment_button;
    private JPanel north_panel;
      private JTextArea name_textarea;
      private JTextArea info_textarea;
    public CondMenu(data.Condominium link){
        super("Condominium Visual");
        this.link=link;
        condmenu=this;
        setTitle("'"+link.getName()+"' Management");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ///////////////////////////////////
        //Creating all listeners
        actionhandler = new ActionHandler();
        //////////////////////////////////
        
        center_panel=new JPanel();
        apartment_panel=new JPanel();
        apartment_button=new ArrayList<>();
        
        apartment_panel.setPreferredSize(new Dimension(getApPanelWidth(),getApPanelHeight()));
        center_panel.add(apartment_panel);
        center_panel.setBackground(Color.yellow);
        add(center_panel,BorderLayout.CENTER);
        
        /////////////////////////////////////////////////
        north_panel=new JPanel();
        name_textarea=new JTextArea();
        info_textarea=new JTextArea();
        
        name_textarea.setText("' "+link.getName()+" '");
        name_textarea.setFont(new Font("SansSerif", Font.ITALIC, 68));
        name_textarea.setMaximumSize(new Dimension(500,200));
        name_textarea.setEditable(false);
        
        info_textarea.setEditable(false);
        north_panel.add(name_textarea);
        north_panel.add(info_textarea);
        north_panel.setBackground(Color.blue);
        add(north_panel,BorderLayout.NORTH);
        /////////////////////////////////////////////////
        refreshApartmentButtons();
        north_panel.setBackground( Color.decode("#17223b") );
        center_panel.setBackground(Color.decode("#0b111d"));
        apartment_panel.setBackground(Color.decode("#0b111d"));
        name_textarea.setForeground(Color.decode("#ffea00"));
        name_textarea.setBackground( Color.decode("#17223b") );
        info_textarea.setForeground(Color.decode("#ffea00"));
        info_textarea.setBackground( Color.decode("#17223b") );
        setVisible(true);
        setResizable(false);
    }
    /**
     * Return the Width apartment_panel has to be to fit correctly the apartment buttons floor-per-floor
     * @return the width apartment_panel has to be
     */
    private int getApPanelWidth(){
        return link.getnApartEachFloor()*(60+5);
    }
    /**
     * Return the Width apartment_panel has to be to fit correctly the apartment buttons floor-per-floor
     * @return the width apartment_panel has to be
     */
    private int getApPanelHeight(){
        return link.getnFloors()*(60+6);
    }
    /**
     * Refresh all the graphics of his CondMenu frame.
     * -Clears all the buttons and recreates them with updated colors
     * -Updates the JTextArea placed NORTH to display the correct infos
     */
    public void refreshApartmentButtons(){
        apartment_panel.removeAll();
        apartment_button.clear();
        
        for(int n=0;n<link.getApSize();n++){
            apartment_button.add(new JButton(""+(n+1)));
            apartment_panel.add(apartment_button.get(n));
            if(link.a[n].isEmpty()){
                apartment_button.get(n).setBackground(Color.green);
            }
            else{
                apartment_button.get(n).setBackground(Color.red);
            }
        }
        info_textarea.setText("Empty Apartments: "+link.countEmptyApartment()+"/"+link.a.length
                + "\n\nOccupied apartments: "+link.countOccupiedApartment()+"/"+link.a.length
                + "\n\nNumber of tenants: "+link.countTenants());
        
        apartment_button.forEach((g)-> g.setPreferredSize(new Dimension(60,60)));
        apartment_button.forEach((g)-> g.addActionListener(actionhandler));
        revalidate();
        
    }
    
    private class ActionHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ModApartment modapartment=new ModApartment(link.a[apartment_button.indexOf(e.getSource())]);
        }	
    }	
}

