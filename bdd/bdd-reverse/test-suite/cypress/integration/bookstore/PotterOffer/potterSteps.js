import { Given, Then, Before } from "cypress-cucumber-preprocessor/steps";

const url = "https://bookstore.io/";

Given(`a customer`, () => {
   cy.visit(url);
});

When(`I buy any book`, () => {
   cy.get('#book1').check();
   cy.get('#calculate').click();
});

When(`I buy two books`, () => {
   cy.get('#book1').check();
   cy.get('#book2').check();
   cy.get('#calculate').click();
});

When(`I buy two books`, () => {
   cy.get('#book1').check();
   cy.get('#book2').check();
   cy.get('#calculate').click();
});

When(`I buy three books`, () => {
   cy.get('#book1').check();
   cy.get('#book2').check();
   cy.get('#book3').check();
   cy.get('#calculate').click();
});

When(`I buy four books`, () => {
   cy.get('#book1').check();
   cy.get('#book2').check();
   cy.get('#book3').check();
   cy.get('#book4').check();
   cy.get('#calculate').click();
});

When(`I buy five books`, () => {
   cy.get('#book1').check();
   cy.get('#book2').check();
   cy.get('#book3').check();
   cy.get('#book4').check();
   cy.get('#book5').check();
   cy.get('#calculate').click();
});


Then(`It will cost me 8 EUR`, () => {
   cy.get("#cost").should('have.text', 'Total Cost: 8 EUR');
});

Then(`It will cost me 15.20 EUR`, () => {
   cy.get("#cost").should('have.text', 'Total Cost: 15.2 EUR');
});

Then(`It will cost me 21.60 EUR`, () => {
   cy.get("#cost").should('have.text', 'Total Cost: 21.6 EUR');
});

Then(`It will cost me 25.60 EUR`, () => {
   cy.get("#cost").should('have.text', 'Total Cost: 25.6 EUR');
});

Then(`It will cost me 32 EUR`, () => {
   cy.get("#cost").should('have.text', 'Total Cost: 32 EUR');
});