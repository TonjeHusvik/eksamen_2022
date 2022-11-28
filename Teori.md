### Devops eksamen
I "Free" planen til GitHub er "branch protection" ikke tillat når et repository er privat. Det vil si at dere ikke kan konfigurere GitHub til å hindre push mot for eksempel main branch direkte, eller konfigurere regler for godkjenning før merge av pull request osv.
I denne oppgaven blir dere bedt om å beskrive hvordan dette kan gjøres, men dere trenger altså ikke konfigurere dette for repoet dere leverer.

# Bonusoppgave - 5 Poeng
Vi fant aldi ut av hvorfor ovnernevnte problem oppstår av og til med Maven i Cloud9. Hvis du klarer å reprodusere feilen konsekvent og kan komme med en forklaring på hvorfor dette skjer, og hva vi kan gjøre for å fikse det, gis 5 ekstra poeng.

## Del 1 DevOps-prinsipper - 20 poeng
Hva er utfordringene med dagens systemutviklingsprosess - og hvordan vil innføring av DevOps kunne være med på å løse disse? Hvilke DevOps prinsipper blir brutt?
En vanlig respons på mange feil under release av ny funksjonalitet er å gjøre det mindre hyppig, og samtidig forsøke å legge på mer kontroll og QA. Hva er problemet med dette ut ifra et DevOps perspektiv, og hva kan være en bedre tilnærming?
Teamet overleverer kode til en annen avdelng som har ansvar for drift - hva er utfordringen med dette ut ifra et DevOps perspektiv, og hvilke gevinster kan man få ved at team han ansvar for både drift- og utvikling?
Å release kode ofte kan også by på utfordringer. Beskriv hvilke- og hvordan vi kan bruke DevOps prinsipper til å redusere eller fjerne risiko ved hyppige leveraner.

## Del 2 CI - 20 poeng
Oppgave 3 - Branch protection og status sjekker - Beskriv hva sensor må gjøre for å konfigurere sin fork på en slik måte at
Ingen kan pushe kode direkte på main branch
Kode kan merges til main branch ved å lage en Pull request med minst en godkjenning
Kode kan merges til main bare når feature branchen som pull requesten er basert på, er verifisert av GitHub Actions.

Først 

## Del 3 Docker - 20 poeng
Oppgave 1  - Beskriv hva du må gjøre for å få workflow til å fungere med din DockerHub konto? Hvorfor feiler workflowen?

## Del 4 Del - Metrics med Micrometer 20 poeng


## Del 5 Del - Terraform og CloudWatch Dashboards - 20 poeng

