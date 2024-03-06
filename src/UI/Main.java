/*package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // Création de la fenêtre principale
        JFrame frame = new JFrame("JMenuBar");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Centrer la fenêtre sur l'écran
        frame.setLocationRelativeTo(null);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu optionsMenu = new JMenu("Options");
     // Sous-menu "Ajouter"
        JMenu ajouterMenu = new JMenu("Ajouter");
        
        // Création des panneaux pour organiser l'interface
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // panneau du titre
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(400, 100));
        topPanel.setBackground(Color.CYAN);

        JLabel inscription = new JLabel("Menu Principal");// Label pour le titre	
        inscription.setFont(new Font("Monotype Corsiva", Font.BOLD, 40));
        inscription.setForeground(Color.BLUE);
        topPanel.add(inscription);

        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        // les boutons avec leurs actions associées
        JMenuItem ajouterStadeItem = new JMenuItem(" Stade");
        ajouterStadeItem.addActionListener(e -> {
            frame.dispose();
            try {
                new FStade(null);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        ajouterMenu.add(ajouterStadeItem);


        JMenuItem ajouterMatchItem = new JMenuItem(" Match");
        ajouterMatchItem.addActionListener(e -> {
            frame.dispose();
            try {
                new FMatch(null);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        ajouterMenu.add(ajouterMatchItem);

        
        JMenuItem ajouterEquipeItem = new JMenuItem(" Equipe");
        ajouterEquipeItem.addActionListener(e -> {
            frame.dispose();
            try {
                new FEquipe(null);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        ajouterMenu.add(ajouterEquipeItem);
        
        JMenuItem ajouterJoueurItem = new JMenuItem(" Joueur");
        ajouterJoueurItem.addActionListener(e -> {
            frame.dispose();
            try {
                new FJoueur(null);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        JMenuItem ajouterButItem = new JMenuItem(" But");
        ajouterButItem.addActionListener(e -> {
            frame.dispose();
            try {
                new FBut(null);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        ajouterMenu.add(ajouterButItem);
        // Ajout du sous-menu "Ajouter" au menu "Options"
        //optionsMenu.add(ajouterMenu);
        
        //JButton quitterButton = new JButton("Quitter");
        //quitterButton.addActionListener(e -> System.exit(0));
        JMenuItem quitterItem = new JMenuItem("Quitter");
        // ActionListener pour afficher une boîte de dialogue de confirmation avant de quitter l'application
        quitterItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(frame,
                        "Êtes-vous sûr de vouloir quitter ?", "Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0); // Quitter l'application
                }
            }
        });
        ajouterMenu.add(quitterItem);

        menuBar.add(ajouterMenu);
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
        // Modifier le design du bouton "Quitter"
        quitterItem.setBackground(Color.yellow);
        quitterItem.setFont(new Font("Arial", Font.BOLD, 16));
        quitterItem.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        // Ajout des boutons au panneau des boutons
        buttonPanel.add(ajouterEquipeItem);
        buttonPanel.add(ajouterJoueurItem);
        buttonPanel.add(ajouterStadeItem);
        buttonPanel.add(ajouterMatchItem);
        buttonPanel.add(ajouterButItem);
        buttonPanel.add(quitterItem);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        frame.add(mainPanel);// Ajout du panneau principal à la fenêtre
        frame.setVisible(true);
    }
}*/
package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;


public class Main {
    public static void main(String[] args) {
        // Création de la fenêtre principale
        JFrame frame = new JFrame("Principale");
        frame.setSize(550, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Centrer la fenêtre sur l'écran
        frame.setLocationRelativeTo(null);

        // Barre de menu principale
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Menu "Options"
        JMenu optionsMenu = new JMenu("Options :");
        menuBar.add(optionsMenu);

        // Sous-menu "Ajouter"
        JMenu ajouterMenu = new JMenu("Ajouter");
        menuBar.add(ajouterMenu);

        // Boutons du panneau de boutons
        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));
    /* // Chargement de l'image depuis le fichier
        ImageIcon logo = new ImageIcon("C:\\Users\\boubk\\eclipse-workspace\\ANSER\\src\\UI\\FOOT1.png"); // Mettez le chemin correct ici

        // Création d'un JLabel pour afficher l'image
        JLabel imageLabel = new JLabel(logo);*/
        
        // Chargement de l'image du fond depuis le fichier
        ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\boubk\\eclipse-workspace\\ANSER\\src\\UI\\foot4.png.jpg"); // Mettez le chemin correct ici

        // Création d'un JLabel pour contenir l'image du fond
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BorderLayout());
      
        JMenuItem ajouterEquipeItem = new JMenuItem("Equipe");
        ajouterEquipeItem.addActionListener(e -> {
            frame.dispose();
            try {
                new FEquipe(null);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        ajouterMenu.add(ajouterEquipeItem);
        //buttonPanel.add(new JButton("Equipe")); // Ajout du bouton dans le panneau

        JMenuItem ajouterJoueurItem = new JMenuItem("Joueur");
        ajouterJoueurItem.addActionListener(e -> {
            frame.dispose();
            try {
                new FJoueur(null);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        ajouterMenu.add(ajouterJoueurItem);
        //buttonPanel.add(new JButton("Joueur")); // Ajout du bouton dans le panneau
        
     // Ajout des boutons et leurs actions associées
        JMenuItem ajouterStadeItem = new JMenuItem("Stade");
        ajouterStadeItem.addActionListener(e -> {
            frame.dispose();
            try {
                new FStade(null);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        ajouterMenu.add(ajouterStadeItem);
        //buttonPanel.add(new JButton("Stade")); // Ajout du bouton dans le panneau
        
        JMenuItem ajouterMatchItem = new JMenuItem("Match");
        ajouterMatchItem.addActionListener(e -> {
            frame.dispose();
            try {
                new FMatch(null);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        ajouterMenu.add(ajouterMatchItem);
        //buttonPanel.add(new JButton("Match")); // Ajout du bouton dans le panneau
        
        JMenuItem ajouterButItem = new JMenuItem("But");
        ajouterButItem.addActionListener(e -> {
            frame.dispose();
            try {
                new FBut(null);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        ajouterMenu.add(ajouterButItem);
        //buttonPanel.add(new JButton("But")); // Ajout du bouton dans le panneau

        /*JMenuItem quitterItem = new JMenuItem("Quitter");
        quitterItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(frame,
                        "Êtes-vous sûr de vouloir quitter ?", "Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0); // Quitter l'application
                }
            }
        });*/
        //ajouterMenu.add(quitterItem);
    

     // Création du panneau principal pour contenir tous les éléments
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // panneau du titre
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(400, 100));
        topPanel.setBackground(Color.GRAY);

        JLabel inscription = new JLabel("Menu Principal");// Label pour le titre	
        inscription.setFont(new Font("Monotype Corsiva", Font.BOLD, 40));
        inscription.setForeground(Color.BLACK);
        topPanel.add(inscription);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
     // Ajout du JLabel contenant l'image au panneau principal
        //mainPanel.add(imageLabel, BorderLayout.WEST);
       mainPanel.add(backgroundLabel, BorderLayout.CENTER); // Positionnez l'arrière-plan au centre
        
        
        frame.add(mainPanel);// Ajout du panneau principal à la fenêtre
        frame.setVisible(true);
    }
}
