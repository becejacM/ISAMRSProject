package rs.team15.aspects;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import rs.team15.model.Guest;

/*
 * Aspekt se registruje kao komponenta anotacijom @Component kako bi
 * usla u nadleznost Spring kontejnera koji ce voditi racuna o ovom beanu.
 */
@Component
@Aspect
public class SendEmailAspect {

	/*Aspect za slanje email-a kada se registruje korisnik. Aspekt se poziva nakon upisa korisnika u bazu. 
	 * Email se salje sa email-a iz aplication.properties : ricardmiki@gmail.com, a sifra je : milanamiki*/
	private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailAspect.class);
	
	@Autowired
	private JavaMailSender javaMailSender;

	/*
	 * Koriscenje klase za ocitavanje vrednosti iz application.properties fajla
	 */
	@Autowired
	private Environment env;
	
	/*
	 * Najznacajniji termini koji se koriste prilikom implementacije aspekata u Springu su:
	 * 	1. Advice - predstavlja akciju koja se sprovodi od strane aspekta nad odgovarajucim join point-om
	 * 				(opcije su Before, AfterReturning, AfterThrowing, After, Around)
	 * 	2. Join point - tacka koja predstavlja originalnu metodu koja se izvrsava
	 * 	3. Pointcut - predikat koji odgovara join point-u, specificira izraz koji se dodaje u Advice i specificira
	 * 				koja se metoda izvrsava kada se aktivira aspekt metoda (izraz se moze pisati i pod zasebnom anotacijom @Pointcut)
	 * Vise informacija na http://docs.spring.io/spring/docs/current/spring-framework-reference/html/aop.html
	 */
	//@After(value = "execution(* rs.team15.service.GuestService.create(..) && args(guest,..))")
	
	@After("execution(* rs.team15.service.GuestService.create(..)) && args(guest,..)")
	@Async
	public void sendConfirmationEmail(JoinPoint joinPoint, Guest guest) throws Throwable {
		LOGGER.info("@Around: Pre poziva metode - " + joinPoint.getTarget().getClass().getName() + " - " + new Date());
		System.out.println("Slanje emaila...");

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(guest.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Registration confirm ");
		mail.setText("Hello " + guest.getFirstName() + "\n Click and verify your email : http://localhost:8082/api/user/confirm/"+guest.getEmail());
		javaMailSender.send(mail);
		
		//System.out.println("Objekat vracen iz metode: " + result.getFirstName());
		LOGGER.info("@Around: Posle poziva metode - " + joinPoint.getTarget().getClass().getName() + " - " + new Date());
	}
}
