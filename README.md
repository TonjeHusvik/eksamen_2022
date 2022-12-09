[![CI pipeline](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/ci.yml/badge.svg)](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/ci.yml)
[![Docker build & publish](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/docker.yml/badge.svg)](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/docker.yml)
[![Terraform CloudWatch](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/cloudwatch_dashboard.yml/badge.svg)](https://github.com/TonjeHusvik/eksamen_2022/actions/workflows/cloudwatch_dashboard.yml)

# PGR301 eksamen 2022
Kandidatnummer: 1013

## Del 1 - DevOps-prinsipper
Jeg er usikker om oppgavene spør etter drøftinger rundt devops-prinsipper generelt eller om det er basert på "Shopifly" og Gaffel consulting vi har fått høre litt om i oppgaven. Jeg går ut fra at første oppgave handler om Gaffel consulting men skriver litt generelt om DevOps på den likevel.


- *Hva er utfordringene med dagens systemutviklingsprosess - og hvordan vil innføring av DevOps kunne være med på å løse disse? Hvilke DevOps prinsipper blir brutt?*

DevOps prinsipper handler om å ha en bra arbeidsflyt, kontinuerlig forbedring og å jobbe med en ting av gangen for å oppdage problemer tidlig, 
identifisere flaskehalser og lavere terskel for å lære av egne feil. I tillegg så er det kort vei til marked og stor sannsynlighet for utvikling basert på feedback fra brukere.
Med DevOps får man en mer effektiv måte å arbeide på da man jobber mer effektiv med mer samarbeid, større stabilitet og redusert risiko, 
her vil også kostnadene bli lavere fordi man slipper å vente på andre//å være avhengig av andre teams.
I versjonert byggeprosess som github actions som sørger for sikkerhet og sårbarheter ved scanning, dette brukes mye innen DevOps for stabil og robust kode.

Utfordringene i dag er at måten man jobber på på tvers av teamene er tidkrevende, ueffektiv og gir dyrere kostnader. 
En del bedrifter har idag også monolittisk arkitektur som betyr at prosjektet kan være produsert av et team og driftet av et annet.
Dette bidrar til mer økte kostnader i form av waste og er generelt en mer knotete måte å jobbe på, i tillegg til ueffektivitet og at det er tidkrevende.
Store bedrifter kan fremdeles ha manuelle tester og et eget team med testutviklere, slik at de har blitt avhengige av dette istedenfor å automatisere tester.

Prinsipper som blir brutt av Gaffel consulting er
- de deployer altfor sjeldent, 4 deployinger årlig(!!). Best case scenario er man man deployer flere ganger om dagen.
- de ansette flere testere er greit for kvalitetssikring er viktig og det er fortsatt behov for manuelle tester, men man kan også bruke automatiserte testere og sikre seg at kode kjører og tester passerer med workflows.
- grøsser over at de deployer med feilet kode, men kanskje fordel hvis de driver på med Test Driven Development.
- å rulle tilbake til forrige versjon kan være problematisk i forhold til alle som har pushet koden sin, og nå mister koden fordi man må rollbacke. Det er synd at de opplevde forsinket funksjonalitet grunnet at koden feilet og de måtte rulle tilbake.
- bruker zip istedenfor github actions/github? Tar tid. Kroklete måte å gjøre det på når det kan automatiseres

Konklusjon: junior-konsulentene i Gaffel consulting må bli flinkere til å bruke google.


- *En vanlig respons på mange feil under release av ny funksjonalitet er å gjøre det mindre hyppig, og samtidig forsøke å legge på mer kontroll og QA(Quality assurance). 
Hva er problemet med dette ut ifra et DevOps perspektiv, og hva kan være en bedre tilnærming?*

Kvalitetssikring = BRA! En bedre tilnærming kan være å ta det litt roligere, ikke svelge over for mye eller ta for store oppgaver. 
Gjør arbeidet synlig for resten av teamet slik at teamet får bedre kontroll.
I følge DevOps-prinsipper skal man release sjeldent med færrest mulig overleveringer men å gjøre det altfor sjeldent kan gjøre at nye funksjonaliteter blirvforsinket. 


- *Teamet overleverer kode til en annen avdelng som har ansvar for drift - hva er utfordringen med dette ut ifra et DevOps perspektiv, 
og hvilke gevinster kan man få ved at team han ansvar for både drift- og utvikling?*

Teamet med driftansvar har mest sannsynlig ventet på leveransen som gir mindre arbeidsflyt, i tillegg så må de sette seg inn i koden. 
Man gjør ting bedre når man er bevisst på at man skal fortsette med prosjektet kontinuerlig, istedenfor å gjøre raske løsninger for man vet at man aldri skal røre koden igjen etter overlevering. 
En utvikler som skriver kode bryr seg ikke om drift og vedlikehold av infrastruktur.


- *Å release kode ofte kan også by på utfordringer. Beskriv hvilke- og hvordan vi kan bruke DevOps prinsipper til å redusere eller fjerne risiko ved hyppige leveranser.*

Hyppige leveranser == mindre tid til kvalitetssikring sikkerhet og muligens oppleve mer/oftere feil. Man vil gjerne ha hyppige deployinger, dette kan gjerne skje flere ganger om dagen og er automatisert. 
Derimot er det sjeldent man releaser kode og man vil ha færrest mulig overleveringer grunnet større risiko for feil, ustabilitet og mangler.

Teamet kan heller prøve å jobbe mer med koden, mer bruk av github actions og fokus på byggejobber for mer sikkerhet og robust kode. 
Det er en ide å ha kontroll over hva andre gjør, altså arbeid mer synlig, med kanban-board for eksempel.


## Del - 2 CI
- *Beskriv hva sensor må gjøre for å konfigurere sin fork på en slik måte at..*

    *..ingen kan pushe kode direkte på main branch*
    
    *..kode kan merges til main branch ved å lage en Pull request med minst en godkjenning*
    
    *..kode kan merges til main bare når feature branchen som pull requesten er basert på, er verifisert av GitHub Actions.*

Gå til ditt forkede repository > Settings > Branches > `add branch protection rule` > huk av følgende: (se til at default branch er satt til master/main, alt etter hva sensor har på sitt repo)

- `Require a pull request before merging`, huk også av `Require approvals` med 1 som antall som gjør at pull request må godkjennes èn gang av noen andre før merging. I tillegg må du også huke av `Require approval of the most recent push`, denne gjør at man ikke kan pushe kode direkte på main branch.

- `Require status check before merging`, i tillegg til `Require branches to be up to date before merging`. Denne gjør at Github Actions verifiserer koden og gjør at man ikke kan gjøre merge hvis koden ikke kompilerer.


## Del 3 - Docker
- *Beskriv med egne ord hva du må gjøre for å få workflow til å fungere med din DockerHub konto? Hvorfor feiler workflowen?* 

Jeg la merke til at det er secrets i worklflow-filen som skal inneholde brukernavn og token(passord) som man må legge inn i Github slik at feltene vår sine verdier. 
I Docker Hub blir repoet laget automatisk når docker.yml bygger. I oppgave 3 blir dette fjernet og erstattet med sende Docker image til ECR istedet.

- *Beskriv deretter med egne ord hva sensor må gjøre for å få sin fork til å laste opp container image til sitt eget ECR repo.*

I AWS > ECR > Create repository > søk etter og trykk på ditt nylagde ECR repo. Oppe til høyre, trykk på knappen som heter "View push commands" > Kopier og lim inn stegene i terminalvinduet i cloud9. Når man har gjort alt så kan man pushe i cloud9 (eller ønsket IDE) og et image vil dukke opp i repoet i ECR.

> Står HVA sensor på gjøre, ikke HVORFOR. Derfor er det lite forklaring, burde man ha forklart bedre HVORFOR man vil gjøre det?


## Del 4 - Metrics med Micrometer
Ingen drøftingsoppgaver i del 4.


## Del 5 - Terraform og CloudWatch Dashboards
- *Forklar med egne ord. Hva er årsaken til dette problemet? Hvorfor forsøker Terraform å opprette en bucket, når den allerede eksisterer?*

Feilmeldingen sier at jeg allerede har en eksisterende s3-bucket med dette navnet, det kan være fordi state-filen til bucketen ikke oppdaterer seg automatisk(??til å eksistere??).
Jeg fikk løst problemet ved å endre s3-bucket fra resource til data i databucket.tf, siden den  kun skal brukes med data-analyse så trenger den ikke å være en resource, siden man ikke skal lage noe av den.

Ellers så kan man også løse problemet ved å importere ressurser til terraform med denne kommanboen: `terraform import aws_s3_bucket.analyticsbucket analytics-1013`, siden bucket allerede er laget fra før av.


## Alarmer
Ingen drøftingsoppgaver i alarmer.


### Bonusoppgave - 5 Poeng 
- *Vi fant aldri ut av hvorfor ovnernevnte problem oppstår av og til med Maven i Cloud9. Hvis du klarer å reprodusere feilen konsekvent og kan komme med en forklaring på hvorfor dette skjer, og hva vi kan gjøre for å fikse det, gis 5 ekstra poeng.*

*java.lang.Error:
Unresolved compilation problem:
The method builder() is undefined for the type Cart
at no.shoppifly.CartServiceTest.shouldRemoveCartAfterCheckout(CartServiceTest.java:13)*


Fikk ikke til å trigge feilmeldingen, men om jeg skulle gjettet tror jeg at det har noe med feil versjon av lombok i pom.xml å gjøre. Jeg satte en eldre versjon der og fikk en lignende feil, bare at den reagerte på feil metoder (gettere og settere) ikke Builder.


## Kilder
[S3 Bucket already excist](https://github.com/hashicorp/terraform-provider-aws/issues/423#issuecomment-510072042)

