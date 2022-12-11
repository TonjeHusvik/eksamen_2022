[![CI pipeline](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/ci.yml/badge.svg)](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/ci.yml)
[![Docker build & publish](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/docker.yml/badge.svg)](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/docker.yml)
[![Terraform CloudWatch](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/cloudwatch_dashboard.yml/badge.svg)](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/cloudwatch_dashboard.yml)

# PGR301 eksamen 2022
Kandidatnummer: 1013

## Del 1 - DevOps-prinsipper
- *Hva er utfordringene med dagens systemutviklingsprosess - og hvordan vil innføring av DevOps kunne være med på å løse disse? Hvilke DevOps prinsipper blir brutt?*

Innføring i DeovOps hos Shopifly og Gaffel consulting vil føre til mer effektivitet, mulighet for å oppdage  problemer tidligere, og lavere terskel for å lære av egne feil. I tillegg er det kort vei til marked og stor sannsynlighet for utvikling basert på feedback fra brukere.
Prinsipper som Gaffel consulting og Shopifly bryter er at de deployer kode altfor sjeldent, dette gjør at brukere får forsinket funksjonalitet og de kunne blitt kvitt dette med å deploye oftere. Konsulentene deployer også selv om koden har feilet, som gjør at de må rulle tilbake som kan føre til nedetid som tar unødvendig tid og ressurser. 

De ansetter flere testere som er greit siden kvalitetssikring er viktig og det er fortsatt behov for manuelle tester, men man kan også bruke automatiserte testere og sikre seg at kode kjører og tester passerer med workflows.
I versjonert byggeprosess som github actions sørger for sikkerhet og sårbarheter ved scanning, dette brukes mye innen DevOps for stabil og robust kode. Her sender teamene zip-filer til hverandre som lastes ned manuelt istedenfor github actions eller elastic beanstalk der det gjøres automatisk som tar lengre tid og fører til waste, så her går det ann å automatisere prosessen. 


<br />

- *En vanlig respons på mange feil under release av ny funksjonalitet er å gjøre det mindre hyppig, og samtidig forsøke å legge på mer kontroll og QA(Quality assurance). 
Hva er problemet med dette ut ifra et DevOps perspektiv, og hva kan være en bedre tilnærming?*

Kvalitetssikring = BRA! En bedre tilnærming kan være å fokusere på bedre testing av funksjonalitet. I tillegg når man har mye release burde man også ha automatisere kvalitetssikrings-prosessen. I følge DevOps-prinsipper skal man ha sjeldnere overleveringer men å gjøre det altfor sjeldent kan gjøre at nye funksjonaliteter blir forsinket. 

<br />

- *Teamet overleverer kode til en annen avdeling som har ansvar for drift - hva er utfordringen med dette ut ifra et DevOps perspektiv, 
og hvilke gevinster kan man få ved at team han ansvar for både drift- og utvikling?*

Det kan hende den andre avdelingen må vente på koden som da vil resultere i waste. Her burde man også automatisere prosessen da avdelingene overleverer leveransen med zip-fil. At et team har ansvar for utvikling og drift gjør det enklere å finne problemer i prod, samt å bygge services raskere.

<br />

- *Å release kode ofte kan også by på utfordringer. Beskriv hvilke- og hvordan vi kan bruke DevOps prinsipper til å redusere eller fjerne risiko ved hyppige leveranser.*

Man prøver å unngå å release ofte grunnet risiko for nedetid. Hyppige leveranser betyr mindre tid til manuell kvalitetssikring og derfor vil det lurt å ha automatisk kvalitetssikring som automatiske tester og QA. Man vil gjerne ha hyppige deployinger, dette kan gjerne skje ofte og er automatisert. 
Hvis man er avhengig av andre i prosessen vil dette være waste og all ventetid på andre er unødvendig og ueffektivt. 

Teamet kan heller fokusere på mer bruk av github actions og byggejobber for mer sikkerhet og for å gjøre arbeidet mer synlig. 

