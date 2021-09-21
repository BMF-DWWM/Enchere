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
	private String sqlVerif = "Select no_utilisateur,pseudo,email,mot_de_passe from Utilisateurs where (pseudo=? or email=?) and mot_de_passe=?";
	private String sqlVerifCreationCompte = "Select pseudo,email from Utilisateurs where pseudo=? and email=?";
	private static final String sqlCreationCompte = "insert into Utilisateurs(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe) values(?,?,?,?,?,?,?,?,?)";


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

		
	
	public Utilisateur VerifConnection(String Pseudo,String Password) {
		try {
			
			Connection connection = ConnectionProvider.getConnextion();
			PreparedStatement pstmt = connection.prepareStatement(sqlVerif);
			pstmt.setString(1,Pseudo);
			pstmt.setString(2,Pseudo);
			pstmt.setString(3,Password);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				if(Pseudo.contains("@")) {
					return new Utilisateur(rs.getString("email"));
				}
				else {
					return new Utilisateur(rs.getString("pseudo"));
				}
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

	public Utilisateur VerifPseudo(String Pseudo, String Email) {
		
		try {
			 Connection connection = ConnectionProvider.getConnextion();

			PreparedStatement pstmt = connection.prepareStatement(sqlVerifCreationCompte);
			pstmt.setString(1,Pseudo);
			pstmt.setString(2,Email);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				System.out.println("Pseudo/email deja utiliser");
				return new Utilisateur();
			}
			else {
				System.out.println("Pseudo & email disponible");
			}
			} catch (SQLException e) {
				System.out.println("Erreur de verification");
				e.printStackTrace();
			}
			return null;
		} 


	public Utilisateur CreationCompte (String Pseudo,String Nom,String Prenom,String Email,String Telephone,String Rue,String CodePostal,String Ville,String Password) {
		
	try {
		Connection connection = ConnectionProvider.getConnextion();
		PreparedStatement pstmt = connection.prepareStatement(sqlCreationCompte,Statement.RETURN_GENERATED_KEYS);
		Utilisateur utilisateur = new Utilisateur(); 
		
		pstmt.setString(1, Pseudo);
		pstmt.setString(2, Nom);
		pstmt.setString(3, Prenom);
		pstmt.setString(4, Email);
		pstmt.setString(5, Telephone);
		pstmt.setString(6, Rue);
		pstmt.setString(7, CodePostal);
		pstmt.setString(8, Ville);
		pstmt.setString(9, Password);
		
		int nbRows = pstmt.executeUpdate();
		if(nbRows == 1) {
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt(1));
				System.out.println("Profil Créer");
			}
		}
		} catch (SQLException e) {
			System.out.println("Erreur insertion nouveau compte ");
			e.printStackTrace();
		}
	return null;
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

