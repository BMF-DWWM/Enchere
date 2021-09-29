package fr.eni.Enchere.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.eni.Enchere.BO.ArticlesVendu;
import fr.eni.Enchere.BO.Utilisateur;



public class UtilisateurDAOImplt implements DAOUtilisateur {
	public  UtilisateurDAOImplt() {
	}
	
	private String sqlmdpOublierVerif = "Select pseudo from Utilisateurs where pseudo=?";
	private String sqlmdpOublier = "update Utilisateurs set mot_de_passe=? where pseudo=? ";
	private String sqlSelectAll = "Select idUser,identifiant,password from Utilisateur";
	private String sqlVerif = "Select * from Utilisateurs where (pseudo=? or email=?) and mot_de_passe=?";
	private String sqlVerifCreationCompte = "Select pseudo,email from Utilisateurs where pseudo=? and email=?";
	private String sqlCreationCompte = "insert into Utilisateurs(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe) values(?,?,?,?,?,?,?,?,?)";
	private String sqlUpdate = "update UTILISATEURS SET pseudo = ?,nom = ?,prenom = ?,email= ?,telephone = ?,rue = ?,code_postal = ?,ville = ?,mot_de_passe = ? where no_utilisateur = ?";
	private String sqlSelectUser = "select * from UTILISATEURS where no_utilisateur = ?";

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
					return new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getBoolean("administrateur"));
					
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
				return new Utilisateur(rs.getString("Pseudo"));
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
	public Utilisateur updateMdp(String Pseudo,String Password) throws DALException {
		
		try {
			Connection connection = ConnectionProvider.getConnextion();
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sqlmdpOublierVerif);
			
			pstmt.setString(1, Pseudo);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {

				pstmt = connection.prepareStatement(sqlmdpOublier);
				pstmt.setString(1,Password);
				pstmt.setString(2, Pseudo);
				pstmt.executeUpdate();
				System.out.println("mot de passe changer avec succes");
				return new Utilisateur(Pseudo);
			}
			else {
				System.out.println("aucun compte créer avec ce pseudo");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public void update(Utilisateur utilisateur) throws DALException {
		PreparedStatement pstmt = null;
		try (Connection cnx = ConnectionProvider.getConnextion() ){
			pstmt = cnx.prepareStatement(sqlUpdate);
			pstmt.setString(1, utilisateur.getPseudo() ) ;
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getNoUtilisateur());
			
			
		pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Update profil failed"+ utilisateur, e);
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					throw new DALException("close failed - ", e);
				}
			}
		}

		
	}

	
	@Override
	public Utilisateur selectbyId(int id) throws DALException {
		Utilisateur utilisateur = null;
		PreparedStatement pstmt = null;
		try (Connection cnx = ConnectionProvider.getConnextion()){
			pstmt= cnx.prepareStatement(sqlSelectUser);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
		
			
			if(rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"),
						rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),
						rs.getString("telephone"), rs.getString(" rue"),rs.getString("code_Postal"),
						rs.getString("ville"), rs.getString("mot_De_Passe"),rs.getInt("credit") ,
						rs.getBoolean("administrateur"));
				
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException ("SelectBtId failed - id = " + id, e);
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return utilisateur;
	}


	@Override
	public List<Utilisateur> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
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

