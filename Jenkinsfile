pipeline {
    agent any

    tools {
        maven 'Maven3'
        git 'Default'
    }

    environment {
    PATH = "$PATH:/usr/local/Cellar/maven/3.9.5/libexec/bin"
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

        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    def qg = waitForQualityGate()
                    if (qg.status != 'OK') {
                      error "Pipeline aborted due to quality gate failure: ${qg.status}"
                    }
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
