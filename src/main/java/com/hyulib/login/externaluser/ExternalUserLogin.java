package com.hyulib.login.externaluser;

import KISA.SHA256;
import org.apache.commons.codec.binary.Base64;

public class ExternalUserLogin {
    public String passwordEncode(String password){

        SHA256 sha256 = new SHA256(password.getBytes());
        return new String(Base64.encodeBase64(sha256.GetHashCode()));

    }
}
