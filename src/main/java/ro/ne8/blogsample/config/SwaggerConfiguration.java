package ro.ne8.blogsample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final String API_BLOG_SAMPLE = "blog-sample";
    private static final String MAINTAINER = "Catalin Patrut";
    private static final String MAINTAINER_EMAIL = "cpatrut@outlook.com";

    @Bean
    public Docket api() {
        return
                new Docket(DocumentationType.SWAGGER_2)
                        .groupName(SwaggerConfiguration.API_BLOG_SAMPLE)
                        .select()
                        .apis(RequestHandlerSelectors.basePackage(
                                "ro.ne8.blogsample.controllers"
                        ))
                        .paths(PathSelectors.any())
                        .build()
                        .apiInfo(this.apiInfo("Blog API", "Blog rest endpoints"));
    }

    private ApiInfo apiInfo(final String title, final String description) {
        final Contact contact = new Contact(SwaggerConfiguration.MAINTAINER, "",
                SwaggerConfiguration.MAINTAINER_EMAIL);
        return new ApiInfo(title, description, "1.0", "terms of controller url",
                contact, "license", "license url");
    }
}
