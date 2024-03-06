/*package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import Dao.StadeDao;
import Dao.StadeDaoImpl;
import model.Stade;

public class AjouterStade extends JDialog{
	 Object[] row;

	    public AjouterStade(DefaultTableModel model) {
	        setLocationRelativeTo(null);
	        setSize(300, 200);

	        JPanel panelNorth = new JPanel();
	        JPanel panelSouth = new JPanel();
	        JPanel panelEast = new JPanel();
	        JPanel panelWest = new JPanel();
	        JPanel panelCenter = new JPanel();
	        panelNorth.setPreferredSize(new Dimension(200, 10));
	        panelSouth.setPreferredSize(new Dimension(200, 10));
	        panelEast.setPreferredSize(new Dimension(10, 200));
	        panelWest.setPreferredSize(new Dimension(10, 200));
	        getContentPane().add(panelCenter, BorderLayout.CENTER);
	        getContentPane().add(panelNorth, BorderLayout.NORTH);
	        getContentPane().add(panelSouth, BorderLayout.SOUTH);
	        getContentPane().add(panelEast, BorderLayout.EAST);
	        getContentPane().add(panelWest, BorderLayout.WEST);

	        panelCenter.setLayout(new GridLayout(4, 2, 5, 3));

	        JTextField IDStade = new JTextField();
	        JTextField nomStade = new JTextField();
	        JTextField pays = new JTextField();
	        JTextField ville = new JTextField();
	        JButton ajouter = new JButton("Ajouter");
	        JButton annuler = new JButton("Annuler");
	        panelCenter.add(new JLabel("IDStade"));
	        panelCenter.add(IDStade);
	        panelCenter.add(new JLabel("nomStade"));
	        panelCenter.add(nomStade);
	        panelCenter.add(new JLabel("pays"));
	        panelCenter.add(pays);
	        panelCenter.add(new JLabel("ville"));
	        panelCenter.add(ville);
	        panelCenter.add(annuler);
	        panelCenter.add(ajouter);
	        
	        ajouter.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String idValue = IDStade.getText();
	                String nomValue = nomStade.getText();
	                String paysValue = pays.getText();
	                String villeValue = ville.getText();

	                if (idValue.isEmpty() || nomValue.isEmpty() || paysValue.isEmpty() || villeValue.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
	                    return;
	                }

	                try {
	                    int idStade = Integer.parseInt(idValue);
	                    row = new Object[]{idStade, nomValue, paysValue,villeValue};
	                    model.addRow(row);

	                    StadeDao daoStade = new StadeDaoImpl();
	                    Stade stade = new Stade(idStade, nomValue, paysValue, villeValue);
	                    daoStade.addStade(stade);

	                    JOptionPane.showMessageDialog(null, "Stade ajoutée avec succès.");
	                    dispose();
	                } catch (NumberFormatException | SQLException ex) {
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de Stade.");
	                }
				}
			});
	        ((AbstractDocument) IDStade.getDocument()).setDocumentFilter(new DocumentFilter() {
	            @Override
	            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
	                if (string == null) {
	                    return;
	                }

	                if (string.matches("\\d+")) { // Vérifie si la saisie est un nombre
	                    super.insertString(fb, offset, string, attr);
	                } else {
	                    JOptionPane.showMessageDialog(null, "Veuillez saisir des chiffres.");
	                }
	            }

	            @Override
	            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
	                if (text == null) {
	                    return;
	                }

	                if (text.matches("\\d+")) { // Vérifie si la saisie est un nombre
	                    super.replace(fb, offset, length, text, attrs);
	                } else {
	                    JOptionPane.showMessageDialog(null, "Veuillez saisir des chiffres.");
	                }
	            }
	        });
	     // Ajout d'une action pour le bouton "Annuler"
	        annuler.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                dispose(); // Fermer la fenêtre de dialogue lors de l'annulation
	            }
	        });

	        setVisible(true);
	    }
}*/
package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


import Dao.StadeDao;
import Dao.StadeDaoImpl;
import model.Stade;

public class AjouterStade extends JDialog {
    Object[] row;

    public AjouterStade(DefaultTableModel model) {
        setLocationRelativeTo(null);
        setSize(300, 200);

        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelEast = new JPanel();
        JPanel panelWest = new JPanel();
        JPanel panelCenter = new JPanel();
        panelNorth.setPreferredSize(new Dimension(200, 10));
        panelSouth.setPreferredSize(new Dimension(200, 10));
        panelEast.setPreferredSize(new Dimension(10, 200));
        panelWest.setPreferredSize(new Dimension(10, 200));
        getContentPane().add(panelCenter, BorderLayout.CENTER);
        getContentPane().add(panelNorth, BorderLayout.NORTH);
        getContentPane().add(panelSouth, BorderLayout.SOUTH);
        getContentPane().add(panelEast, BorderLayout.EAST);
        getContentPane().add(panelWest, BorderLayout.WEST);

        panelCenter.setLayout(new GridLayout(6, 2, 5, 3));

        JTextField IDStade = new JTextField();
        JTextField nomStadeField = new JTextField();
        JTextField paysField = new JTextField();
        JTextField villeField = new JTextField();
        JButton ajouterButton = new JButton("Ajouter");
        JButton annulerButton = new JButton("Annuler");

        panelCenter.add(new JLabel("IDStade"));
        panelCenter.add(IDStade);
        panelCenter.add(new JLabel("nomStade"));
        panelCenter.add(nomStadeField);
        panelCenter.add(new JLabel("Pays"));
        panelCenter.add(paysField);
        panelCenter.add(new JLabel("Ville"));
        panelCenter.add(villeField);
        panelCenter.add(annulerButton);
        panelCenter.add(ajouterButton);

        ajouterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 String idValue = IDStade.getText();
	             String nomValue = nomStadeField.getText();
	             String paysValue = paysField.getText();
	             String villeValue = villeField.getText();
	             if (idValue.isEmpty()|| nomValue.isEmpty()|| paysValue.isEmpty()|| villeValue.isEmpty()){
	            	 JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
	            	 return;
	             }
	             try {
	                    int IDStade = Integer.parseInt(idValue);
	                    row = new Object[]{IDStade, nomValue, paysValue, villeValue};
	                    model.addRow(row);

	                    StadeDao daoStade = new StadeDaoImpl();
	                    Stade stade = new Stade(IDStade, nomValue, paysValue, villeValue);
	                    daoStade.addStade(stade);

	                    JOptionPane.showMessageDialog(null, "Stade ajoutée avec succès.");
	                    dispose();
	                } catch (NumberFormatException | SQLException ex) {
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de Stade.");
	                }
			}
		});
           
        // Filtre pour le champ ID Stade permettant uniquement les chiffres
        ((AbstractDocument) IDStade.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string == null) {
                    return;
                }
                if (string.matches("\\d+")) {
                    super.insertString(fb, offset, string, attr);
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez saisir des chiffres.");
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text == null) {
                    return;
                }
                if (text.matches("\\d+")) {
                    super.replace(fb, offset, length, text, attrs);
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez saisir des chiffres.");
                }
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }
}
