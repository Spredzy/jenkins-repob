pipeline {
    agent none

    stages {
        stage("test") {
          sh "env"
        }
    }
}
