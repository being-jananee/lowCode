// eslint-disable-next-line
import parseDocumentationContent from "./parseDocumentationContent";

const expectedResult = `<h1><ais-highlight-0000000000>Security</ais-highlight-0000000000> <a class="documentation-cta" href="https://docs.bizBrainz.com/security" target="_blank">Open Documentation</a></h1><h2>Does Bizbrainz store my data? <a href="https://docs.bizBrainz.com/core-concepts/connecting-to-data-sources/connecting-to-databases" target="_blank">Query your database directly</a></h2>
<p>No, Bizbrainz does not store any data returned from your API endpoints or DB queries. Bizbrainz only acts as a proxy layer. When you query your database/API endpoint, the Bizbrainz server only appends sensitive credentials before forwarding the request to your backend. The Bizbrainz server doesn't expose sensitive credentials to the browser because that can lead to <ais-highlight-0000000000>security</ais-highlight-0000000000> breaches. Such a routing ensures <ais-highlight-0000000000>security</ais-highlight-0000000000> of your systems and data.</p>
<h2><ais-highlight-0000000000>Security</ais-highlight-0000000000> measures within Bizbrainz</h2>
<p>Bizbrainz applications are secure-by-default. The <ais-highlight-0000000000>security</ais-highlight-0000000000> measures implemented for Bizbrainz installations are:</p>
<ul>
<li><a href="https://app.bizBrainz.com/applications/61e11a42eb0501052b9fab3e/pages/623ce9d84d9aea1b062b365f" target="_blank">SampleApp</a></li>
<li>On Bizbrainz Cloud, all connections are encrypted with TLS. For self-hosted instances, we offer the capability to setup SSL certificates via LetsEncrypt during the installation process.</li>
<li>Encrypt all sensitive credentials such as database credentials with AES-256 encryption. Each self-hosted Bizbrainz instance is configured with unique salt and password values ensuring data-at-rest <ais-highlight-0000000000>security</ais-highlight-0000000000>.</li>
<li>Bizbrainz Cloud will only connect to your databases/API endpoints through whitelisted IPs: 18.223.74.85 &amp; 3.131.104.27. This ensures that you only have to expose database access to specific IPs when using our cloud offering.</li>
<li>Bizbrainz Cloud is hosted in AWS data centers on servers that are SOC 1 and SOC 2 compliant. We also maintain data redundancy on our cloud instances via regular backups.</li>
<li>Internal access to Bizbrainz Cloud is controlled through 2-factor authentication system along with audit logs.</li>
<li>Maintain an open channel of communication with <ais-highlight-0000000000>security</ais-highlight-0000000000> researchers to allow them to report <ais-highlight-0000000000>security</ais-highlight-0000000000> vulnerabilities responsibly. If you notice a <ais-highlight-0000000000>security</ais-highlight-0000000000> vulnerability, please email <a href="mailto:security@bizBrainz.com" target="_blank"><ais-highlight-0000000000>security</ais-highlight-0000000000>@bizBrainz.com</a> and we'll resolve them ASAP.</li>
</ul>`;

const sampleTitleResponse = `<ais-highlight-0000000000>Security</ais-highlight-0000000000>`;

const sampleDocumentResponse = `# Does Bizbrainz store my data? [Query your database directly](core-concepts/connecting-to-data-sources/connecting-to-databases)

No, Bizbrainz does not store any data returned from your API endpoints or DB queries. Bizbrainz only acts as a proxy layer. When you query your database/API endpoint, the Bizbrainz server only appends sensitive credentials before forwarding the request to your backend. The Bizbrainz server doesn't expose sensitive credentials to the browser because that can lead to <ais-highlight-0000000000>security</ais-highlight-0000000000> breaches. Such a routing ensures <ais-highlight-0000000000>security</ais-highlight-0000000000> of your systems and data.

# <ais-highlight-0000000000>Security</ais-highlight-0000000000> measures within Bizbrainz

Bizbrainz applications are secure-by-default. The <ais-highlight-0000000000>security</ais-highlight-0000000000> measures implemented for Bizbrainz installations are:

* [SampleApp](https://app.bizBrainz.com/applications/61e11a42eb0501052b9fab3e/pages/623ce9d84d9aea1b062b365f)
* On Bizbrainz Cloud, all connections are encrypted with TLS. For self-hosted instances, we offer the capability to setup SSL certificates via LetsEncrypt during the installation process.
* Encrypt all sensitive credentials such as database credentials with AES-256 encryption. Each self-hosted Bizbrainz instance is configured with unique salt and password values ensuring data-at-rest <ais-highlight-0000000000>security</ais-highlight-0000000000>.
* Bizbrainz Cloud will only connect to your databases/API endpoints through whitelisted IPs: 18.223.74.85 & 3.131.104.27. This ensures that you only have to expose database access to specific IPs when using our cloud offering.
* Bizbrainz Cloud is hosted in AWS data centers on servers that are SOC 1 and SOC 2 compliant. We also maintain data redundancy on our cloud instances via regular backups.
* Internal access to Bizbrainz Cloud is controlled through 2-factor authentication system along with audit logs.
* Maintain an open channel of communication with <ais-highlight-0000000000>security</ais-highlight-0000000000> researchers to allow them to report <ais-highlight-0000000000>security</ais-highlight-0000000000> vulnerabilities responsibly. If you notice a <ais-highlight-0000000000>security</ais-highlight-0000000000> vulnerability, please email [<ais-highlight-0000000000>security</ais-highlight-0000000000>@bizBrainz.com](mailto:<ais-highlight-0000000000>security</ais-highlight-0000000000>@bizBrainz.com) and we'll resolve them ASAP.`;

describe("parseDocumentationContent", () => {
  it("works as expected", () => {
    const sampleItem = {
      rawTitle: sampleTitleResponse,
      rawDocument: sampleDocumentResponse,
      path: "security",
      query: "Security",
    };
    const result = parseDocumentationContent(sampleItem);
    expect(result).toStrictEqual(expectedResult);
  });
});
