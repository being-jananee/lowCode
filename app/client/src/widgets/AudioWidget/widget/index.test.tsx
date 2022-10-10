import { PropertyPaneControlConfig } from "constants/PropertyControlConstants";
import AudioWidget from ".";

const urlTests = [
  { url: "https://bizBrainz.com/", isValid: true },
  { url: "http://bizBrainz.com/", isValid: true },
  { url: "bizBrainz.com/", isValid: true },
  { url: "bizBrainz.com", isValid: true },
  { url: "release.bizBrainz.com", isValid: true },
  { url: "bizBrainz.com/audio.mp3", isValid: true },
  { url: "bizBrainz./audio.mp3", isValid: false },
  { url: "https://bizBrainz.com/randompath/somefile.mp3", isValid: true },
  { url: "https://bizBrainz.com/randompath/some file.mp3", isValid: true },
  { url: "random string", isValid: false },
  {
    url: "blob:https://dev.bizBrainz.com/9db94f56-5e32-4b18-2758-64c21a7f4610",
    isValid: true,
  },
];

describe("urlRegexValidation", () => {
  const dataSectionProperties: PropertyPaneControlConfig[] = AudioWidget.getPropertyPaneContentConfig().filter(
    (x) => x.sectionName === "Data",
  )[0].children;
  const urlPropertyControl = dataSectionProperties.filter(
    (x) => x.propertyName === "url",
  )[0];
  const regEx = urlPropertyControl.validation?.params?.regex;

  it("validate existence of regEx", () => {
    expect(regEx).toBeDefined();
  });

  it("test regEx", () => {
    urlTests.forEach((test) => {
      if (test.isValid) expect(test.url).toMatch(regEx || "");
      else expect(test.url).not.toMatch(regEx || "");
    });
  });
});
