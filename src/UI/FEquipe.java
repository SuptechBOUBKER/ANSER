package UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.EquipesDao;
import Dao.EquipesDaoImpl;
import model.Equipe;
public class FEquipe extends JFrame{
	public FEquipe(String argument) throws SQLException {
		ImageIcon backgroundIcon;
		  JLabel backgroundLabel;
		//Initialisation de la fenêtre
		setSize(600, 500); //Définir les dimensions
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//Ajouter les JPanels
		JPanel panelNorth=new JPanel();
		JPanel panelSouth=new JPanel();
		JPanel panelEast=new JPanel();
		JPanel panelWest=new JPanel();
		JPanel panelCenter=new JPanel();panelCenter.setBackground(Color.LIGHT_GRAY);
		//Définir les dimensions des JPanels
		panelNorth.setPreferredSize(new Dimension(600, 100));panelNorth.setBackground(Color.LIGHT_GRAY);
		panelSouth.setPreferredSize(new Dimension(600, 60));panelSouth.setBackground(Color.LIGHT_GRAY);
		panelEast.setPreferredSize(new Dimension(20, 500));panelEast.setBackground(Color.LIGHT_GRAY);
		panelWest.setPreferredSize(new Dimension(20, 500));panelWest.setBackground(Color.LIGHT_GRAY);
		//**********************************************
		getContentPane().add(panelNorth,BorderLayout.NORTH);
		getContentPane().add(panelSouth,BorderLayout.SOUTH);
		getContentPane().add(panelCenter,BorderLayout.CENTER);
		getContentPane().add(panelEast,BorderLayout.EAST);
		getContentPane().add(panelWest,BorderLayout.WEST);
		 // Créer un JPanel avec une image de fond
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\boubk\\eclipse-workspace\\ANSER\\src\\UI\\foot4.png.jpg");
                Image image = backgroundIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        //setContentPane(backgroundPanel);
        /*// Ajouter l'image de fond au JLayeredPane
		 // Créer un JLayeredPane
       JLayeredPane layeredPane = new JLayeredPane();

       ImageIcon backgroundIcon1 = new ImageIcon("C:\\Users\\boubk\\eclipse-workspace\\ANSER\\src\\UI\\foot4.png.jpg");
       Image image = backgroundIcon1.getImage();
       JLabel backgroundLabel1 = new JLabel(backgroundIcon1);
       backgroundLabel1.setBounds(0, 0, image.getWidth(null), image.getHeight(null));
       layeredPane.add(backgroundLabel1, JLayeredPane.DEFAULT_LAYER);*/


		//Créer un objet TableModel
		Object [] entete= new Object[]{"IDEquipe","nomEquipe","pays"};
		Object [][] data= new Object[][]{};
		DefaultTableModel equipeModel=new DefaultTableModel(data,entete);
		
		EquipesDao daoEtudiant=new EquipesDaoImpl();
		List<Equipe> equipes=daoEtudiant.getAllEquipe();	
		equipes.forEach(equipe->{
			equipeModel.addRow(new Object[]{equipe.getIDEquipeD(),equipe.getNomEquipe(),equipe.getPays(),
					});
		});
		//Créer un composant JTable
		JTable table=new JTable(equipeModel);
		table.setPreferredScrollableViewportSize(new Dimension(500, 160));
		//Ajouter JTable à panelCenter
		panelCenter.add(new JScrollPane(table));
		//Ajouter panelCenter à la fenêtre
		getContentPane().add(panelCenter,BorderLayout.CENTER);
		getContentPane().add(panelNorth,BorderLayout.NORTH);
		getContentPane().add(panelSouth,BorderLayout.SOUTH);
		getContentPane().add(panelEast,BorderLayout.EAST);
		getContentPane().add(panelWest,BorderLayout.WEST);
		JButton quitterButton = new JButton("Retour");
        panelSouth.add(quitterButton); // Ajout du bouton à panelSouth

        quitterButton.addActionListener(e -> {
            dispose(); // Fermer la fenêtre FStade
            Main.main(new String[]{}); // Redémarrer Main
        });
		//Ajouter les bouton ajouterEtudiant et supprimerEtudiant
		JButton ajouter=new JButton("Ajouter");
		JButton supprimer=new JButton("Supprimer");
		panelSouth.add(ajouter);
		panelSouth.add(supprimer);
		JLabel inscription=new JLabel("Equipe");
		inscription.setFont(new Font("Monotype Corsiva",Font.BOLD,40));
		inscription.setForeground(Color.BLUE);
		panelNorth.add(inscription);
		//Ajouter l'action clic sur Ajouter
		ajouter.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				AjouterEquipes ae= new AjouterEquipes(equipeModel);
			}
		});
		//##############################################################
		/*supprimer.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				int r= JOptionPane.showConfirmDialog(FEquipe.this,"Voulez vous vraiment supprimer l'étudiants?","Suppression",JOptionPane.YES_NO_OPTION);
				System.out.println(r);
				if (r==0) {
				int selectedRow=table.getSelectedRow();
				if (selectedRow!=-1) {
					equipeModel.removeRow(selectedRow);
				}
				}
			}
		});*/
        //####################
		/*supprimer.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int r = JOptionPane.showConfirmDialog(FEquipe.this, "Voulez-vous vraiment supprimer l'équipe ?", "Suppression", JOptionPane.YES_NO_OPTION);
		        if (r == 0) {
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow != -1) {
		                int idToDelete = (int) table.getValueAt(selectedRow, 0); // Récupérer l'IDEquipe de la ligne sélectionnée
		                equipeModel.removeRow(selectedRow); // Supprimer la ligne de la JTable

		                // Supprimer l'équipe de la base de données
		                EquipesDao daoEquipe = new EquipesDaoImpl();
		                daoEquipe.deleteEquipe(idToDelete); // Appeler la méthode de suppression dans le DAO
		            }
		        }
		    }
		});*/
		supprimer.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int r = JOptionPane.showConfirmDialog(FEquipe.this, "Voulez-vous vraiment supprimer l'équipe ?", "Suppression", JOptionPane.YES_NO_OPTION);
		        if (r == 0) {
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow != -1) {
		                int idToDelete = (int) table.getValueAt(selectedRow, 0); // Récupérer l'IDEquipe de la ligne sélectionnée
		                equipeModel.removeRow(selectedRow); // Supprimer la ligne de la JTable

		                // Supprimer l'équipe de la base de données
		                EquipesDao daoEquipe = new EquipesDaoImpl();
		                try {
							daoEquipe.deleteEquipe(idToDelete);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} // Appeler la méthode de suppression dans le DAO
		            }
		        }
		    }
		});
		setVisible(true); //Afficher la fenêtre	
	}
}

