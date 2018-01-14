# Webshop

In dit project wordt er een webshop gemaakt in Angular 4 en Dropwizard

# Hsleiden

Dit project is in opdracht van Hsleiden. Voor de module IPRWC moeten we een webshop maken. 
Deze webshop zal gaan over het verkopen van Marvel Comics.

Let op: Deze webshop is niet echt en is alleen gemaakt voor leerdoeleinden


# Uri's  

    PUT     /api/account/edit       Aanpassen account gegevens opslaan
    GET     /api/account/me         Ingelogt account ophalen
    POST    /api/account/register   Nieuw account aanmaken
    POST    /api/login              Inloggen
    POST    /api/order/create       Nieuwe order maken
    GET     /api/order/history      Order history opvragen
    GET     /api/product/list/{type} Producten in de shop opvrageb (type kan zijn Film, Merchandise of Stripboek)
    GET     /api/product/{ids}      Haal een aantal producten op volgens format id-id-id, dat streepje is verplicht
    
    ADMIN ONLY
    GET     /api/account/all            Haalt alle accounts op (Zonder wachtwoord)
    DELETE  /api/account/delete/{id}    Deletes 1 account
    GET     /api/product/all            Haalt alle producten op
    POST    /api/product/create         Maak een nieuwe product
    DELETE  /api/product/delete/{id}    Delete een product (zet op non-actief)
    PUT     /api/product/update         Update een product
    GET     /api/product/get/{id}       Haalt 1 product op voor product aanpassen
 
# Inloggegevens  

Username: Student@test.nl wachtwoord: Testen123  
Username: Docent@test.nl wachtwoord: TestDocent1
