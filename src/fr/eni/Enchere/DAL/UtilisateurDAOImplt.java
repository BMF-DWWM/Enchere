package fr.eni.Enchere.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.eni.Enchere.BO.Utilisateur;
import fr.eni.Enchere.DAL.ConnectionProvider;


public class UtilisateurDAOImplt implements DAOUtilisateur {
	public  UtilisateurDAOImplt() {
	}
	
	private String sqlSelectAll = "Select idUser,identifiant,password from Utilisateur";
	private String sqlVerif = "Select no_utilisateur,pseudo,mot_de_passe from Utilisateurs where pseudo=? and mot_de_passe=?";
	private String sqlVerifPseudo = "Select identifiant from Utilisateur where identifiant=?";
	private static final String sqlInsert = "insert into Utilisateur(identifiant,password) values(?,?)";


	
//public void SelectAll() {
//		
//		try  {
//		Connection connection = ConnectionProvider.getConnextion();
//		Statement stmt = connection.createStatement();
//		ResultSet rs = stmt.executeQuery(sqlSelectAll);
//		
//		while(rs.next()) {
//			System.out.println("Id:" + rs.getInt("idUser"));
//			System.out.println("Identifiant:" + rs.getString("identifiant"));
//			System.out.println("Password:" + rs.getString("password"));
//			
//		}
//		} catch (SQLException e) {
//			System.out.println("Erreur récuperation donnée");
//			e.printStackTrace();
//		}
//	} 

		
	
	public DAOUtilisateur VerifConnection(String Pseudo,String Password) {
		try {
			
			Connection connection = ConnectionProvider.getConnextion();
			PreparedStatement pstmt = connection.prepareStatement(sqlVerif);
			pstmt.setString(1,Pseudo);
			pstmt.setString(2,Password);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return (DAOUtilisateur) new Utilisateur(rs.getString("pseudo"));
			}
			else {
				System.out.println("pas ok");
			}
		} 
		catch (SQLException e) {
			System.out.println("Erreur de verification");
			e.printStackTrace();
		}
		return null;

	}


	public Utilisateur VerifPseudo(String Pseudo) {
		
		try {
			 Connection connection = ConnectionProvider.getConnextion();
			 Utilisateur utilisateur = new Utilisateur(Pseudo);

			PreparedStatement pstmt = connection.prepareStatement(sqlVerifPseudo);
			pstmt.setString(1,Pseudo);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				System.out.println("Pseudo deja utiliser");
				return utilisateur;
			}
			else {
				System.out.println("Pseudo disponible");
			}
			} catch (SQLException e) {
				System.out.println("Erreur de verification");
				e.printStackTrace();
			}
			return null;
		} 


	public void CreationCompte (String Pseudo,String Password) {
		
	try {
		Connection connection = ConnectionProvider.getConnextion();
		PreparedStatement pstmt = connection.prepareStatement(sqlInsert,Statement.RETURN_GENERATED_KEYS);
		Utilisateur utilisateur = new Utilisateur(); 
		
		pstmt.setString(1, Pseudo);
		pstmt.setString(2, Password);
		
		int nbRows = pstmt.executeUpdate();
		if(nbRows == 1) {
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
		}
		} catch (SQLException e) {
			System.out.println("Erreur insertion nouveau compte ");
			e.printStackTrace();
		}
	}


	@Override
	public DAOUtilisateur selectbyId(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Utilisateur> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void update(Utilisateur t) throws DALException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void insert(Utilisateur t) throws DALException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(int id) throws DALException {
		// TODO Auto-generated method stub
		
	}
	
}

