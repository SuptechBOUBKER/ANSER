package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import Dao.EquipesDao;
import Dao.EquipesDaoImpl;
import Dao.MatchDao;
import Dao.MatchDaoImpl;
import Dao.StadeDao;
import Dao.StadeDaoImpl;
import Dao.JoueurDao;
import Dao.JoueurDaoImpl;
import Dao.*;

import model.Equipe;
import model.Joueur;
import model.Match;
import model.Stade;
import model.*;

public class AjouterBut extends JDialog {
	private DefaultTableModel butModel;

	public AjouterBut(DefaultTableModel model) {
		this.butModel = model;
        setLocationRelativeTo(null);
        setSize(300, 200);

        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelEast = new JPanel();
        JPanel panelWest = new JPanel();
        JPanel panelCenter = new JPanel();

        panelNorth.setPreferredSize(new Dimension(300, 10));
        panelSouth.setPreferredSize(new Dimension(300, 10));
        panelEast.setPreferredSize(new Dimension(10, 200));
        panelWest.setPreferredSize(new Dimension(10, 200));

        getContentPane().add(panelCenter, BorderLayout.CENTER);
        getContentPane().add(panelNorth, BorderLayout.NORTH);
        getContentPane().add(panelSouth, BorderLayout.SOUTH);
        getContentPane().add(panelEast, BorderLayout.EAST);
        getContentPane().add(panelWest, BorderLayout.WEST);
        panelCenter.setLayout(new GridLayout(6, 1, 5, 2));
       
        MatchDao daoMatch = new MatchDaoImpl();
        List<Match> matches = null;
        try {
            matches = daoMatch.getAllMatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        final List<Match> matchFinal = matches;
        String[] idMatches = new String[matches.size()];
        for (int i = 0; i < matches.size(); i++) {
            idMatches[i] = String.valueOf(matches.get(i).getIDMatch());
        }
        JComboBox<String> matchCombo = new JComboBox<>(idMatches);
        
        //##############
        EquipesDao daoEquipe = new EquipesDaoImpl();
        List<Equipe> equipes = null;
        try {
            equipes = daoEquipe.getAllEquipe();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        final List<Equipe> equipeFinal = equipes;
        String[] nomEquipe = new String[equipes.size()];
        for (int i = 0; i < equipes.size(); i++) {
            nomEquipe[i] = equipes.get(i).getNomEquipe();
        }
        JComboBox<String> EquipeCombo = new JComboBox<>(nomEquipe);
        //##############
        JoueurDao daoJoueur = new JoueurDaoImpl();
        List<Joueur> joueurs = null;
        try {
            joueurs = daoJoueur.getAllJoueur();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        final List<Joueur> joueurFinal = joueurs;
        String[] nomJoueur = new String[joueurs.size()];
        for (int i = 0; i < joueurs.size(); i++) {
            nomJoueur[i] = joueurs.get(i).getNomJoueur();
        }
        JComboBox<String> JoueurCombo = new JComboBox<>(nomJoueur);
        
        
        JTextField IDBut = new JTextField();
        JTextField minute = new JTextField();
        JButton ajouter = new JButton("Ajouter");
        JButton annuler = new JButton("Annuler");
        /*panelCenter.add(new JLabel("Equipe_gagné"));
        panelCenter.add(equipeCombo);*/
        panelCenter.add(new JLabel("IDMatch"));
        panelCenter.add(matchCombo);
        panelCenter.add(new JLabel("Equipe_Marqué"));
        panelCenter.add(EquipeCombo);
        panelCenter.add(new JLabel("NomJoueur"));
        panelCenter.add(JoueurCombo);
        panelCenter.add(new JLabel("IDBut"));
        panelCenter.add(IDBut);
        panelCenter.add(new JLabel("minute"));
        panelCenter.add(minute);
        panelCenter.add(annuler);
        panelCenter.add(ajouter);
        
        matchCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndexMatch = matchCombo.getSelectedIndex();

                if (selectedIndexMatch != -1) {
                    Match selectedMatch = matchFinal.get(selectedIndexMatch);

                    // Récupérer les équipes associées à ce match
                    Equipe equipe1 = selectedMatch.getEquipeD();
                    Equipe equipe2 = selectedMatch.getEquipeV();

                    // Mettre à jour le modèle du JComboBox EquipeCombo
                    String[] nomEquipes = { equipe1.getNomEquipe(), equipe2.getNomEquipe() };
                    EquipeCombo.setModel(new DefaultComboBoxModel<>(nomEquipes));
                }
            }
        });
/*
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idValue = IDBut.getText();
                String idminute = minute.getText();
                if (idValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
                    return;
                }

                //int selectedIndexEquipe = equipeCombo.getSelectedIndex();
                int selectedIndexMatch = matchCombo.getSelectedIndex();
                //int selectedIndexEquipe2 = equipeCombo2.getSelectedIndex();
                int selectedIndexEquipe = EquipeCombo.getSelectedIndex();
                int selectedIndexJoueur = JoueurCombo.getSelectedIndex();

                if (selectedIndexMatch == -1 ||  selectedIndexEquipe == -1  ||selectedIndexJoueur == -1) {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une équipe et un joueur et un match.");
                    return;
                }

                Equipe selectedEquipe = equipeFinal.get(selectedIndexEquipe);
                //Equipe selectedEquipe1 = equipeFinal.get(selectedIndexEquipe);
				Match selectedMatch = matchFinal.get(selectedIndexMatch);
                Joueur selectedJoueur = joueurFinal.get(selectedIndexJoueur);
                
                try {
                    int idBut = Integer.parseInt(idValue);
                    int minute = Integer.parseInt(idminute);
                    ButDao daoBut = new ButDaoImpl();
                    
                    But but = new But(idBut, minute,
                    		selectedMatch, selectedEquipe, selectedJoueur);
                    daoBut.addBut(but);
                    Object[] rowData = {but.getIDBut(), minute, 
                    		selectedMatch.getIDMatch(), selectedEquipe.getNomEquipe(),selectedJoueur.getNomJoueur()};
                    model.addRow(rowData);
                    JOptionPane.showMessageDialog(null, "But ajouté avec succès.");
                    dispose();
                } catch (NumberFormatException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du But.");
                }	
            }
        });
*/
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idValue = IDBut.getText();
                String idminute = minute.getText();
                if (idValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
                    return;
                }

                int selectedIndexMatch = matchCombo.getSelectedIndex();
                int selectedIndexEquipe = EquipeCombo.getSelectedIndex();
                int selectedIndexJoueur = JoueurCombo.getSelectedIndex();

                if (selectedIndexMatch == -1 || selectedIndexEquipe == -1 || selectedIndexJoueur == -1) {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une équipe et un joueur et un match.");
                    return;
                }

                Equipe selectedEquipe = equipeFinal.get(selectedIndexEquipe);
                Match selectedMatch = matchFinal.get(selectedIndexMatch);
                Joueur selectedJoueur = joueurFinal.get(selectedIndexJoueur);

                try {
                    int idBut = Integer.parseInt(idValue);
                    int minute = Integer.parseInt(idminute);
                    ButDao daoBut = new ButDaoImpl();

                    // Insérer le nouveau but dans la base de données
                    But but = new But(idBut, minute, selectedMatch, selectedEquipe, selectedEquipe, selectedJoueur);
                    daoBut.addBut(but);

                    // Ajouter les données au modèle de tableau
                    String equipeMarquee = selectedMatch.getEquipeD().getNomEquipe() + " vs " + selectedMatch.getEquipeV().getNomEquipe();
                    Object[] rowData = {but.getIDBut(), minute, selectedMatch.getIDMatch(), equipeMarquee, selectedJoueur.getNomJoueur()};
					butModel.addRow(rowData);

                    JOptionPane.showMessageDialog(null, "But ajouté avec succès.");
                    dispose();
                } catch (NumberFormatException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du But.");
                }
            }
        });

        ((AbstractDocument) IDBut.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("\\d+")) {
                    super.insertString(fb, offset, string, attr);
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez saisir des chiffres.");
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("\\d+")) {
                    super.replace(fb, offset, length, text, attrs);
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez saisir des chiffres.");
                }
            }
        });

        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}
