pipeline {
    agent { label 'jenkins-jnlp-agent' }

    stages {
        stage("test") {
          steps {
            sh "env"
          }
        }
    }
}
