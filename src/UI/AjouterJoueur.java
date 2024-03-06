package UI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import Dao.EquipesDao;
import Dao.EquipesDaoImpl;
import Dao.JoueurDao;
import Dao.JoueurDaoImpl;
import model.Equipe;
import model.Joueur;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AjouterJoueur extends JDialog {
    Object[] row;

    public AjouterJoueur(DefaultTableModel model) {
    	// Configuration de la fenêtre de dialogue
        setLocationRelativeTo(null);
        setSize(300, 200);
        
        // Création des panneaux pour l'organisation de la disposition
        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelEast = new JPanel();
        JPanel panelWest = new JPanel();
        JPanel panelCenter = new JPanel();
        
        // Configuration des tailles préférées des panneaux
        panelNorth.setPreferredSize(new Dimension(200, 10));
        panelSouth.setPreferredSize(new Dimension(200, 10));
        panelEast.setPreferredSize(new Dimension(10, 200));
        panelWest.setPreferredSize(new Dimension(10, 200));
        
        // Ajout des panneaux à la fenêtre de dialogue avec des dispositions spécifiques
        getContentPane().add(panelCenter, BorderLayout.CENTER);
        getContentPane().add(panelNorth, BorderLayout.NORTH);
        getContentPane().add(panelSouth, BorderLayout.SOUTH);
        getContentPane().add(panelEast, BorderLayout.EAST);
        getContentPane().add(panelWest, BorderLayout.WEST);
        
        // Configuration de la disposition du panneau central
        panelCenter.setLayout(new GridLayout(6, 2, 5, 3));

        // Création des champs de saisie, du combobox et des boutons
        JTextField IDJoueur = new JTextField();
        JTextField nomJoueur = new JTextField();
        JTextField poste = new JTextField();

        // Récupération des équipes depuis la base de données	
        EquipesDao daoEquipe = new EquipesDaoImpl();
      
        // Création d'une JComboBox pour afficher les équipes
        List<Equipe> equipes = null;
        try {
            equipes = daoEquipe.getAllEquipe();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        final List<Equipe> equipesFinal = equipes;

        String[] nomEquipe = new String[equipes.size()];
        for (int i = 0; i < equipes.size(); i++) {
            nomEquipe[i] = equipes.get(i).getNomEquipe();
        }

        JComboBox<String> equipeCombo = new JComboBox<>(nomEquipe);
/*        EquipesDao daoEquipe = new EquipesDaoImpl();
        List<Equipe> equipes = null;
        try {
            equipes = daoEquipe.getAllEquipe();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<Equipe> equipesFinal = equipes;
        List<String> nomsEquipes = new ArrayList<>();
        String[] nomEquipe = new String[equipes.size()];
        for (int i = 0; i < equipes.size(); i++) {
            nomEquipe[i] = equipes.get(i).getNomEquipe();
        }
        for (Equipe equipe : equipes) {
            nomsEquipes.add(equipe.getNomEquipe()); // Ajoute le nom de l'équipe D
            nomsEquipes.add(equipe.getNomEquipeV()); // Ajoute le nom de l'équipe V
        }
        String[] nomEquipesArray = nomsEquipes.toArray(new String[0]);
        JComboBox<String> equipeCombo = new JComboBox<>(nomEquipesArray);*/
        JButton ajouter = new JButton("Ajouter");
        JButton annuler = new JButton("Annuler");

        panelCenter.add(new JLabel("Equipe"));
        panelCenter.add(equipeCombo);
        // Création des JComboBox pour le choix du poste et ajout des éléments
        panelCenter.add(new JLabel("poste"));
        String[] postes = {"gardien", "arrières gauche", "arrières droit", "milieux défensifs","milieux offensive","attaquant","avant-centre"}; // Vous pouvez modifier les postes disponibles ici
        JComboBox<String> posteCombo = new JComboBox<>(postes);
        panelCenter.add(posteCombo);
        panelCenter.add(new JLabel("IDJoueur"));
        panelCenter.add(IDJoueur);
        panelCenter.add(new JLabel("nomJoueur"));
        panelCenter.add(nomJoueur);
        panelCenter.add(annuler);
        panelCenter.add(ajouter);

        // ActionListener pour le bouton "ajouter"
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idValue = IDJoueur.getText();
                String nomValue = nomJoueur.getText();
                String posteValue = posteCombo.getSelectedItem().toString(); // Obtenez la valeur sélectionnée dans le JComboBox posteCombo

                if (idValue.isEmpty() || nomValue.isEmpty() || posteValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
                    return;
                }

                int selectedIndex = equipeCombo.getSelectedIndex();
                /*if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une équipe.");
                    return;
                }*/

                Equipe selectedEquipe = equipesFinal.get(selectedIndex);

                try {
                    int idJoueur = Integer.parseInt(idValue);
                    JoueurDao daoJoueur = new JoueurDaoImpl();
                    Joueur joueur = new Joueur(idJoueur, nomValue, posteValue, selectedEquipe);
                    daoJoueur.addJoueur(joueur);

                    Object[] rowData = {joueur.getIDJoueur(), joueur.getNomJoueur(), joueur.getPoste(), selectedEquipe.getNomEquipe()};
                    model.addRow(rowData);

                    JOptionPane.showMessageDialog(null, "Joueur ajouté avec succès.");
                    dispose();
                } catch (NumberFormatException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du joueur.");
                }
            }
        });
        
     // ActionListener pour le bouton "ajouter"
       /* ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idValue = IDJoueur.getText();
                String nomValue = nomJoueur.getText();
                String posteValue = posteCombo.getSelectedItem().toString(); // Obtenez la valeur sélectionnée dans le JComboBox posteCombo

                if (idValue.isEmpty() || nomValue.isEmpty() || posteValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
                    return;
                }

                if (equipesFinal.size() % 2 == 0) {
                    List<Equipe> equipesDomicile = new ArrayList<>();
                    List<Equipe> equipesVisiteur = new ArrayList<>();

                    // Parcours de la liste et séparation des équipes domicile et visiteur
                    for (int i = 0; i < equipesFinal.size(); i++) {
                        if (i % 2 == 0) {
                            equipesDomicile.add(equipesFinal.get(i));
                        } else {
                            equipesVisiteur.add(equipesFinal.get(i));
                        }
                    }

                    // Utilisation des listes d'équipes domicile et visiteur comme nécessaire
                    // Par exemple, pour obtenir les équipes à l'index sélectionné dans equipeCombo
                    int selectedIndex = equipeCombo.getSelectedIndex();
                    if (selectedIndex >= 0 && selectedIndex < equipesDomicile.size()) {
                        Equipe equipeDomicile = equipesDomicile.get(selectedIndex);
                        Equipe equipeVisiteur = equipesVisiteur.get(selectedIndex);

                        try {
                            int idJoueur = Integer.parseInt(idValue);
                            JoueurDao daoJoueur = new JoueurDaoImpl();
                            
                            // Utilisation des équipes récupérées pour le joueur
                            Joueur joueur = new Joueur(idJoueur, nomValue, posteValue, equipeDomicile, equipeVisiteur);
                            daoJoueur.addJoueur(joueur);

                            Object[] rowData = {joueur.getIDJoueur(), joueur.getNomJoueur(), joueur.getPoste(), equipeDomicile.getNomEquipe(), equipeVisiteur.getNomEquipeV()};
                            model.addRow(rowData);

                            JOptionPane.showMessageDialog(null, "Joueur ajouté avec succès.");
                            dispose();
                        } catch (NumberFormatException | SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du joueur.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Veuillez sélectionner une équipe valide.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nombre d'équipes invalide.");
                }
            }
        });
*/


        
        // DocumentFilter pour le champ IDJoueur pour n'autoriser que des chiffres ,héritant
        ((AbstractDocument) IDJoueur.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                // Vérifie si le texte de remplacement est nul ou contient autre chose que des chiffres
                if (text == null || !text.matches("\\d+")) {
                    // Si oui, affiche un message d'erreur et interrompt le remplacement
                    JOptionPane.showMessageDialog(null, "Veuillez saisir des chiffres.");
                    return;
                }
                // Si le texte de remplacement est composé uniquement de chiffres, remplace normalement le texte dans le champ de texte
                super.replace(fb, offset, length, text, attrs);
            }

        });

        // ActionListener pour le bouton "annuler"
        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Affichage de la fenêtre de dialogue
        setVisible(true);
    }
}
