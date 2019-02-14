/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import data.DataAccess;
import static frames.MainMenu.mainmenu;
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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author utente
 */
public class CreateCond extends JFrame {

    private JPanel p;
    private JTextField name_field;
    private JLabel floors_label;
    private JSpinner floors_spinner;
    private JLabel apartments_label;
    private JSpinner apartments_spinner;

    private JScrollPane sm_scrollpane;
    private JPanel sm_panel;
    private ArrayList<JPanel> content_panel;
    private ArrayList<JSpinner> sm_spinner;
    private ArrayList<JLabel> sm_label;

    //private JCheckBox attic_checkbox;
    private JButton create_button;

    public CreateCond() {
        super("Create new Condominium");
        ///////////////////////////////////
        //Creating all listeners
        FocusHandler focushandler = new FocusHandler();
        ActionHandler actionhandler = new ActionHandler();
        KeyTypedAdapter keytypedhandler = new KeyTypedAdapter();
        MouseClickedAdapter mouseclickedhandler = new MouseClickedAdapter();
        ChangeHandler changehandler = new ChangeHandler();
        //////////////////////////////////

        setMinimumSize(new Dimension(700, 500));

        p = new JPanel();
        p.setSize(new Dimension(700, 500));
        p.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.NORTHWEST;

        name_field = new JTextField("<Enter Condominium name here>");
        name_field.addFocusListener(focushandler);
        name_field.addActionListener(actionhandler);
        name_field.addKeyListener(keytypedhandler);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        p.add(name_field, constraints);

        floors_label = new JLabel("How many floors?");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        p.add(floors_label, constraints);

        floors_spinner = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
        constraints.gridx = 1;
        constraints.gridy = 1;
        floors_spinner.setBackground(Color.decode("#17223b"));
        floors_spinner.setForeground(Color.decode("#ffea00"));
        p.add(floors_spinner, constraints);

        apartments_label = new JLabel("How many apartments does each floor have?");
        constraints.gridx = 0;
        constraints.gridy = 2;
        p.add(apartments_label, constraints);

        apartments_spinner = new JSpinner(new SpinnerNumberModel(1, 1, null, 1));
        constraints.gridx = 1;
        constraints.gridy = 2;
        apartments_spinner.addChangeListener(changehandler);
        p.add(apartments_spinner, constraints);

        JLabel smpanel_label = new JLabel("Enter the surface (square meters) of your apartments on a typical floor in your condominium:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        
        p.add(smpanel_label, constraints);
        ///////////////////////////////////////////
        ///////////////////////////////////////////
        sm_panel = new JPanel();
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.gridheight = 2;
        constraints.fill = GridBagConstraints.BOTH;
        sm_panel.setPreferredSize(new Dimension(200, 101));
       
        sm_scrollpane = new JScrollPane(sm_panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        /*sm_scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sm_scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
         */
        sm_scrollpane.setPreferredSize(new Dimension(200, 100));
        sm_panel.setBackground(Color.red);

        content_panel = new ArrayList<>();
        sm_label = new ArrayList<>();
        sm_spinner = new ArrayList<>();
        p.add(sm_scrollpane, constraints);

        refreshSmButtons();
        
        ///////////////////////////////////////////
        ///////////////////////////////////////////
        create_button = new JButton("Save and Create");
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        create_button.addMouseListener(mouseclickedhandler);
        
        p.add(create_button, constraints);

        p.setVisible(true);
        add(p);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        p.setBackground( Color.decode("#17223b") );
        setResizable(false);
        setVisible(true);
        checkTextField();
        apartments_spinner.setBackground(Color.decode("#17223b"));
        apartments_spinner.setForeground(Color.decode("#ffea00"));
        apartments_label.setForeground(Color.decode("#ffea00"));
        smpanel_label.setForeground(Color.decode("#ffea00"));
        floors_label.setForeground(Color.decode("#ffea00"));
        create_button.setForeground(Color.WHITE);
        create_button.setBackground(Color.RED);
        create_button.setEnabled(false);
        revalidate();
        repaint();
        p.requestFocus();
    }
    /**
     * Returns the height the sm_panel has to be to include all the surface-setting spinners/labels whilst being visible to the sm_scrollbar
     * @return the height sm_panel has to be
     */
    private int getSmPanelHeight() {
        int height = (27 + 10) * content_panel.size() + 70;
        return height;
    }
    /**
     * Checks if name_field has accepted values or not. If it doesn't, he sets the name_field border red, the Create button background red and disables it
     */
    private void checkTextField() {
        if (name_field.getText().isEmpty() || name_field.getText() == "<Enter Condominium name here>") {
            name_field.setBorder(BorderFactory.createLineBorder(Color.red, 3));
            create_button.setBackground(Color.decode("#ff0000"));
            create_button.setEnabled(false);
            if (getFocusOwner().equals(name_field)) {
                name_field.setFont(name_field.getFont().deriveFont(Font.PLAIN));
            } else {
                name_field.setText("Invalid Name");
                name_field.setFont(name_field.getFont().deriveFont(Font.ITALIC));
            }
        
        } else {
            create_button.setBackground(Color.decode("#44b93c"));
            create_button.setEnabled(true);
            name_field.setBorder(BorderFactory.createEtchedBorder());
        }
        name_field.setBackground(Color.decode("#17223b"));
        name_field.setForeground(Color.decode("#ffea00"));
        
        revalidate();
        repaint();
    }
    /**
     * Refreshes the content of the sm_scrollpane
     */
    private void refreshSmButtons() {
        content_panel.clear();
        sm_spinner.clear();
        sm_label.clear();
        sm_panel.removeAll();

        for (int n = 0; n <= (Integer) apartments_spinner.getValue() - 1; n++) {
            content_panel.add(new JPanel());
            sm_label.add(new JLabel("Ap." + (n + 1)));
            sm_spinner.add(new JSpinner(new SpinnerNumberModel((float) 1, (float) 0.1, null, (float) 0.1)));
            content_panel.get(n).add(sm_label.get(n));
            content_panel.get(n).add(sm_spinner.get(n));
        }
        
        sm_label.forEach((g) -> g.setPreferredSize(new Dimension(45, 15)));
        sm_label.forEach((g) -> g.setForeground(Color.decode("#ffea00")));
        sm_spinner.forEach((g) -> g.setPreferredSize(new Dimension(45, 20)));
        content_panel.forEach((g) -> g.setPreferredSize(new Dimension(100, 27)));
        content_panel.forEach((g) -> g.setBackground(Color.decode("#283b67")));
        content_panel.forEach((g) -> sm_panel.add(g));
        sm_panel.setPreferredSize(new Dimension(200, getSmPanelHeight()));
        sm_panel.setBackground(Color.decode("#223258"));
        
        sm_panel.revalidate();
    }

    private class KeyTypedAdapter extends KeyAdapter {

        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getSource() == name_field) {
                checkTextField();
            }
        }
    }