/*EquipesDao daoEquipe = new EquipesDaoImpl();
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
    nomsEquipes.add(equipe.getNomEquipe()); // Ajoute le nom de l'équipe V
}
String[] nomEquipesArray = nomsEquipes.toArray(new String[0]);
JComboBox<String> equipeCombo = new JComboBox<>(nomEquipesArray);*/

//JComboBox<String> equipeCombo1 = new JComboBox<>(nomEquipe);

//JComboBox<String> equipeCombo1 = new JComboBox<>(nomEquipesArray);
//JComboBox<String> equipeCombo2 = new JComboBox<>(nomEquipeV);
/*EquipesDao daoEquipe1 = new EquipesDaoImpl();
List<Equipe> equipes1 = null;
try {
    equipes1 = daoEquipe1.getAllEquipe();
} catch (SQLException throwables) {
    throwables.printStackTrace();
}

final List<Equipe> equipesFinal1 = equipes1;
//final List<Equipe> equipesFinal1 = equipes;
//final List<Equipe> equipesFinal2 = equipes;

String[] nomEquipe1 = new String[equipes1.size()];
for (int i = 0; i < equipes1.size(); i++) {
    nomEquipe1[i] = equipes1.get(i).getNomEquipe();
}
JComboBox<String> equipeCombo1 = new JComboBox<>(nomEquipe1);


EquipesDao daoEquipe2 = new EquipesDaoImpl();
List<Equipe> equipes2 = null;
try {
    equipes2 = daoEquipe2.getAllEquipe();
} catch (SQLException throwables) {
    throwables.printStackTrace();
}

final List<Equipe> equipesFinal2 = equipes2;
//final List<Equipe> equipesFinal1 = equipes;
//final List<Equipe> equipesFinal2 = equipes;

String[] nomEquipe2 = new String[equipes2.size()];
for (int i = 0; i < equipes2.size(); i++) {
    nomEquipe2[i] = equipes2.get(i).getNomEquipe();
}
JComboBox<String> equipeCombo2 = new JComboBox<>(nomEquipe2);*/
//#############
/*MatchDao daoMatch = new MatchDaoImpl();
List<Match> match = null;
try {
    match = daoMatch.getAllMatch();
} catch (SQLException throwables) {
    throwables.printStackTrace();
}
final List<Match> matchFinal = match;
Integer[] IDMatchDomicile = new Integer[match.size()];
Integer[] IDMatchVisiteur = new Integer[match.size()];
for (int i = 0; i < match.size(); i++) {
    IDMatchDomicile[i] = match.get(i).getEquipeDomicile();
    IDMatchVisiteur[i] = match.get(i).getEquipeVisiteur();
}
JComboBox<Integer> matchComboDomicile = new JComboBox<>(IDMatchDomicile);
JComboBox<Integer> matchComboVisiteur = new JComboBox<>(IDMatchVisiteur);*/
// Utilisez matchComboDomicile et matchComboVisiteur comme vous le souhaitez dans votre application*/
//#####################"""



