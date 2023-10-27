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
                sh 'mvn clean install' // Assuming a Maven-based Java project.

            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'  // Use appropriate Maven goals or build commands
                jacoco(execPattern: 'target/jacoco.exec')
                echo 'Testing'
            }
        }

        stage("Quality Gate") {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    script {
                        def qualityGateStatus = waitForQualityGate()

                        if (qualityGateStatus != 'OK') {
                            error "Quality Gate failed: ${qualityGateStatus}"
                        } else {
                            echo 'Quality Gate passed'
                        }
                    }
                }
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
            when {
                expression { currentBuild.resultIsBetterOrEqualTo('SUCCESS') }
            }
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
