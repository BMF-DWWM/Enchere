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


public class ArticleDAOImplt implements DAOArt<ArticlesVendu> {
	
	private static final String sqlSelectArticleById = "select * from ARTICLES_VENDUS av\r\n" + 
			"	inner join UTILISATEURS u\r\n" + 
			"	on u.no_utilisateur = av.no_utilisateur\r\n" + 
			"	where no_article = ?";
	private static final String sqlSelectAllRun= "select * from ARTICLES_VENDUS av\r\n" + 
			"	inner join UTILISATEURS u\r\n" + 
			"	ON u.no_utilisateur=av.no_utilisateur";
	private static final String sqlSelectAllRunByMotCle = "select * from ARTICLES_VENDUS av\r\n"
			+ "inner join UTILISATEURS u\r\n"
			+ "ON u.no_utilisateur=av.no_utilisateur\r\n"
			+ "where nom_article like";
	private static final String sqlUpdate= "update Articles_Vendus  set nom_article = ?, description = ?, "
			+ "date_debut_encheres = ?, date_fin_encheres= ?, prix_initial = ?, prix_vente = ?, "
			+ "no_utilisateur = ?, no_categorie = ? where no_article = ?";
	private static final String sqlInsert = "insert into Articles_Vendus (nom_article, description,date_debut_encheres,"
			+ "date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie)"
			+ " values (?,?,?,?,?,?,?,?)";
	private static final String sqlDelete = "delete from Articles_Vendus where no_article = ?" ;
	private static final String sqlUpdateNoAcquereur = "update ARTICLES_VENDUS set no_acquereur = ? where no_article = ?";
	
	public  ArticleDAOImplt() {
	}

	@Override
	public ArticlesVendu selectbyId(int id) throws DALException {
		ArticlesVendu article = null;
		PreparedStatement pstmt = null;
		try (Connection cnx = ConnectionProvider.getConnextion()){
			pstmt= cnx.prepareStatement(sqlSelectArticleById);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
		
			
			if(rs.next()) {
				article = new ArticlesVendu(rs.getInt("no_article"),rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres"),
						rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"), rs.getString("pseudo"), rs.getInt("no_acquereur"));
				
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
		return article;
	}

	@Override
	public List<ArticlesVendu> selectAll() throws DALException {
		ArticlesVendu article = null;
		List<ArticlesVendu> listArticle = new ArrayList<ArticlesVendu>();
		try (Connection cnx = ConnectionProvider.getConnextion()){
			
			PreparedStatement pstmt = cnx.prepareStatement(sqlSelectAllRun);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				article = new ArticlesVendu(rs.getInt("no_article"),rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres"),
						rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"), rs.getString("pseudo"), rs.getInt("no_acquereur"));		
				listArticle.add(article);
		}
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// TODO: handle finally clause
		}return listArticle;
	}
	public List<ArticlesVendu> selectAllByMotCle(String mot, String categorie, String option) throws DALException {
		ArticlesVendu article = null;
		List<ArticlesVendu> listArticle = new ArrayList<ArticlesVendu>();
		try (Connection cnx = ConnectionProvider.getConnextion()){
			PreparedStatement pstmt = cnx.prepareStatement(sqlSelectAllRunByMotCle+" '%"+ mot+ "%' " + categorie + option);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				article = new ArticlesVendu(rs.getInt("no_article"),rs.getString("nom_article"), rs.getString("description"), rs.getDate("date_debut_encheres"),
						rs.getDate("date_fin_encheres"), rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"),
						rs.getInt("no_categorie"), rs.getString("pseudo"), rs.getInt("no_acquereur"));		
				listArticle.add(article);
		}
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// TODO: handle finally clause
		}return listArticle;
	}
	

	@Override
	public void update(ArticlesVendu article ) throws DALException {
		PreparedStatement pstmt = null;
		try (Connection cnx = ConnectionProvider.getConnextion() ){
			pstmt = cnx.prepareStatement(sqlUpdate);
			pstmt.setInt(1, article.getNoArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, (Date)article.getDateDebutEncheres());
			pstmt.setDate(4, (Date)article.getDateFinEncheres());
			pstmt.setInt(5, article.getPrixInitial());
			pstmt.setInt(6, article.getPrixVente());
			pstmt.setInt(7, article.getNoUtilisateur());
			pstmt.setInt(8, article.getNoCategorie());
			
		pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Update article failed"+ article, e);
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
	public void insert(ArticlesVendu article) throws DALException {

		PreparedStatement pstmt = null;
		try (Connection cnx = ConnectionProvider.getConnextion() ){
			pstmt = cnx.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, (Date) article.getDateDebutEncheres());
			pstmt.setDate(4, (Date)article.getDateFinEncheres());
			pstmt.setInt(5, article.getPrixInitial());
			pstmt.setInt(6, article.getPrixVente());
			pstmt.setInt(7, article.getNoUtilisateur());
			pstmt.setInt(8, article.getNoCategorie());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				article.setNoArticle(rs.getInt(1));
			}
		} catch (Exception e) {
			throw new DALException("Insert article failed"+ article, e);
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
			throw new DALException("Delete article failed"+ noArticle, e);
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
		PreparedStatement pstmt = null;
		try (Connection cnx = ConnectionProvider.getConnextion() ){
			pstmt = cnx.prepareStatement(sqlUpdateNoAcquereur);
			pstmt.setInt(1, noAcquereur);
			pstmt.setInt(2, noArticle);
			
		pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DALException("Update noAcquereur article failed"+ noAcquereur + " " +noArticle + e);
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
	public Enchere selectbyIdUserAndIdArticle(int idUtilisateur, int IdArticle) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere sqlSelectMax(int idArticle) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int noArticle, int noutilisateur) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateCreditInsertEnchere(int soldeCredit, int montantEnchere, int noUtilisateur) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateCreditUpdateEnchere(int soldeCredit, int nouvelleEnchere, int ancienneEnchere, int noUtilisateur)
			throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void UpdateCreditRollBackEnchere(int soldeCredit, int derniereEnchere, int noUtilisateur)
			throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Enchere selectbyIdUser(int iduser) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}





}
