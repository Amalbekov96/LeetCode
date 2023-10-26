pipeline {
    agent any

    tools {
        maven 'Maven3'
        git 'Default'
        sonarqube 'SonarQubeScanner5.0.1'
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
            }
        }

        stage('Test') {
            steps {
                echo 'Testing'
            }
        }

        stage('Run SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQubeScanner5.0.1') {
                    sh 'mvm sonar:sonar'
                }
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                // Deploy the built application to a local Tomcat server.
                echo 'Deploying'
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
