package likelion.underdog.songgotmae.domain.admin;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Data
public class AdminService {
    private final Set<String> adminUsers = new HashSet<>();

    public AdminService() {
        adminUsers.add("admin");
    }

    public boolean isAdmin(String username) {
        return adminUsers.contains(username);
    }
}
