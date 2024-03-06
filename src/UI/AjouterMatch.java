/*package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

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
import Dao.JoueurDao;
import Dao.JoueurDaoImpl;
import Dao.StadeDao;
import Dao.StadeDaoImpl;
import Dao.MatchDaoImpl;
import Dao.MatchDao;

import model.Stade;
import model.Equipe;
import model.Joueur;
import model.Match;

public class AjouterMatch extends JDialog{
	 Object[] row;

	    public AjouterMatch(DefaultTableModel model) {
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
	        
	        JTextField IDMatch = new JTextField();
	        
	        EquipesDao daoEquipe = new EquipesDaoImpl();
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

	        EquipesDao daoEquipe1 = new EquipesDaoImpl();
	        List<Equipe> equipes1 = null;
	        try {
	            equipes = daoEquipe.getAllEquipe();
	        } catch (SQLException throwables) {
	            throwables.printStackTrace();
	        }

	        final List<Equipe> equipesFinal1 = equipes;

	        String[] nomEquipe1 = new String[equipes.size()];
	        for (int i = 0; i < equipes.size(); i++) {
	            nomEquipe[i] = equipes.get(i).getNomEquipe();
	        }

	        JComboBox<String> equipeCombo1 = new JComboBox<>(nomEquipe);
	        
	        StadeDao daoStade=new StadeDaoImpl();
	        List<Stade> stades = null;
	        try {
				stades=daoStade.getAllStade();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
	        final List<Stade> stadesFinal = stades;
	        String[] nomStade = new String [stades.size()];
	        for (int i=0;i<stades.size();i++) {
	        	nomStade[i] = stades.get(i).getNomStade();
	        }
	        JComboBox<String> stadesCombo = new JComboBox<>(nomStade);
	        JButton ajouter = new JButton("Ajouter");
	        JButton annuler = new JButton("Annuler");
	        panelCenter.add(new JLabel("EquipeD"));
	        panelCenter.add(equipeCombo);
	        panelCenter.add(new JLabel("EquipeV"));
	        panelCenter.add(equipeCombo1);
	        panelCenter.add(new JLabel("Stade"));
	        panelCenter.add(stadesCombo);
	        panelCenter.add(new JLabel("IDMatch"));
	        panelCenter.add(IDMatch);
	        
	        ajouter.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String idValue = IDMatch.getText();
	                 // Obtenez la valeur sélectionnée dans le JComboBox posteCombo

	                if (idValue.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
	                    return;
	                }

	                int selectedIndex = equipeCombo.getSelectedIndex();
	                if (selectedIndex == -1) {
	                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une équipe.");
	                    return;
	                }

	                Equipe selectedEquipe = equipesFinal.get(selectedIndex);
	                //Equipe selectedEquipe1 = equipesFinal.get(selectedIndex);
	                Stade selectedStade = stadesFinal.get(selectedIndex);

	                try {
	                    int idMatch = Integer.parseInt(idValue);
	                    MatchDao daoMatch = new MatchDaoImpl();
	                    Match match = new Match(idMatch,selectedEquipe,selectedStade);
	                    daoMatch.addMatch(match);

	                    Object[] rowData = {match.getIDMatch(),selectedEquipe.getNomEquipe(),selectedStade.getNomStade()};
	                    model.addRow(rowData);

	                    JOptionPane.showMessageDialog(null, "Joueur ajouté avec succès.");
	                    dispose();
	                } catch (NumberFormatException | SQLException ex) {
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du match.");
	                }
	            }
	        });
	        ((AbstractDocument) IDMatch.getDocument()).setDocumentFilter(new DocumentFilter() {
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

	        annuler.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                dispose();
	            }
	        });

	        setVisible(true);
}
}*/

package UI;
import Dao.EquipesDao;
import Dao.EquipesDaoImpl;
import Dao.StadeDao;
import Dao.StadeDaoImpl;
import Dao.MatchDao;
import Dao.MatchDaoImpl;
import model.Equipe;
import model.Stade;
import model.Match;

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
import java.util.List;