    private class MouseClickedAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource().equals(create_button)) {
                if (create_button.isEnabled()) {
                    ArrayList<Float> sm = new ArrayList<>();
                    int n = 0;
                    sm_spinner.forEach((g) -> sm.add((float) g.getValue()));
                    DataAccess.c.add(new data.Condominium(sm.toArray(new Float[sm.size()]), (int) floors_spinner.getValue(), (int) apartments_spinner.getValue(), (String) name_field.getText()));
                    mainmenu.refreshPanels();
                    dispose();
                }
            }
        }
    }

    private class FocusHandler implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            if (e.getSource() == name_field) {
                if (name_field.getText().equals("<Enter Condominium name here>") || name_field.getFont().isItalic()) {
                    name_field.setFont(name_field.getFont().deriveFont(Font.PLAIN));
                    name_field.setText("");
                }
                checkTextField();
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (e.getSource() == name_field) {
                if (name_field.getText().isEmpty()) {
                    name_field.setText("Invalid Name");
                    name_field.setFont(name_field.getFont().deriveFont(Font.ITALIC));
                    create_button.setEnabled(false);
                }
            }
        }
    }

    private class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == name_field) {
                checkTextField();
                if (!name_field.getText().isEmpty()) {
                    floors_spinner.requestFocus();
                }
            }
        }
    }

    private class ChangeHandler implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            if (e.getSource().equals(apartments_spinner)) {
                sm_scrollpane.requestFocus();
                refreshSmButtons();
                apartments_spinner.requestFocus();
            }
        }
    }
}
