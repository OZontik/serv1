package zozo.company.repository;

import org.springframework.stereotype.Repository;
import zozo.company.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Repository
public class PostRepositoryStubImpl implements PostRepository {
    ConcurrentHashMap<Integer, String> postsColl = new ConcurrentHashMap<>();
    AtomicInteger integer = new AtomicInteger(2);

    public PostRepositoryStubImpl() {
        Post post1 = new Post(1, "Первый пост");
        Post post2 = new Post(2, "Второй пост");
        postsColl.put(1, post1.getContent());
        postsColl.put(2, post2.getContent());
    }

    public List<Post> all() {

        return (List) new ArrayList(postsColl.values());
    }

    public Optional<Post> getById(long id) {
        Optional post = Optional.ofNullable(postsColl.get(id));
        return post;
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            postsColl.put(integer.incrementAndGet(), post.getContent());
        }
        if (post.getContent() == null) {
            System.out.println("Вероятно пост с таким id удален, обновление не возможно");
        } else {
            postsColl.replace((int) post.getId(), post.getContent());
        }
        return post;
    }

    public void removeById(long id) {
        postsColl.remove(id);
    }
}
