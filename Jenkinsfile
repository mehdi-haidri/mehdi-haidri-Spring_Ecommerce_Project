pipeline {
    agent any

    environment {
        DOCKERHUB = credentials('dockerhub-id')
        NAMESPACE = "microservices"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/mehdi-haidri/mehdi-haidri-Spring_Ecommerce_Project.git'
                
                script {
                    env.GIT_BRANCH = sh(script: "git rev-parse --abbrev-ref HEAD", returnStdout: true).trim()
                }
            }
        }

        stage('Init Build Info') {
            steps {
                script {
                    wrap([$class: 'BuildUser']) {
                        env.TRIGGERED_BY = env.BUILD_USER
                        echo "Build triggered by ${env.TRIGGERED_BY}"
                    }
                }
            }
        }

        stage('Notify Start') {
            steps {
                emailext (
                    subject: "🚀 Pipeline Started: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                    body: "<h3>Pipeline Started</h3><p>Build <b>#${env.BUILD_NUMBER}</b> has started.</p><p><b>Branch:</b> ${env.GIT_BRANCH}</p><p><b>Triggered by:</b> ${env.TRIGGERED_BY ?: 'Unknown (automated)'}</p><p><a href='${env.BUILD_URL}'>View Build Details</a></p>",
                    to: 'mehdihaidri3@gmail.com',
                    mimeType: 'text/html'
                )
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    def services = ['config-server','customer','order','product','payment','gateway','discovery','notification']

                    for (service in services) {
                        echo "Building image for ${service}..."
                        sh "docker build -t ${DOCKERHUB_USR}/${service}:latest ./Services/${service}"
                    }
                }
            }
        }

        stage('Scan Docker Images') {
            steps {
                script {
                    def services = ['config-server','customer','order','product','payment','gateway','discovery','notification']

                    for (service in services) {
                        echo "Scanning image for ${service} with Trivy..."
                        sh "trivy image  --severity CRITICAL ${DOCKERHUB_USR}/${service}:latest || exit 1"
                    }
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    sh "echo ${DOCKERHUB_PSW} | docker login -u ${DOCKERHUB_USR} --password-stdin"

                    def services = ['config-server','customer','order','product','payment','gateway','discovery','notification']

                    for (service in services) {
                        echo "Pushing image for ${service}..."
                        sh "docker push ${DOCKERHUB_USR}/${service}:latest"
                    }

                    sh 'docker logout'
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    sh "export KUBECONFIG=/var/jenkins_home/.kube/config"
                    echo "Deploying Config Server..."
                    sh "kubectl apply -f k8s-services/config-server-deployment.yaml --namespace=${NAMESPACE}"
                    sh "kubectl rollout status deployment/config-server --namespace=${NAMESPACE}"

                    echo "Deploying Discovery Service..."
                    sh "kubectl apply -f k8s-services/discovery-deployment.yaml --namespace=${NAMESPACE}"
                    sh "kubectl rollout status deployment/discovery --namespace=${NAMESPACE}"

                    def microservices = ['gateway', 'customer','product','notification','payment','order']
                    for (service in microservices) {
                        echo "Deploying ${service}..."
                        sh "kubectl apply -f k8s-services/${service}-deployment.yaml --namespace=${NAMESPACE}"
                        sh "kubectl rollout status deployment/${service} --namespace=${NAMESPACE}"
                    }
                }
            }
        }
    }

    post {
        success {
            emailext (
                subject: "✅ SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "<h3>✅ Build Succeeded</h3><p>Job: ${env.JOB_NAME}</p><p>Build Number: ${env.BUILD_NUMBER}</p><p>Branch: ${env.GIT_BRANCH}</p><p>Triggered by: ${env.TRIGGERED_BY ?: 'Unknown'}</p><p><a href='${env.BUILD_URL}'>View Build Details</a></p>",
                to: 'mehdihaidri3@gmail.com',
                mimeType: 'text/html'
            )
        }
        failure {
            emailext (
                subject: "❌ FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "<h3>❌ Build Failed</h3><p>Job: ${env.JOB_NAME}</p><p>Build Number: ${env.BUILD_NUMBER}</p><p>Branch: ${env.GIT_BRANCH}</p><p>Triggered by: ${env.TRIGGERED_BY ?: 'Unknown'}</p><p><a href='${env.BUILD_URL}'>View Logs</a></p>",
                to: 'mehdihaidri3@gmail.com',
                mimeType: 'text/html'
            )
        }
    }
}
