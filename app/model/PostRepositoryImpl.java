package model;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import java.util.List;

public class PostRepositoryImpl implements PostRepository{

    private final JPAApi jpaApi;
private final DbExecutionContext executionContext;


@Inject
    public PostRepositoryImpl(JPAApi jpaApi, DbExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public List<Post> getAll() {
    jpaApi.withTransaction(entityManager ->{
        entityManager.createQuery("select p from Post p", Post.class);
    });
        return null;
    }
}
