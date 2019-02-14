/**
 * Contains all the frames/graphics. When it needs to read/write internal data, it refers to DataAccess
 */
package frames;

import static frames.CondMenu.condmenu;
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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author utente
 */
public class ModApartment extends JFrame {
    data.Apartment link;
    
    private JPanel populatePanel;
    private JButton populate_button;
    
    private JPanel modPanel;
    private JLabel owner_label;
    private JTextField owner_field;
    private JLabel tenants_label;
    private JSpinner tenants_spinner;
    private JLabel info_label;

    private JButton cancel_button;
    private JButton save_button;
    private JButton evict_button;
    
    public ModApartment(data.Apartment link) {
        super("Modify");
        this.link=link;
        setVisible(true);
        ///////////////////////////////////
        //Creating all listeners
        FocusHandler focushandler = new FocusHandler();
        ActionHandler actionhandler = new ActionHandler();
        KeyTypedAdapter keytypedhandler = new KeyTypedAdapter();
        MouseClickedAdapter mouseclickedhandler = new MouseClickedAdapter();
        //////////////////////////////////
        
        setMinimumSize(new Dimension(400, 300));
        
        modPanel = new JPanel();
        modPanel.setSize(new Dimension(400, 300));
        modPanel.setLayout(new GridBagLayout());
        
        populatePanel = new JPanel();
        populatePanel.setSize(new Dimension(400, 300));
        
        ////////////////////////////////////////////////////////////////////////
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.NORTHWEST;

        owner_label=new JLabel("Owner's name:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        modPanel.add(owner_label, constraints);
        
        owner_field = new JTextField(link.getOwner());
        owner_field.addFocusListener(focushandler);
        owner_field.addActionListener(actionhandler);
        owner_field.addKeyListener(keytypedhandler);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        modPanel.add(owner_field, constraints);

        tenants_label = new JLabel("Tenants:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;

        modPanel.add(tenants_label, constraints);

        tenants_spinner = new JSpinner(new SpinnerNumberModel(link.getN_tenant(), 0, null, 1));
        constraints.gridx = 1;
        constraints.gridy = 1;
        modPanel.add(tenants_spinner, constraints);

        info_label = new JLabel("Floor: "+link.getFloor()+"  SM: "+link.getSm());
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        modPanel.add(info_label, constraints);
        
        cancel_button = new JButton("Cancel");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        cancel_button.setForeground(Color.WHITE);
        cancel_button.setBackground(Color.DARK_GRAY);
        cancel_button.addMouseListener(mouseclickedhandler);
        modPanel.add(cancel_button, constraints);
        
        save_button=new JButton("Save");
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        save_button.setForeground(Color.WHITE);
        save_button.setBackground(Color.GREEN);
        save_button.addMouseListener(mouseclickedhandler);
        modPanel.add(save_button, constraints);
        
        evict_button=new JButton("EVICT");
        constraints.gridx = 3;
        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        evict_button.setForeground(Color.WHITE);
        evict_button.setBackground(Color.DARK_GRAY);
        evict_button.addMouseListener(mouseclickedhandler);
        modPanel.add(evict_button, constraints);
        
        ////////////////////////////////////////////////////////////////////////
        populate_button = new JButton(">> Populate <<");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        populate_button.setForeground(Color.WHITE);
        populate_button.setBackground(Color.GREEN);
        populate_button.addMouseListener(mouseclickedhandler);
        populatePanel.add(populate_button, constraints);
        ////////////////////////////////////////////////////////////////////////
        modPanel.setVisible(false); add(modPanel);
        populatePanel.setVisible(false); add(populatePanel);
        ////////////////////////////////////////////////////////////////////////
        populatePanel.setBackground(Color.decode("#17223b"));
        modPanel.setBackground(Color.decode("#17223b"));
        owner_label.setForeground(Color.decode("#ffea00"));
        owner_field.setBackground( Color.decode("#17223b") );
        owner_field.setForeground( Color.decode("#ffea00") );
        tenants_label.setForeground(Color.decode("#ffea00"));
        tenants_spinner.setBackground( Color.decode("#17223b") );
        tenants_spinner.setForeground( Color.decode("#ffea00") );
        info_label.setForeground(Color.decode("#ffea00"));
        //save_button
        //evict_button
        if(link.isEmpty()){
            populatePanel.setVisible(true);
            setTitle(link.getOwner()+"--Empty Apartment--");
        }
        else{
            modPanel.setVisible(true);
            setTitle(link.getOwner()+"'s Apartment (F:"+link.getFloor()+" SM:"+link.getSm()+")");
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        
        requestFocusInWindow();
        checkTextField();
    }
    /**
     * Checks if owner_field has accepted values or not. If it doesn't, he sets the owner_field border red, the SAVE button background red and disables it
     */
    private void checkTextField() {
        if (owner_field.getText().isEmpty()) {
            owner_field.setBorder(BorderFactory.createLineBorder(Color.red, 3));
            save_button.setBackground(Color.RED);
            save_button.setEnabled(false);
            if (getFocusOwner()==(owner_field)) {
                owner_field.setFont(owner_field.getFont().deriveFont(Font.PLAIN));
            } else {
                owner_field.setText("Invalid Name");
                owner_field.setFont(owner_field.getFont().deriveFont(Font.ITALIC));
            }
        
        } else {
            save_button.setBackground(Color.GREEN);
            save_button.setEnabled(true);
            owner_field.setBorder(BorderFactory.createEtchedBorder());
        }
    }

    private class KeyTypedAdapter extends KeyAdapter {

        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getSource() == owner_field) {
                checkTextField();
            }
        }
    }

    private class MouseClickedAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource().equals(save_button)) {
                if (save_button.isEnabled()) {
                    setTitle(link.getOwner()+"'s Apartment (F:"+link.getFloor()+" SM:"+link.getSm()+")");
                    link.setOwner(owner_field.getText());
                    link.setN_tenant((int)tenants_spinner.getValue());
                    link.setEmpty(false);
                    condmenu.refreshApartmentButtons();
                    
                    dispose();
                }
            }
            else if(e.getSource().equals(cancel_button)){
                dispose();
            }
            else if(e.getSource().equals(evict_button)){
                link.setOwner("");
                owner_field.setText("");
                link.setN_tenant(0);
                tenants_spinner.setValue(0);
                link.setEmpty(true);
                condmenu.refreshApartmentButtons();
                modPanel.setVisible(false);
                populatePanel.setVisible(true);
            }
            else if(e.getSource().equals(populate_button)){
                populatePanel.setVisible(false);
                modPanel.setVisible(true);
            }
        }
    }

    private class FocusHandler implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            if (e.getSource() == owner_field) {
                if (owner_field.getFont().isItalic()) {
                    owner_field.setFont(owner_field.getFont().deriveFont(Font.PLAIN));
                    owner_field.setText("");
                }
                checkTextField();
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (e.getSource() == owner_field) {
                if (owner_field.getText().isEmpty()) {
                    owner_field.setText("Invalid Name");
                    owner_field.setFont(owner_field.getFont().deriveFont(Font.ITALIC));
                    save_button.setEnabled(false);
                }
            }
        }
    }

    private class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == owner_field) {
                checkTextField();
                if (!owner_field.getText().isEmpty()) {
                    tenants_spinner.requestFocus();
                }
            }
        }
    }
}
