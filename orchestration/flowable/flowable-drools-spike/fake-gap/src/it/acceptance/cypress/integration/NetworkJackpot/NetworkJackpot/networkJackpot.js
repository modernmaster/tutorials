import { Given } from "cypress-cucumber-preprocessor/steps";
import { When } from "cypress-cucumber-preprocessor/steps";

const playerUrl = "http://localhost:8080/player/";
const tempPlayerUrl = "http://localhost:8080/api/players/";
const gameUrl = "http://localhost:8080/game/";

let playerId = 397841694696;

Given(`I play a player with a deposit of 1000`, () => {

    const deposit = 1000;

    const options = {
        method: 'POST',
        url: playerUrl,
        headers: {
            'Content-Type': 'application/json'
        },
        body: {
            "deposit":deposit
        }};

    cy.request(options).then((resp) => {
        expect(resp.body.id).to.be.not.null;
        expect(resp.body.walletAmount).to.equal(deposit);
        playerId = resp.body.id;
    });

});

When(`I play a game`, () => {

    const options = {
        method: 'POST',
        url: gameUrl+playerId,
        headers: {
            'Content-Type': 'application/json'
        }};

    cy.request(options).then((resp) => {
        expect(resp.body.id).to.be.not.null;
        expect(resp.isOkStatusCode).to.equal(true);
    });
});

Then(`I see WalletTransactions being passed to Network Jackpot`, () => {

    const options = {
        method: 'GET',
        url: tempPlayerUrl+playerId,
        headers: {
            'Content-Type': 'application/json'
        }};

    cy.request(options).then((resp) => {
        expect(resp.isOkStatusCode).to.equal(true);
        expect(resp.body.id).to.be.not.null;
        expect(resp.body.walletTransactionList).to.be.not.null;
        let transactionTypes = Cypress._.map(resp.body.walletTransactionList, (o) => Cypress._.pick(o, 'transaction_type'));
        expect(transactionTypes).to.deep.include({transaction_type: 'networkJackpotDebit'});
    });
});

Then(`contain a player id that is listed from this instance`, () => {
    const options = {
        method: 'GET',
        url: tempPlayerUrl+playerId,
        headers: {
            'Content-Type': 'application/json'
        }};

    cy.request(options).then((resp) => {
        expect(resp.isOkStatusCode).to.equal(true);
        expect(resp.body.id).to.be.not.null;
        expect(resp.body.walletTransactionList).to.be.not.null;
        let transactionTypes = Cypress._.map(resp.body.walletTransactionList, (o) => Cypress._.pick(o, 'transaction_type'));
        expect(transactionTypes).to.deep.include({transaction_type: 'networkJackpotDebit'});
    });
});

function req (counter) {
    const options = {
        method: 'GET',
        url: tempPlayerUrl+playerId,
        headers: {
            'Content-Type': 'application/json'
        }};
    cy
        .request(options)
        .then((resp) => {
            if (resp.status === 200) {
                let walletTransaction = resp.body.walletTransactionList.find(transaction=>transaction.transaction_type==='networkJackpotCredit')
                if(walletTransaction) {
                    return;
                }
            }
            cy.wait(2000);
            req(counter++)
        })
}


