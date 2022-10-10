import { isEmail } from "./formhelpers";

describe("isEmail test", () => {
  it("Check whether the valid emails are recognized as valid", () => {
    const validEmails = [
      "bizBrainz@yahoo.com",
      "bizBrainz-100@yahoo.com",
      "bizBrainz.100@yahoo.com",
      "bizBrainz111@bizBrainz.com",
      "bizBrainz-100@bizBrainz.net",
      "bizBrainz.100@bizBrainz.com.au",
      "bizBrainz@1.com",
      "bizBrainz@gmail.com.com",
      "bizBrainz+100@gmail.com",
      "bizBrainz-100@yahoo-test.com",
    ];

    validEmails.forEach((validEmail) => {
      expect(isEmail(validEmail)).toBeTruthy();
    });
  });

  it("Check whether the invalid emails are recognized as invalid", () => {
    const invalidEmails = [
      "bizBrainz",
      "bizBrainz@.com.my",
      "bizBrainz123@gmail.a",
      "bizBrainz123@.com",
      "bizBrainz123@.com.com",
      ".bizBrainz@bizBrainz.com",
      "bizBrainz()*@gmail.com",
      "bizBrainz@%*.com",
      "bizBrainz..2002@gmail.com",
      "bizBrainz.@gmail.com",
      "bizBrainz@bizBrainz@gmail.com",
      "bizBrainz@gmail.com.1a",
    ];

    invalidEmails.forEach((invalidEmail) => {
      expect(isEmail(invalidEmail)).toBeFalsy();
    });
  });
});
