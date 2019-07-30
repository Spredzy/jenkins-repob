pipeline {

    agent { label 'unit-test-runner' }

    stages {
        stage('prep') {
            steps {
                sh 'env'
                script {
                   if (env.BRANCH_NAME != null) {
                       echo 'Gating'
                   } else {
                       echo 'Nightly'
                   }
                }
            }
        }
    }

}
