package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.StadeDao;
import Dao.StadeDaoImpl;
import model.Stade;

public class FStade extends JFrame{
	public FStade(String argument) throws SQLException{
		//Initialisation de la fenêtre
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
				panelNorth.setPreferredSize(new Dimension(600, 100));panelNorth.setBackground(Color.cyan);
				panelSouth.setPreferredSize(new Dimension(600, 60));panelSouth.setBackground(Color.cyan);
				panelEast.setPreferredSize(new Dimension(20, 500));panelEast.setBackground(Color.cyan);
				panelWest.setPreferredSize(new Dimension(20, 500));panelWest.setBackground(Color.cyan);
				//**********************************************
				getContentPane().add(panelNorth,BorderLayout.NORTH);
				getContentPane().add(panelSouth,BorderLayout.SOUTH);
				getContentPane().add(panelCenter,BorderLayout.CENTER);
				getContentPane().add(panelEast,BorderLayout.EAST);
				getContentPane().add(panelWest,BorderLayout.WEST);
				// Ajouter le bouton "Quitter"
		        JButton quitterButton = new JButton("Retour");
		        panelSouth.add(quitterButton); // Ajout du bouton à panelSouth

		        quitterButton.addActionListener(e -> {
		            dispose(); // Fermer la fenêtre FStade
		            Main.main(new String[]{}); // Redémarrer Main
		        });
				//Créer un objet TableModel
				Object [] entete= new Object[]{"idStade","nomStade","pays","ville"};
				Object [][] data= new Object[][]{};
				DefaultTableModel stadeModel=new DefaultTableModel(data,entete);
				
				StadeDao daoStade=new StadeDaoImpl();
				List<Stade> stades=daoStade.getAllStade();	
				stades.forEach(stade->{
					stadeModel.addRow(new Object[]{stade.getIDStade(),stade.getNomStade(),stade.getPays(),stade.getVille()});
				});
				//Créer un composant JTable
				JTable table=new JTable(stadeModel);
				table.setPreferredScrollableViewportSize(new Dimension(500, 160));
				//Ajouter JTable à panelCenter
				panelCenter.add(new JScrollPane(table));
				//Ajouter panelCenter à la fenêtre
				getContentPane().add(panelCenter,BorderLayout.CENTER);
				getContentPane().add(panelNorth,BorderLayout.NORTH);
				getContentPane().add(panelSouth,BorderLayout.SOUTH);
				getContentPane().add(panelEast,BorderLayout.EAST);
				getContentPane().add(panelWest,BorderLayout.WEST);
				//Ajouter les bouton ajouterEtudiant et supprimerEtudiant
				JButton ajouter=new JButton("Ajouter");
				JButton supprimer=new JButton("Supprimer");
				panelSouth.add(ajouter);
				panelSouth.add(supprimer);
				JLabel inscription=new JLabel("Stade");
				inscription.setFont(new Font("Monotype Corsiva",Font.BOLD,40));
				inscription.setForeground(Color.BLUE);
				panelNorth.add(inscription);
				//Ajouter l'action clic sur Ajouter
				ajouter.addActionListener(new ActionListener() {			
					@Override
					public void actionPerformed(ActionEvent e) {
						AjouterStade ae= new AjouterStade(stadeModel);
					}
				});
				supprimer.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						 int r = JOptionPane.showConfirmDialog(FStade.this, "Voulez-vous vraiment supprimer stade ?", "Suppression", JOptionPane.YES_NO_OPTION);
					        if (r == 0) {
					            int selectedRow = table.getSelectedRow();
					            if (selectedRow != -1) {
					                int idToDelete = (int) table.getValueAt(selectedRow, 0); // Récupérer l'IDEquipe de la ligne sélectionnée
					                stadeModel.removeRow(selectedRow); // Supprimer la ligne de la JTable

					                // Supprimer l'équipe de la base de données
					                StadeDao daoStade = new StadeDaoImpl();
					                try {
										daoStade.deleteStade(idToDelete);
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
