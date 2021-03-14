package friendsofmine.services;

import friendsofmine.domain.Article;
import friendsofmine.repositories.ArticleRepositoryInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepositoryInt articleRepository;

    public List<Article> findAllArticles() {
        return articleRepository.findAllArticles();
    }

    public ArticleRepositoryInt getArticleRepository() {
        return articleRepository;
    }

    public void setArticleRepository(ArticleRepositoryInt articleRepository) {
        this.articleRepository = articleRepository;
    }
}