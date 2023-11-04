package likelion.underdog.songgotmae.domain.voc;

import likelion.underdog.songgotmae.domain.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocRepository extends JpaRepository<Voc, Long> {
    Page<Voc> findAllByOrderByCreatedAt(Pageable pageable);
}
