package com.example.junitsamples.study.common;

public interface ApiEndPoint {

    class ApiVersion {
        private static final String V1 = "/api/v1";
        private static final String V2 = "/api/v2";
    }

    class ApiEnvRoot {
        public static final String LOCAL = "http://localhost:";
        public static final String DEV = "";
        public static final String STAGING = "";
        public static final String PRODUCTION = "";
    }

    class StudyHomeController {
        public static final String HELLO = ApiVersion.V1 + "/hello";
        public static final String HELLO_DTO = HELLO + "/dto";
    }

    class PostApiController {
        public static final String BASIC = ApiVersion.V1 + "/posts";
        public static final String SAVE = BASIC;
        public static final String UPDATE = BASIC + "/{id}";
        public static final String FIND_BY_ID = BASIC + "/{id}";
        public static final String DELETE = BASIC + "/{id}";
    }

    class IndexController {
        public static final String BASIC = "/posts";
        public static final String POSTS_SAVE = BASIC + "/save";
        public static final String POSTS_UPDATE = BASIC + "/update" + "/{id}";
    }
}
