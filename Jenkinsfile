pipeline {

    agent { label 'unit-test-runner' }

    stages {
        stage('Scenario') {
            steps {
                script {
                    if (env.CHANGE_ID) {
                      echo 'There is a CHANGE_ID'
                    } else {
                      echo 'There is NO CHANGE_ID'
                    }
                    sh 'env'
                    echo 'hello world'
                    /* jobs = load('build-scenarios.groovy') */
                    /* parallel jobs */
                }
            }
        }
    }

}
