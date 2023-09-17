package likelion.underdog.songgotmae.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import likelion.underdog.songgotmae.domain.post.PostRepository;
import likelion.underdog.songgotmae.domain.post.PostService;
import likelion.underdog.songgotmae.web.dto.PostDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = PostController.class)
// springbootTest는 전체 bean을 전부 띄우는 테스트 어노테이션
// webMVCTest는 controller test에 필요한 bean만 불러오는 어노테이션이라 생각하면 된다. (service Bean은 불러오지 않음 -> 반드시 service 빈들은 mockbean 처리해줘야 함)
// 마치 repository test를 할 때에는 dataJpaTest 어노테이션을 사용하는 것과 유사하다.
@ActiveProfiles("test")
class PostControllerTest {

    @Autowired
    private ObjectMapper om;
    @Autowired
    private MockMvc mockMvc;
    @MockBean // springboot starter test에 포함되어 있음.
    private PostService postService;
    @MockBean
    private PostRepository postRepository;

    @Test
    void createPost() {
    }

    @Test
    void findAllPosts() {
    }

    @Test
    void findApprovedPosts() {
    }

    @Test
    void findMemberPosts() {
    }

    @DisplayName("유저가 입력한 키워드, 그리고 클라이언트에서 전해주는 page=0++, size=8으로 게시글 제목을 검색/조회한다.")
    @Test
    @WithMockUser
    void searchPost() throws Exception {
        // given
        String keyword = "string";
        String page = "0";
        String size = "8";
        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
        info.add("keyword", keyword);
        info.add("page", page);
        info.add("size", size);

        // then
        mockMvc.perform(get("http://localhost:8080/api/v1/posts")
                                .header("accept", "application/json")
                                .params(info)
//                                .param("keyword", keyword)
//                                .param("page", page)
//                                .param("size", size)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

}
