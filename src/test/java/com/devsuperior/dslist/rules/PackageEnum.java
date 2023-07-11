package com.devsuperior.dslist.rules;

public enum PackageEnum {

    BASE_PACKAGE("com.devsuperior.dslist"),
    REPOSITORY_PACKAGE(".repositories"),
    ENTITY_PACKAGE(".entities"),
    ENTITY_PRIMARY_PACKAGE(".entities.primaries"),
    DTO_PACKAGE(".dto"),
    CONTROLLER_PACKAGE(".controllers"),
    SERVICE_PACKAGE(".services"),
    SERVICE_IMPL_PACKAGE(".services.impl"),
    PROJECTION_PACKAGE(".projections");

    private final String packagePath;

    PackageEnum(String packagePath) { this.packagePath = packagePath; }

    public String getPackagePath() {
        if (this == BASE_PACKAGE) {
            return packagePath;
        } else {
            return BASE_PACKAGE.getPackagePath() + this.packagePath;
        }
    }
}
