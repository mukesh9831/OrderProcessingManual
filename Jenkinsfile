pipeline {
    agent any

    environment {
        // Adjust Mongo URI if needed
        MONGO_URI = "mongodb://localhost:27017"
    }

    triggers {
        // This triggers Jenkins build when GitHub webhook fires (on push to master)
        githubPush()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building the Maven project...'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests...'
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying to local/private network...'
                // Example: run your Jetty server
                sh 'mvn jetty:run &'
            }
        }
    }

    post {
        success {
            echo '✅ Build and deploy successful!'
        }
        failure {
            echo '❌ Build failed!'
        }
    }
}
