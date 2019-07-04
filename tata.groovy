pipeline {
    agent none

    stages {
        stage("test") {
          steps {
            sh "env"
          }
        }
    }
}
