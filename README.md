[![CI pipeline](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/ci.yml/badge.svg)](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/ci.yml)
[![Docker build & publish](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/docker.yml/badge.svg)](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/docker.yml)
[![Terraform CloudWatch](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/cloudwatch_dashboard.yml/badge.svg)](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/cloudwatch_dashboard.yml)

# PGR301 eksamen 2022
Kandidatnummer: 1013

## Del 1 - DevOps-prinsipper
- *Hva er utfordringene med dagens systemutviklingsprosess - og hvordan vil innføring av DevOps kunne være med på å løse disse? Hvilke DevOps prinsipper blir brutt?*

**Utfordringer:**
1. Ingen logging/statistikk
2. De deployer sjeldent
3. Ansatte flere testere til å skrive manuelle tester
4. De ansatte pusher med feil i koden
5. De deployer med feil og må rulle tilbake
6. Leveranse mellom teamene i form av zip-fil
7. Konsulentene gir opp hvis de ikke får til en oppgave og begynner på andre oppgaver

**Løsninger:** 
1. Kort vei til kundebase som gir feedback.
2. Deploye oftere slik at brukere slipper å få forsinket funksjonalitet
3. Begynne med og fokusere på automatisert testing. 
4. Beskytte main branch slik at man ikke kan pushe direkte, men via pull request som å godkjennes.
5. Automatisere pipelines/workflow slik at branches ikke kan merges med main om byggingen feiler. Github Actions eller AWS sørger for sikkerhet og sårbarhet ved scanning og brukes mye innenfor devOps for å få mer stabilt og robust prosjekt.
6. Dette kan bli automatisert med github og aws. Å manuelt dele filer på tvers av teamene tar lengre tid og fører til waste.
7. Må lære av egne feil og selskapet må begynne å organisere kompetanse og implementere devOps.


**Prinsipper som blir brutt:**
- Flyt, siden mye av prosjektet ikke er automatisert og at de ikke har behersket bruken av pipelines.
- Feedback, da de verken har overvåkning, logging og metrics.
- Kontinuerlig forbedring, rett og slett fordi de ikke gjør et forsøk på å forbedre seg. Lærer ikke av egne eller hverandres feil og ingen tenker på å nedbetale teknisk gjeld eller waste.

De bryter altså alle devOps prinsippene.

<br />
<br />
- *En vanlig respons på mange feil under release av ny funksjonalitet er å gjøre det mindre hyppig, og samtidig forsøke å legge på mer kontroll og QA(Quality assurance). 
Hva er problemet med dette ut ifra et DevOps perspektiv, og hva kan være en bedre tilnærming?*

**Problem:**
1. Funksjonaliteter blir forsinket
2. Manuell QA

**Bedre tilnærming:** 
1. Release oftere med mer fokus på automatisering av tester, workflow og branch protection for å forhindre feil under release.
2. Automatisere testing av kode, slik at man vet at det som kommer gjennom er godkjent

<br />
<br />
- *Teamet overleverer kode til en annen avdeling som har ansvar for drift - hva er utfordringen med dette ut ifra et DevOps perspektiv, 
og hvilke gevinster kan man få ved at team han ansvar for både drift- og utvikling?*

**Utfordring:**
1. Hvis drifts-avdeling er på ferie så blir prosjektet satt på vent.
2. Utviklingsteamet gjør ferdig utviklingen og er "ferdig".
3. Drift må vente på utvikling-team ved f.eks. leveranse av zip-fil, 
 
**Gevinster:**
1. Dette er en typisk flaskehals så gevinsten vil være effektivitet og mindre waste. Det vil også eliminere muligheten til å vente på andre og enklere å finne problemer i prod. 
2. Om utviklerne også har ansvar for drift vil de aldri bli "ferdig" med prosjektet og utviklerne vil få mer eierskap og ansvar overfor koden slik at den blir oversiktlig og mindre "quick fixes".
3. Automatisere prosessen.

Det vil også gjøre at teamet jobber mer effektivt og raskere da de slipper å kommunisere og vente på andre avdelinger.
<br />
<br />

- *Å release kode ofte kan også by på utfordringer. Beskriv hvilke- og hvordan vi kan bruke DevOps prinsipper til å redusere eller fjerne risiko ved hyppige leveranser.*

- Kontinuerlig leveranse gir mindre oppgaver til hver utvikler som blir mer presise og ivaretatt. 
- Mer synlig hva man gjør siden man jobber med en liten bit og slipper å context switche så mye. 
- Enklere å se hva andre jobber med og lettere for synlig arbeid. 
- Mulighet for å gjøre seg ferdig med oppgavene og man begrenser WIP(work in progress).
- Hyppige leveranser betyr mindre tid til manuell kvalitetssikring og derfor vil det lurt å ha automatisk kvalitetssikring som automatiske tester og QA. 
- Man får mindre tid til store oppgaver så det bør gis mindre oppgaver for å rekke release, biprodukt av det er at det er lettere for andre å sette seg inn i oppgaven og koden til andre når der ikke er tusenvis av linjer.

<br />
<br />

## Del - 2 CI
*Beskriv hva sensor må gjøre for å konfigurere sin fork på en slik måte at*

- *Ingen kan pushe kode direkte på main branch*
    
- *Kode kan merges til main branch ved å lage en Pull request med minst en godkjenning*
    
- *Kode kan merges til main bare når feature branchen som pull requesten er basert på, er verifisert av GitHub Actions.*

Gå til ditt forkede repository > Settings > Branches > `add branch protection rule` > huk av følgende: (se til at default branch er satt til master/main, alt etter hva sensor har på sitt repo)

