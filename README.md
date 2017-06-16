# Predmetni projekat iz ISA i MRS (Internet softverskih arhitektura i Metodologije razvoja softvera)

#Clanovi tima:
	1. Becejac Milana, SW10/2014
	2. Rajanovic Sandra, SW17/2014
	3. Zdravkovic Tatjana, SW38/2014

#Tema projekta:
Projektni zadatak predstavlja aplikaciju koja omogucava korisnicima da rezervisu stolove u zeljenom restoranu, za odabrani termin(datum i vreme) i zeljeni vremenski period izrazen u satima. Aplikacija omogucava rezervacije u razlicitim restoranima koji su registrovani u okviru sistema.

#Koriscene tehnologije:
	1. Java + Spring Boot
	2. AngularJS
	3. REST servisi
	4. MySql baza podataka
	5. Bootstrap


#Uputstvo za pokretanje projekta
	1. Clone/Download projekat sa gitHub-a sa 			https://github.com/becejacM/ISAMRSProject
	2. U Eclipse-u importujte projekat: 
		- desni klik
		- import...
  		- Existing Maeven Projects
	3. Build projekat:
		- desni klik -> Run as -> Maven clean
		- desni klik -> Run as -> Maven build (u goals 	ukucajte package)
	4. Pokretanje projekta:
		- desni klik -> Run as -> Spring Boot App

U fajlu aplication.properties se nalaze podesavanja za aplikaciju:
	- server.port = 8082
	- spring.datasource.url=jdbc:mysql://localhost:3306/mrsisa_project?verifyServerCertificate=false&useSSL=false&requireSSL=false
	- spring.datasource.username=root
	- spring.datasource.password=sandra

Da bi se aplikacija pokrenula potrebno je skinuti MySql bazu podataka sa sajta: https://dev.mysql.com/, takodje potrebno je skinuti MySql connector sa https://dev.mysql.com/downloads/connector/j/.

Kada se baza instalira potrebno je kreirati semu baze podataka u MySql Workbench-u i rucno kreirati tabele sa potrebnim podacima ili jednostavno pokrenuti skriptu shema.sql koja kreira tabele i popunjava ih podacima.

Kada je baza podesena i povezana sa projektom i projekat je pokrenut, treba otici na Google Chrome ili neki drugi pretrazivac i ukucati u polje za adresu : http://localhost:8082. Tada, ako je sve u redu otvorice se stranica za logovanje na sajt.