/*package UI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import Dao.EquipesDao;
import Dao.EquipesDaoImpl;
import model.Equipe;

public class FEquipe extends JFrame {
    private DefaultTableModel equipeModel;

    public FEquipe(String argument) throws SQLException {
        // Initialisation de la fenêtre
        setSize(600, 500); // Définir les dimensions
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Créer un JPanel avec une image de fond
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\boubk\\eclipse-workspace\\ANSER\\src\\UI\\foot4.png.jpg");
                Image image = backgroundIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };

        setContentPane(backgroundPanel);

        // Ajouter les JPanels
        JPanel panelNorth = new JPanel();
        JPanel panelSouth = new JPanel();
        JPanel panelCenter = new JPanel(new BorderLayout()); // Use BorderLayout for the center panel

        // Définir les dimensions des JPanels
        panelNorth.setPreferredSize(new Dimension(600, 100));
        panelSouth.setPreferredSize(new Dimension(600, 60));

        getContentPane().add(panelNorth, BorderLayout.NORTH);
        getContentPane().add(panelSouth, BorderLayout.SOUTH);
        getContentPane().add(panelCenter, BorderLayout.CENTER);

        // Créer un objet TableModel
        Object[] entete = new Object[]{"IDEquipe", "nomEquipe", "pays"};
        Object[][] data = new Object[][]{};
        equipeModel = new DefaultTableModel(data, entete);

        // Charger les données depuis la base de données
        EquipesDao daoEquipe = new EquipesDaoImpl();
        List<Equipe> equipes = daoEquipe.getAllEquipe();
        equipes.forEach(equipe -> {
            equipeModel.addRow(new Object[]{equipe.getIDEquipeD(), equipe.getNomEquipe(), equipe.getPays()});
        });

        // Créer un composant JTable
        JTable table = new JTable(equipeModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 160));

        // Ajouter JTable à panelCenter
        panelCenter.add(new JScrollPane(table));

        // Ajouter panelCenter à la fenêtre
        getContentPane().add(panelCenter, BorderLayout.CENTER);

        JButton ajouter = new JButton("Ajouter");
        JButton supprimer = new JButton("Supprimer");
        panelSouth.add(ajouter);
        panelSouth.add(supprimer);

        JButton retourButton = new JButton("Retour");
        panelSouth.add(retourButton); // Add the "Retour" button

        JLabel inscription = new JLabel("Equipe");
        inscription.setFont(new Font("Monotype Corsiva", Font.BOLD, 40));
        inscription.setForeground(Color.BLUE);
        panelNorth.add(inscription);

        // Ajouter l'action clic sur Ajouter
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AjouterEquipes ae = new AjouterEquipes(equipeModel);
            }
        });

        // Ajouter l'action clic sur Supprimer
        supprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = JOptionPane.showConfirmDialog(FEquipe.this, "Voulez-vous vraiment supprimer l'équipe ?", "Suppression", JOptionPane.YES_NO_OPTION);
                if (r == 0) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int idToDelete = (int) table.getValueAt(selectedRow, 0);
                        equipeModel.removeRow(selectedRow);

                        try {
                            daoEquipe.deleteEquipe(idToDelete);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });

        // Ajouter l'action clic sur Retour
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Main.main(new String[]{});
            }
        });

        setVisible(true);
    }
}*/
