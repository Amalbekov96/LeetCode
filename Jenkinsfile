pipeline {
    agent any

    tools {
        maven 'Maven3'
        git 'Default'
    }

    environment {
    PATH = "$PATH:/usr/local/Cellar/maven/3.9.5/libexec/bin"
//         // Define the SonarQube server configuration
//         SONARQUBE_HOME = tool name: 'SonarQubeScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
//         SONARQUBE_SERVER_URL = 'http://localhost:9000' // Replace with your SonarQube server URL
//         SONARQUBE_TOKEN = credentials('sqa_f9d0750bc8a3340b1717acaaca950ba9ef4ba755') // Use the SonarQube authentication token from Jenkins credentials
    }

    
    stages {
        stage('Build') {
            steps {
                // Build your application. Replace this with your actual build commands.
                sh 'mvn clean package' // Assuming a Maven-based Java project.
                sh 'mvn test jacoco:report'  // Use appropriate Maven goals or build commands
                jacoco(execPattern: 'target/jacoco.exec')
            }
        }

        stage('Test') {
            steps {
                echo 'Testing'
            }
        }

        stage('Run SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube-10.2.1') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                deploy adapters: [tomcat9(credentialsId: '91750011-363b-4a0f-85a5-740f2e550efd', path: '', url: 'http://localhost:9090/')], contextPath: '/LeetCode-1.0-SNAPSHOT', war: '*/*.war'
            }
        }
    }

    post {
        success {
            echo 'Pipeline successfully executed!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
} 
