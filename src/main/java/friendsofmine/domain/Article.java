package friendsofmine.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class Article {

    @PositiveOrZero
    private int articleId;

    @NotNull
    @Size(min = 1, max = 256)
    private String titre;

    private String categorie;

    public Article(int id, String titre, String categorie) {
        articleId = id;
        this.titre = titre;
        this.categorie = categorie;
    }

    public Article() { }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Article) && (articleId == ((Article) obj).getArticleId());
    }
}
