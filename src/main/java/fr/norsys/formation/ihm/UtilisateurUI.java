/**
 * 
 */
package fr.norsys.formation.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fr.norsys.formation.dto.DtoAdresse;
import fr.norsys.formation.dto.DtoUtilisateur;
import fr.norsys.formation.service.UtilisateurService;

/**
 * @author technomaker09
 * 
 */
public class UtilisateurUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel container;
	private JPanel paneNord;
	private JPanel paneSud;
	private JPanel paneCentre;
	private JPanel paneCentreAjout;
	private JPanel paneEst;
	private JButton ajouterUtilisateur;
	private JButton modifierUtilisateur;
	private JButton afficherUtilisateur;
	private JButton supprimerUtilisateur;
	private JScrollPane utilisateurTableSc;
	private JScrollPane adresseTableSc;
	private JLabel listeUtilisateur;
	private JTable utilisateurTable;
	private JTable adresseTable;

	private static JOptionPane optionPane;

	private UtilisateurService service;
	private JTextField idTF;
	private JTextField nomTF;
	private JTextField prenomTF;
	private JLabel idL;
	private JLabel nomL;
	private JLabel prenomL;
	private JButton validerB;
	private JButton accueilB;
	private JButton ajouterAdresse;
	private JButton supprimerAdresse;


	private static boolean ajout = true;
	private static List<String> adresseSupp = null;

	public UtilisateurUI() {
		service = new UtilisateurService();
		adresseSupp = new ArrayList<String>();
		ImageIcon addUser = new ImageIcon(IUtilisateurConst.RSRC_AJOUTER_UTILISATERUR);
		ImageIcon deleteUser = new ImageIcon(IUtilisateurConst.RSRC_SUPPRIMER_UTILISATERUR);
		ImageIcon modifyUser = new ImageIcon(IUtilisateurConst.RSRC_MODIFIER_UTILISATERUR);
		ImageIcon diplayUser = new ImageIcon(IUtilisateurConst.RSRC_AFFICHER_UTILISATERUR);
		
		container = new JPanel();
		paneCentre = new JPanel();
		paneNord = new JPanel();
		paneSud = new JPanel();
		paneEst = new JPanel();
		paneCentreAjout = new JPanel();
		optionPane = new JOptionPane();

		idL = new JLabel(IUtilisateurConst.ID_UTILISATEUR);
		nomL = new JLabel(IUtilisateurConst.NOM_UTILISATEUR);
		prenomL = new JLabel(IUtilisateurConst.PRENOM_UTILISATEUR);
		validerB = new JButton(IUtilisateurConst.VALIDER_UTILISATEUR);
		accueilB = new JButton(IUtilisateurConst.ACCUEIL_UTILISATEUR);
		ajouterAdresse = new JButton(IUtilisateurConst.AJOUTER_ADRESSE);
		supprimerAdresse = new JButton(IUtilisateurConst.SUPPRIMER_ADRESSE);
		idTF = new JTextField();
		nomTF = new JTextField();
		prenomTF = new JTextField();

		ajouterUtilisateur = new JButton();
		ajouterUtilisateur.setIcon(addUser);
		ajouterUtilisateur.setToolTipText(IUtilisateurConst.AJOUTER_UTILISATEUR);
		modifierUtilisateur = new JButton();
		modifierUtilisateur.setToolTipText(IUtilisateurConst.MODIFIER_UTILISATEUR);
		modifierUtilisateur.setIcon(modifyUser);
		afficherUtilisateur = new JButton();
		afficherUtilisateur.setToolTipText(IUtilisateurConst.AFFICHER_UTILISATEUR);
		afficherUtilisateur.setIcon(diplayUser);
		
		supprimerUtilisateur = new JButton();
		supprimerUtilisateur.setToolTipText(IUtilisateurConst.SUPPRIMER_UTILISATEUR);
		supprimerUtilisateur.setIcon(deleteUser);

		listeUtilisateur = new JLabel(IUtilisateurConst.LISTE_UTILISATEUR);
		listeUtilisateur.setForeground(Color.WHITE);

		getJTableUtilisateur();

		ajouterUtilisateur.setPreferredSize(new Dimension(155, 80));
		ajouterUtilisateur.setBounds(10, 10, 60, 100);
		ajouterUtilisateur
				.setActionCommand(IUtilisateurConst.AJOUTER_UTILISATEUR);
		ajouterUtilisateur.addActionListener(this);

		modifierUtilisateur.setPreferredSize(new Dimension(155, 80));
		modifierUtilisateur.setBounds(10, 100, 60, 100);
		modifierUtilisateur
				.setActionCommand(IUtilisateurConst.MODIFIER_UTILISATEUR);
		modifierUtilisateur.addActionListener(this);

		supprimerUtilisateur.setPreferredSize(new Dimension(155, 80));
		supprimerUtilisateur.setBounds(10, 165, 60, 100);
		supprimerUtilisateur
				.setActionCommand(IUtilisateurConst.SUPPRIMER_UTILISATEUR);
		supprimerUtilisateur.addActionListener(this);

		afficherUtilisateur.setPreferredSize(new Dimension(155, 80));
		afficherUtilisateur.setBounds(10, 250, 60, 100);
		afficherUtilisateur
				.setActionCommand(IUtilisateurConst.AFFICHER_UTILISATEUR);
		afficherUtilisateur.addActionListener(this);

		paneNord.setPreferredSize(new Dimension(500, 50));
		paneNord.setBackground(Color.BLACK);

		paneCentre.setPreferredSize(new Dimension(600, 350));
		paneCentre.setBackground(Color.GRAY);
		paneCentreAjout.setPreferredSize(new Dimension(600, 350));
		paneCentreAjout.setBackground(Color.GRAY);

		paneSud.setPreferredSize(new Dimension(600, 55));
		paneSud.setBackground(Color.BLACK);

		paneEst.setPreferredSize(new Dimension(160, 50));
		paneEst.setBackground(Color.WHITE);

		paneEst.add(ajouterUtilisateur);
		paneEst.add(modifierUtilisateur);
		paneEst.add(afficherUtilisateur);
		paneEst.add(supprimerUtilisateur);

		setTitle(IUtilisateurConst.TITRE_UTILISATEUR);
		setSize(new Dimension(600, 480));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		container.setBackground(Color.GRAY);
		container.setLayout(new BorderLayout());
		container.add(paneCentre, BorderLayout.CENTER);
		container.add(paneSud, BorderLayout.SOUTH);
		container.add(paneNord, BorderLayout.NORTH);
		container.add(paneEst, BorderLayout.EAST);

		paneCentre.setLayout(null);
		paneCentre.add(listeUtilisateur);
		paneCentre.add(utilisateurTableSc);
		listeUtilisateur.setBounds(150, 15, 200, 20);
		utilisateurTableSc.setBounds(15, 40, 400, 300);

		setContentPane(container);

		idL.setBounds(35, 10, 100, 25);
		nomL.setBounds(35, 40, 100, 25);
		prenomL.setBounds(35, 70, 100, 25);

		idTF.setBounds(135, 10, 100, 20);
		idTF.setEditable(false);
		nomTF.setBounds(135, 40, 100, 20);
		prenomTF.setBounds(135, 70, 100, 20);

		validerB.setBounds(5, 310, 200, 25);
		accueilB.setBounds(220, 310, 200, 25);
		ajouterAdresse.setBounds(250, 65, 60, 25);
		supprimerAdresse.setBounds(330, 65, 60, 25);
		validerB.setActionCommand(IUtilisateurConst.VALIDER_UTILISATEUR);
		accueilB.setActionCommand(IUtilisateurConst.ACCUEIL_UTILISATEUR);
		ajouterAdresse.setActionCommand(IUtilisateurConst.AJOUTER_ADRESSE);
		supprimerAdresse.setActionCommand(IUtilisateurConst.SUPPRIMER_ADRESSE);
		validerB.addActionListener(this);
		accueilB.addActionListener(this);
		supprimerAdresse.addActionListener(this);
		ajouterAdresse.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
//------------traitement pour l'ajout d'un utilisateur
		if (IUtilisateurConst.AJOUTER_UTILISATEUR == actionCommand) {
			ajouterUtilisateur();
			updatePane2();
			
//------------traitement pour l'affichage d'un utilisateur
		} else if (IUtilisateurConst.AFFICHER_UTILISATEUR == actionCommand) {
			if (-1 != utilisateurTable.getSelectedRow()) {
				afficherUtilisateur();
				updatePane2();
			} else {
				getMessageDialog(JOptionPane.WARNING_MESSAGE,
						IUtilisateurConst.MESSAGE_AVERTISSEMENT,
						IUtilisateurConst.UTILISATEUR_SELECTION);
			}
			
//------------traitement pour la modification d'un utilisateur
		} else if (IUtilisateurConst.MODIFIER_UTILISATEUR == actionCommand) {
			if (-1 != utilisateurTable.getSelectedRow()) {
				modifierUtilisateur();
				updatePane2();
			} else {
				getMessageDialog(JOptionPane.WARNING_MESSAGE,
						IUtilisateurConst.MESSAGE_AVERTISSEMENT,
						IUtilisateurConst.UTILISATEUR_SELECTION);
			}
			
//------------traitement pour la suppression d'un utilisateur
		} else if (IUtilisateurConst.SUPPRIMER_UTILISATEUR == actionCommand) {
			if (-1 != utilisateurTable.getSelectedRow()) {
				supprimerUtilisateur();
			} else {
				getMessageDialog(JOptionPane.WARNING_MESSAGE,
						IUtilisateurConst.MESSAGE_AVERTISSEMENT,
						IUtilisateurConst.UTILISATEUR_SELECTION);
			}
			updatePane1();
			
//------------traitement pour validation de l'ajout ou de modification d'un utilisateur
		} else if (IUtilisateurConst.VALIDER_UTILISATEUR == actionCommand) {
			DtoUtilisateur u = new DtoUtilisateur();
			u.setIdUtilisateur(Integer.valueOf(idTF.getText()));
			u.setNom(nomTF.getText());
			u.setPrenom(prenomTF.getText());
				if (ajout) {
					ajouterUtilisateurTraitement(u);
				} else {
					modifierUtilisateurTraitement(u);
				}
			adresseTableSc.setVisible(false);
			updatePane1();
			
//------------traitement pour la suppression temporaire des adresses d'un utilisateur
			
		} else if (IUtilisateurConst.SUPPRIMER_ADRESSE == actionCommand) {
			if(-1 != adresseTable.getSelectedRow()){
				adresseSupp.add(((DefaultTableModel) adresseTable.getModel()).getValueAt(adresseTable.getSelectedRow(), 0).toString());
				((DefaultTableModel) adresseTable.getModel()).removeRow(adresseTable.getSelectedRow());
			}else if(0 != adresseTable.getRowCount()){
				adresseSupp.add(((DefaultTableModel) adresseTable.getModel()).getValueAt(adresseTable.getRowCount()-1, 0).toString());
				((DefaultTableModel) adresseTable.getModel()).removeRow(adresseTable.getRowCount()-1);
			}
			
//------------traitement pour l'ajout temporaire des adresses d'un utilisateur
		} else if (IUtilisateurConst.AJOUTER_ADRESSE == actionCommand) {
			((DefaultTableModel) adresseTable.getModel())
					.addRow(new Object[] { genCode(),"", "", "", "" });
		} else if (IUtilisateurConst.ACCUEIL_UTILISATEUR == actionCommand) {
			adresseTableSc.setVisible(false);
			updatePane1();
		}
	}
	
	/**
	 * 
	 * Méthode pour récuperer les champs pour ajouter un utilisateur
	 */
	private void ajouterUtilisateur(){
		ajout = true;
		idTF.setText(genCode());
		getJTableAdresse(Integer.valueOf(idTF.getText()));
		nomTF.setEditable(true);
		prenomTF.setEditable(true);
		validerB.setVisible(true);
		supprimerAdresse.setVisible(true);
		ajouterAdresse.setVisible(true);
		nomTF.setText("");
		prenomTF.setText("");
	}
	/**
	 * 
	 * Méthode pour récuperer les données à afficher d'un utilisateur dans les champs
	 */
	private void afficherUtilisateur(){
		idTF.setText(utilisateurTable.getValueAt(
				utilisateurTable.getSelectedRow(), 0).toString());
		nomTF.setText(utilisateurTable.getValueAt(
				utilisateurTable.getSelectedRow(), 1).toString());
		prenomTF.setText(utilisateurTable.getValueAt(
				utilisateurTable.getSelectedRow(), 2).toString());
		getJTableAdresse(Integer.valueOf(idTF.getText()));
		nomTF.setEditable(false);
		prenomTF.setEditable(false);
		validerB.setVisible(false);
		ajouterAdresse.setVisible(false);
		supprimerAdresse.setVisible(false);
	}
	/**
	 * Méthode pour récuperer les données à modifer d'un utilisateur dans les champs
	 */
	private void modifierUtilisateur(){
		ajout = false;
		idTF.setText(utilisateurTable.getValueAt(
				utilisateurTable.getSelectedRow(), 0).toString());
		nomTF.setText(utilisateurTable.getValueAt(
				utilisateurTable.getSelectedRow(), 1).toString());
		prenomTF.setText(utilisateurTable.getValueAt(
				utilisateurTable.getSelectedRow(), 2).toString());
		getJTableAdresse(Integer.valueOf(idTF.getText()));
		nomTF.setEditable(true);
		prenomTF.setEditable(true);
		validerB.setVisible(true);
		supprimerAdresse.setVisible(true);
		ajouterAdresse.setVisible(true);
	}
	
	/**
	 * 
	 * Méthode pour supprimer l'utilisateur sélectionné
	 */
	private void supprimerUtilisateur(){
		try {
			service.supprimerAdresseByUtilisateur((Integer) utilisateurTable
					.getValueAt(
							utilisateurTable.getSelectedRow(),
							0));
			if (1 == service
					.supprimerUtilisateur((Integer) utilisateurTable
							.getValueAt(
									utilisateurTable.getSelectedRow(),
									0))) {
				getMessageDialog(JOptionPane.INFORMATION_MESSAGE,
						IUtilisateurConst.MESSAGE_INFORMATION,
						IUtilisateurConst.UTILISATEUR_SUPPRIME);
			} else {
				getMessageDialog(JOptionPane.INFORMATION_MESSAGE,
						IUtilisateurConst.MESSAGE_INFORMATION,
						IUtilisateurConst.UTILISATEUR_NON_SUPPRIME);
			}
		} catch (SQLException e1) {
			getMessageDialog(JOptionPane.WARNING_MESSAGE,
					IUtilisateurConst.MESSAGE_AVERTISSEMENT,
					IUtilisateurConst.UTILISATEUR_NON_SUPPRIME);
		}
	}
	/**
	 * 
	 * @param u
	 * Méthode pour l'ajout d'un utilisateur donné u
	 */
	private void ajouterUtilisateurTraitement(DtoUtilisateur u){
		try {
			if (1 == service.ajouterUtilisateur(u)) {
				getMessageDialog(JOptionPane.INFORMATION_MESSAGE,
						IUtilisateurConst.MESSAGE_INFORMATION,
						IUtilisateurConst.UTILISATEUR_AJOUTE);
				if(0 != ((DefaultTableModel) adresseTable.getModel()).getRowCount()){
					for(int i=0;i<((DefaultTableModel) adresseTable.getModel()).getRowCount();i++){
						DtoAdresse a = new DtoAdresse();
						a.setIdAdresse(Integer.valueOf((((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 0)).toString()));
						if(null != (((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 1))
								&& !"".equals(((((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 1)).toString()))){
							a.setPays(((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 1).toString());
						}
						if(null != (((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 2))
							&&	!"".equals(((((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 2)).toString()))){
							a.setVille(((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 2).toString());
						}
						if(null != (((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 3))
							&&	!"".equals(((((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 3)).toString()))){
							a.setCodePostal(Integer.valueOf(((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 3).toString()));
						}
						if(null != (((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 4))
								&& !"".equals(((((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 4)).toString()))){
							a.setRue(((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 4).toString());
						}
						if(	null != (((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 5))
								&& !"".equals(((((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 5)).toString()))){
							a.setNumero(Integer.valueOf(((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 5).toString()));
						}
						service.ajouterAdresse(a, Integer.valueOf(idTF.getText()));
					}
				}

			} else {
				getMessageDialog(JOptionPane.INFORMATION_MESSAGE,
						IUtilisateurConst.MESSAGE_INFORMATION,
						IUtilisateurConst.UTILISATEUR_NON_AJOUTE);
			}
		}catch (SQLException e) {
			getMessageDialog(JOptionPane.WARNING_MESSAGE,
					IUtilisateurConst.MESSAGE_AVERTISSEMENT,
					IUtilisateurConst.SQL_ERREUR);
		}
	}
	/**
	 * 
	 * @param u
	 * 
	 * Méthode pour modifier un utilisateur donné u
	 */
	private void modifierUtilisateurTraitement(DtoUtilisateur u){
		try {
			if (1 == service.modifierUtilisateur(u)) {
				getMessageDialog(JOptionPane.INFORMATION_MESSAGE,
						IUtilisateurConst.MESSAGE_INFORMATION,
						IUtilisateurConst.UTILISATEUR_MODIFIE);
				if(0 != ((DefaultTableModel) adresseTable.getModel()).getRowCount()){
					for(int i=0;i<((DefaultTableModel) adresseTable.getModel()).getRowCount();i++){
						DtoAdresse a = new DtoAdresse();
						a.setIdAdresse(Integer.valueOf((((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 0)).toString()));
						if(null != (((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 1))
								&& !"".equals(((((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 1)).toString()))){
							a.setPays(((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 1).toString());
						}
						if(null != (((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 2))
							&&	!"".equals(((((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 2)).toString()))){
							a.setVille(((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 2).toString());
						}
						if(null != (((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 3))
							&&	!"".equals(((((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 3)).toString()))){
							a.setCodePostal(Integer.valueOf(((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 3).toString()));
						}
						if(null != (((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 4))
								&& !"".equals(((((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 4)).toString()))){
							a.setRue(((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 4).toString());
						}
						if(	null != (((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 5))
								&& !"".equals(((((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 5)).toString()))){
							a.setNumero(Integer.valueOf(((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 5).toString()));
						}
						if(0 != ((DtoAdresse)service.rechercherAdresse(Integer.valueOf((((DefaultTableModel) adresseTable.getModel()).getValueAt(i, 0)).toString()))).getIdAdresse()){
							service.modifierAdresse(a);
						}else{
							service.ajouterAdresse(a,  Integer.valueOf(idTF.getText()));
						}
					}	
				}
				for(String s : adresseSupp){
					service.supprimerAdresse(Integer.valueOf(s));
				}
			} else {
				getMessageDialog(JOptionPane.INFORMATION_MESSAGE,
						IUtilisateurConst.MESSAGE_INFORMATION,
						IUtilisateurConst.UTILISATEUR_NON_MODIFIE);
			}
		}catch (SQLException e) {
			getMessageDialog(JOptionPane.WARNING_MESSAGE,
					IUtilisateurConst.MESSAGE_AVERTISSEMENT,
					IUtilisateurConst.SQL_ERREUR);
		}
	}
	/**
	 * 
	 * Méthode pour mettre à jour la liste des utilisateurs
	 */
	private void getJTableUtilisateur() {
		List<DtoUtilisateur> list = null;
		if (null == utilisateurTable) {
			utilisateurTable = new JTable();
		}
		if (null == utilisateurTableSc) {
			utilisateurTableSc = new JScrollPane(utilisateurTable);
		}
		Font police = new Font("Arial", Font.PLAIN, 13);
		utilisateurTable.setBackground(Color.white);
		DefaultTableModel tableM = (DefaultTableModel) utilisateurTable.getModel();
		utilisateurTable.setFont(police);
		utilisateurTable.setModel(tableM);
		tableM.setDataVector(null, IUtilisateurConst.TITRE_TABLE_UTILISATEUR);
		utilisateurTableSc.setPreferredSize(new Dimension(337, 300));
		Object[] ligne = new Object[3];
		try {
			list = service.getListUtilisateur();

		} catch (SQLException e) {
			getMessageDialog(JOptionPane.ERROR_MESSAGE,
					IUtilisateurConst.MESSAGE_ERREUR,
					IUtilisateurConst.SQL_ERREUR);
		}
		for (DtoUtilisateur u : list) {
			ligne[0] = u.getIdUtilisateur();
			ligne[1] = u.getNom();
			ligne[2] = u.getPrenom();
			tableM.addRow(ligne);
		}
	}

	/**
	 * 
	 * @param id
	 * 
	 * Méthode pour mettre à jour la table des adresses d'un utilisateur donnée
	 */
	private void getJTableAdresse(int id) {
		List<DtoAdresse> list = null;
		if (null == adresseTable) {
			adresseTable = new JTable();
		}
		if (null == adresseTableSc) {
			adresseTableSc = new JScrollPane(adresseTable);
		}
		Font police = new Font("Arial", Font.PLAIN, 13);
		adresseTable.setBackground(Color.white);
		DefaultTableModel tableM = (DefaultTableModel) adresseTable.getModel();
		adresseTable.setFont(police);
		adresseTable.setModel(tableM);
		tableM.setDataVector(null, IUtilisateurConst.TITRE_TABLE_ADRESSE);
		Object[] ligne = new Object[6];
		DtoUtilisateur u = new DtoUtilisateur();
		u.setIdUtilisateur(id);
		try {

			list = service.getListAdresseByUtilisateur(u);

		} catch (SQLException e) {
			getMessageDialog(JOptionPane.ERROR_MESSAGE,
					IUtilisateurConst.MESSAGE_ERREUR,
					IUtilisateurConst.SQL_ERREUR);
		}
		for (DtoAdresse a : list) {
			ligne[0] = a.getIdAdresse();
			ligne[1] = a.getPays();
			ligne[2] = a.getVille();
			ligne[3] = a.getCodePostal();
			ligne[4] = a.getRue();
			ligne[5] = a.getNumero();
			tableM.addRow(ligne);
		}
	}
	
	/**
	 * 
	 * @return
	 * 
	 * Méthode pour générer un code 6 caracteres (utilisée dans les identifiants des entités)
	 */
	private String genCode() {
		long date = new Date().getTime();
		String s = String.valueOf(date);
		s = s.substring(3, 10);
		return s;
	}

	/**
	 * 
	 * @param typeMessage
	 * @param titreMessage
	 * @param message
	 * 
	 * Méthode pour afficher le show Message
	 */
	@SuppressWarnings("static-access")
	private static void getMessageDialog(int typeMessage, String titreMessage,
			String message) {
		optionPane.showMessageDialog(null, message, titreMessage, typeMessage);
	}
	/**
	 * Méthode pour afficher les composant pour afficher la liste des utilisateurs
	 */
	private void updatePane1(){
		idL.setVisible(false);
		idTF.setVisible(false);
		nomL.setVisible(false);
		nomTF.setVisible(false);
		prenomL.setVisible(false);
		prenomTF.setVisible(false);
		validerB.setVisible(false);
		accueilB.setVisible(false);
		paneCentre.removeAll();
		getJTableUtilisateur();
		listeUtilisateur.setVisible(true);
		utilisateurTableSc.setVisible(true);
		paneCentre.add(listeUtilisateur);
		paneCentre.add(utilisateurTableSc);
	}
	/**
	 * Méthode pour afficher les composants pour l'edition d'un utilisateur 
	 * (ajout, modification et affichage)
	 */
	private void updatePane2(){
		getJTableAdresse(Integer.valueOf(idTF.getText()));
		adresseTableSc.setBounds(5, 100, 420, 200);
		utilisateurTableSc.setVisible(false);
		listeUtilisateur.setVisible(false);
		paneCentre.removeAll();
		idL.setVisible(true);
		idTF.setVisible(true);
		nomL.setVisible(true);
		nomTF.setVisible(true);
		prenomL.setVisible(true);
		prenomTF.setVisible(true);
		accueilB.setVisible(true);
		adresseTableSc.setVisible(true);
		paneCentre.add(idL);
		paneCentre.add(idTF);
		paneCentre.add(prenomL);
		paneCentre.add(nomTF);
		paneCentre.add(nomL);
		paneCentre.add(prenomTF);
		paneCentre.add(validerB);
		paneCentre.add(accueilB);
		paneCentre.add(ajouterAdresse);
		paneCentre.add(supprimerAdresse);
		paneCentre.add(adresseTableSc);
	}
}
