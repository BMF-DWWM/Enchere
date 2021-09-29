package fr.eni.Enchere.DAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.Enchere.BO.ArticlesVendu;
import fr.eni.Enchere.BO.Enchere;
import fr.eni.Enchere.BO.Utilisateur;

public class EnchereDAOImplt implements DAOArt<Enchere>{
	private static final String sqlSelectEnchereeByIdUser = "select * from Encheres where no_utilisateur =?";
	private static final String sqlSelectEnchereeById = "select * from Encheres where no_article =?";
	private static final String sqlEnchereVerifExiste = "select * from ENCHERES where no_utilisateur = ? and no_article = ?";
	private static final String sqlSelectAllRun= "select * From Encheres";
	private static final String sqlUpdate= "update Encheres  set  date_enchere = ?, "
			+ " montant_enchere= ? where no_article = ? and no_utilisateur = ?";
	private static final String sqlInsert = "insert into Encheres (no_utilisateur, date_enchere,"
			+ "no_article,montant_enchere)"
			+ " values (?,?,?,?)";
	private static final String sqlDelete = "delete from Encheres where no_article = ? and no_utilisateur = ?  " ;
	private static final String sqlSelectMax ="select  MAX(montant_enchere) as montant_enchere,no_article,date_enchere,no_utilisateur from ENCHERES \r\n" + 
			"where no_article = ? \r\n" + 
			"group by no_article,date_enchere, no_utilisateur \r\n" +
			"order by montant_enchere desc";
	private static final String sqlUpdateCreditInsertEnchere =  "update UTILISATEURS set credit = ( ? - ? ) where no_utilisateur = ?";
	private static final String sqlUpdateCreditUpdateEnchere =  "update UTILISATEURS set credit = soldecredit -(nouvelleEnchere- ancienne enchere ) where no_utilisateur = ? ";
	private static final String sqlUpdateCreditRollBackEnchere =  "update UTILISATEURS set credit = soldecredit  - enchere loa plus recente  where no_utilisateur =? " ;
	public EnchereDAOImplt() {
		
	}
	@Override
	public Enchere selectbyIdUserAndIdArticle(int idUtilisateur, int IdArticle) throws DALException {
		Enchere enchere= null;
		PreparedStatement pstmt = null;
		try (Connection cnx = ConnectionProvider.getConnextion()){
			pstmt= cnx.prepareStatement(sqlEnchereVerifExiste);
			pstmt.setInt(1, idUtilisateur);
			pstmt.setInt(2, IdArticle);
			ResultSet rs = pstmt.executeQuery();
		
			
			if(rs.next()) {
				enchere = new Enchere(rs.getInt("no_utilisateur"),rs.getDate("date_enchere"),
						rs.getInt("no_article"), rs.getInt("montant_enchere"));
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException ("selectbyIdUserAndIdArticle failed - id = " + idUtilisateur + IdArticle, e);
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return enchere;
	}

	@Override
	public Enchere selectbyId(int id) throws DALException {
		Enchere enchere= null;
		PreparedStatement pstmt = null;
		try (Connection cnx = ConnectionProvider.getConnextion()){
			pstmt= cnx.prepareStatement(sqlSelectEnchereeById);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
		
			
			if(rs.next()) {
				enchere = new Enchere(rs.getInt("no_utilisateur"),rs.getDate("date_enchere"),
						rs.getInt("no_article"), rs.getInt("montant_enchere"));
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
		return enchere;
	}
	
	

	@Override
	public List<Enchere> selectAll() throws DALException {
		Enchere enchere= null;
		List<Enchere> listEnchere = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnextion()){
			
			PreparedStatement pstmt = cnx.prepareStatement(sqlSelectAllRun);
			pstmt.setMaxRows(1); 
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				enchere = new Enchere(rs.getInt("no_utilisateur"),rs.getDate("date_enchere"),
						rs.getInt("no_article"), rs.getInt("montant_enchere"));	
				listEnchere.add(enchere);
		}
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// TODO: handle finally clause
		}return listEnchere;
	}

	@Override
	public void update(Enchere enchere) throws DALException {
		PreparedStatement pstmt = null;
		try (Connection cnx = ConnectionProvider.getConnextion() ){
			pstmt = cnx.prepareStatement(sqlUpdate);
			pstmt.setDate(1, enchere.getDateEnchere());
			pstmt.setInt(2, enchere.getMontantEnchere());
			pstmt.setInt(3, enchere.getNoArticle());
			pstmt.setInt(4, enchere.getNoUtilisateur());
			
		pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Update enchere failed"+ enchere, e);
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
	public void insert(Enchere enchere) throws DALException {
		PreparedStatement pstmt = null;
		try (Connection cnx = ConnectionProvider.getConnextion() ){
			pstmt = cnx.prepareStatement(sqlInsert);
			pstmt.setInt(1, enchere.getNoUtilisateur());
			pstmt.setDate(2, enchere.getDateEnchere());
			pstmt.setInt(3, enchere.getNoArticle());
			pstmt.setInt(4, enchere.getMontantEnchere());

			
			pstmt.executeUpdate();
			
		
		} catch (Exception e) {
			throw new DALException("Insert enchere failed"+ enchere, e);
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
	public void delete(int noArticle) throws DALException {

		PreparedStatement pstmt = null;
		try (Connection cnx = ConnectionProvider.getConnextion() ){
			pstmt = cnx.prepareStatement(sqlDelete);
			pstmt.setInt(1, noArticle);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Delete enchere failed"+ noArticle, e);
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}



	@Override
	public List<Enchere> selectAllByMotCle(String mot, String categorie, String option) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Enchere sqlSelectMax(int idArticle) throws DALException {
		Enchere enchere= null;
		PreparedStatement pstmt = null;
		try (Connection cnx = ConnectionProvider.getConnextion()){
			pstmt= cnx.prepareStatement(sqlSelectMax);
			pstmt.setInt(1, idArticle);
			ResultSet rs = pstmt.executeQuery();
		
			
			if(rs.next()) {
				enchere = new Enchere(rs.getInt("no_utilisateur"),rs.getDate("date_enchere"),
						rs.getInt("no_article"), rs.getInt("montant_enchere"));
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException ("SelectMax failed - id = " + idArticle, e);
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return enchere;
	}
	@Override
	public void delete(int noArticle, int noutilisateur) throws DALException {

		PreparedStatement pstmt = null;
		try (Connection cnx = ConnectionProvider.getConnextion() ){
			pstmt = cnx.prepareStatement(sqlDelete);
			pstmt.setInt(1, noArticle);
			pstmt.setInt(2, noutilisateur);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Delete enchere failed"+ noArticle, e);
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public void updateNoAcquereur(int noAcquereur, int noArticle) throws DALException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Enchere selectbyIdUser(int iduser) throws DALException {
		Enchere enchere= null;
		PreparedStatement pstmt = null;
		try (Connection cnx = ConnectionProvider.getConnextion()){
			pstmt= cnx.prepareStatement(sqlSelectEnchereeByIdUser);
			pstmt.setInt(1, iduser);
			ResultSet rs = pstmt.executeQuery();
		
			
			if(rs.next()) {
				enchere = new Enchere(rs.getInt("no_utilisateur"),rs.getDate("date_enchere"),
						rs.getInt("no_article"), rs.getInt("montant_enchere"));
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException ("SelectBtIdUser failed - id = " + iduser, e);
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return enchere;
	}
	@Override
	public void UpdateCreditInsertEnchere(int soldeCredit, int montantEnchere, int noUtilisateur) throws DALException {
		PreparedStatement pstmt = null;
		try (Connection cnx = ConnectionProvider.getConnextion() ){
			pstmt = cnx.prepareStatement(sqlUpdateCreditInsertEnchere);
			pstmt.setInt(1, soldeCredit);
			pstmt.setInt(2, montantEnchere);
			pstmt.setInt(3, noUtilisateur);
			
		pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("UpdateCreditInsertEnchere enchere failed"+soldeCredit +montantEnchere+ noUtilisateur, e);
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
	public void UpdateCreditUpdateEnchere(int soldeCredit, int nouvelleEnchere, int ancienneEnchere, int noUtilisateur) throws DALException {
	PreparedStatement pstmt = null;
	try (Connection cnx = ConnectionProvider.getConnextion() ){
		pstmt = cnx.prepareStatement(sqlUpdateCreditUpdateEnchere);
		pstmt.setInt(1, soldeCredit);
		pstmt.setInt(2, nouvelleEnchere);
		pstmt.setInt(3, ancienneEnchere);
		pstmt.setInt(4, noUtilisateur);
		
	pstmt.executeUpdate();
	} catch (Exception e) {
		throw new DALException("UpdateCreditUpdateEnchere enchere failed"+soldeCredit +nouvelleEnchere+ ancienneEnchere +noUtilisateur, e);
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
	public void UpdateCreditRollBackEnchere(int soldeCredit, int derniereEnchere, int noUtilisateur) throws DALException {
		PreparedStatement pstmt = null;
		try (Connection cnx = ConnectionProvider.getConnextion() ){
			pstmt = cnx.prepareStatement(sqlUpdateCreditRollBackEnchere);
			pstmt.setInt(1, soldeCredit);
			pstmt.setInt(2, derniereEnchere);
			pstmt.setInt(4, noUtilisateur);
			
		pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("UpdateCreditRollBackEnchere enchere failed"+soldeCredit +derniereEnchere+ noUtilisateur , e);
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

}