public class AjouterMatch extends JDialog {

    public AjouterMatch(DefaultTableModel model) {
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

        panelCenter.setLayout(new GridLayout(5, 2, 6, 3));
        JTextField IDMatch = new JTextField();

        EquipesDao daoEquipe = new EquipesDaoImpl();
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
        
        EquipesDao daoEquipe1 = new EquipesDaoImpl();
        List<Equipe> equipes1 = null;
        try {
            equipes1 = daoEquipe1.getAllEquipe();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        final List<Equipe> equipesFinal1 = equipes1;

        String[] nomEquipe1 = new String[equipes1.size()];
        for (int i = 0; i < equipes1.size(); i++) {
            nomEquipe1[i] = equipes1.get(i).getNomEquipe();
        }
        JComboBox<String> equipeCombo1 = new JComboBox<>(nomEquipe1);
        //#############
        /*EquipesDao daoEquipe1 = new EquipesDaoImpl();
        List<Equipe> equipes1 = null;
        try {
            equipes1 = daoEquipe1.getAllEquipe();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        final List<Equipe> equipesFinal1 = equipes1;

        String[] nomEquipe1 = new String[equipes1.size()];
        for (int i = 0; i < equipes1.size(); i++) {
            nomEquipe1[i] = equipes1.get(i).getNomEquipeV();
        }

        JComboBox<String> equipeCombo1 = new JComboBox<>(nomEquipe1);*/
        //#####################"""
        StadeDao daoStade = new StadeDaoImpl();
        List<Stade> stades = null;
        try {
            stades = daoStade.getAllStade();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        final List<Stade> stadesFinal = stades;
        String[] nomStade = new String[stades.size()];
        for (int i = 0; i < stades.size(); i++) {
            nomStade[i] = stades.get(i).getNomStade();
        }
        JComboBox<String> stadesCombo = new JComboBox<>(nomStade);
        JButton ajouter = new JButton("Ajouter");
        JButton annuler = new JButton("Annuler");
        panelCenter.add(new JLabel("EquipeDomicile"));
        panelCenter.add(equipeCombo);
        panelCenter.add(new JLabel("EquipeVisiteur"));
        panelCenter.add(equipeCombo1);
        
       /* panelCenter.add(new JLabel("EquipeVisiteur"));
        panelCenter.add(equipeCombo1);*/
        
        panelCenter.add(new JLabel("Stade"));
        panelCenter.add(stadesCombo);
        panelCenter.add(new JLabel("IDMatch"));
        panelCenter.add(IDMatch);
        panelCenter.add(annuler);
        panelCenter.add(ajouter);
        
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idValue = IDMatch.getText();
                if (idValue.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
                    return;
                }

                int selectedIndexEquipe1 = equipeCombo.getSelectedIndex();
                int selectedIndexEquipe = equipeCombo1.getSelectedIndex();
                //int selectedIndexEquipe2 = equipeCombo1.getSelectedIndex();
                int selectedIndexStade = stadesCombo.getSelectedIndex();

                if (selectedIndexEquipe1 == -1 || selectedIndexEquipe == -1 || selectedIndexStade == -1) {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une équipe et un stade.");
                    return;
                }

                Equipe selectedEquipe1 = equipesFinal.get(selectedIndexEquipe1);
                Equipe selectedEquipe2 = equipesFinal1.get(selectedIndexEquipe);
                Stade selectedStade = stadesFinal.get(selectedIndexStade);

                try {
                    int idMatch = Integer.parseInt(idValue);
                    MatchDao daoMatch = new MatchDaoImpl();
                    Match match = new Match(idMatch, selectedEquipe1, selectedEquipe2, selectedStade);
                    daoMatch.addMatch(match);

                    Object[] rowData = { match.getIDMatch(), selectedEquipe1.getNomEquipe(), 
                    		selectedEquipe2.getNomEquipe(),selectedStade.getNomStade() };
                    model.addRow(rowData);

                    JOptionPane.showMessageDialog(null, "Match ajouté avec succès.");
                    dispose();
                } catch (NumberFormatException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du match.");
                }	
            }
        });

