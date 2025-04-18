pipeline {
    agent any

    environment {
        IMAGE_NAME = 'dharani/urlshortener'
        CONTAINER_PORT = '8067'
        HOST_PORT = '8090'
    }

    stages {
        stage('Clone Repository') {
            steps {
                echo "Cloning project repository..."
                git branch: 'main', url: 'https://github.com/Dharanisree2004/UrlShortener.git'
            }
        }

        stage('Build Project') {
            steps {
                echo "Building the Spring Boot project with Maven..."
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building Docker image..."
                sh 'docker build -t $IMAGE_NAME .'
            }
        }

        stage('Run Containers with Docker Compose') {
            steps {
                echo "Starting application using docker-compose..."
                sh 'docker-compose down'
                sh 'docker-compose up -d'
            }
        }

        stage('Display API Endpoints') {
            steps {
                script {
                    echo """
-------------------------------------------------------
🎉 Application Successfully Deployed!
✅ You can now test the following endpoints via Postman:

🔗 Shorten a URL      : http://localhost:$HOST_PORT/link/shorten   [POST]
📊 Get URL Stats      : http://localhost:$HOST_PORT/link/stats/{shortcode}   [GET]
📚 View All URLs      : http://localhost:$HOST_PORT/link/urls     [GET]
-------------------------------------------------------
                    """
                }
            }
        }
    }

    post {
        failure {
            echo "⚠️ Pipeline failed. Please check the logs for details."
        }
        success {
            echo "✅ Pipeline completed successfully!"
        }
    }
}
