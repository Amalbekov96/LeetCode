pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        // Define the SonarQube server configuration
        SONARQUBE_HOME = tool name: 'SonarQubeScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
        SONARQUBE_SERVER_URL = 'http://localhost:9000' // Replace with your SonarQube server URL
        SONARQUBE_TOKEN = credentials('sqa_f9d0750bc8a3340b1717acaaca950ba9ef4ba755') // Use the SonarQube authentication token from Jenkins credentials
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
                script {
                    // Run the SonarQube analysis
                    sh "${SONARQUBE_HOME}/bin/sonar-scanner -Dsonar.host.url=${SONARQUBE_SERVER_URL} -Dsonar.login=${SONARQUBE_TOKEN}"
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
