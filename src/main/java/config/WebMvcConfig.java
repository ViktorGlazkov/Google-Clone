package config;

import core.indexer.IndexerService;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.IOException;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "core")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    IndexerService indexerService;

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public IndexSearcher indexSearcher() throws IOException {
        return new IndexSearcher(indexerService.readIndex());
    }

    @Bean
    public MultiFieldQueryParser queryParser() {
        return new MultiFieldQueryParser(
                new String[]{"body", "title"},
                new RussianAnalyzer());
    }
}
