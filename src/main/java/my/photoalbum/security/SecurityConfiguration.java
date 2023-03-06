package my.photoalbum.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) 
  	throws Exception {
    http.authorizeHttpRequests()
        .requestMatchers("/photos/create","/photos/edit/**").hasAuthority("ADMIN") 	//per creare o modificare un libro bisogna essere ADMIN
//        .requestMatchers(HttpMethod.POST, "/photos/**").hasAuthority("ADMIN")		//per fare il POST su /books (richiesto per eliminare un libro) bisogna essere ADMIN
        .requestMatchers("/photos/**").hasAuthority("ADMIN")		//per fare il POST su /books (richiesto per eliminare un libro) bisogna essere ADMIN
        .requestMatchers("/categories", "/categories/**").hasAuthority("ADMIN")		//per accedere alle categorie bisogna essere ADMIN
        .requestMatchers("/photos","/photos/**").hasAnyAuthority("USER", "ADMIN") 	//per accedere all'elenco libri (/books) o dettaglio libri (/books/**) bisogna esser USER o ADMIN
        .requestMatchers("/**").permitAll()											//chiunque può accedere alla Home se esistesse
        .requestMatchers("/guest").permitAll()											//chiunque può accedere alle pagine front
        .and().formLogin()			//abilita il supporto al form login (auto generato)
        .and().logout()			//abilita il supporto al form logout (auto generato)
    	.and().exceptionHandling()
    	.accessDeniedPage("/access-denied.html") //pagina personalizzata in caso di accesso negato
    	.and().csrf().disable();
    
    return http.build();
  }
  
  @Bean
  DatabaseUserDetailsService userDetailsService() {
    return new DatabaseUserDetailsService();
  }
  
  @Bean
  PasswordEncoder passwordEncoder() {
//	  System.out.println("USER: "+passwordEncoder().encode("pw3"));
//	  System.out.println("ADMIN: "+passwordEncoder().encode("pw43"));
	  
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  } 
  
  @Bean
  DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
 
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
 
    return authProvider;
  }  

}