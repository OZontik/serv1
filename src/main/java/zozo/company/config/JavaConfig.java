package zozo.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zozo.company.controller.PostController;
import zozo.company.repository.PostRepository;
import zozo.company.repository.PostRepositoryStubImpl;
import zozo.company.service.PostService;

@Configuration
public class JavaConfig {
    @Bean

    public PostController postController(PostService service) {
        return new PostController(service);
    }

    @Bean
    public PostService postService(PostRepository repository) {
        return new PostService(repository);
    }

    @Bean
    public PostRepository postRepository() {
        return new PostRepositoryStubImpl();
    }
}