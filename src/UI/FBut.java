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

import Dao.MatchDao;
import Dao.MatchDaoImpl;
import model.But;
import model.Match;
import Dao.*;

public class FBut extends JFrame{
	 private DefaultTableModel butModel;
	public FBut(String argumen) throws SQLException{
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
		
		initButModel(); // Initialisation du modèle de tableau
		//########################
		JButton quitterButton = new JButton("Retour");
        panelSouth.add(quitterButton); // Ajout du bouton à panelSouth

        quitterButton.addActionListener(e -> {
            dispose(); // Fermer la fenêtre FStade
            Main.main(new String[]{}); // Redémarrer Main
        });
      //Créer un objet TableModel
      		Object [] entete= new Object[]{"IDBut","minute", "IDMatch", "Equipe_Marqué", "nomJoueur"};
      		Object [][] data= new Object[][]{};
      		DefaultTableModel butModel=new DefaultTableModel(data,entete);
      		ButDao daoBut = new ButDaoImpl();                                                                                  
      		List<But> buts = daoBut.getAllBut();
      		buts.forEach(but -> {
      		    String equipeMarquee = but.getMatch().getEquipeD().getNomEquipe() + " vs " + but.getMatch().getEquipeV().getNomEquipe();
      		    butModel.addRow(new Object[]{but.getIDBut(), but.getMinute(), but.getMatch().getIDMatch(), equipeMarquee, but.getJoueur().getNomJoueur()});
      		});
      	//Créer un composant JTable	
			JTable table=new JTable(butModel);
			table.setPreferredScrollableViewportSize(new Dimension(520, 160));
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
			JLabel inscription=new JLabel("But");
			inscription.setFont(new Font("Courier New",Font.BOLD,40));
			inscription.setForeground(Color.BLUE);
			panelNorth.add(inscription);
			
			ajouter.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					AjouterBut ae= new AjouterBut(butModel);
				}
			});
			supprimer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					        int r = JOptionPane.showConfirmDialog(FBut.this, "Voulez-vous vraiment supprimer but ?", "Suppression", JOptionPane.YES_NO_OPTION);
					        if (r == 0) {
					            int selectedRow = table.getSelectedRow();
					            if (selectedRow != -1) {
					                int idToDelete = (int) table.getValueAt(selectedRow, 0); // Récupérer l'IDEquipe de la ligne sélectionnée
					                butModel.removeRow(selectedRow); // Supprimer la ligne de la JTable

					                // Supprimer l'équipe de la base de données
					                ButDao daoBut = new ButDaoImpl();
					                try {
										daoBut.deleteBut(idToDelete);
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} // Appeler la méthode de suppression dans le DAO
					            }
					        }
				}
			});
			setVisible(true); 
	}
	private void initButModel() throws SQLException {
		// TODO Auto-generated method stub
		 Object[] entete = new Object[]{"IDBut", "minute", "IDMatch", "Equipe_Marqué", "nomJoueur"};
	        Object[][] data = new Object[][]{};
	        butModel = new DefaultTableModel(data, entete);

	        ButDao daoBut = new ButDaoImpl();
	        List<But> buts = daoBut.getAllBut();
	        buts.forEach(but -> {
	            butModel.addRow(new Object[]{but.getIDBut(), but.getMinute(),
	                    but.getMatch().getIDMatch(), but.getEquipeD().getNomEquipe(),  but.getEquipeV().getNomEquipe(), but.getJoueur().getNomJoueur()});
	        });
	    }

	    public DefaultTableModel getButModel() {
	        return butModel;
	    
	}
}
