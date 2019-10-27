package widgets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import widgets.Storage.IStorage;
import widgets.Storage.RamStorage;

@Configuration
public class ApplicationConfiguration 
{	
    @Bean
    public IStorage getStorage() 
    {
        return new RamStorage();
    }
}
