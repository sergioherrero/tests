pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'docker build -t app .'
      }
    }
    stage('Test') {
      steps {
        echo 'Test'
        sh 'docker run -d -p 80:80 app'
        sh '/bin/nc -vz localhost 80'
      }
      post {
        always {
          sh 'docker stop app'
        }
      }
    }
    stage ('Push Registry') {
      steps {
        sh 'docker tag app sergioherrero/app:stable'
        sh 'docker push sergioherrero/app:stable'
      }
    }
  }
}
