package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.JoueurDaoImpl;
import model.Joueur;
import Dao.EquipesDao;
import Dao.EquipesDaoImpl;
import Dao.JoueurDao;
public class FJoueur extends JFrame{
	public FJoueur(String argumen) throws SQLException{
		setSize(600, 500); //Définir les dimensions
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//Ajouter les JPanels
		JPanel panelNorth=new JPanel();
		JPanel panelSouth=new JPanel();
		JPanel panelEast=new JPanel();
		JPanel panelWest=new JPanel();
		JPanel panelCenter=new JPanel();panelCenter.setBackground(Color.CYAN);
		//Définir les dimensions des JPanels
		panelNorth.setPreferredSize(new Dimension(600, 100));panelNorth.setBackground(Color.CYAN);
		panelSouth.setPreferredSize(new Dimension(600, 60));panelSouth.setBackground(Color.cyan);
		panelEast.setPreferredSize(new Dimension(20, 500));panelEast.setBackground(Color.cyan);
		panelWest.setPreferredSize(new Dimension(20, 500));panelWest.setBackground(Color.cyan);
		//**********************************************
		getContentPane().add(panelNorth,BorderLayout.NORTH);
		getContentPane().add(panelSouth,BorderLayout.SOUTH);
		getContentPane().add(panelCenter,BorderLayout.CENTER);
		getContentPane().add(panelEast,BorderLayout.EAST);
		getContentPane().add(panelWest,BorderLayout.WEST);
		
		/*ImageIcon originalIcon = new ImageIcon(getClass().getResource("/UI/Free  Wallpaper, Football, Field Background Images, Soccer Field Condensing Background Photo Background PNG and Vectors.jpeg"));
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(685, 650, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);*/
		
        
		JButton quitterButton = new JButton("Retour");
        panelSouth.add(quitterButton); // Ajout du bouton à panelSouth

        quitterButton.addActionListener(e -> {
            dispose(); // Fermer la fenêtre FJoueur
            Main.main(new String[]{}); // Redémarrer Main
        });
        
		//Créer un objet TableModel
		Object [] entete= new Object[]{"IDJoueur","nomJoueur","poste","Equipe"};
		Object [][] data= new Object[][]{};
		DefaultTableModel joueurModel=new DefaultTableModel(data,entete);
		
		JoueurDao daoJoueur = new JoueurDaoImpl();
		List<Joueur> joueurs=daoJoueur.getAllJoueur();
		joueurs.forEach(joueur->{
			//String equipeInfo = joueur.getEquipe().getNomEquipe() + " (" + joueur.getEquipe().getPays() + ")";
		    joueurModel.addRow(new Object[]{joueur.getIDJoueur(), joueur.getNomJoueur(), joueur.getPoste(),joueur.getEquipe().getNomEquipe()});
		});
		//Créer un composant JTable
				JTable table=new JTable(joueurModel);
				table.setPreferredScrollableViewportSize(new Dimension(500, 160));
				//Ajouter JTable à panelCenter
				panelCenter.add(new JScrollPane(table));
				//Ajouter panelCenter à la fenêtre
				getContentPane().add(panelCenter,BorderLayout.CENTER);
				getContentPane().add(panelNorth,BorderLayout.NORTH);
				getContentPane().add(panelSouth,BorderLayout.SOUTH);
				getContentPane().add(panelEast,BorderLayout.EAST);
				getContentPane().add(panelWest,BorderLayout.WEST);
				//Ajouter les bouton ajouterJoueur et supprimerJoueur
				JButton ajouter=new JButton("Ajouter");
				JButton supprimer=new JButton("Supprimer");
				panelSouth.add(ajouter);
				panelSouth.add(supprimer);
				JLabel inscription=new JLabel("Joueur");
				inscription.setFont(new Font("Courier New",Font.BOLD,40));
				inscription.setForeground(Color.BLUE);
				panelNorth.add(inscription);
				//Ajouter l'action clic sur Ajouter
				ajouter.addActionListener(new ActionListener() {			
					@Override
					public void actionPerformed(ActionEvent e) {
						AjouterJoueur ae= new AjouterJoueur(joueurModel);
					}
				});
				supprimer.addActionListener(new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        int r = JOptionPane.showConfirmDialog(FJoueur.this, "Voulez-vous vraiment supprimer l'équipe ?", "Suppression", JOptionPane.YES_NO_OPTION);
				        if (r == 0) {
				            int selectedRow = table.getSelectedRow();
				            if (selectedRow != -1) {
				                int idToDelete = (int) table.getValueAt(selectedRow, 0); // Récupérer l'IDEquipe de la ligne sélectionnée
				                joueurModel.removeRow(selectedRow); // Supprimer la ligne de la JTable

				                // Supprimer l'équipe de la base de données
				                JoueurDao daoJoueur = new JoueurDaoImpl();
				                try {
									daoJoueur.deleteJoueur(idToDelete);
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