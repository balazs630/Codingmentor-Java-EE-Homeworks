curl --verbose --write-out "\n" --cookie cookie.txt --cookie-jar cookie.txt --data @newMobile.json --header "Content-Type: application/json" --request DELETE 'http://localhost:8080/2-homework-webshop-web/app/shoppingcart/remove'
