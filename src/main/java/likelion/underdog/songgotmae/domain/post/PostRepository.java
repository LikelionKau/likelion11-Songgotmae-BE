package likelion.underdog.songgotmae.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p join fetch p.author")
    public List<Post> findAllPosts();

    @Query("select p from Post p join fetch p.author where p.isApproved = true")
    public List<Post> findApprovedPosts();

    @Query("select p from Post p join fetch p.author where p.isApproved = false")
    public List<Post> findNotApprovedPosts();

    @Query("select p from Post p join fetch p.author where p.author.id = :authorId")
    public List<Post> findPostsByMemberId(Long authorId);
}
