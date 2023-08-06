package likelion.underdog.songgotmae.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    /*
    * springfox의 Docket -> springdoc의 GroupOpenApi
    **/
    @Bean
    public OpenAPI sgmaeAPI() {
        return new OpenAPI()
                .info(new Info().title("송곳매 API")
                        .description("항공대 청원서비스 송곳매 API specification")
                        .version("v0.0.1")
                        .license(new License().name("LikelionKau 깃허브 주소").url("https://github.com/LikelionKau")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("songgotmae-public")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("songgotmae-admin")
                .pathsToMatch("/admin/**")
//                .addOpenApiMethodFilter(method -> method.isAnnotationPresent(Admin.class))
                .build();
    }


}