<br />
<br />

## Del - 2 CI
*Beskriv hva sensor må gjøre for å konfigurere sin fork på en slik måte at*

- *ingen kan pushe kode direkte på main branch*
    
- *kode kan merges til main branch ved å lage en Pull request med minst en godkjenning*
    
- *kode kan merges til main bare når feature branchen som pull requesten er basert på, er verifisert av GitHub Actions.*

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
Hos meg bruker jeg samme navn(kandidatnr) på begge to, men grønn er Docker image og lilla er navnet på repoet i ECR.

<br />
<br />

## Del 4 - Metrics med Micrometer
Ingen drøftingsoppgaver i del 4.

<br />
<br />

## Del 5 - Terraform og CloudWatch Dashboards
*Konsulentene i Gaffel consulting hadde ambisiøse planer om å få Terraform-koden i dette repoet til å kjøre i GitHub Actions. Workflowen kjørte bra første gang, men nå feiler den hver gang, og klager over at en bucket med samme navn allerede eksisterer. Shopifly har tenkt på bruke denne bucketen til data-analyse.*
- *Forklar med egne ord. Hva er årsaken til dette problemet? Hvorfor forsøker Terraform å opprette en bucket, når den allerede eksisterer?*

Når cloudwatch_dashboard.yml kjører første gang skal det bli opprettet en state-fil og en s3-bucket. Terraform.tfstate inneholder detaljer om infrastrukturen basert på terraform-koden og hver gang worklowen kjører vil terraform sammenligne endringer i terraform-koden med den eksisterende state-filen slik at den vet hva som har blitt endret. 

Det som mest sannsynlig har skjedd her er at siden kommandoene kjøres via GitHub actions er det ingen får opprettet statefil lokalt hos seg. Og hver gang workflow kjøres tror terraform at det ikke finnes noen infrastruktur og prøver å lage en fra bunn av, som ikke vil fungere da det har blitt opprettet en bucket og det er derfor de får feilmeldingen om at den allerede eksisterer. Informasjonen at bucketen har eksistert ligger i state-filen som ingen hadde. Dette kan løses ved å slette s3-bucket og at en på teamet kjører workflow på nytt slik at en ny state-fil opprettes som deles med alle.

Jeg tolket det slik at siden den kun skal brukes med data-analyse så trenger den ikke å være en resource, siden man ikke skal lage noe av den, så jeg fikk løst problemet ved å endre s3-bucket fra resource til data i databucket.tf.

Ellers så kan man også løse problemet ved å importere ressurser til terraform med denne kommanboen: `terraform import aws_s3_bucket.analyticsbucket analytics-1013`, siden bucket allerede er laget fra før av.

<br />

### Alarmer
Ingen drøftingsoppgaver i alarmer.

<br />
<br />

## Bonusoppgave - 5 Poeng 
*java.lang.Error:
Unresolved compilation problem:
The method builder() is undefined for the type Cart at no.shoppifly.CartServiceTest.shouldRemoveCartAfterCheckout(CartServiceTest.java:13)*

- *Vi fant aldri ut av hvorfor ovnernevnte problem oppstår av og til med Maven i Cloud9. Hvis du klarer å reprodusere feilen konsekvent og kan komme med en forklaring på hvorfor dette skjer, og hva vi kan gjøre for å fikse det, gis 5 ekstra poeng.*

Fikk ikke til å trigge feilmeldingen, men om jeg kan tenke meg at det har noe med feil versjon av lombok i pom.xml å gjøre. Jeg satte en eldre versjon der og fikk en lignende feil, bare at den reagerte på feil metoder (gettere og settere) ikke Builder. 
Ellers mistenker jeg også at det kan ha noe med jar-filen til Lombok å gjøre men den finner jeg lokalt og ikke i Cloud9.


## Kilder
[S3 Bucket already excist](https://github.com/hashicorp/terraform-provider-aws/issues/423#issuecomment-510072042)

