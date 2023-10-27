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
                jacoco(execPattern: 'target/jacoco.exec')
                echo 'Testing'
            }
        }

        stage("Quality Gate") {
            steps {
                timeout(time: 30, unit: 'MINUTES') {
                    script {
                        // Ensure the 'withSonarQubeEnv' wrapper is used for SonarQube analysis
                        withSonarQubeEnv('sonarqube-10.2.1') {
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
                // Add your deployment steps to Tomcat here
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
