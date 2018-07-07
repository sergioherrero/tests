pipeline {
  agent any
  stages {
    stage('step 1') {
      steps {
        echo 'hello world'
      }
      post {
        always {
          echo 'post hello world'
        }
      }
    }
    stage('step 2') {
      steps {
        echo 'hello world 2'
      }
    }
  }
}
