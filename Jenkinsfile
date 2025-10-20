pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-id') // Add in Jenkins
        DOCKERHUB_USERNAME = elmahdidevops
        DOCKERHUB_PASSWORD = elmahdi@2003
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://githubom/your-username/MicroServices_Project.git'
            }
        }

        stage('Build & Push Docker Images') {
            steps {
                script {
                    def services = ['customer','order','product','payment','gateway','discovery']
                    for (service in services) {
                        docker.build("${DOCKERHUB_USERNAME}/${service}:latest", "./Services/${service}").push()
                    }
                }
            }
        }
    }

    post {
        success {
            echo "All images built and pushed successfully!"
        }
        failure {
            echo "Something went wrong."
        }
    }
}
