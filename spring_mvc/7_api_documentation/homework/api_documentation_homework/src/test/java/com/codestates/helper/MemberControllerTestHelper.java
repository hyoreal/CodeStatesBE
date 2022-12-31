package com.codestates.helper;

import java.net.URI;

public interface MemberControllerTestHelper extends ControllerTestHelper {
    String MEMBER_URL = "/v11/members";
    default URI getURI() {
        return createURI(MEMBER_URL);
    }

    default URI getURI(long memberId) {
        return createURI(MEMBER_URL + "/{member-id}", memberId);
    }

    default String getUrl() {
        return MEMBER_URL;
    }

    default String getUrl(long memberId) {
        return MEMBER_URL + "/{member-id}";
    }
}
