package com.devsuperior.dslist;

import com.devsuperior.dslist.rules.PackageEnum;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchIgnore;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.GeneralCodingRules;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import org.hibernate.metamodel.model.domain.ManagedDomainType;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.devsuperior.dslist")
public class ArchUnitTests {

    //TODO: Componente x Pacote
    @ArchTest
    static ArchRule classes_repositories_in_repository =
            ArchRuleDefinition
                    .classes()
                    .that()
                    .haveSimpleNameEndingWith("Repository")
                    .should()
                    .resideInAnyPackage(PackageEnum.REPOSITORY_PACKAGE.getPackagePath());

    @ArchTest
    static ArchRule classes_interface_in_projection =
            ArchRuleDefinition
                    .classes()
                    .that()
                    .haveSimpleNameEndingWith("Projection")
                    .should()
                    .resideInAnyPackage(PackageEnum.PROJECTION_PACKAGE.getPackagePath());

    @ArchTest
    static ArchRule classes_dto_in_dto =
            ArchRuleDefinition
                    .classes()
                    .that()
                    .haveSimpleNameEndingWith("DTO")
                    .should()
                    .resideInAnyPackage(PackageEnum.DTO_PACKAGE.getPackagePath());

    @ArchTest
    static ArchRule classes_controller_in_controller =
            ArchRuleDefinition
                    .classes()
                    .that()
                    .haveSimpleNameEndingWith("Controller")
                    .should()
                    .resideInAnyPackage(PackageEnum.CONTROLLER_PACKAGE.getPackagePath());

    @ArchTest
    static ArchRule classes_service_in_services =
            ArchRuleDefinition
                    .classes()
                    .that()
                    .haveSimpleNameEndingWith("Service")
                    .should()
                    .resideInAnyPackage(PackageEnum.SERVICE_PACKAGE.getPackagePath());

    @ArchTest
    static ArchRule classes_service_impl_in_impl =
            ArchRuleDefinition
                    .classes()
                    .that()
                    .haveSimpleNameEndingWith("Impl")
                    .should()
                    .resideInAnyPackage(PackageEnum.SERVICE_IMPL_PACKAGE.getPackagePath());

    //TODO: Tipo
    @ArchTest
    static ArchRule classes_services_interfaces_in_service =
            ArchRuleDefinition
                    .classes()
                    .that()
                    .haveSimpleNameEndingWith("Service")
                    .should()
                    .beInterfaces();

    //TODO: Annotation
    @ArchTest
    static ArchRule classes_annotation_entity_in_entity =
            ArchRuleDefinition
                    .classes()
                    .that()
                    .resideInAnyPackage(PackageEnum.ENTITY_PACKAGE.getPackagePath())
                    .should()
                    .beAnnotatedWith(Entity.class);

    @ArchTest
    static ArchRule classes_annotation_embeddable_in_primaries =
            ArchRuleDefinition
                    .classes()
                    .that()
                    .resideInAnyPackage(PackageEnum.ENTITY_PRIMARY_PACKAGE.getPackagePath())
                    .should()
                    .beAnnotatedWith(Embeddable.class);

    @ArchTest
    static ArchRule classes_annotation_rest_controller_in_controller =
            ArchRuleDefinition
                    .classes()
                    .that()
                    .resideInAnyPackage(PackageEnum.CONTROLLER_PACKAGE.getPackagePath())
                    .should()
                    .beAnnotatedWith(RestController.class);

    @ArchTest
    static ArchRule classes_controller_in_controller_and_rest_controller =
            ArchRuleDefinition
                    .classes()
                    .that()
                    .resideInAnyPackage(PackageEnum.CONTROLLER_PACKAGE.getPackagePath())
                    .should()
                    .beAnnotatedWith(RestController.class);

    //TODO: Herança

    //TODO: Nome
    static ArchRule classes_service_name_service =
            ArchRuleDefinition
                    .classes()
                    .that()
                    .resideInAnyPackage(PackageEnum.SERVICE_PACKAGE.getPackagePath())
                    .should()
                    .haveSimpleNameEndingWith("Service");

    //TODO: Injeção de dependência
    @ArchTest
    static ArchRule no_have_injection_dependency_with_properties =
            GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

    //TODO: Customizada
    @ArchTest
    static final ArchRule layer_dependencies_are_respected = layeredArchitecture()
            .consideringAllDependencies()
            .layer("Controller").definedBy(PackageEnum.CONTROLLER_PACKAGE.getPackagePath())
            .layer("Service").definedBy(PackageEnum.SERVICE_PACKAGE.getPackagePath())
            .layer("ServiceImpl").definedBy(PackageEnum.SERVICE_IMPL_PACKAGE.getPackagePath())
            //.layer(REPOSITORY).definedBy("..repository..")
            .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
            .whereLayer("ServiceImpl").mayOnlyBeAccessedByLayers("Service")
            //.whereLayer(REPOSITORY).mayOnlyBeAccessedByLayers(SERVICE)
            ;

}
