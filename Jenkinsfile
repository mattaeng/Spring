def PROJECT_NAME = 'mattaeng-api-0.0.1-SNAPSHOT.jar'
pipeline {
    agent any
    tools {
        gradle 'Gradle-8.3'
    }
    stages {
        stage('Prepare') {
            steps {
                sh 'gradle wrapper clean'
            }
        }
        stage('Build') {
            steps {
                sh 'gradle wrapper build -x test'
            }
        }
        stage('Test') {
            steps {
                sh 'gradle wrapper test'
            }
        }
        stage('Deploy') {
            steps {
                echo "Deploy"
            }
        }
    }
}