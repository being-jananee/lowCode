package com.bizBrainz.external.services.ce;

public interface EncryptionServiceCE {

    String encryptString(String plaintext);

    String decryptString(String encryptedText);
}
