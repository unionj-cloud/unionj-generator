<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
<#if hasParent>
    <parent>
        <groupId>${parentGroupId}</groupId>
        <artifactId>${parentArtifactId}</artifactId>
        <version>${parentVersion}</version>
    </parent>
</#if>

    <groupId>${groupId}</groupId>
    <artifactId>${artifactId}</artifactId>
    <version>${version}</version>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>${protoGroupId}</groupId>
            <artifactId>${protoArtifactId}</artifactId>
            <version>${protoVersion}</version>
        </dependency>
        <dependency>
            <groupId>${serviceGroupId}</groupId>
            <artifactId>${serviceArtifactId}</artifactId>
            <version>${serviceVersion}</version>
        </dependency>
    </dependencies>
</project>
