package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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

import Dao.EquipesDaoImpl;
import Dao.EquipesDao;
import model.Equipe;

public class AjouterEquipes extends JDialog {
    Object[] row;

    public AjouterEquipes(DefaultTableModel model) {
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

        panelCenter.setLayout(new GridLayout(5, 2, 5, 3));

        JTextField IDEquipe = new JTextField();
        JTextField nomEquipe = new JTextField();
        JTextField pays = new JTextField();
        JButton ajouter = new JButton("Ajouter");
        JButton annuler = new JButton("Annuler");
        panelCenter.add(new JLabel("IDEquipe"));
        panelCenter.add(IDEquipe);
        panelCenter.add(new JLabel("nomEquipe"));
        panelCenter.add(nomEquipe);
        panelCenter.add(new JLabel("pays"));
        panelCenter.add(pays);
        panelCenter.add(annuler);
        panelCenter.add(ajouter);
        
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idValue = IDEquipe.getText();
                String nomValue = nomEquipe.getText();
                String paysValue = pays.getText();
       

                if (idValue.isEmpty() || nomValue.isEmpty() || paysValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
                    return;
                }

                try {
                    int idEquipe = Integer.parseInt(idValue);
                    row = new Object[]{idEquipe, nomValue, paysValue};
                    model.addRow(row);

                    EquipesDao daoEquipes = new EquipesDaoImpl();
                    Equipe equipe = new Equipe(idEquipe, nomValue, paysValue);
                    daoEquipes.addEquipe(equipe);

                    JOptionPane.showMessageDialog(null, "Équipe ajoutée avec succès.");
                    dispose();
                } catch (NumberFormatException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de l'équipe.");
                }
            }
        });
        
     // Création d'un DocumentFilter pour restreindre la saisie aux chiffres dans IDEquipe
        ((AbstractDocument) IDEquipe.getDocument()).setDocumentFilter(new DocumentFilter() {
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
}
