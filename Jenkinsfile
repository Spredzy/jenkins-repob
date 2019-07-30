pipeline {

    agent none

    stages {
        stage('Scenario') {
            steps {
                script {
                    jobs = load('build-scenarios.groovy')
                    parallel jobs
                }
            }
        }
    }

}
