pipeline {

    agent { label 'jenkins-jnlp-agent' }

    stages {
        stage('Scenario') {
            steps {
                sh 'printenv'

                script {
                    if (env.CHANGE_FORK) {
                        GIT_FORK = env.CHANGE_FORK
                    } else {
                        GIT_FORK = 'ansible'
                    }
                }

                echo "$GIT_FORK"
            }
        }
    }

}
