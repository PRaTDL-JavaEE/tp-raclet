package friendsofmine.repositories;

import friendsofmine.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepositoryWithJdbc implements ArticleRepositoryInt {

    @Autowired
    private DataSource dataSource;

    public List<Article> findAllArticles() {
        List<Article> articles = new ArrayList<>();
        ResultSet fetchedArticles;
        Connection connexion = null;
        
        try {
            connexion = dataSource.getConnection();
            Statement statement = connexion.createStatement();
            fetchedArticles = statement.executeQuery("SELECT * FROM articles");
            while(fetchedArticles.next()) {
                Article art = new Article();
                art.setArticleId(fetchedArticles.getInt("id"));
                art.setTitre(fetchedArticles.getString("titre"));
                art.setCategorie(fetchedArticles.getString("categorie"));
                articles.add(art);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connexion != null)
                    connexion.close();
            } catch(SQLException sqle) {
                sqle.printStackTrace();
            }
        }

        return articles;
    }
}
