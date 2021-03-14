package friendsofmine.repositories;

import friendsofmine.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
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

    public Article findArticleById(int id) {
        Article article = null;
        ResultSet fetchedArticle;
        Connection connexion = null;

        try {
            connexion = dataSource.getConnection();
            PreparedStatement statement =
                    connexion.prepareStatement("SELECT id, titre, categorie FROM articles WHERE id = ?");

            statement.setString(1, String.valueOf(id));
            fetchedArticle = statement.executeQuery();

            if(fetchedArticle.next()) {
                article = new Article();
                article.setArticleId(fetchedArticle.getInt("id"));
                article.setTitre(fetchedArticle.getString("titre"));
                article.setCategorie(fetchedArticle.getString("categorie"));
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

        return article;

    }

    public Article saveArticle(Article article) {
        Connection connexion = null;
        try {
            connexion = dataSource.getConnection();
            PreparedStatement statement =
                    connexion.prepareStatement("INSERT INTO articles (id, titre, categorie) VALUES (?, ?, ?)");

            statement.setString(1, String.valueOf(article.getArticleId()));
            statement.setString(2, String.valueOf(article.getTitre()));
            statement.setString(3, String.valueOf(article.getCategorie()));

            statement.execute();
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

        return article;
    }


}
