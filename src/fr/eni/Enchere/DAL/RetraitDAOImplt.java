package fr.eni.Enchere.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.Enchere.BO.Enchere;
import fr.eni.Enchere.BO.Retrait;

public class RetraitDAOImplt implements DAOArt<Retrait> {

	private static final String sqlSelectRetraiteById = "select * from Retraits where no_article =?";
	private static final String sqlSelectAllRun= "select * From Retraits";
	private static final String sqlUpdate= "update Retraits set  = ?, "
			+ "code_postal = ?, = ? where no_article = ?";
	private static final String sqlInsert = "insert into Retraits (no_article, rue,"
			+ "code_postal,ville)"
			+ " values (?,?,?,?)";
	private static final String sqlDelete = "delete from Retraits where no_article = ?" ;
	
public RetraitDAOImplt() {
		
	}

@Override
public Retrait selectbyId(int id) throws DALException {
	Retrait retrait= null;
	PreparedStatement pstmt = null;
	try (Connection cnx = ConnectionProvider.getConnextion()){
		pstmt= cnx.prepareStatement(sqlSelectRetraiteById);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
	
		
		if(rs.next()) {
			retrait = new Retrait(rs.getInt("no_article"), rs.getString("rue"),
					rs.getString("code_Postal"), rs.getString("ville"));
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
	return retrait;
}

@Override
public List<Retrait> selectAll() throws DALException {
	Retrait retrait= null;
	List<Retrait> listRetrait= new ArrayList<Retrait>();
	try (Connection cnx = ConnectionProvider.getConnextion()){
		
		PreparedStatement pstmt = cnx.prepareStatement(sqlSelectAllRun);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			
			retrait = new Retrait(rs.getInt("no_article"), rs.getString("rue"),
					rs.getString("codePostal"), rs.getString("ville"));
			listRetrait.add(retrait);
	}
					
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		// TODO: handle finally clause
	}return listRetrait;
}

@Override
public void update(Retrait retrait) throws DALException {
	PreparedStatement pstmt = null;
	try (Connection cnx = ConnectionProvider.getConnextion() ){
		pstmt = cnx.prepareStatement(sqlUpdate);
		pstmt.setInt(1, retrait.getNoArticle());
		pstmt.setString(2, retrait.getRue());
		pstmt.setString(3, retrait.getCodePostal());
		pstmt.setString(4, retrait.getVille());
		
	pstmt.executeUpdate();
	} catch (Exception e) {
		throw new DALException("Update retrait failed"+ retrait, e);
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
public void insert(Retrait retrait) throws DALException {
	PreparedStatement pstmt = null;
	try (Connection cnx = ConnectionProvider.getConnextion() ){
		pstmt = cnx.prepareStatement(sqlInsert);
		pstmt.setInt(1, retrait.getNoArticle());
		pstmt.setString(2, retrait.getRue());
		pstmt.setString(3, retrait.getCodePostal());
		pstmt.setString(4, retrait.getVille());

		
		pstmt.executeUpdate();

	
	} catch (Exception e) {
		throw new DALException("Insert retrait failed"+ retrait, e);
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
public List<Retrait> selectAllByMotCle(String mot, String categorie, String option) throws DALException {
	// TODO Auto-generated method stub
	return null;
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
public void updateNoAcquereur(int noAcquereur, int noArticle) throws DALException {
	// TODO Auto-generated method stub
	
}
}
