pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'docker build -t app:test .'
      }
    }
    stage('Test') {
      steps {
        echo 'Test'
        sh 'docker run -d --rm -p 80:80 --name app app:test'
        sh 'nc -vz localhost 80'
      }
      post {
        always {
          sh 'docker stop app'
        }
      }
    }
    stage ('Push Registry') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'docker-key', passwordVariable: 'password', usernameVariable: 'user')]) {
          sh 'docker tag app:test sergioherrero/app:stable'
          sh 'docker push sergioherrero/app:stable'
        }
      }
    }
  }
}
