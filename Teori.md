#### Husk å legg til bagdes øverst før levering

# PGR301 eksamen 2022
Kandidatnummer: 1013

## Del 1 - DevOps-prinsipper
- *Hva er utfordringene med dagens systemutviklingsprosess - og hvordan vil innføring av DevOps kunne være med på å løse disse? Hvilke DevOps prinsipper blir brutt?*

Utfordringene i dag er at måten man jobber på på tvers av ?teamene? er tidkrevende, ueffektiv og gir dyrere kostnader.
Men DevOps får man en mer effektiv måte å arbeide mellom teamene på, arbeidsoppgaven er skilt mellom teamene og de kan gjøre sitt uten at noen trenger å vente på andre eller å bli forstyrret av konflikter kodemessig. 
I tillegg vil kostnadene bli lavere da man jobber mer effektiv med mer samarbeid. Man kan si av man har automatisert arbeidsprossesen.

Prinsipper som vanligvis blir brutt er at man skal release sjeldnere enn det som er normalt i dag.


- *En vanlig respons på mange feil under release av ny funksjonalitet er å gjøre det mindre hyppig, og samtidig forsøke å legge på mer kontroll og QA. Hva er problemet med dette ut ifra et DevOps perspektiv, og hva kan være en bedre tilnærming?*

En bedre tilnærming kan være å


- *Teamet overleverer kode til en annen avdelng som har ansvar for drift - hva er utfordringen med dette ut ifra et DevOps perspektiv, og hvilke gevinster kan man få ved at team han ansvar for både drift- og utvikling?*

Er den 


- *Å release kode ofte kan også by på utfordringer. Beskriv hvilke- og hvordan vi kan bruke DevOps prinsipper til å redusere eller fjerne risiko ved hyppige leveraner.*

svar


## Del - 2 CI
# FERDIG
- *Beskriv hva sensor må gjøre for å konfigurere sin fork på en slik måte at..*

    *..ingen kan pushe kode direkte på main branch*
    
    *..kode kan merges til main branch ved å lage en Pull request med minst en godkjenning*
    
    *..kode kan merges til main bare når feature branchen som pull requesten er basert på, er verifisert av GitHub Actions.*

Gå til ditt forkede repository > Settings > Branches > `add branch protection rule` > huk av følgende: (se til at default branch er satt til master/main, alt etter hva sensor har på sitt repo)

- `Require a pull request before merging`, huk også av `Require approvals` med 1 som antall som gjør at pull request må godkjennes èn gang av noen andre før merging. I tillegg må du også huke av `Require approval of the most recent push`, denne gjør at man ikke kan pushe kode direkte på main branch.

- `Require status check before merging`, i tillegg til `Require branches to be up to date before merging`. Denne gjør at Github Actions verifiserer koden og gjør at man ikke kan gjøre merge hvis koden ikke kompilerer.


## Del 3 - Docker
# FERDIG
*Beskriv med egne ord hva du må gjøre for å få workflow til å fungere med din DockerHub konto? Hvorfor feiler workflowen?* 

Jeg la merke til at det er secrets i worklflow-filen som skal inneholde brukernavn og token(passord) som man må legge inn i Github slik at feltene vår sine verdier. 
I Docker Hub blir repoet laget automatisk når docker.yml bygger. I oppgave 3 blir dette fjernet og erstattet med sende Docker image til ECR istedet.

*Beskriv deretter med egne ord hva sensor må gjøre for å få sin fork til å laste opp container image til sitt eget ECR repo.*

I AWS > ECR > Create repository > søk etter og trykk på ditt nylagde ECR repo. Oppe til høyre, trykk på knappen som heter "View push commands" > Kopier og lim inn stegene i terminalvinduet i cloud9. Når man har gjort alt så kan man pushe i cloud9 (eller ønsket IDE) og et image vil dukke opp i repoet i ECR.

> Står HVA sensor på gjøre, ikke HVORFOR. Derfor er det lite forklaring, burde man ha forklart bedre HVORFOR man vil gjøre det?


## Del 4 - Metrics med Micrometer
Ingen drøftingsoppgaver i del 4.


## Del 5 - Terraform og CloudWatch Dashboards
### FERDIG
*Forklar med egne ord. Hva er årsaken til dette problemet? Hvorfor forsøker Terraform å opprette en bucket, når den allerede eksisterer?*

Jeg fikk løst problemet ved å endre S3 bucket fra resource til data.

Filen gjør at terraform kan lage, endre og slette infrastruktur i AWS.

state, backend, provider, kjører bucket lokalt så den får ikke til å overskrive??

Her forteller vi Terraform at state-informasjon skal lagres i S3, i en Bucket som ligger i Stockholm regionen, med et filnavn du selv bestemmer ved å endre "key"

https://github.com/hashicorp/terraform-provider-aws/issues/423#issuecomment-510072042


## Alarmer
Ingen drøftingsoppgaver.


### Bonusoppgave - 5 Poeng
Vi fant aldri ut av hvorfor ovnernevnte problem oppstår av og til med Maven i Cloud9. Hvis du klarer å reprodusere feilen konsekvent og kan komme med en forklaring på hvorfor dette skjer, og hva vi kan gjøre for å fikse det, gis 5 ekstra poeng.

java.lang.Error:
Unresolved compilation problem:
The method builder() is undefined for the type Cart
at no.shoppifly.CartServiceTest.shouldRemoveCartAfterCheckout(CartServiceTest.java:13)

Dette gjorde jeg for å få problemet:

Dette gjorde jeg for å fikse problemet:

























