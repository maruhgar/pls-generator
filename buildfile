# Generated by Buildr 1.4.4, change to your liking
# Standard maven2 repository
require 'buildr/jetty'
require 'readline'
repositories.remote << 'https://repo.maven.apache.org/maven2/'

desc 'Playlist Generator'

LOGBACK = group('logback-classic', 
            'logback-core',
            :under=>'ch.qos.logback', 
            :version=>'1.2.3')

JAVAX = struct(
    :el         => 'javax.el:javax.el-api:jar:3.0.0', 
    :inject     => 'javax.inject:javax.inject:jar:1', 
    :jstl       => 'javax.servlet:jstl:jar:1.1.2', 
    :servletapi => 'javax.servlet:javax.servlet-api:jar:3.1.0',
    :validation => 'javax.validation:validation-api:jar:1.1.0.Final'
)

COMMONS = struct(
    :collections   => 'commons-collections:commons-collections:jar:3.2.1',
    :configuration => 'commons-configuration:commons-configuration:jar:1.10',
    :lang          => 'commons-lang:commons-lang:jar:2.5',
    :logging       => 'commons-logging:commons-logging:jar:1.1.1'
)

SLF4JRUNTIME = group('jul-to-slf4j',
                    'jcl-over-slf4j',
                    'log4j-over-slf4j',
                    :under=>'org.slf4j',
                    :version=>'1.7.25')

SPRING = group('spring-beans', 
            'spring-context', 
            'spring-core', 
            'spring-tx',
            'spring-web', 
            'spring-webmvc',
            :under=>'org.springframework', 
            :version=>'5.1.3.RELEASE')

SPRINGRUNTIME = group('spring-aop',
            'spring-expression',
            :under=>'org.springframework', 
            :version=>'5.1.3.RELEASE')

SPRINGSECURITYRUNTIME = group('spring-security-config', 
                    'spring-security-web',
                    :under=>'org.springframework.security',
                    :version=>'5.1.2.RELEASE')

RUNTIME = [LOGBACK, 
    SLF4JRUNTIME, 
    SPRINGRUNTIME, 
    SPRINGSECURITYRUNTIME, 
    COMMONS.collections]

define 'pls' do

    project.group = 'net.sourceforge.jukebox'

    project.version = '1.3-SNAPSHOT'

    compile.with SPRING,
        COMMONS.configuration,
        COMMONS.lang,
        JAVAX.inject,
        JAVAX.jstl,
        JAVAX.validation,
        JAVAX.servletapi,
        JAVAX.el,
        transitive('org.hibernate.validator:hibernate-validator:jar:6.0.14.Final'), 
        'org.glassfish:javax.el:jar:3.0.0',
        'org.slf4j:slf4j-api:jar:1.7.25', 
        'org.springframework.security:spring-security-core:jar:5.1.2.RELEASE',
        'taglibs:standard:jar:1.1.2' 

    test.using :testng

    test.with COMMONS.logging,
        COMMONS.collections,
        SPRINGRUNTIME,
        SPRINGSECURITYRUNTIME,
        LOGBACK,
        transitive('org.hibernate.validator:hibernate-validator:jar:6.0.14.Final'), 
        'org.testng:testng:jar:6.14.3', 
        transitive('org.mockito:mockito-core:jar:2.23.4', 
            'org.springframework:spring-test:jar:5.1.3.RELEASE')
   
    package :war, 
        :id => 'pls'
    package(:war).libs += artifacts(RUNTIME)
    package(:war).libs -= artifacts(JAVAX.servletapi)

    Java.classpath.concat([
        "jetty:jsp-api:jar:2.1-6.0.2"
    ])

    task("jetty"=>[package(:war), jetty.use]) do |task|
        jetty.deploy("http://localhost:8080", task.prerequisites.first)
	Readline::readline('[Type ENTER to stop Jetty]')
    end

end
