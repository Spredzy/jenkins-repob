def i18n_files = false

pipeline {

    agent { label 'buildvm' }

    stages {
        stage('Does it contain pofile') {
            steps {
                script {
                    i18n_files = sh(
                        script: 'git diff-tree --no-commit-id --name-only -r HEAD | grep -E "*.po"',
                        returnStatus: true
                    ) == 0

                    echo "Let see: ${i18n_files}"
                }
            }
        }

        stage('Actually do something') {

            when {
              expression {
                return i18n_files
              }
            }

            steps {
              echo "Doing Something"
            }
        }
    }

}
