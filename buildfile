# Generated by Buildr 1.4.4, change to your liking
# Standard maven2 repository
repositories.remote << 'http://10.99.30.70/artifactory/repo'

desc 'Playlist Generator'

LOGBACK = group('logback-classic', 
            'logback-core',
            :under=>'ch.qos.logback', 
            :version=>'0.9.26')

JAVAX = struct(
    :jstl       => 'javax.servlet:jstl:jar:1.1.2', 
    :servletapi => 'javax.servlet:servlet-api:jar:2.5',
    :validation => 'javax.validation:validation-api:jar:1.0.0.GA'
)

COMMONS = struct(
    :collections   => 'commons-collections:commons-collections:jar:3.2.1',
    :configuration => 'commons-configuration:commons-configuration:jar:1.6',
    :lang          => 'commons-lang:commons-lang:jar:2.5',
    :logging       => 'commons-logging:commons-logging:jar:1.1.1'
)

SLF4JRUNTIME = group('jul-to-slf4j',
                    'jcl-over-slf4j',
                    'log4j-over-slf4j',
                    :under=>'org.slf4j',
                    :version=>'1.6.1')

SPRING = group('spring-beans', 
            'spring-context', 
            'spring-core', 
            'spring-web', 
            'spring-webmvc',
            :under=>'org.springframework', 
            :version=>'3.0.5.RELEASE')

SPRINGRUNTIME = group('spring-asm', 
            'spring-aop',
            'spring-expression',
            'spring-tx',
            :under=>'org.springframework', 
            :version=>'3.0.5.RELEASE')

SPRINGSECURITYRUNTIME = group('spring-security-config', 
                    'spring-security-web',
                    :under=>'org.springframework.security',
                    :version=>'3.0.5.RELEASE')

RUNTIME = [LOGBACK, 
    SLF4JRUNTIME, 
    SPRINGRUNTIME, 
    SPRINGSECURITYRUNTIME, 
    COMMONS.collections]

define 'pls' do

    project.group = 'net.sourceforge.jukebox'

    project.version = '1.0-SNAPSHOT'

    compile.with SPRING,
        COMMONS.configuration,
        COMMONS.lang,
        JAVAX.jstl,
        JAVAX.validation,
        JAVAX.servletapi,
        'org.hibernate:hibernate-validator:jar:4.1.0.Final', 
        'org.slf4j:slf4j-api:jar:1.6.1', 
        'org.springframework.security:spring-security-core:jar:3.0.5.RELEASE',
        'taglibs:standard:jar:1.1.2' 

    test.using :testng

    test.with 'org.springframework:spring-asm:jar:3.0.5.RELEASE', 
        'org.springframework:spring-expression:jar:3.0.5.RELEASE', 
        COMMONS.logging,
        COMMONS.collections,
        LOGBACK,
        transitive('org.mockito:mockito-core:jar:1.8.5', 
            'org.springframework:spring-test:jar:3.0.5.RELEASE')
   
    package :war, 
        :id => 'pls'
    package(:war).libs += artifacts(RUNTIME)
    package(:war).libs -= artifacts(JAVAX.servletapi)

end