//Equipe selectedEquipe1 = equipesFinal1.get(selectedIndexEquipe1);
//Equipe selectedEquipe2 = equipesFinal2.get(selectedIndexEquipe2);
/*try {
    int idBut = Integer.parseInt(idValue);
    ButDao daoBut = new ButDaoImpl();
    But but = new But(idBut, selectedEquipe.getIDEquipe(), selectedJoueur.getIDJoueur(), 
    selectedMatch1.getEquipeDomicile(), selectedMatch2.getEquipeVisiteur());
    daoBut.addBut(but);

    Object[] rowData = { but.getIDBut(), selectedEquipe.getNomEquipe(), selectedJoueur.getNomJoueur(), 
    selectedMatch1.getIDMatch() ,selectedMatch2.getIDMatch()};
    model.addRow(rowData);
*/


/*
public class AjouterBut extends JDialog {
	public AjouterBut(DefaultTableModel model) {
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
        panelCenter.setLayout(new GridLayout(7, 1, 5, 2));
        EquipesDao daoEquipe = new EquipesDaoImpl();
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
        JComboBox<String> equipeCombo = new JComboBox<>(nomEquipesArray);
        //JComboBox<String> equipeCombo1 = new JComboBox<>(nomEquipe);
        
        //JComboBox<String> equipeCombo1 = new JComboBox<>(nomEquipesArray);
        //JComboBox<String> equipeCombo2 = new JComboBox<>(nomEquipeV);
        /*EquipesDao daoEquipe1 = new EquipesDaoImpl();
        List<Equipe> equipes1 = null;
        try {
            equipes1 = daoEquipe1.getAllEquipe();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        final List<Equipe> equipesFinal1 = equipes1;
        //final List<Equipe> equipesFinal1 = equipes;
        //final List<Equipe> equipesFinal2 = equipes;

        String[] nomEquipe1 = new String[equipes1.size()];
        for (int i = 0; i < equipes1.size(); i++) {
            nomEquipe1[i] = equipes1.get(i).getNomEquipe();
        }
        JComboBox<String> equipeCombo1 = new JComboBox<>(nomEquipe1);
        
        
        EquipesDao daoEquipe2 = new EquipesDaoImpl();
        List<Equipe> equipes2 = null;
        try {
            equipes2 = daoEquipe2.getAllEquipe();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        final List<Equipe> equipesFinal2 = equipes2;
        //final List<Equipe> equipesFinal1 = equipes;
        //final List<Equipe> equipesFinal2 = equipes;

        String[] nomEquipe2 = new String[equipes2.size()];
        for (int i = 0; i < equipes2.size(); i++) {
            nomEquipe2[i] = equipes2.get(i).getNomEquipe();
        }
        JComboBox<String> equipeCombo2 = new JComboBox<>(nomEquipe2);*/
        //#############
        /*MatchDao daoMatch = new MatchDaoImpl();
        List<Match> match = null;
        try {
            match = daoMatch.getAllMatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        final List<Match> matchFinal = match;
        String[] nomEquipeD = new String[match.size()];
        for (int i = 0; i < match.size(); i++) {
            nomEquipeD[i] = match.get(i).getEquipeDomicile();
        }
        String[] nomEquipeV = new String[match.size()];
        for (int i = 0; i < match.size(); i++) {
            nomEquipeV[i] = match.get(i).getEquipeVisiteur();
        }
        JComboBox<String> matchCombo = new JComboBox<>(nomEquipeD);
        JComboBox<String> matchCombo1 = new JComboBox<>(nomEquipeV);*/
        // Utilisez matchComboDomicile et matchComboVisiteur comme vous le souhaitez dans votre application*/
        //#####################"""
       /* JoueurDao daoJoueur = new JoueurDaoImpl();
        List<Joueur> joueurs = null;
        try {
            joueurs = daoJoueur.getAllJoueur();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        final List<Joueur> joueurFinal = joueurs;
        String[] nomJoueur = new String[joueurs.size()];
        for (int i = 0; i < joueurs.size(); i++) {
            nomJoueur[i] = joueurs.get(i).getNomJoueur();
        }
        JComboBox<String> JoueurCombo = new JComboBox<>(nomJoueur);
        JTextField IDBut = new JTextField();
        JTextField minute = new JTextField();
        JButton ajouter = new JButton("Ajouter");
        JButton annuler = new JButton("Annuler");
        panelCenter.add(new JLabel("Equipe_gagné"));
        panelCenter.add(equipeCombo);
        panelCenter.add(new JLabel("Match_EquipeDomicile"));
        panelCenter.add(matchCombo);
       panelCenter.add(new JLabel("Match_EquipeVisiteur"));
        panelCenter.add(matchCombo1);
        panelCenter.add(new JLabel("NomJoueur"));
        panelCenter.add(JoueurCombo);
        panelCenter.add(new JLabel("IDBut"));
        panelCenter.add(IDBut);
        panelCenter.add(new JLabel("minute"));
        panelCenter.add(minute);
        panelCenter.add(annuler);
        panelCenter.add(ajouter);
        
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idValue = IDBut.getText();
                String idminute = minute.getText();
                if (idValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
                    return;
                }

                int selectedIndexEquipe = equipeCombo.getSelectedIndex();
                int selectedIndexMatch = matchCombo.getSelectedIndex();
                int selectedIndexMatch1 = matchCombo1.getSelectedIndex();
                //int selectedIndexEquipe2 = equipeCombo2.getSelectedIndex();
                //int selectedIndexEquipe1 = equipeCombo1.getSelectedIndex();
                int selectedIndexJoueur = JoueurCombo.getSelectedIndex();
                

                if (selectedIndexEquipe == -1 || selectedIndexMatch == -1 || selectedIndexMatch1 == -1||  selectedIndexJoueur == -1) {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une équipe et un joueur et un match.");
                    return;
                }

   
				Equipe selectedEquipe = equipesFinal.get(selectedIndexEquipe);
				Match selectedMatch = matchFinal.get(selectedIndexMatch);
				Match selectedMatch1 = matchFinal.get(selectedIndexMatch1);
                Joueur selectedJoueur = joueurFinal.get(selectedIndexJoueur);*/
                //Equipe selectedEquipe1 = equipesFinal1.get(selectedIndexEquipe1);
                //Equipe selectedEquipe2 = equipesFinal2.get(selectedIndexEquipe2);
                /*try {
                    int idBut = Integer.parseInt(idValue);
                    ButDao daoBut = new ButDaoImpl();
                    But but = new But(idBut, selectedEquipe.getIDEquipe(), selectedJoueur.getIDJoueur(), 
                    selectedMatch1.getEquipeDomicile(), selectedMatch2.getEquipeVisiteur());
                    daoBut.addBut(but);

                    Object[] rowData = { but.getIDBut(), selectedEquipe.getNomEquipe(), selectedJoueur.getNomJoueur(), 
                    selectedMatch1.getIDMatch() ,selectedMatch2.getIDMatch()};
                    model.addRow(rowData);*/
                
              /* try {
                    int idBut = Integer.parseInt(idValue);
                    int minute = Integer.parseInt(idminute);
                    ButDao daoBut = new ButDaoImpl();
                    
                    But but = new But(idBut, minute,selectedEquipe,
                    		selectedMatch, selectedMatch1, selectedJoueur);
                    daoBut.addBut(but);
                    Object[] rowData = {but.getIDBut(), minute	, 
                    		selectedEquipe.getNomEquipe(), selectedMatch.getEquipeDomicile()
                    		,selectedMatch1.getEquipeVisiteur(), selectedJoueur.getNomJoueur()};
                    model.addRow(rowData);
                    JOptionPane.showMessageDialog(null, "But ajouté avec succès.");
                    dispose();
                } catch (NumberFormatException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du But.");
                }	
            }
        });

        ((AbstractDocument) IDBut.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string.matches("\\d+")) {
                    super.insertString(fb, offset, string, attr);
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez saisir des chiffres.");
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text.matches("\\d+")) {
                    super.replace(fb, offset, length, text, attrs);
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez saisir des chiffres.");
                }
            }
        });

        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}*/