- `Require a pull request before merging`, huk også av `Require approvals` med 1 som antall som gjør at pull request må godkjennes èn gang av noen andre før merging. I tillegg må du også huke av `Require approval of the most recent push`, denne gjør at man ikke kan pushe kode direkte på main branch. Huk også av Ìnclude adminstrators`slik at det gjelder alle.

- `Require status check before merging`, i tillegg til `Require branches to be up to date before merging`. Denne gjør at Github Actions verifiserer koden og gjør at man ikke kan gjøre merge hvis koden ikke kompilerer.

<br />
<br />

## Del 3 - Docker
- *Beskriv med egne ord hva du må gjøre for å få workflow til å fungere med din DockerHub konto? Hvorfor feiler workflowen?* 

Legge inn `DOCKER_HUB_USERNAME` og `DOCKER_HUB_PASSWORD` som samsvarer med brukernavn og passord til Docker Hub som values inne på secrets på GitHub actions, Settings > Secrets > New repository secret.
(Jeg har gjort dette, men i neste oppgave blir dette fjernet i docker.yml og erstattet med sende Docker image til ECR istedet).

- *Beskriv deretter med egne ord hva sensor må gjøre for å få sin fork til å laste opp container image til sitt eget ECR repo.*

Sensor må legge inn `AWS_ACCESS_KEY_ID` og `AWS_SECRET_ACCESS_KEY` i secrets i Github Actions. Hvis sensor ikke har nøkler må sensor gå til AWS > IAM > (Quick links -shortcut til høyre) My security credentials > Create access key og fyll nøklene inn i GitHub actions. Settings > Secrets > New repository secret, access key skal være verdien i `AWS_ACCESS_KEY_ID` og secret access key i `AWS_SECRET_ACCESS_KEY`.

Så må sensor endre ECR kommandoene i docker.yml, der det står mitt kandidatnr må endres til sensor's eget repo. Det er uthevet på bildet under hvor sensor må endre til sitt repo. Hvis sensor ikke har ECR repo fra før kan dette lages ved å gå til AWS > ECR > Create repository > navngi repo. 
<img width="837" alt="image" src="https://user-images.githubusercontent.com/46429425/206769847-f95f722e-eaa4-462f-8c7c-cdcf8ee40471.png">
Hos meg bruker jeg 1013 begge steder, men grønn er Docker image og lilla er navnet på repoet i ECR.

<br />
<br />

## Del 4 - Metrics med Micrometer
Ingen drøftingsoppgaver i del 4.

<br />
<br />

## Del 5 - Terraform og CloudWatch Dashboards
*Konsulentene i Gaffel consulting hadde ambisiøse planer om å få Terraform-koden i dette repoet til å kjøre i GitHub Actions. Workflowen kjørte bra første gang, men nå feiler den hver gang, og klager over at en bucket med samme navn allerede eksisterer. Shopifly har tenkt på bruke denne bucketen til data-analyse.*
*Forklar med egne ord. Hva er årsaken til dette problemet? Hvorfor forsøker Terraform å opprette en bucket, når den allerede eksisterer?*

Når terraform-koden kjører første gang, skal det bli opprettet en state-fil og en s3-bucket. Terraform.tfstate inneholder detaljer om infrastrukturen på aws basert på terraform-koden. Hver gang worklowen kjører vil terraform sammenligne endringer i terraform-koden med den eksisterende state-filen så den vet om det har blitt gjort noen endringer. 

Det som har skjedd er at en statefil med info mangler hos alle og ingen vet hvorfor. Man har ikke state-filen da den ble opprettet første gang så den vet ikke hvilken infrastruktur som finnes. 
Siden filen ikke eksisterer tror terraform at det ikke finnes noen infrastruktur og prøver å lage en fra bunn av hver gang workflow kjøres. Dette vil ikke fungere da det har blitt opprettet en bucket og det er derfor de får feilmeldingen om at den allerede eksisterer.
Informasjonen om at bucketen har eksistert ligger i state-filen som ingen har. Dette kan løses ved å slette s3-bucket og at en på teamet kjører workflow på nytt slik at en ny state-fil opprettes som deles med alle.

Jeg har tolket oppgaven slik at siden bucketen kun skal brukes for data-analyse så trenger den ikke å være en resource siden man ikke skal lage noe av den. Derfor fikk jeg løst problemet ved å endre s3-bucket fra resource til data i databucket.tf.

Ellers så kan man også løse problemet ved å importere ressurser til terraform med denne kommandoen: `terraform import aws_s3_bucket.analyticsbucket analytics-1013`, siden bucket allerede er finnes fra før.

<br />

### Alarmer
Ingen drøftingsoppgaver i alarmer.

<br />
<br />

## Bonusoppgave - 5 Poeng 
*java.lang.Error:
Unresolved compilation problem:
The method builder() is undefined for the type Cart at no.shoppifly.CartServiceTest.shouldRemoveCartAfterCheckout(CartServiceTest.java:13)*

*Vi fant aldri ut av hvorfor ovnernevnte problem oppstår av og til med Maven i Cloud9. Hvis du klarer å reprodusere feilen konsekvent og kan komme med en forklaring på hvorfor dette skjer, og hva vi kan gjøre for å fikse det, gis 5 ekstra poeng.*

Fikk desverre ikke til å trigge feilmeldingen, men jeg kan tenke meg at det har noe med feil versjon av lombok i pom.xml å gjøre. Jeg satte en eldre versjon der og fikk en lignende feil, bare at den reagerte på feil metoder (gettere og settere) men ikke Builder. 
Ellers mistenker jeg også at det kan ha noe med jar-filen til Lombok å gjøre men den finner jeg lokalt og ikke i Cloud9.


## Kilder
[S3 Bucket already excist](https://github.com/hashicorp/terraform-provider-aws/issues/423#issuecomment-510072042)

