package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepositoryStubImpl implements PostRepository {

  private AtomicLong id = new AtomicLong(0);
  private Map<Long, Post> map = new ConcurrentHashMap<>();

  public List<Post> all() {
    return new ArrayList<>(map.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(map.get(id));
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      long newId = id.incrementAndGet();
      post.setId(newId);
    }
    map.put(post.getId(), post);
    return post;
  }

  public void removeById(long id) {
    map.remove(id);
  }
}
