const publishPage = require("../../../locators/publishWidgetspage.json");
const testdata = require("../../../fixtures/testdata.json");

const testUrl = "http://localhost:5001/v1/parent/cmd";
describe("Upgrade bizBrainz version", () => {
  it.skip("Upgrade Bizbrainz version and verify the Applications", () => {
    const uuid = () => Cypress._.random(0, 10000);
    const name = uuid();
    cy.wait(2000);

    cy.GetCWD(testUrl);

    cy.log("Stop the container");
    cy.StopTheContainer(testUrl, "bizBrainz"); // stop the old container
    cy.wait(2000);

    cy.GetCWD(testUrl);

    cy.log("Get path");
    cy.GetPath(testUrl, "bizBrainz").then((path) => {
      cy.log(path);
      path = path.split("  ");
      path = path[1].split(" ");
      path = path[0].slice(0, -7);
      cy.log(path);

      localStorage.setItem("ContainerName", `bizBrainz-160_${name}_updated`);

      cy.log("Start old stack container");
      cy.CreateAContainer(
        testUrl,
        path + "/oldstack/160",
        "latest",
        `bizBrainz-160_${name}_updated`,
      );
      cy.wait(45000);

      cy.log("Verify Logs");
      cy.GetAndVerifyLogs(testUrl, `bizBrainz-160_${name}_updated`); // Get and verify the logs
    });

    //verify the Applications after upgrade
    cy.LoginFromAPI(testdata.UPGRADEUSERNAME, testdata.UPGRADEPASSWORD);
    cy.visit(testdata.APPURL);

    cy.get(".t--buttongroup-widget").should("exist");
    cy.get(".t--buttongroup-widget")
      .children()
      .should("have.length", 3);

    cy.get(publishPage.backToEditor).click({ force: true });

    cy.get(".t--buttongroup-widget").should("exist");
    cy.get(".t--buttongroup-widget")
      .children()
      .should("have.length", 3);

    cy.log("Stop the container");
    cy.StopTheContainer(testUrl, localStorage.getItem("ContainerName")); // stop the old container
    cy.wait(2000);
  });

  it.skip("Upgrade Bizbrainz from CE to EE and verify the Applications", () => {
    cy.log("Stop the bizBrainz container");
    cy.StopTheContainer(testUrl, "bizBrainz"); // stop the old container
    cy.wait(2000);

    const uuid = () => Cypress._.random(0, 10000);
    const name = uuid();
    cy.wait(2000);

    cy.log("Get path");
    cy.GetPath(testUrl, "bizBrainz").then((path) => {
      path = path.split("  ");
      path = path[1].split(" ");
      path = path[0].slice(0, -7);
      cy.log(path);

      localStorage.setItem(
        "ContainerName",
        `bizBrainz-160-ce-${name}-enterprise`,
      );

      cy.log("Start old stack container");
      cy.CreateEEContainer(
        testUrl,
        path + "/oldstack/ce",
        "latest",
        `bizBrainz-160-ce-${name}-enterprise`,
      );
      cy.wait(45000);

      cy.log("Verify Logs");
      cy.GetAndVerifyLogs(testUrl, `bizBrainz-160-ce-${name}-enterprise`); // Get and verify the logs
    });

    //verify the Applications after upgrade
    cy.LoginFromAPI(testdata.UPGRADEUSERNAME, testdata.UPGRADEPASSWORD);
    cy.visit(testdata.APPURL);

    cy.get(".t--buttongroup-widget").should("exist");
    cy.get(".t--buttongroup-widget")
      .children()
      .should("have.length", 3);

    cy.get(publishPage.backToEditor).click({ force: true });

    cy.get(".t--buttongroup-widget").should("exist");
    cy.get(".t--buttongroup-widget")
      .children()
      .should("have.length", 3);
  });
});
