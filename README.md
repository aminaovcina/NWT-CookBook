# NWT-CookBook application

-Napomene za userpreferences servis:
  -testovi se trebaju pokrenuti prije bilo kakvog dodatnog dodavanja instanci u bazu podataka (napravljeni su za instance dodane u main-u)
  
-RecipeService: testovi rade na osnovu trenutnog stanja u bazi, bez dodavanja novih instanci za get metode, dodavanjem ili brisanjem instanci neki testovi za get neće biti vise validni
  port: 8080
  
 
 -Userservice: 
  - Pokretanje aplikacije: pokrecu se u posebnom VSC-u aplikacija eurekaservice, recipeservice i userservice. Najprije je potrebno promijeniti port za bazu (ukoliko ste pokrenuli i userpreferences servis) koja je u userpreferences service na 3308, treba promijeniti na 3306. Baza se pokrece preko xampp-a, zato je port 3306! Kada se pokrene potrebno je kreirati baze i za userservice i za recipeservice (ovo vrijedi samo ukoliko se zeli provjeriti komunikacija izmedju userservice i recipe, bez pokretanja userpreferences servisa). kada se aplikacija pokrene, automatski se kreiraju tabele u bazama. 
  Baza userservice je user_db, dok je baza recipeservice recipe_db2.
  - Za komunikaciju izmedju userservica i recipeservisa potrebno je unijeti isti id usera u bazi user_db, i u bazi recipe_db2 id accounta, da bi za tog usera vratili recepte.
  - Sto se tice testova, oni koji padaju, a to su post i delete, padaju jer je email adresa i username unique (ovo sam vec objasnila u kodu pod komentarima!), jer kada se prvi put pokrene i postavi se u bazu neki user ili account, drugim pokretanjem ce pasti jer se insertuje mail ili username vec postojeci! Za delete je slucaj kada se prvi put obrise, a drugi put nema tog usera ili accounta, jer je obrisan!
  
  