        ((AbstractDocument) IDMatch.getDocument()).setDocumentFilter(new DocumentFilter() {
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

//###################
/*public class AjouterMatch extends JDialog {

     JComboBox<Equipe> equipeDomicileCombo;
     JComboBox<Equipe> equipeVisiteurCombo;
     JComboBox<Stade> stadeCombo;
     JTextField idMatch;

     DefaultTableModel matchModel;

    public AjouterMatch(DefaultTableModel matchModel) {
        this.matchModel = matchModel;
        setLocationRelativeTo(null);
        setSize(300, 200);
        initUI();
    }

     void initUI() {
        JPanel panelCenter = new JPanel();
        getContentPane().add(panelCenter, BorderLayout.CENTER);
        panelCenter.setLayout(new GridLayout(5, 2, 6, 3));

        idMatch = new JTextField();
        equipeDomicileCombo = new JComboBox<>();
        equipeVisiteurCombo = new JComboBox<>();
        stadeCombo = new JComboBox<>();

        JButton ajouter = new JButton("Ajouter");
        JButton annuler = new JButton("Annuler");

       

        panelCenter.add(new JLabel("Équipe Domicile:"));
        panelCenter.add(equipeDomicileCombo);

        panelCenter.add(new JLabel("Équipe Visiteur:"));
        panelCenter.add(equipeVisiteurCombo);

        panelCenter.add(new JLabel("Stade:"));
        panelCenter.add(stadeCombo);
        idMatch = new JTextField();
        ((AbstractDocument) idMatch.getDocument()).setDocumentFilter(new DocumentFilter() {
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

        // Add the idMatch component to the panel
        panelCenter.add(new JLabel("ID du Match:"));
        panelCenter.add(idMatch);

        panelCenter.add(annuler);
        panelCenter.add(ajouter);

        EquipesDao daoEquipe = new EquipesDaoImpl();
        StadeDao daoStade = new StadeDaoImpl();

        try {
            List<Equipe> equipes = daoEquipe.getAllEquipe();
            for (Equipe equipe : equipes) {
                equipeDomicileCombo.addItem(equipe);
                equipeVisiteurCombo.addItem(equipe);
            }

            List<Stade> stades = daoStade.getAllStade();
            for (Stade stade : stades) {
                stadeCombo.addItem(stade);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la récupération des données.");
        }

        ajouter.addActionListener(e -> ajouterMatch());
        annuler.addActionListener(e -> {
            // Action when "Annuler" button is clicked (close the dialog)
            dispose();
        });

        setVisible(true);
    }

     void ajouterMatch() {
        try {
            String id = idMatch.getText();
            Equipe equipeDomicile = (Equipe) equipeDomicileCombo.getSelectedItem();
            Equipe equipeVisiteur = (Equipe) equipeVisiteurCombo.getSelectedItem();
            Stade stadeSelectionnee = (Stade) stadeCombo.getSelectedItem();
            int idStade = stadeSelectionnee.getIDStade();

            matchModel.addRow(new Object[]{id, equipeDomicile, equipeVisiteur, stadeSelectionnee});

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Suptech2", "root", "");
                 PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO matchs (IDMatch, IDEquipeD, IDEquipeV, IDStade) VALUES (?, ?, ?, ?)")) {

                preparedStatement.setString(1, id);
                preparedStatement.setInt(2, equipeDomicile.getIDEquipeD());
                preparedStatement.setInt(3, equipeVisiteur.getIDEquipeD());
                preparedStatement.setInt(4, stadeSelectionnee.getIDStade());

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Match ajouté avec succès.");
                dispose();

            } catch (SQLException sqlException) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du match à la base de données.");
                sqlException.printStackTrace();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Une erreur s'est produite lors de l'opération.");
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DefaultTableModel model = new DefaultTableModel();
        new AjouterMatch(model).setVisible(true);
    }
}*/
