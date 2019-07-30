pipeline {

    agent none

    stages {
        stage('Scenario') {
            steps {
                jobs = load('build-scenarios.groovy')
                parallel jobs
            }
        }
    }

}
