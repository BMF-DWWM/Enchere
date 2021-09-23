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
	
	private static final String sqlSelectEnchereeById = "select * from Encheres where no_article =?";
	private static final String sqlSelectAllRun= "select * From Encheres";
	private static final String sqlUpdate= "update Encheres  set no_utilisateur = ?, date_enchere = ?, "
			+ "no_article = ?, montant_enchere= ? where no_article = ?";
	private static final String sqlInsert = "insert into Encheres (no_utilisateur, date_enchere,"
			+ "no_article,montant_enchere)"
			+ " values (?,?,?,?)";
	private static final String sqlDelete = "delete from Encheres where no_utilisateur = ?" ;
	
	public EnchereDAOImplt() {
		
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
			pstmt.setInt(1, enchere.getNoUtilisateur());
			pstmt.setDate(2, enchere.getDateEnchere());
			pstmt.setInt(3, enchere.getNoArticle());
			pstmt.setInt(4, enchere.getMontantEnchere());
			
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
	public List<Enchere> selectAllByMotCle(String mot, String categorie) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}


}
